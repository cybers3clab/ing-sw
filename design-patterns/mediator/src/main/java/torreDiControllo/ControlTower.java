package torreDiControllo;

import java.util.ArrayList;
import java.util.List;

class ControlTower implements ATC {
    private final List<Flight> flights = new ArrayList<>();
    private boolean runwayFree = true;

    @Override
    public void registerFlight(Flight flight) { flights.add(flight); }

    @Override
    public void requestLanding(Flight sender) {
        if (runwayFree) {
            System.out.println("TORRE: Pista libera per " + sender.flightNumber);
            runwayFree = false;
        } else {
            System.out.println("TORRE: Pista occupata! Negato a " + sender.flightNumber);
            sender.stop();
        }
    }
}
