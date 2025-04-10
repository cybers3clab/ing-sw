package it.unical.server;

import io.grpc.stub.StreamObserver;
import it.unical.proto.GreeterGrpc;
import it.unical.proto.HelloReply;
import it.unical.proto.HelloRequest;

class GreeterImpl extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        String message = "Sono gRPC su Java, Ciao " + request.getName();
        HelloReply reply = HelloReply
                .newBuilder()
                .setMessage(message)
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}
