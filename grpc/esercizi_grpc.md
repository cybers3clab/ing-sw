# Esercitazione gRPC 

**Corso:** Ingegneria del Software  
**Linguaggio:** Java 21 (server), Python 3.10+ (client)

---

## Esercizio 1 - Servizio `Calculator`

### Obiettivo

Implementare un servizio gRPC di calcolo aritmetico e un client Java che lo invochi. 
Al termine dell'esercizio si deve essere in grado di eseguire le quattro operazioni di base 
su due operandi inviando richieste remote attraverso il proxy generato a partire 
dal file di specifica `.proto`.


Creare il file `src/main/proto/calculator.proto` di cui viene dato il servizio Calculator:

```protobuf
syntax = "proto3";

option java_package = "it.univ.grpclab";
option java_outer_classname = "CalculatorProto";
option java_multiple_files = true;

package calculator;

service Calculator {
  rpc Add      (OperationRequest) returns (OperationResponse);
  rpc Subtract (OperationRequest) returns (OperationResponse);
  rpc Multiply (OperationRequest) returns (OperationResponse);
  rpc Divide   (OperationRequest) returns (OperationResponse);
}
```
## Cosa implementare?
**1. `CalculatorServiceImpl.java` - Real Subject**

Creare la classe nel package `it.univ.grpclab.server`. Deve estendere `CalculatorGrpc.CalculatorImplBase` e implementare i quattro metodi: `add`, `subtract`, `multiply`, `divide`.

Per ciascun metodo:
- calcolare il risultato dell'operazione corrispondente
- costruire un `OperationResponse` con il campo `result` e il campo `description` (es. `"15.0 + 4.0 = 19.0"`)
- invocare `responseObserver.onNext(response)` e `responseObserver.onCompleted()`

Per il metodo `divide`, gestire il caso in cui `operand_b` sia zero restituendo un errore gRPC con status `INVALID_ARGUMENT` e un messaggio descrittivo.

**2. `CalculatorServer.java`**

Avviare un server gRPC sulla porta `50051` registrando il servizio implementato al punto precedente. Aggiungere un hook di shutdown per la chiusura ordinata.

**3. `CalculatorClient.java`**

Aprire un canale verso `localhost:50051`, ottenere uno `CalculatorBlockingStub` 
e invocare tutte e quattro le RPC con operandi a scelta. 
Stampare la descrizione di ciascun risultato. 
Gestire l'eccezione `StatusRuntimeException` e stampare il codice di stato ricevuto.

### Verifica

L'output del client deve mostrare i risultati delle quattro operazioni e, per la divisione per zero, il messaggio di errore proveniente dal server.

---

## Esercizio 2 - Servizio `Rubrica`

### Obiettivo

Implementare un servizio gRPC per la gestione di una rubrica contatti. 
Il server Java mantiene i contatti in memoria. 
Al termine dell'esercizio si deve poter 
- aggiungere un contatto, 
- recuperarlo per nome e 
- ottenere la lista completa.

Il tutto deve poter essere fatto sia da un client Java sia da un client Python, **senza modificare il server**.

### Interfaccia `.proto`

Di seguito vengono ripartati il servizio a cui aggiungere i tipi di messaggio:

```protobuf
service Rubrica {
  rpc AddContact (Person)        returns (Result);
  rpc GetContact (NomeContatto)  returns (Person);
  rpc GetAll     (ListRequest)   returns (ContactList);
}
```

### Cosa implementare

**1. `RubricaServiceImpl.java` - Real Subject**

- mantenere i contatti in una `ConcurrentHashMap<String, Person>` con chiave `nome`
- implementare `addContact`: inserire il contatto nella mappa e rispondere con `Result` di successo; se esiste già un contatto con lo stesso nome, rispondere con `Result` di fallimento e un messaggio esplicativo (senza lanciare eccezione)
- implementare `getContact`: cercare il contatto per nome; se non trovato, restituire un errore gRPC con status `NOT_FOUND`
- implementare `getAll`: restituire una `ContactList` con tutti i contatti presenti nella mappa

**2. Aggiornare `CalculatorServer.java`**

Registrare entrambi i servizi sullo stesso server:

```java
ServerBuilder.forPort(50051)
    .addService(new CalculatorServiceImpl())
    .addService(new RubricaServiceImpl())
    ...
```

**3. `RubricaClient.java` — client Java**

Invocare in sequenza:
- `AddContact` per inserire almeno tre contatti
- `GetContact` per recuperarne uno per nome
- `GetContact` con un nome inesistente (gestire il `NOT_FOUND`)
- `GetAll` e stampare l'elenco completo

**4. `rubrica_client.py` - client Python**

Nella cartella `grpc-python-client`:

```bash
# Copiare il .proto aggiornato
cp ../grpc-proxy-lab/src/main/proto/calculator.proto .

# Rigenerare lo stub Python
python -m grpc_tools.protoc \
    --python_out=. \
    --grpc_python_out=. \
    -I. \
    calculator.proto
```

Implementare `rubrica_client.py` che, usando lo stub Python, 
esegua le stesse operazioni del client Java: inserimento di almeno due contatti, 
ricerca per nome, stampa della lista completa.
