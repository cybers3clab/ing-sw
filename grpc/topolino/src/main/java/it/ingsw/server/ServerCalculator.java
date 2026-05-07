package it.ingsw.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class ServerCalculator {
    public static void main(String[] args) {
        try {
            Server server = ServerBuilder
                    .forPort(50010)
                    .addService(new CalculatorImpl())
                    .build()
                    .start();

            System.out.println("Server gRPC avviato sulla porta 50010");
            System.out.println("In ascolto di chiamate remote...");

            // Chiusura elegante all'interrupt
            Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));

            server.awaitTermination();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
