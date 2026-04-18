package prontoSoccorso;

public class AbstractHandler implements Handler{
    private Handler next;

    @Override
    public Handler setNext(Handler next) {
        this.next = next;
        return next;
    }

    public boolean handle(Paziente paziente){
        if (next != null) return next.handle(paziente);
        return false;
    }
}
