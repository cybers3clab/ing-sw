package torreDiControllo;

public class Airbus extends Flight {
    public Airbus(ATC towerMediator, String id) { super(towerMediator, id); }

    public void land() {
        System.out.println("Airbus " + flightNumber + ": Richiedo autorizzazione atterraggio.");
        towerMediator.requestLanding(this);
    }

    @Override
    public void stop() {
        System.out.println("Airbus " + flightNumber + ": Ricevuto, resto in attesa fuori pista.");
    }
}
