// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: BullyingDetectionService.proto

package com.edu.greenwich.managementsystem.grpc;

public final class BullyingDetectionServiceOuterClass {
  private BullyingDetectionServiceOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_edu_greenwich_managementsystem_grpc_MsgRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_edu_greenwich_managementsystem_grpc_MsgRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_edu_greenwich_managementsystem_grpc_MsgResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_edu_greenwich_managementsystem_grpc_MsgResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036BullyingDetectionService.proto\022\'com.ed" +
      "u.greenwich.managementsystem.grpc\"\035\n\nMsg" +
      "Request\022\017\n\007message\030\001 \001(\t\"\037\n\013MsgResponse\022" +
      "\020\n\010response\030\001 \001(\t2\226\001\n\030BullyingDetectionS" +
      "ervice\022z\n\rdetectComment\0223.com.edu.greenw" +
      "ich.managementsystem.grpc.MsgRequest\0324.c" +
      "om.edu.greenwich.managementsystem.grpc.M" +
      "sgResponseB\002P\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_edu_greenwich_managementsystem_grpc_MsgRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_edu_greenwich_managementsystem_grpc_MsgRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_edu_greenwich_managementsystem_grpc_MsgRequest_descriptor,
        new java.lang.String[] { "Message", });
    internal_static_com_edu_greenwich_managementsystem_grpc_MsgResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_edu_greenwich_managementsystem_grpc_MsgResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_edu_greenwich_managementsystem_grpc_MsgResponse_descriptor,
        new java.lang.String[] { "Response", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
