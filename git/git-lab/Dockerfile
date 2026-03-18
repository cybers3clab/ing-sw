FROM alpine:3.19

RUN apk add --no-cache git openssh

# Questo passo serve per creare l'utente git, che sarà utilizzato per accedere al server Git. 
# L'utente avrà una shell limitata (git-shell) per motivi di sicurezza, e la password sarà impostata su "git123". 
# In un ambiente di produzione, è consigliabile utilizzare chiavi SSH invece di password per l'autenticazione.
RUN adduser -D -s /usr/bin/git-shell git && \
    echo "git:git123" | chpasswd

# Configurazione di SSH
RUN ssh-keygen -A && \
    mkdir -p /home/git/.ssh && \
    chmod 700 /home/git/.ssh

# Copia delle chiavi pubbliche degli studenti
COPY studente/authorized_keys /home/git/.ssh/authorized_keys

# Impostazione dei permessi corretti per la cartella .ssh e il file authorized_keys.
RUN chmod 600 /home/git/.ssh/authorized_keys && \
    chown -R git:git /home/git/.ssh

# Crea cartella repos per i repository Git e assegna i permessi all'utente git.
RUN mkdir -p /repos && chown git:git /repos

# Espone la porta 22 per SSH e avvia il servizio SSH in modalità foreground.
EXPOSE 22
CMD ["/usr/sbin/sshd", "-D", "-e"]
