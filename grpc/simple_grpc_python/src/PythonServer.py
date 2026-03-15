from concurrent import futures
import grpc
import Greeter_pb2 as Action
import Greeter_pb2_grpc as Service


class Greeter(Service.GreeterServicer):
    def SayHello(self, request, context):
        return Action.HelloReply(message="Sono gRPC su python, Ciao " + request.name)


port = "50051"
server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
Service.add_GreeterServicer_to_server(Greeter(), server)
server.add_insecure_port("localhost:"+port)
server.start()
print(f"Listening on port {port}")
server.wait_for_termination()