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
import com.hazelcast.jet.config.JetConfig
import com.hazelcast.jet.config.JobConfig
import com.hazelcast.jet.datamodel.Tuple2
import com.hazelcast.jet.pipeline.*
import kotlinx.coroutines.*
import kotlin.system.measureNanoTime

class JetOrderMsgBombV2 {
    var jet: JetInstance = ClusterUtils.getJetClient()

    fun buildPipeline(bomb: Int): Pipeline {
        val p: Pipeline = Pipeline.create()
        val source = SourceBuilder.stream(DataMap.OrderMsg.mapName) { ctx ->
            CustomOrderMsgSource.OrderMsgListener(DataMap.OrderMsg)
        }.fillBufferFn(CustomOrderMsgSource.OrderMsgListener::fillBufferFn)
            .build()
        p.readFrom(source)
            .withIngestionTimestamps()
            .rebalance()
            .map { (key, order) ->
                println("${key}, ${order.id}") // always only print out in 1 jet node
                order
            }
            .writeTo(CustomOrderMsgSink.sink(DataMap.OrderMsgSink))
        return p
    }

    fun go(bomb: Int) {
        try {
            val p: Pipeline = buildPipeline(bomb)
            ClusterUtils.cancelJob(jet.getJob(JetOrderMsgBombV2::class.simpleName!!))
            val timeCost = measureNanoTime {
                jet.newJob(p, JobConfig().setName(JetOrderMsgBombV2::class.simpleName!!)).join()
            }
            val orderMsgSinkMap = ClusterUtils.getCacheMap<OrderMsg>(DataMap.OrderMsgSink)
            println(CustomOrderMsgSink.count)
            println(orderMsgSinkMap.size)
            println("Jet: finish in ${timeCost / 10e8} seconds.")
        } finally {
            Jet.shutdownAll()
        }
    }
}

fun main(args: Array<String>) {
//    ClusterUtils.setupJet()
    JetOrderMsgBombV2().go(1) // 10 mn times
}