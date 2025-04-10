package it.unical.chat.client;

import io.grpc.*;
import it.unical.proto.GreeterGrpc;
import it.unical.proto.HelloReply;
import it.unical.proto.HelloRequest;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ClientChatMain {
    private final GreeterGrpc.GreeterBlockingStub blockingStub;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public ClientChatMain(Channel channel) {
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
        ClientChatMain clientMain = new ClientChatMain(channel);

        String response = "start";
        while(!response.equals("end")) {
            System.out.print(ANSI_RED + "clientMessage: ");
            Scanner sc = new Scanner(System.in);
            String clientMessage = "clientMessage: " + sc.nextLine();
            response = clientMain.greet(clientMessage);
            System.out.println(ANSI_GREEN + response);
        }



        channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }
}
