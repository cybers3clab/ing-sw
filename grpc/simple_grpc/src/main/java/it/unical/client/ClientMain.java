package it.unical.client;

import io.grpc.*;
import it.unical.proto.GreeterGrpc;
import it.unical.proto.HelloReply;
import it.unical.proto.HelloRequest;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ClientMain {
    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    public ClientMain(Channel channel) {
        this.blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public String greet(String name) {
        HelloRequest request = HelloRequest
                .newBuilder()
                .setName(name)
                .build();

        HelloReply response = blockingStub.sayHello(request);

        return response.getMessage();
    }

    public static void main(String[] args) throws Exception {
        String target = "localhost:50051";
        ManagedChannel channel = Grpc
                .newChannelBuilder(
                        target,
                        InsecureChannelCredentials.create()
                )
                .build();
        ClientMain clientMain = new ClientMain(channel);

        System.out.println("Inserisci il tuo nome");
        String user = new Scanner(System.in).nextLine();
        System.out.println("Invio il tuo nome al server gRPC");

        String response = clientMain.greet(user);
        System.out.println("Il server gRPC dice: " + response);

        channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }
}
