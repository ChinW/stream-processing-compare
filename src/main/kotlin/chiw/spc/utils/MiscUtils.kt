package chiw.spc.utils

import chiw.spc.types.CommodityPortable
import chiw.spc.types.CountryPortable
import chiw.spc.types.DataMap
import chiw.spc.types.OrderPortable
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

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
        for(index in 0 until amount) {
            val order = OrderPortable(
                id = "portable-order-${index}",
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
}