#!/bin/bash
# Esegui questo script prima della lezione!

KEY_PATH="$HOME/.ssh/id_git_lab"

if [ -f "$KEY_PATH" ]; then
  echo "Attenzione!!! Chiave già esistente in $KEY_PATH"
else
  ssh-keygen -t ed25519 -f "$KEY_PATH" -N "" -C "$(whoami)-git-lab"
  echo "Chiave SSH generata con successo!"
fi

echo ""
echo "La chiave SSH pubblica da aggiungere al server Git è:"
echo ""
cat "${KEY_PATH}.pub"
