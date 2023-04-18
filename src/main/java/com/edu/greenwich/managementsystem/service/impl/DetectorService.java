package com.edu.greenwich.managementsystem.service.impl;

import com.edu.greenwich.managementsystem.grpc.BullyingDetectionServiceGrpc;
import com.edu.greenwich.managementsystem.grpc.MsgRequest;
import com.edu.greenwich.managementsystem.grpc.MsgResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class DetectorService {

    @GrpcClient("grpc-greenwich-service")
    BullyingDetectionServiceGrpc.BullyingDetectionServiceBlockingStub syncClient;
    public String detectComment(String comment) {
        MsgRequest msgRequest = MsgRequest.newBuilder().setMessage(comment).build();
        MsgResponse predictedResult = syncClient.detectComment(msgRequest);
        return predictedResult.getResponse();
    }
}
