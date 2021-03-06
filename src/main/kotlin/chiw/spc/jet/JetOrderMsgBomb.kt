package chiw.spc.jet

import chiw.spc.proto.CommodityMsg
import chiw.spc.proto.OrderMsg
import chiw.spc.types.CommodityPortable
import chiw.spc.types.DataMap
import chiw.spc.types.OrderPortable
import chiw.spc.utils.ClusterUtils
import chiw.spc.utils.MiscUtils
import com.hazelcast.jet.Jet
import com.hazelcast.jet.JetInstance
import com.hazelcast.jet.Traversers
import com.hazelcast.jet.config.JobConfig
import com.hazelcast.jet.datamodel.Tuple2
import com.hazelcast.jet.pipeline.*
import kotlin.system.measureNanoTime

class JetOrderMsgBomb {
    var jet: JetInstance = ClusterUtils.getJetClient()

    fun buildPipeline(bomb: Int): Pipeline {
        val p: Pipeline = Pipeline.create()
        p.readFrom(
            Sources.remoteMapJournal<String, OrderMsg>(
                DataMap.OrderMsg.mapName,
                ClusterUtils.getCacheClientConfig(),
                JournalInitialPosition.START_FROM_CURRENT
            )
        )
            .withIngestionTimestamps()
            .rebalance()
            .flatMap { (key, order) ->
                println("reblanced ${key}")
                val bomb = MutableList<Tuple2<CommodityMsg, OrderMsg>>(bomb) {
                    Tuple2.tuple2(order.commodity, order)
                }
                Traversers.traverseIterable(bomb)
            }
            .rebalance()
            .map { (commodity, order) -> order }
            .writeTo(Sinks.remoteMap(
                DataMap.OrderMsgSink.mapName,
                ClusterUtils.getCacheClientConfig(),
                { it.id }
            ) { it })
        return p
    }

    fun go(bomb: Int) {
        try {
            val p: Pipeline = buildPipeline(bomb)
            ClusterUtils.cancelJob(jet.getJob(JetOrderMsgBomb::class.simpleName!!))
            val timeCost = measureNanoTime {
                jet.newJob(p, JobConfig().setName(JetOrderMsgBomb::class.simpleName!!)).join()
            }
            println("Jet: finish in ${timeCost / 10e8} seconds.")
        } finally {
            Jet.shutdownAll()
        }
    }
}

fun main(args: Array<String>) {
    ClusterUtils.setupJet()
    JetOrderMsgBomb().go(1) // 10 mn times
}