package chiw.spc.types

import org.apache.flink.api.common.typeinfo.TypeInfoFactory
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.common.typeinfo.Types
import java.lang.reflect.Modifier
import java.lang.reflect.Type
import kotlin.system.exitProcess

object FlinkDataType {
    val orderTypeInfo = generateTypeInfo(OrderPortable::class.java)

    class OrderTypeInfo : TypeInfoFactory<OrderPortable>() {
        override fun createTypeInfo(
            t: Type?,
            genericParameters: MutableMap<String, TypeInformation<*>>?
        ): TypeInformation<OrderPortable> {
            println("type is ${OrderPortable::class.java.typeName}")
            return orderTypeInfo
        }
    }

    class CommodityTypeInfo : TypeInfoFactory<CommodityPortable>() {
        override fun createTypeInfo(
            t: Type?,
            genericParameters: MutableMap<String, TypeInformation<*>>?
        ): TypeInformation<CommodityPortable> {
            return generateTypeInfo(CommodityPortable::class.java)
        }
    }

    fun <T : PortableBase> generateTypeInfo(thisClass: Class<T>): TypeInformation<T> {
        val map = hashMapOf<String, TypeInformation<*>>()
        val fields = thisClass.declaredFields.filter { !Modifier.isStatic(it.modifiers) }
        for (field in fields) {
            map[field.name] =
                when (field.type) {
                    Boolean::class.java -> Types.BOOLEAN
                    Double::class.java -> Types.DOUBLE
                    String::class.java -> Types.STRING
                    CountryPortable::class.java -> Types.ENUM(CountryPortable::class.java)
                    CommodityPortable::class.java -> generateTypeInfo(CommodityPortable::class.java)
                    Array<CommodityPortable>::class.java -> Types.OBJECT_ARRAY(generateTypeInfo(CommodityPortable::class.java))
                    else -> {
                        println("ERROR!!! missing type mapping for ${field.name}")
                        exitProcess(1)
                    }
                }
        }
        return Types.POJO(thisClass, map)
    }
}