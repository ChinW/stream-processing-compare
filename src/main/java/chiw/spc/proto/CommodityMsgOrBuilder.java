// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: commodity.proto

package chiw.spc.proto;

public interface CommodityMsgOrBuilder extends
    // @@protoc_insertion_point(interface_extends:chi.base.proto.CommodityMsg)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string id = 1;</code>
   * @return Whether the id field is set.
   */
  boolean hasId();
  /**
   * <code>string id = 1;</code>
   * @return The id.
   */
  java.lang.String getId();
  /**
   * <code>string id = 1;</code>
   * @return The bytes for id.
   */
  com.google.protobuf.ByteString
      getIdBytes();

  /**
   * <code>string name = 2;</code>
   * @return Whether the name field is set.
   */
  boolean hasName();
  /**
   * <code>string name = 2;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>double price = 3;</code>
   * @return Whether the price field is set.
   */
  boolean hasPrice();
  /**
   * <code>double price = 3;</code>
   * @return The price.
   */
  double getPrice();

  /**
   * <code>double remainingQty = 4;</code>
   * @return Whether the remainingQty field is set.
   */
  boolean hasRemainingQty();
  /**
   * <code>double remainingQty = 4;</code>
   * @return The remainingQty.
   */
  double getRemainingQty();
}
