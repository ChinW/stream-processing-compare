package chiw.spc.types

import com.hazelcast.nio.serialization.Portable
import com.hazelcast.nio.serialization.PortableFactory

class PortableTypeFactory : PortableFactory {
    companion object {
        const val FACTORY_ID = 1;
    }

    override fun create(classId: Int): Portable? {
        return when (classId) {
            ClassId.Order.getClassIdValue() -> OrderPortable()
            ClassId.Commodity.getClassIdValue() -> CommodityPortable()
            else -> {
                null
            }
        }
    }
}