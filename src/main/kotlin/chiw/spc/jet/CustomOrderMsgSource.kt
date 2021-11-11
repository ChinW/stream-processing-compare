package chiw.spc.jet

import chiw.spc.proto.OrderMsg
import chiw.spc.types.DataMap
import chiw.spc.utils.ClusterUtils
import com.hazelcast.core.EntryEvent
import com.hazelcast.jet.pipeline.BatchSource
import com.hazelcast.jet.pipeline.SourceBuilder
import com.hazelcast.jet.pipeline.StreamSource
import com.hazelcast.map.listener.EntryAddedListener
import com.hazelcast.map.listener.EntryUpdatedListener
import java.io.Serializable
import java.util.concurrent.ConcurrentHashMap

object CustomOrderMsgSource {
    fun stream(dataMap: DataMap): StreamSource<Map.Entry<String, OrderMsg>> {
        return SourceBuilder.stream(dataMap.mapName) { ctx ->
            OrderMsgListener(dataMap)
        }.fillBufferFn(OrderMsgListener::fillBufferFn)
            .build()
    }

    fun batch(dataMap: DataMap): BatchSource<Map.Entry<String, OrderMsg>> {
        return SourceBuilder.batch(dataMap.mapName) { ctx ->
            OrderMsgListener(dataMap, true)
        }.fillBufferFn(OrderMsgListener::fillBufferFn)
            .build()
    }

    class OrderMsgListener(dataMap: DataMap, val isBatch: Boolean = false) : Serializable {
        val dataBuffer: ConcurrentHashMap<String, OrderMsg> = ConcurrentHashMap()
        val cacheMap = ClusterUtils.getCacheMap<OrderMsg>(dataMap)

        init {
            val exisitingData = cacheMap.values.associateByTo(HashMap()) { it.id }
            dataBuffer.putAll(exisitingData)
            if (!isBatch) {
                val handler = object : EntryAddedListener<String, OrderMsg>,
                    EntryUpdatedListener<String, OrderMsg> {
                    override fun entryAdded(event: EntryEvent<String, OrderMsg>) {
                        this@OrderMsgListener.onEventUpdated(event)
                    }

                    override fun entryUpdated(event: EntryEvent<String, OrderMsg>) {
                        this@OrderMsgListener.onEventUpdated(event)
                    }
                }
                cacheMap.addEntryListener(handler, true)
            }
        }

        fun onEventUpdated(event: EntryEvent<String, OrderMsg>) {
            dataBuffer[event.key] = event.value
        }

        fun fillBufferFn(
            socketBuffer: SourceBuilder.SourceBuffer<Map.Entry<String, OrderMsg>>
        ) {
            if (dataBuffer.size > 0) {
                for (item in dataBuffer) {
                    socketBuffer.add(item)
                }
                dataBuffer.clear()
                if (isBatch) {
                    socketBuffer.close()
                }
            }
        }
    }
}

