# ISTRUZIONI
___

## Creazione virtual environment

    python3 -m venv <nome_virtual_env>

## Impostazione virtual env
    
### Windows:

    .\<nome_virtual_env>\Scripts\activate

Se ci fosse qualche problema con l'attivazione del virtual-env su 
windows, probabilmente le execution policy sono settate in maniera 
troppo restrittiva, per risolvere, 
su un terminale avviato come amministratore, scrivere:

     Set-ExecutionPolicy Bypass

### Unix-based (Linux/MacOS)

    . ./<nome_virtual_env>/bin/activate
___

## Installazione librerie

    pip install -r requirements.txt

___

## Compilare file .proto

    python compile_proto.py <proto_base_path>
___

Per ulteriori informazioni in merito al framework gRPC 
fare riferimento alla [documentazione](https://grpc.io/docs/)
ufficiale