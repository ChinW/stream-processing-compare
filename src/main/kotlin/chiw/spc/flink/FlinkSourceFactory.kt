package chiw.spc.flink

import chiw.spc.types.DataMap
import chiw.spc.utils.ClusterUtils
import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.slf4j.LoggerFactory
import java.util.*

class FlinkSourceFactory<T>(val dataMap: DataMap) : SourceFunction<T> {
    val log = LoggerFactory.getLogger("dataMap-${dataMap.mapName}")

    private var mapListenerId: UUID? = null

    override fun run(ctx: SourceFunction.SourceContext<T>) {
        log.info("start running - ${dataMap.mapName}")
        val map = ClusterUtils.getCacheMap<T>(dataMap)
        map.mapValues {
            ctx.collect(it.value)
        }
    }

    fun clearListeners() {
        if (mapListenerId != null) {
            val map = ClusterUtils.getCacheMap<T>(dataMap)
            map.removeEntryListener(mapListenerId!!)
        }
    }

    override fun cancel() {
        log.info("cancel source!")
    }
}