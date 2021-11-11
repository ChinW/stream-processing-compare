package chiw.spc.flink

import chiw.spc.types.*
import chiw.spc.utils.ClusterUtils
import com.hazelcast.map.IMap
import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.api.java.tuple.Tuple2
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.datastream.DataStream
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction
import org.apache.flink.streaming.api.functions.sink.SinkFunction
import org.apache.flink.util.Collector
import org.slf4j.LoggerFactory

class FlinkOrderPortableBomb {
    val log = LoggerFactory.getLogger(FlinkOrderPortableBomb::class.java)

    fun run(bomb: Int) {
        val env = StreamExecutionEnvironment.getExecutionEnvironment()
        val source = DataMap.PortableOrder
        val sink = DataMap.PortableOrderSink
        env.config.disableGenericTypes()
        env.config.enableObjectReuse()
        val orders: DataStream<OrderPortable> = env
            .addSource(FlinkSourceFactory<OrderPortable>(source))
            .returns(FlinkDataType.orderTypeInfo)
            .name("source-${source}")
        orders
            .flatMap(FlinkFlatMap(bomb))
            .map(FlinkMap())
            .addSink(FlinkSink(sink))
            .name("sink-${sink}")
        log.info("env.executionPlan:\n${env.executionPlan}")
        env.execute("${source}-${sink}-pipeline")
    }

    class FlinkFlatMap(val bomb: Int) : FlatMapFunction<OrderPortable, Tuple2<CommodityPortable, OrderPortable>> {
        override fun flatMap(order: OrderPortable, out: Collector<Tuple2<CommodityPortable, OrderPortable>>) {
            for (i in 0 until bomb) {
                out.collect(Tuple2(order.commodity, order))
            }
        }
    }

    class FlinkMap : MapFunction<Tuple2<CommodityPortable, OrderPortable>, OrderPortable> {
        override fun map(value: Tuple2<CommodityPortable, OrderPortable>): OrderPortable {
            return value.f1
        }
    }

    class FlinkSink<T : PortableBase>(val dataMap: DataMap) : RichSinkFunction<T>() {
        private lateinit var map: IMap<String, T>

        override fun open(parameters: Configuration?) {
            map = ClusterUtils.getCacheMap(dataMap)
        }

        override fun invoke(item: T, context: SinkFunction.Context) {
            map.setAsync(item.getIdentity(), item)
        }
    }
}

fun main() {
    val dataflow = FlinkOrderPortableBomb()
    dataflow.run(100)
}