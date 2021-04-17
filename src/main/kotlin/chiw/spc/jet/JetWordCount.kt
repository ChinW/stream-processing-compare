package chiw.spc.jet

import chiw.spc.utils.ClusterUtils
import chiw.spc.utils.MiscUtils
import com.hazelcast.function.Functions.wholeItem
import com.hazelcast.jet.Jet
import com.hazelcast.jet.JetInstance
import com.hazelcast.jet.Traversers.traverseArray
import com.hazelcast.jet.aggregate.AggregateOperations.counting
import com.hazelcast.jet.pipeline.Pipeline
import com.hazelcast.jet.pipeline.SinkBuilder
import com.hazelcast.jet.pipeline.Sinks
import com.hazelcast.jet.pipeline.test.TestSources
import java.util.regex.Pattern
import kotlin.system.measureNanoTime

class JetWordCount {
    var jet: JetInstance = ClusterUtils.setupJet()
    val doNothingSink = SinkBuilder.sinkBuilder(
        "do-nothing-sink"
    ) { it ->
        Unit
    }.receiveFn { unit, item: Map.Entry<String, Long> ->
    }.build()

    fun buildPipeline(): Pipeline {
        val delimiter: Pattern = Pattern.compile("\\W+")
        val p: Pipeline = Pipeline.create()
        p.readFrom(TestSources.items(MiscUtils.getBookLines()))
            .flatMap { entry ->
                val output = arrayListOf<String>()
                val words = delimiter.split(entry.toLowerCase() )
                repeat(1000) { // bomb
                    output.addAll(words)
                }
                traverseArray( output.toTypedArray() )
            }
            .filter { word -> word.isNotEmpty() }
            .groupingKey(wholeItem())
            .aggregate(counting())
            .writeTo(doNothingSink)
        return p
    }

     fun go() {
        try {
            val p: Pipeline = buildPipeline()
            val timeCost = measureNanoTime {
                jet.newJob(p).join()
            }
            println("Jet: finish in ${timeCost / 10e8} seconds.")
        } finally {
            Jet.shutdownAll()
        }
    }

    private fun checkResults(counts: Map<String, Long>): Map<String, Long> {
        if (counts["the"] != 27843L) {
            throw AssertionError("Wrong count of 'the'")
        }
        println("Count of 'the' is valid")
        return counts
    }
}

fun main(args: Array<String>) {
    JetWordCount().go()
}