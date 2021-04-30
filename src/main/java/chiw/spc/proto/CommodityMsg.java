// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: commodity.proto

package chiw.spc.proto;

/**
 * Protobuf type {@code chi.base.proto.CommodityMsg}
 */
public final class CommodityMsg extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:chi.base.proto.CommodityMsg)
    CommodityMsgOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CommodityMsg.newBuilder() to construct.
  private CommodityMsg(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CommodityMsg() {
    id_ = "";
    name_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new CommodityMsg();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CommodityMsg(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();
            bitField0_ |= 0x00000001;
            id_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();
            bitField0_ |= 0x00000002;
            name_ = s;
            break;
          }
          case 25: {
            bitField0_ |= 0x00000004;
            price_ = input.readDouble();
            break;
          }
          case 33: {
            bitField0_ |= 0x00000008;
            remainingQty_ = input.readDouble();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return chiw.spc.proto.CommodityPB.internal_static_chi_base_proto_CommodityMsg_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return chiw.spc.proto.CommodityPB.internal_static_chi_base_proto_CommodityMsg_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            chiw.spc.proto.CommodityMsg.class, chiw.spc.proto.CommodityMsg.Builder.class);
  }

  private int bitField0_;
  public static final int ID_FIELD_NUMBER = 1;
  private volatile java.lang.Object id_;
  /**
   * <code>string id = 1;</code>
   * @return Whether the id field is set.
   */
  @java.lang.Override
  public boolean hasId() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>string id = 1;</code>
   * @return The id.
   */
  @java.lang.Override
  public java.lang.String getId() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      id_ = s;
      return s;
    }
  }
  /**
   * <code>string id = 1;</code>
   * @return The bytes for id.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getIdBytes() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      id_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int NAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object name_;
  /**
   * <code>string name = 2;</code>
   * @return Whether the name field is set.
   */
  @java.lang.Override
  public boolean hasName() {
    return ((bitField0_ & 0x00000002) != 0);
  }
  /**
   * <code>string name = 2;</code>
   * @return The name.
   */
  @java.lang.Override
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PRICE_FIELD_NUMBER = 3;
  private double price_;
  /**
   * <code>double price = 3;</code>
   * @return Whether the price field is set.
   */
  @java.lang.Override
  public boolean hasPrice() {
    return ((bitField0_ & 0x00000004) != 0);
  }
  /**
   * <code>double price = 3;</code>
   * @return The price.
   */
  @java.lang.Override
  public double getPrice() {
    return price_;
  }

  public static final int REMAININGQTY_FIELD_NUMBER = 4;
  private double remainingQty_;
  /**
   * <code>double remainingQty = 4;</code>
   * @return Whether the remainingQty field is set.
   */
  @java.lang.Override
  public boolean hasRemainingQty() {
    return ((bitField0_ & 0x00000008) != 0);
  }
  /**
   * <code>double remainingQty = 4;</code>
   * @return The remainingQty.
   */
  @java.lang.Override
  public double getRemainingQty() {
    return remainingQty_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) != 0)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, id_);
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
    }
    if (((bitField0_ & 0x00000004) != 0)) {
      output.writeDouble(3, price_);
    }
    if (((bitField0_ & 0x00000008) != 0)) {
      output.writeDouble(4, remainingQty_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, id_);
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
    }
    if (((bitField0_ & 0x00000004) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(3, price_);
    }
    if (((bitField0_ & 0x00000008) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(4, remainingQty_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof chiw.spc.proto.CommodityMsg)) {
      return super.equals(obj);
    }
    chiw.spc.proto.CommodityMsg other = (chiw.spc.proto.CommodityMsg) obj;

    if (hasId() != other.hasId()) return false;
    if (hasId()) {
      if (!getId()
          .equals(other.getId())) return false;
    }
    if (hasName() != other.hasName()) return false;
    if (hasName()) {
      if (!getName()
          .equals(other.getName())) return false;
    }
    if (hasPrice() != other.hasPrice()) return false;
    if (hasPrice()) {
      if (java.lang.Double.doubleToLongBits(getPrice())
          != java.lang.Double.doubleToLongBits(
              other.getPrice())) return false;
    }
    if (hasRemainingQty() != other.hasRemainingQty()) return false;
    if (hasRemainingQty()) {
      if (java.lang.Double.doubleToLongBits(getRemainingQty())
          != java.lang.Double.doubleToLongBits(
              other.getRemainingQty())) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasId()) {
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + getId().hashCode();
    }
    if (hasName()) {
      hash = (37 * hash) + NAME_FIELD_NUMBER;
      hash = (53 * hash) + getName().hashCode();
    }
    if (hasPrice()) {
      hash = (37 * hash) + PRICE_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          java.lang.Double.doubleToLongBits(getPrice()));
    }
    if (hasRemainingQty()) {
      hash = (37 * hash) + REMAININGQTY_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          java.lang.Double.doubleToLongBits(getRemainingQty()));
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static chiw.spc.proto.CommodityMsg parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static chiw.spc.proto.CommodityMsg parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static chiw.spc.proto.CommodityMsg parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static chiw.spc.proto.CommodityMsg parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static chiw.spc.proto.CommodityMsg parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static chiw.spc.proto.CommodityMsg parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static chiw.spc.proto.CommodityMsg parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static chiw.spc.proto.CommodityMsg parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static chiw.spc.proto.CommodityMsg parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static chiw.spc.proto.CommodityMsg parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static chiw.spc.proto.CommodityMsg parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static chiw.spc.proto.CommodityMsg parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(chiw.spc.proto.CommodityMsg prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code chi.base.proto.CommodityMsg}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:chi.base.proto.CommodityMsg)
      chiw.spc.proto.CommodityMsgOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return chiw.spc.proto.CommodityPB.internal_static_chi_base_proto_CommodityMsg_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return chiw.spc.proto.CommodityPB.internal_static_chi_base_proto_CommodityMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              chiw.spc.proto.CommodityMsg.class, chiw.spc.proto.CommodityMsg.Builder.class);
    }

    // Construct using chiw.spc.proto.CommodityMsg.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      id_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      name_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      price_ = 0D;
      bitField0_ = (bitField0_ & ~0x00000004);
      remainingQty_ = 0D;
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return chiw.spc.proto.CommodityPB.internal_static_chi_base_proto_CommodityMsg_descriptor;
    }

    @java.lang.Override
    public chiw.spc.proto.CommodityMsg getDefaultInstanceForType() {
      return chiw.spc.proto.CommodityMsg.getDefaultInstance();
    }

    @java.lang.Override
    public chiw.spc.proto.CommodityMsg build() {
      chiw.spc.proto.CommodityMsg result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public chiw.spc.proto.CommodityMsg buildPartial() {
      chiw.spc.proto.CommodityMsg result = new chiw.spc.proto.CommodityMsg(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        to_bitField0_ |= 0x00000001;
      }
      result.id_ = id_;
      if (((from_bitField0_ & 0x00000002) != 0)) {
        to_bitField0_ |= 0x00000002;
      }
      result.name_ = name_;
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.price_ = price_;
        to_bitField0_ |= 0x00000004;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.remainingQty_ = remainingQty_;
        to_bitField0_ |= 0x00000008;
      }
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof chiw.spc.proto.CommodityMsg) {
        return mergeFrom((chiw.spc.proto.CommodityMsg)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(chiw.spc.proto.CommodityMsg other) {
      if (other == chiw.spc.proto.CommodityMsg.getDefaultInstance()) return this;
      if (other.hasId()) {
        bitField0_ |= 0x00000001;
        id_ = other.id_;
        onChanged();
      }
      if (other.hasName()) {
        bitField0_ |= 0x00000002;
        name_ = other.name_;
        onChanged();
      }
      if (other.hasPrice()) {
        setPrice(other.getPrice());
      }
      if (other.hasRemainingQty()) {
        setRemainingQty(other.getRemainingQty());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      chiw.spc.proto.CommodityMsg parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (chiw.spc.proto.CommodityMsg) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object id_ = "";
    /**
     * <code>string id = 1;</code>
     * @return Whether the id field is set.
     */
    public boolean hasId() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>string id = 1;</code>
     * @return The id.
     */
    public java.lang.String getId() {
      java.lang.Object ref = id_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        id_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string id = 1;</code>
     * @return The bytes for id.
     */
    public com.google.protobuf.ByteString
        getIdBytes() {
      java.lang.Object ref = id_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        id_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string id = 1;</code>
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      id_ = getDefaultInstance().getId();
      onChanged();
      return this;
    }
    /**
     * <code>string id = 1;</code>
     * @param value The bytes for id to set.
     * @return This builder for chaining.
     */
    public Builder setIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      bitField0_ |= 0x00000001;
      id_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object name_ = "";
    /**
     * <code>string name = 2;</code>
     * @return Whether the name field is set.
     */
    public boolean hasName() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>string name = 2;</code>
     * @return The name.
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
     * @return The bytes for name.
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
     * @param value The name to set.
     * @return This builder for chaining.
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearName() {
      bitField0_ = (bitField0_ & ~0x00000002);
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     * @param value The bytes for name to set.
     * @return This builder for chaining.
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      bitField0_ |= 0x00000002;
      name_ = value;
      onChanged();
      return this;
    }

    private double price_ ;
    /**
     * <code>double price = 3;</code>
     * @return Whether the price field is set.
     */
    @java.lang.Override
    public boolean hasPrice() {
      return ((bitField0_ & 0x00000004) != 0);
    }
    /**
     * <code>double price = 3;</code>
     * @return The price.
     */
    @java.lang.Override
    public double getPrice() {
      return price_;
    }
    /**
     * <code>double price = 3;</code>
     * @param value The price to set.
     * @return This builder for chaining.
     */
    public Builder setPrice(double value) {
      bitField0_ |= 0x00000004;
      price_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double price = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearPrice() {
      bitField0_ = (bitField0_ & ~0x00000004);
      price_ = 0D;
      onChanged();
      return this;
    }

    private double remainingQty_ ;
    /**
     * <code>double remainingQty = 4;</code>
     * @return Whether the remainingQty field is set.
     */
    @java.lang.Override
    public boolean hasRemainingQty() {
      return ((bitField0_ & 0x00000008) != 0);
    }
    /**
     * <code>double remainingQty = 4;</code>
     * @return The remainingQty.
     */
    @java.lang.Override
    public double getRemainingQty() {
      return remainingQty_;
    }
    /**
     * <code>double remainingQty = 4;</code>
     * @param value The remainingQty to set.
     * @return This builder for chaining.
     */
    public Builder setRemainingQty(double value) {
      bitField0_ |= 0x00000008;
      remainingQty_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double remainingQty = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearRemainingQty() {
      bitField0_ = (bitField0_ & ~0x00000008);
      remainingQty_ = 0D;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:chi.base.proto.CommodityMsg)
  }

  // @@protoc_insertion_point(class_scope:chi.base.proto.CommodityMsg)
  private static final chiw.spc.proto.CommodityMsg DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new chiw.spc.proto.CommodityMsg();
  }

  public static chiw.spc.proto.CommodityMsg getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CommodityMsg>
      PARSER = new com.google.protobuf.AbstractParser<CommodityMsg>() {
    @java.lang.Override
    public CommodityMsg parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CommodityMsg(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CommodityMsg> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CommodityMsg> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public chiw.spc.proto.CommodityMsg getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

