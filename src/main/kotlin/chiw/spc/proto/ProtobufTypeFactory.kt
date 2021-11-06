package chiw.spc.proto

import com.hazelcast.jet.protobuf.ProtobufSerializer

class OrderSerializer: ProtobufSerializer<OrderMsg>(OrderMsg::class.java, 100)
class CommoditySerializer: ProtobufSerializer<CommodityMsg>(CommodityMsg::class.java, 101)
//class CommodityTypeSerializer: ProtobufSerializer<CommodityTypeMsg>(CommodityTypeMsg::class.java, 101)
