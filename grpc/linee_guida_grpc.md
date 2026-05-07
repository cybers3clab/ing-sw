# Linee guida gRPC
___

### Set up file .proto
syntax = "proto3";

option java_package = "it.ingsw";
option java_outer_classname = "NameProto";
option java_multiple_files = true;

package namePackage;
___
### Setup Server
Di seguito il codice necessario per avviare un server gRPC:

    Server server = ServerBuilder
    .forPort(PORT)
    .addService(<concreteService1>)
    .addService(<concreteService2>)
    .build()
    .start();

    System.out.println("Server gRPC avviato sulla porta " + PORT);
    System.out.println("In ascolto di chiamate remote...");

    // Chiusura elegante all'interrupt
    Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));
    server.awaitTermination();
        
### Setup Client
Di seguito il codice necessario per avviare un client gRPC:

```java
    ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

    //    the client code goes here 
    //
    //

    channel.shutdown();
```

___
### Setup pom.xml
Di seguito le dipendenze necessarie da aggiungere al file pom.xml:

```xml
<properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <grpc.version>1.75.0</grpc.version>
        <protobuf.version>3.25.3</protobuf.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>io.grpc</groupId>
            <artifactId>grpc-netty-shaded</artifactId>
            <version>${grpc.version}</version>
        </dependency>
        <dependency>
            <groupId>io.grpc</groupId>
            <artifactId>grpc-protobuf</artifactId>
            <version>${grpc.version}</version>
        </dependency>
        <dependency>
            <groupId>io.grpc</groupId>
            <artifactId>grpc-stub</artifactId>
            <version>${grpc.version}</version>
        </dependency>
        <!-- Necessario per @javax.annotation.Generated -->
        <dependency>
            <groupId>org.apache.tomcat</groupId>
            <artifactId>annotations-api</artifactId>
            <version>6.0.53</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>

    <build>
    <extensions>
        <!-- Rileva l'OS per scaricare il protoc corretto -->
        <extension>
            <groupId>kr.motd.maven</groupId>
            <artifactId>os-maven-plugin</artifactId>
            <version>1.7.1</version>
        </extension>
    </extensions>

    <plugins>
        <plugin>
            <groupId>org.xolstice.maven.plugins</groupId>
            <artifactId>protobuf-maven-plugin</artifactId>
            <version>0.6.1</version>
            <configuration>
                <protocArtifact>
                    com.google.protobuf:protoc:${protobuf.version}:exe:${os.detected.classifier}
                </protocArtifact>
                <pluginId>grpc-java</pluginId>
                <pluginArtifact>
                    io.grpc:protoc-gen-grpc-java:${grpc.version}:exe:${os.detected.classifier}
                </pluginArtifact>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>compile</goal>
                        <goal>compile-custom</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
    </build>
```
