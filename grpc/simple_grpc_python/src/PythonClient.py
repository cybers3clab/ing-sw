import grpc
import Greeter_pb2 as Action
import Greeter_pb2_grpc as Service


def run():
    with grpc.insecure_channel('localhost:50051') as channel:
        name = input("Inserisci il tuo nome: ")
        print("Invio il tuo nome al server gRPC\n")

        stub = Service.GreeterStub(channel)
        response = stub.SayHello(Action.HelloRequest(name=name))

        print("Il server gRPC dice: " + response.message)


if __name__ == '__main__':
    run()
