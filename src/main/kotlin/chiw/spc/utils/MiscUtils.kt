package chiw.spc.utils

import chiw.spc.proto.CommodityMsg
import chiw.spc.proto.CountryMsg
import chiw.spc.proto.OrderMsg
import chiw.spc.types.CommodityPortable
import chiw.spc.types.CountryPortable
import chiw.spc.types.DataMap
import chiw.spc.types.OrderPortable
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashMap

object MiscUtils {
    fun getBookLines(): List<String> {
        val lineNum = longArrayOf(0)
        val bookLines: MutableMap<Long, String> = HashMap()
        val stream: InputStream = javaClass.getResourceAsStream("/shakespeare-complete-works.txt")
        BufferedReader(InputStreamReader(stream)).use { reader ->
            reader.lines().forEach { line: String ->
                bookLines[++lineNum[0]] = line
            }
        }
        return bookLines.values.toList()
    }

    fun fillPortableOrders(amount: Int) {
        val orderMap = ClusterUtils.getCacheMap<OrderPortable>(DataMap.PortableOrder)
        val buffer = hashMapOf<String, OrderPortable>()
        for (index in 0 until amount) {
            val order = OrderPortable(
                id = "order-portable-${index}",
                quantity = Math.random() * 100000,
                price = Math.random() * 100000,
                commodity = CommodityPortable(
                    id = "commodity-${index}"
                ),
                country = CountryPortable.JP,
                userId = "userid-${index}",
                createdAt = System.currentTimeMillis().toDouble()
            )
            buffer[order.getIdentity()] = order
        }
        orderMap.putAll(buffer)
        println("${DataMap.PortableOrder} size: ${orderMap.size}")
    }

    fun fillOrderMsg(amount: Int) {
        val orderMap = ClusterUtils.getCacheMap<OrderMsg>(DataMap.OrderMsg)
        val buffer = hashMapOf<String, OrderMsg>()
        for (index in 0 until amount) {
            val order = OrderMsg.newBuilder()
                .setHzCustomId(100)
                .setId("order-msg-#${index}")
                .setQuantity((Math.round(Math.ceil((Math.random() * 10000))) / 100).toInt())
                .setCountry(CountryMsg.US)
                .setCommodity(
                    CommodityMsg.newBuilder()
                        .setId("commodity-${index}")
                        .build()
                )
                .setUserId("userid-${index}")
                .setCreatedAt(System.currentTimeMillis())
                .setTimeCost((Math.random() * 10000).toInt())
                .build()
            buffer[order.id] = order
        }
        orderMap.putAll(buffer)
        println("${DataMap.OrderMsg} size: ${orderMap.size}")
    }
}

fun main() {
    val orderMap = ClusterUtils.getCacheMap<OrderMsg>(DataMap.OrderMsg)
    val order = OrderMsg.newBuilder()
        .setHzCustomId(100)
        .setId("test")
        .setQuantity(0)
        .setIsValid(false)
        .build()
    orderMap.put(order.id, order)
    order.toBuilder().setIsValid(false).setQuantity(0).build()
    orderMap.put(order.id, order)
    val returnedOrder = orderMap.get(order.id)
    MiscUtils.fillOrderMsg(1000)
}