package torreDiControllo;

public class Boeing extends Flight {
    public Boeing(ATC towerMediator, String id) { super(towerMediator, id); }

    public void land() {
        System.out.println("Boeing " + flightNumber + ": In fase di avvicinamento per atterraggio.");
        towerMediator.requestLanding(this);
    }

    @Override
    public void stop() {
        System.out.println("Boeing " + flightNumber + ": Ricevuto, mantengo quota di sicurezza.");
    }
}
