import os
import sys
from grpc_tools import protoc


def compile_proto(base_path):

    out_dir = os.path.join("src")
    os.makedirs(out_dir, exist_ok=True)

    proto_files = [os.path.join(base_path, f) for f in os.listdir(base_path) if f.endswith('.proto')]
    if not proto_files:
        raise FileNotFoundError(f"No .proto files found in the directory {base_path}")

    for proto_file in proto_files:
        command = [
            'grpc_tools.protoc',
            f'--proto_path={base_path}',
            f'--python_out={out_dir}',
            f'--grpc_python_out={out_dir}',
            proto_file
        ]

        if protoc.main(command) != 0:
            raise Exception(f"Error: Protobuf compilation failed for {proto_file}")
        else:
            print(f"{proto_file} has been successfully compiled.")


if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Usage: python compile_proto.py <proto_base_path>")
    else:
        proto_base_path = sys.argv[1]
        if not os.path.isdir(proto_base_path):
            print(f"The provided path {proto_base_path} is not a valid directory.")
        else:
            compile_proto(proto_base_path)
