package torreDiControllo;

public class ATCDemo {
    public static void main(String[] args) {
        ControlTower towerMediator = new ControlTower();

        Airbus f1 = new Airbus(towerMediator, "AZ123");
        Boeing f2 = new Boeing(towerMediator, "BA789");

        towerMediator.registerFlight(f1);
        towerMediator.registerFlight(f2);

        f1.land(); // Primo aereo: atterra
        f2.land(); // Secondo aereo: viene fermato dalla torre
    }
}
