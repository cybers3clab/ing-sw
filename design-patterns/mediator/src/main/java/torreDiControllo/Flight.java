package torreDiControllo;

public abstract class Flight {
    protected ATC towerMediator;
    protected String flightNumber;

    public Flight(ATC towerMediator, String flightNumber) {
        this.towerMediator = towerMediator;
        this.flightNumber = flightNumber;
    }
    public abstract void stop();
}
