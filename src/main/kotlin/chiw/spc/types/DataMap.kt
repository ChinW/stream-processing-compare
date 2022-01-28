package chiw.spc.types

enum class DataMap(val mapName: String) {
    PortableOrder("PortableOrder"),
    OrderMsg("OrderMsg"),
    PortableOrderSink("PortableOrderSink"),
    OrderMsgSink("OrderMsgSink")
}