package chiw.spc.jet

import chiw.spc.proto.OrderMsg
import com.hazelcast.map.EntryProcessor
import com.hazelcast.nio.ObjectDataInput
import com.hazelcast.nio.ObjectDataOutput
import com.hazelcast.nio.serialization.IdentifiedDataSerializable

class OrderMutationEntryProcessor() : EntryProcessor<String, OrderMsg, Boolean>,
    IdentifiedDataSerializable {

    var number: Double = 0.0

    constructor(number: Double) : this() {
        this.number = number
    }

    override fun process(entry: MutableMap.MutableEntry<String, OrderMsg>): Boolean {
        println("math.random ${number}")
        entry.setValue(entry.value.toBuilder().setQuantity(entry.value.quantity + 1).build())
        return true
    }

    override fun writeData(out: ObjectDataOutput) {
        out.writeDouble(this.number)
    }

    override fun readData(input: ObjectDataInput) {
        this.number = input.readDouble()
    }

    override fun getFactoryId(): Int {
        return 2
    }

    override fun getClassId(): Int {
        return 1
    }
}