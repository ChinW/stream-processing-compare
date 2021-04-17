package chiw.spc.utils

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
}