package incrocio;

public class TestIncrocio {
    public static void main(String[] args) {
        IncrocioCentrale incrocio = new IncrocioCentrale();

        Semaforo nord  = new Semaforo("Nord",  StatoSemaforo.VERDE, incrocio);
        Semaforo sud   = new Semaforo("Sud",   StatoSemaforo.VERDE, incrocio);
        Semaforo est   = new Semaforo("Est",   StatoSemaforo.ROSSO, incrocio);
        Semaforo ovest = new Semaforo("Ovest", StatoSemaforo.ROSSO, incrocio);

        incrocio.setNord(nord);
        incrocio.setSud(sud);
        incrocio.setEst(est);
        incrocio.setOvest(ovest);

        System.out.println("=== Nord diventa ROSSO ===");
        nord.cambiaStato(StatoSemaforo.ROSSO);

        System.out.println("=== Est diventa VERDE ===");
        est.cambiaStato(StatoSemaforo.VERDE);
    }
}
