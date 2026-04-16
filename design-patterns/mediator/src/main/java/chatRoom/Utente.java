package chatRoom;

public record Utente (String nome, ChatMediator mediator){

    public void inviaMessaggio(String messaggio) {
        System.out.println("[" + nome + "] invia: " + messaggio);
        mediator.inviaMessaggio(messaggio, this);
    }

    public void riceviMessaggio(String messaggio, String mittente) {
        System.out.println("[" + nome + "] riceve da " + mittente + ": " + messaggio);
    }
}
