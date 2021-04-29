package chiw.spc.flink

import chiw.spc.utils.MiscUtils
import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.api.java.DataSet
import org.apache.flink.api.java.ExecutionEnvironment
import org.apache.flink.api.java.tuple.Tuple2
import org.apache.flink.streaming.api.datastream.DataStream
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.api.functions.sink.SinkFunction
import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.util.Collector
import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.HashMap
import java.util.logging.Logger

class FlinkWordCount {
    val log = LoggerFactory.getLogger(FlinkWordCount::class.java)

    fun run(bomb: Int) {
        val env = StreamExecutionEnvironment.getExecutionEnvironment()
        env.config.enableObjectReuse()
        val lines = env.addSource(FlinkWordCountSource())
        val counts: DataStream<Tuple2<String, Int>> =
            lines.flatMap(Tokenizer(bomb))
                .keyBy { it.f0 }
                .sum(1)
        counts.addSink(FlinkWordCountSink())
        log.info("env.executionPlan is ${env.executionPlan}")
        env.execute("word-count")
    }

    class Tokenizer(val bomb: Int) : FlatMapFunction<String, Tuple2<String, Int>> {
        override fun flatMap(value: String, out: Collector<Tuple2<String, Int>>) {
            val tokens = value.toLowerCase().split("\\W+".toRegex()).toTypedArray()
            repeat(bomb) { // bomb
                for (token in tokens) {
                    if (token.length > 0) {
                        out.collect(Tuple2<String, Int>(token, 1))
                    }
                }
            }
        }
    }

    class FlinkWordCountSource : SourceFunction<String> {
        val log = LoggerFactory.getLogger(FlinkWordCountSource::class.java)

        override fun run(ctx: SourceFunction.SourceContext<String>) {
            val bookLines = MiscUtils.getBookLines()
            for(line in bookLines) {
                ctx.collect(line)
            }
        }

        override fun cancel() {}
    }

    class FlinkWordCountSink: SinkFunction<Tuple2<String, Int>> {
        val log = LoggerFactory.getLogger(FlinkWordCountSink::class.java)
        override fun invoke(value: Tuple2<String, Int>?, context: SinkFunction.Context?) {
        }
    }
}

fun main(){
    val flinkWordCount = FlinkWordCount()
    flinkWordCount.run(1000)
}