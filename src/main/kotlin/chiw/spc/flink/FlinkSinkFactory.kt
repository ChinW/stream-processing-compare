package chiw.spc.flink

import chiw.spc.types.DataMap
import chiw.spc.types.PortableBase
import chiw.spc.utils.ClusterUtils
import com.hazelcast.map.IMap
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction
import org.apache.flink.streaming.api.functions.sink.SinkFunction
import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.slf4j.LoggerFactory
import java.util.*

sealed class FlinkSinkFactory {
    class PortableSink<T: PortableBase>(val dataMap: DataMap) : RichSinkFunction<T>() {
        val log = LoggerFactory.getLogger("sink-${dataMap.mapName}")
        private lateinit var map: IMap<String, T>

        override fun open(parameters: Configuration?) {
            map = ClusterUtils.getCacheMap(dataMap)
        }

        override fun invoke(item: T, context: SinkFunction.Context) {
            map.setAsync(item.getIdentity(), item)
        }
    }
}