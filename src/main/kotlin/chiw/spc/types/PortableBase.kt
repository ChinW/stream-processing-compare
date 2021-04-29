//package chiw.spc.types
//
//import com.hazelcast.nio.serialization.Portable
//import com.hazelcast.nio.serialization.PortableReader
//import com.hazelcast.nio.serialization.PortableWriter
//import org.slf4j.LoggerFactory
//import java.lang.reflect.Modifier
//
//abstract class PortableBase(val classId: ClassId)
//    : DataIdentity<String>, Portable
////    : BasicType()
//{
//    private val log = LoggerFactory.getLogger(classId.name)
//    var nid: String = ""
//
//    override fun getFactoryId(): Int {
//        return TypeFactory.FACTORY_ID;
//    }
//
//    override fun getClassId(): Int {
//        return classId.getClassIdValue();
//    }
//
//    override fun readPortable(reader: PortableReader?) {
//        if (reader != null) {
//            val fields = this::class.java.declaredFields.filter { reader.hasField(it.name) }
//            for (field in fields) {
//                try {
//                    field.isAccessible = true
//                    if (field[this] is Portable) {
//                        field[this] = reader.readPortable(field.name)
//                    } else {
//                        when (field.type) {
//                            Boolean::class.java -> field[this] = reader.readBoolean(field.name)
//                            Double::class.java -> field[this] = reader.readDouble(field.name)
//                            String::class.java -> field[this] = reader.readUTF(field.name)
//                            CountryP::class.java -> field[this] = CountryP.valueOf(reader.readUTF(field.name))
//                            Array<Boolean>::class.java -> field[this] = reader.readBooleanArray(field.name)
//                            Array<Double>::class.java -> field[this] = reader.readDoubleArray(field.name)
//                            Array<String>::class.java -> field[this] = reader.readUTFArray(field.name)
//                            Array<ClientP>::class.java -> field[this] = (reader.readPortableArray(field.name)
//                                .map { it as ClientP }).toTypedArray()
//                            else -> {
//                                log.error("Unsupported type in reader")
//                            }
//                        }
//                    }
//                } catch (e: Exception) {
//                    log.error("Reader exception: ${field.name} => ${field.type.name}, msg: ${e.message}")
//                }
//            }
//            this.nid = reader.readUTF("nid")
//        }
//    }
//
//    override fun writePortable(writer: PortableWriter?) {
//        if (writer != null) {
//            val fields = this::class.java.declaredFields.filter { !Modifier.isStatic(it.modifiers) }
//            for (field in fields) {
//                try {
//                    field.isAccessible = true
//                    val value = field.get(this)
//                    if (field[this] is Portable) {
//                        writer.writePortable(field.name, value as Portable)
//                    } else {
//                        when (field.type) {
//                            Boolean::class.java -> writer.writeBoolean(field.name, value as Boolean)
//                            Double::class.java -> writer.writeDouble(field.name, value as Double)
//                            String::class.java -> writer.writeUTF(field.name, value as String)
//                            CountryP::class.java -> writer.writeUTF(field.name, (value as CountryP).name)
//                            // NOTE: for portable arrays field, if it is emtpy, hazelcast will raise an error like
//                            // "Cannot write null portable array without explicitly registering class definition!"
//                            // and in the following updates, this class definition will be always lack of this
//                            // field!
//                            // it might raise error like:
//                            // "Invalid field name: 'clients' for ClassDefinition {id: 1, version: 0}"
//                            // Conclusion: Try to avoid to use array unless you paid attention to emtpy array
//                            Array<ClientP>::class.java -> {
//                                if ((value as Array<*>).isNotEmpty()) {
//                                    writer.writePortableArray(field.name, value as Array<out Portable>)
//                                } else {
//                                    // FIXME: you need to register class definition first
//                                    writer.writeNullPortable(field.name, this.factoryId, this.classId.getClassIdValue())
//                                }
//                            }
//                            Array<Boolean>::class.java -> {
//                                    writer.writeBooleanArray(field.name, value as BooleanArray)
//                            }
//                            Array<Double>::class.java -> {
//                                    writer.writeDoubleArray(field.name, value as DoubleArray)
//                            }
//                            Array<String>::class.java -> {
//                                    writer.writeUTFArray(field.name, value as Array<out String>?)
//                            }
//                            else -> {
//                                log.error("Unsupported type in writer")
//                            }
//                        }
//                    }
//                } catch (e: Exception) {
//                    println("Writer Exception: field: ${field.name}, field name: ${field.type.name}}, msg: ${e.message}")
//                }
//            }
//            writer.writeUTF("nid", this.getIdentity())
//        }
//    }
//}
//
//
//abstract class BasicType() : DataIdentity<String> {
//    var nid: String = ""
//}