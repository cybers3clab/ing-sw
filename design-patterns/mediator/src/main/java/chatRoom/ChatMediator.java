package chatRoom;

public interface ChatMediator {
    void inviaMessaggio(String messaggio, Utente mittente);
    void aggiungiUtente(Utente utente);
}
