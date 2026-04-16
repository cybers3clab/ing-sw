package chatRoom;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom implements ChatMediator {
    private final List<Utente> utenti = new ArrayList<>();

    @Override
    public void aggiungiUtente(Utente utente) {
        utenti.add(utente);
    }

    @Override
    public void inviaMessaggio(String messaggio, Utente mittente) {
        for (Utente u : utenti) {
            // il messaggio non viene recapitato al mittente stesso
            if (u != mittente) {
                u.riceviMessaggio(messaggio, mittente.nome());
            }
        }
    }

}
