package chiw.spc.types

import org.apache.flink.api.common.typeinfo.TypeInfo

@TypeInfo(FlinkDataType.CommodityTypeInfo::class)
data class CommodityPortable(
    var id: String = "",
    var name: String  = "",
    var price: Double = 0.0,
    var remainingQty: Double = 0.0,
) : PortableBase(ClassId.Commodity) {
    override fun getIdentity(): String {
        return this.id
    }
}