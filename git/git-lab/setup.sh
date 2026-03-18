#!/bin/bash

# Da eseguire dopo aver avviato il container
docker exec -it git-server sh -c "git init --bare /repos/progetto.git && chown -R git:git /repos"

echo "Repository git creato: ssh://git@localhost:2222/repos/progetto.git"
