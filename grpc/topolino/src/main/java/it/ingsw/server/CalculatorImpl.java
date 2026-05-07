package it.ingsw.server;


import io.grpc.stub.StreamObserver;
import it.ingsw.proto.CalculatorGrpc;
import it.ingsw.proto.OperationRequest;
import it.ingsw.proto.OperationResponse;

public class CalculatorImpl extends CalculatorGrpc.CalculatorImplBase {

    @Override
    public void add(OperationRequest request, StreamObserver<OperationResponse> responseObserver) {
        int res = request.getOpA() + request.getOpB();
        OperationResponse or = OperationResponse.newBuilder().setRes(res).build();

        responseObserver.onNext(or);
        responseObserver.onCompleted();
    }

    @Override
    public void subtract(OperationRequest request, StreamObserver<OperationResponse> responseObserver) {
        int res = request.getOpA() - request.getOpB();
        OperationResponse or = OperationResponse.newBuilder().setRes(res).build();

        responseObserver.onNext(or);
        responseObserver.onCompleted();
    }

    @Override
    public void multiply(OperationRequest request, StreamObserver<OperationResponse> responseObserver) {
        int res = request.getOpA() * request.getOpB();
        OperationResponse or = OperationResponse.newBuilder().setRes(res).build();

        responseObserver.onNext(or);
        responseObserver.onCompleted();
    }

    @Override
    public void divide(OperationRequest request, StreamObserver<OperationResponse> responseObserver) {
        if (request.getOpB() == 0){
            responseObserver.onNext(null);
            responseObserver.onCompleted();
            return;
        }
        int res = request.getOpA() / request.getOpB();
        OperationResponse or = OperationResponse.newBuilder().setRes(res).build();

        responseObserver.onNext(or);
        responseObserver.onCompleted();
    }
}
