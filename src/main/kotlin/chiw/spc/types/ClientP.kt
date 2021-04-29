//package chiw.spc.types
//
//import org.apache.flink.api.common.typeinfo.TypeInfo
//
//@TypeInfo(FlinkTypeFactory.ClientPFactory::class)
//data class ClientP(
//    var id: String = "",
//    var name: String  = "",
//    var sales: String = ""
//) : PortableBase(ClassId.Client) {
//    override fun getIdentity(): String {
//        return this.id
//    }
//}