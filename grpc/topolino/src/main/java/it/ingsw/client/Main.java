package it.ingsw.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import it.ingsw.proto.CalculatorGrpc;
import it.ingsw.proto.OperationRequest;
import it.ingsw.proto.OperationResponse;

public class Main {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50010)
                .usePlaintext()
                .build();

        CalculatorGrpc.CalculatorBlockingStub stub =
                CalculatorGrpc.newBlockingStub(channel);

        OperationRequest request = OperationRequest.newBuilder()
                .setOpA(1)
                .setOpB(2)
                .build();
        OperationResponse or = stub.add(request);
        System.out.println("Risultato addizione:" + or.getRes());


        channel.shutdown();
    }
}