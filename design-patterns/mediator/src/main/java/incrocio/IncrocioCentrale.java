package incrocio;

public class IncrocioCentrale implements ControlloreTraffico {
    private Semaforo nord, sud, est, ovest;

    public void setNord(Semaforo s) {
        this.nord = s;
    }
    public void setSud(Semaforo s) {
        this.sud = s;
    }
    public void setEst(Semaforo s) {
        this.est = s;
    }
    public void setOvest(Semaforo s) {
        this.ovest = s;
    }

    @Override
    public void semaforoCambiato(Semaforo semaforoCheHaCambiato) {
        // Regola: Nord/Sud e Est/Ovest sono sempre opposti
        if (semaforoCheHaCambiato == nord || semaforoCheHaCambiato == sud) {
            StatoSemaforo opposto = opposto(semaforoCheHaCambiato.getStato());
            est.impostaStato(opposto);
            ovest.impostaStato(opposto);
            // sincronizza l'asse Nord/Sud
            if (semaforoCheHaCambiato == nord)
                sud.impostaStato(semaforoCheHaCambiato.getStato());
            else
                nord.impostaStato(semaforoCheHaCambiato.getStato());
        } else {
            StatoSemaforo opposto = opposto(semaforoCheHaCambiato.getStato());
            nord.impostaStato(opposto);
            sud.impostaStato(opposto);
            if (semaforoCheHaCambiato == est)
                ovest.impostaStato(semaforoCheHaCambiato.getStato());
            else
                est.impostaStato(semaforoCheHaCambiato.getStato());
        }
        System.out.println("---");
    }

    private StatoSemaforo opposto(StatoSemaforo s) {
        return s == StatoSemaforo.VERDE ? StatoSemaforo.ROSSO : StatoSemaforo.VERDE;
    }
}

