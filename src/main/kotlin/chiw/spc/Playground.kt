package chiw.spc.proto;

fun main() {
    CommodityTypeMsg.valueOf("")
    val fields = CommodityMsg.getDescriptor().fields
    for (field in fields) {
        field.enumType.value
        println(field.type)
    }
}