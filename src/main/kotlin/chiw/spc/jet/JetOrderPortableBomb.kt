package chiw.spc.jet

import chiw.spc.types.CommodityPortable
import chiw.spc.types.DataMap
import chiw.spc.types.OrderPortable
import chiw.spc.utils.ClusterUtils
import chiw.spc.utils.MiscUtils
import com.hazelcast.jet.Jet
import com.hazelcast.jet.JetInstance
import com.hazelcast.jet.Traversers
import com.hazelcast.jet.datamodel.Tuple2
import com.hazelcast.jet.pipeline.Pipeline
import com.hazelcast.jet.pipeline.SinkBuilder
import com.hazelcast.jet.pipeline.Sinks
import com.hazelcast.jet.pipeline.Sources
import kotlin.system.measureNanoTime

class JetOrderPortableBomb {
    var jet: JetInstance = ClusterUtils.getJetClient()
    val doNothingSink = SinkBuilder.sinkBuilder(
        "do-nothing-sink"
    ) { it -> Unit }.receiveFn { unit, item: OrderPortable -> }.build()

    fun buildPipeline(bomb: Int): Pipeline {
        val p: Pipeline = Pipeline.create()
        p.readFrom(Sources.remoteMap<String, OrderPortable>(
            DataMap.PortableOrder.mapName,
            ClusterUtils.getCacheClientConfig()
        ))
            .flatMap { (key, order) ->
                val bomb = MutableList<Tuple2<CommodityPortable, OrderPortable>>(1000) {
                    Tuple2.tuple2(order.commodity, order)
                }
                Traversers.traverseIterable(
                    bomb
                )
            }
            .map { (commodity, order) ->
                order
            }
            .writeTo( Sinks.remoteMap(
                DataMap.PortableOrderSink.mapName,
                ClusterUtils.getCacheClientConfig(),
                {it.id}
            ) {
                it
            })
            .setLocalParallelism(8) // as default the sink parallelism is 1
        return p
    }

     fun go(bomb: Int) {
        try {
            val p: Pipeline = buildPipeline(bomb)
            val timeCost = measureNanoTime {
                jet.newJob(p).join()
            }
            println("Jet: finish in ${timeCost / 10e8} seconds.")
        } finally {
            Jet.shutdownAll()
        }
    }
}

fun main(args: Array<String>) {
    ClusterUtils.setupCache()
    ClusterUtils.setupJet()
    MiscUtils.fillPortableOrders(10_000)
    JetOrderPortableBomb().go(1000) // 10 mn times
}