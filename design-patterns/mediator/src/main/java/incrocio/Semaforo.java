package incrocio;

public class Semaforo {
    private final String nome;
    private StatoSemaforo stato;
    private final ControlloreTraffico controllore;

    public Semaforo(String nome,
                    StatoSemaforo statoIniziale,
                    ControlloreTraffico controllore) {
        this.nome = nome;
        this.stato = statoIniziale;
        this.controllore = controllore;
    }

    public void cambiaStato(StatoSemaforo nuovoStato) {
        this.stato = nuovoStato;
        System.out.println(nome + " → " + stato);
        controllore.semaforoCambiato(this); // notifica il mediator
    }

    public void impostaStato(StatoSemaforo stato) {
        // usato dal mediator per aggiornare senza ri-notificare
        this.stato = stato;
        System.out.println("  [sync] " + nome + " → " + stato);
    }

    public StatoSemaforo getStato() {
        return stato;
    }

    public String getNome() {
        return nome;
    }
}

