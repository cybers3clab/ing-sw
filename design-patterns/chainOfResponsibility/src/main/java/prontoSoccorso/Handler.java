package prontoSoccorso;

public interface Handler {
    boolean handle(Paziente paziente);
    Handler setNext(Handler next);
}
