package it.unical.chat.server;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;

import java.io.IOException;
import java.util.Scanner;

import io.grpc.stub.StreamObserver;
import it.unical.proto.GreeterGrpc;
import it.unical.proto.HelloReply;
import it.unical.proto.HelloRequest;

class GreeterChatImpl extends GreeterGrpc.GreeterImplBase {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";



    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {

        String clientMessage = request.getName();
        System.out.println(ANSI_RED + clientMessage);

        if(clientMessage.equals("clientMessage: exit")){
            String serverMessage = "end";
            HelloReply reply = HelloReply
                    .newBuilder()
                    .setMessage(serverMessage)
                    .build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        } else {
            System.out.print(ANSI_GREEN + "serverMessage: ");
            Scanner sc = new Scanner(System.in);
            String serverMessage = "serverMessage: " + sc.nextLine();
            HelloReply reply = HelloReply
                    .newBuilder()
                    .setMessage(serverMessage)
                    .build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

    }

}

public class ServerChatMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50051;
        Server server = Grpc
                .newServerBuilderForPort(
                        port,
                        InsecureServerCredentials.create()
                )
                .addService( new GreeterChatImpl())
                .build()
                .start();

        System.out.println("Server started, listening on port " + port);

        server.awaitTermination();
    }
}