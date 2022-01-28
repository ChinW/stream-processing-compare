package chiw.spc.types

import chiw.spc.jet.OrderMutationEntryProcessor
import com.hazelcast.nio.serialization.DataSerializableFactory
import com.hazelcast.nio.serialization.IdentifiedDataSerializable

class DataSerializableTypeFactory : DataSerializableFactory {
    companion object {
        const val FACTORY_ID = 2;
    }

    override fun create(typeId: Int): IdentifiedDataSerializable? {
        if(typeId == 1) {
            return OrderMutationEntryProcessor()
        }
        return null
    }
}