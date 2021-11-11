package chiw.spc.types

import org.apache.flink.api.common.typeinfo.TypeInfo

@TypeInfo(FlinkDataType.OrderTypeInfo::class)
data class OrderPortable(
    var id: String = "",
    var quantity: Double = 0.0,
    var price: Double = 0.0,
    var commodity: CommodityPortable = CommodityPortable(),
    var country: CountryPortable = CountryPortable.None,
    var userId: String = "",
    var createdAt: Double = 0.0,
    var timeCost: Double = 0.0,
) : PortableBase(ClassId.Order) {
    override fun getIdentity(): String {
        return this.id
    }
}