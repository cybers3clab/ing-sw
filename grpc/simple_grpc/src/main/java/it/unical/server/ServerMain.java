package it.unical.server;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ServerMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50051;
        Server server = Grpc
                .newServerBuilderForPort(
                        port,
                        InsecureServerCredentials.create()
                )
                .addService( new GreeterImpl())
                .build()
                .start();

        System.out.println("Server started, listening on port " + port);

        server.awaitTermination();
    }
}