package chiw.spc.proto;

import chiw.spc.types.DataMap
import chiw.spc.utils.ClusterUtils
import org.slf4j.LoggerFactory

fun main() {
    val log = LoggerFactory.getLogger("PG")
    val cache = ClusterUtils.getCacheMap<OrderMsg>(DataMap.OrderMsg)
    val order = OrderMsg.newBuilder()
        .setId("1")
        .setHzCustomId(9)
        .setQuantity(0)
        .build()
    cache.put(order.id, order)
    println(cache.get("1"))
}