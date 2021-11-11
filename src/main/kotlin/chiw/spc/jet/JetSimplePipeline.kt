package chiw.spc.jet

import chiw.spc.proto.OrderMsg
import chiw.spc.types.DataMap
import chiw.spc.utils.ClusterUtils
import com.hazelcast.jet.Jet
import com.hazelcast.jet.JetInstance
import com.hazelcast.jet.config.JobConfig
import com.hazelcast.jet.core.Processor
import com.hazelcast.jet.pipeline.Pipeline
import com.hazelcast.jet.pipeline.Sinks
import com.hazelcast.jet.pipeline.SourceBuilder
import com.hazelcast.jet.pipeline.SourceBuilder.SourceBuffer
import com.hazelcast.jet.pipeline.StreamSource
import kotlin.system.measureNanoTime


class JetSimplePipeline {
    var jet: JetInstance = ClusterUtils.getJetClient()

    class CustomSourceContext {
        var step = 1000
        var prev = System.currentTimeMillis()
        var counter = 0
        fun fillBuffer(buf: SourceBuffer<Int>) {
            if (System.currentTimeMillis() > prev + step) {
                buf.add(counter++)
                prev += step.toLong()
            }
        }
    }

    fun buildPipeline(bomb: Int): Pipeline {
        val source: StreamSource<Int> = SourceBuilder.stream(
            "emit-every-1s-source"
        ) { c: Processor.Context? -> CustomSourceContext() }
            .fillBufferFn { obj: CustomSourceContext, buf: SourceBuffer<Int> ->
                obj.fillBuffer(
                    buf
                )
            }
            .build()

        val p: Pipeline = Pipeline.create()
        p.readFrom(source)
            .withIngestionTimestamps()
            .rebalance()
            .map { item ->
                println(item)
            }
            .writeTo(Sinks.noop())
        return p
    }

    fun go(bomb: Int) {
        val p: Pipeline = buildPipeline(bomb)
        ClusterUtils.cancelJob(jet.getJob(JetSimplePipeline::class.simpleName!!))
        jet.newJob(p, JobConfig().setName(JetSimplePipeline::class.simpleName!!)).join()
    }
}

fun main(args: Array<String>) {
    JetSimplePipeline().go(1) // 10 mn times
}