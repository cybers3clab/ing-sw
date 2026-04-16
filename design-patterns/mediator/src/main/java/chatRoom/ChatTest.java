package chatRoom;

public class ChatTest {
    public static void main(String[] args) {
        ChatMediator chat = new ChatRoom();

        Utente alice = new Utente("Alice", chat);
        Utente bob = new Utente("Bob", chat);
        Utente carlo = new Utente("Carlo", chat);

        chat.aggiungiUtente(alice);
        chat.aggiungiUtente(bob);
        chat.aggiungiUtente(carlo);

        alice.inviaMessaggio("Ciao a tutti!");
        bob.inviaMessaggio("Ciao Alice!");
    }
}
