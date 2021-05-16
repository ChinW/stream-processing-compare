package chiw.spc.types

enum class DataMap(val mapName: String) {
    PortableOrder("PortableOrder"),
    OrderMsg("MsgOrder"),
    OrderMsg2("MsgOrder2"),
    PortableOrderSink("PortableOrderSink"),
    OrderMsgSink("OrderMsgSink")
}