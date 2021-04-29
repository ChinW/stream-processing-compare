//package chi.base.types
//
//import chi.base.annotation.Column
//import org.apache.flink.api.common.typeinfo.TypeInfo
//import kotlin.reflect.full.declaredMemberProperties
//
//@TypeInfo(FlinkTypeFactory.OrderPFactory::class)
//data class OrderP(
//    var id: String = "",
//    var parent: String = "",
//    var quantity: Double = 0.0,
//    var price: Double = 0.0,
//    var country: CountryP = CountryP.None,
//    var client: ClientP = ClientP(),
//    var userId: String = "",
//    @Column("created_at")
//    var createdAt: Double = 0.0,
//    var updatedAt: Double = 0.0,
//    var timeCost: Double = 0.0,
//) : PortableBase(ClassId.Order)
//{
//    override fun getIdentity(): String {
//        return this.id
//    }
//}