// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: commodity.proto

package chiw.spc.proto;

public final class CommodityPB {
  private CommodityPB() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_chi_base_proto_CommodityMsg_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_chi_base_proto_CommodityMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017commodity.proto\022\016chi.base.proto\"\214\001\n\014Co" +
      "mmodityMsg\022\017\n\002id\030\001 \001(\tH\000\210\001\001\022\021\n\004name\030\002 \001(" +
      "\tH\001\210\001\001\022\022\n\005price\030\003 \001(\001H\002\210\001\001\022\031\n\014remainingQ" +
      "ty\030\004 \001(\001H\003\210\001\001B\005\n\003_idB\007\n\005_nameB\010\n\006_priceB" +
      "\017\n\r_remainingQtyB\037\n\016chiw.spc.protoB\013Comm" +
      "odityPBP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_chi_base_proto_CommodityMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_chi_base_proto_CommodityMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_chi_base_proto_CommodityMsg_descriptor,
        new java.lang.String[] { "Id", "Name", "Price", "RemainingQty", "Id", "Name", "Price", "RemainingQty", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}