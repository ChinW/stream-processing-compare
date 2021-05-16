package chiw.spc.jet

import chiw.spc.proto.CommodityMsg
import chiw.spc.proto.OrderMsg
import chiw.spc.types.CommodityPortable
import chiw.spc.types.DataMap
import chiw.spc.types.OrderPortable
import chiw.spc.utils.ClusterUtils
import chiw.spc.utils.MiscUtils
import com.hazelcast.core.EntryEvent
import com.hazelcast.jet.Jet
import com.hazelcast.jet.JetInstance
import com.hazelcast.jet.Traversers
import com.hazelcast.jet.datamodel.Tuple2
import com.hazelcast.jet.pipeline.*
import com.hazelcast.map.listener.EntryAddedListener
import com.hazelcast.map.listener.EntryUpdatedListener
import java.util.concurrent.ConcurrentHashMap
import kotlin.system.measureNanoTime

object CustomOrderMsgSink {
    var count = 0
   fun sink(dataMap: DataMap): Sink<OrderMsg> {
       return SinkBuilder.sinkBuilder(dataMap.mapName) { ctx ->
           OrderMsgWriter(dataMap)
       }.receiveFn(OrderMsgWriter::receiveFn)
           .flushFn(OrderMsgWriter::flushFn)
           .build()
   }
    class OrderMsgWriter(dataMap: DataMap) {
        val map = ClusterUtils.getCacheMap<OrderMsg>(dataMap)
        private val buffer = ConcurrentHashMap<String, OrderMsg>()

        fun receiveFn(item: OrderMsg) {
            count += 1
            buffer[item.id] = item
        }

        fun flushFn() {
            map.putAll(buffer)
            buffer.clear()
        }
    }
}

