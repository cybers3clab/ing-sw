import grpc
import Greeter_pb2 as Action
import Greeter_pb2_grpc as Service


def run():
    with grpc.insecure_channel('localhost:50051') as channel:
        stub = Service.GreeterStub(channel)
        name = input("Digite il tuo nome: ")
        while True:
            message = input("> ")
            if "exit" in message:
                exit(0)
            message = name + ": " + message
            response = stub.SayHello(Action.HelloRequest(name=message))
            print(response.message)


if __name__ == '__main__':
    run()
