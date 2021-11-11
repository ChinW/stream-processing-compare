package chiw.spc.flink

import chiw.spc.proto.CommodityMsg
import chiw.spc.proto.CountryMsg
import chiw.spc.proto.OrderMsg
import chiw.spc.types.*
import chiw.spc.utils.ClusterUtils
import com.hazelcast.map.IMap
import com.twitter.chill.protobuf.ProtobufSerializer
import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.java.tuple.Tuple2
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.datastream.DataStream
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction
import org.apache.flink.streaming.api.functions.sink.SinkFunction
import org.apache.flink.util.Collector
import org.slf4j.LoggerFactory

class FlinkOrderMsgBomb {
    val log = LoggerFactory.getLogger(FlinkOrderMsgBomb::class.java)

    fun run(bomb: Int) {
        val env = StreamExecutionEnvironment.getExecutionEnvironment()
        val source = DataMap.OrderMsg
        val sink = DataMap.OrderMsgSink
//        env.config.disableGenericTypes()
        env.config.enableObjectReuse()
        env.config.registerTypeWithKryoSerializer(CommodityMsg::class.java, ProtobufSerializer::class.java)
        env.config.registerTypeWithKryoSerializer(CountryMsg::class.java, ProtobufSerializer::class.java)
        env.config.registerTypeWithKryoSerializer(OrderMsg::class.java, ProtobufSerializer::class.java)
        val orders: DataStream<OrderMsg> = env
            .addSource(FlinkSourceFactory<OrderMsg>(source))
            .returns(TypeInformation.of(OrderMsg::class.java))
            .name("source-${source}")
        orders
            .flatMap(FlinkFlatMap(bomb))
            .map(FlinkMap())
            .addSink(FlinkSink(sink))
            .name("sink-${sink}")
        log.info("env.executionPlan:\n${env.executionPlan}")
        env.execute("${source}-${sink}-pipeline")
    }

    class FlinkFlatMap(val bomb: Int) : FlatMapFunction<OrderMsg, Tuple2<CommodityMsg, OrderMsg>> {
        override fun flatMap(order: OrderMsg, out: Collector<Tuple2<CommodityMsg, OrderMsg>>) {
            for (i in 0 until bomb) {
                out.collect(Tuple2(order.commodity, order))
            }
        }
    }

    class FlinkMap : MapFunction<Tuple2<CommodityMsg, OrderMsg>, OrderMsg> {
        override fun map(value: Tuple2<CommodityMsg, OrderMsg>): OrderMsg {
            return value.f1
        }
    }

    class FlinkSink(val dataMap: DataMap) : RichSinkFunction<OrderMsg>() {
        private lateinit var map: IMap<String, OrderMsg>

        override fun open(parameters: Configuration?) {
            map = ClusterUtils.getCacheMap(dataMap)
        }

        override fun invoke(item: OrderMsg, context: SinkFunction.Context) {
            map.setAsync(item.id, item)
        }
    }
}

fun main() {
    val dataflow = FlinkOrderMsgBomb()
    dataflow.run(1000)
}