package com.edu.greenwich.managementsystem.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: BullyingDetectionService.proto")
public final class BullyingDetectionServiceGrpc {

  private BullyingDetectionServiceGrpc() {}

  public static final String SERVICE_NAME = "com.edu.greenwich.managementsystem.grpc.BullyingDetectionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.edu.greenwich.managementsystem.grpc.MsgRequest,
      com.edu.greenwich.managementsystem.grpc.MsgResponse> getDetectCommentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "detectComment",
      requestType = com.edu.greenwich.managementsystem.grpc.MsgRequest.class,
      responseType = com.edu.greenwich.managementsystem.grpc.MsgResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.edu.greenwich.managementsystem.grpc.MsgRequest,
      com.edu.greenwich.managementsystem.grpc.MsgResponse> getDetectCommentMethod() {
    io.grpc.MethodDescriptor<com.edu.greenwich.managementsystem.grpc.MsgRequest, com.edu.greenwich.managementsystem.grpc.MsgResponse> getDetectCommentMethod;
    if ((getDetectCommentMethod = BullyingDetectionServiceGrpc.getDetectCommentMethod) == null) {
      synchronized (BullyingDetectionServiceGrpc.class) {
        if ((getDetectCommentMethod = BullyingDetectionServiceGrpc.getDetectCommentMethod) == null) {
          BullyingDetectionServiceGrpc.getDetectCommentMethod = getDetectCommentMethod = 
              io.grpc.MethodDescriptor.<com.edu.greenwich.managementsystem.grpc.MsgRequest, com.edu.greenwich.managementsystem.grpc.MsgResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.edu.greenwich.managementsystem.grpc.BullyingDetectionService", "detectComment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.edu.greenwich.managementsystem.grpc.MsgRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.edu.greenwich.managementsystem.grpc.MsgResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new BullyingDetectionServiceMethodDescriptorSupplier("detectComment"))
                  .build();
          }
        }
     }
     return getDetectCommentMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BullyingDetectionServiceStub newStub(io.grpc.Channel channel) {
    return new BullyingDetectionServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BullyingDetectionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new BullyingDetectionServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BullyingDetectionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new BullyingDetectionServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class BullyingDetectionServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void detectComment(com.edu.greenwich.managementsystem.grpc.MsgRequest request,
        io.grpc.stub.StreamObserver<com.edu.greenwich.managementsystem.grpc.MsgResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDetectCommentMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDetectCommentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.edu.greenwich.managementsystem.grpc.MsgRequest,
                com.edu.greenwich.managementsystem.grpc.MsgResponse>(
                  this, METHODID_DETECT_COMMENT)))
          .build();
    }
  }

  /**
   */
  public static final class BullyingDetectionServiceStub extends io.grpc.stub.AbstractStub<BullyingDetectionServiceStub> {
    private BullyingDetectionServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BullyingDetectionServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BullyingDetectionServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BullyingDetectionServiceStub(channel, callOptions);
    }

    /**
     */
    public void detectComment(com.edu.greenwich.managementsystem.grpc.MsgRequest request,
        io.grpc.stub.StreamObserver<com.edu.greenwich.managementsystem.grpc.MsgResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDetectCommentMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class BullyingDetectionServiceBlockingStub extends io.grpc.stub.AbstractStub<BullyingDetectionServiceBlockingStub> {
    private BullyingDetectionServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BullyingDetectionServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BullyingDetectionServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BullyingDetectionServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.edu.greenwich.managementsystem.grpc.MsgResponse detectComment(com.edu.greenwich.managementsystem.grpc.MsgRequest request) {
      return blockingUnaryCall(
          getChannel(), getDetectCommentMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class BullyingDetectionServiceFutureStub extends io.grpc.stub.AbstractStub<BullyingDetectionServiceFutureStub> {
    private BullyingDetectionServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BullyingDetectionServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BullyingDetectionServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BullyingDetectionServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.edu.greenwich.managementsystem.grpc.MsgResponse> detectComment(
        com.edu.greenwich.managementsystem.grpc.MsgRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDetectCommentMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DETECT_COMMENT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final BullyingDetectionServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(BullyingDetectionServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DETECT_COMMENT:
          serviceImpl.detectComment((com.edu.greenwich.managementsystem.grpc.MsgRequest) request,
              (io.grpc.stub.StreamObserver<com.edu.greenwich.managementsystem.grpc.MsgResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class BullyingDetectionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BullyingDetectionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.edu.greenwich.managementsystem.grpc.BullyingDetectionServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BullyingDetectionService");
    }
  }

  private static final class BullyingDetectionServiceFileDescriptorSupplier
      extends BullyingDetectionServiceBaseDescriptorSupplier {
    BullyingDetectionServiceFileDescriptorSupplier() {}
  }

  private static final class BullyingDetectionServiceMethodDescriptorSupplier
      extends BullyingDetectionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    BullyingDetectionServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (BullyingDetectionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BullyingDetectionServiceFileDescriptorSupplier())
              .addMethod(getDetectCommentMethod())
              .build();
        }
      }
    }
    return result;
  }
}
