package proxies.remote;

import java.util.Map;
import java.util.Random;

/**
 * RealSubject locale usato come stand-in del server remoto nella demo.
 *
 * In un'applicazione reale questo codice girerebbe su un server separato
 * (REST API, microservizio, ecc.) e il client non ne avrebbe visibilità.
 * Qui è locale per poter eseguire la demo senza connessione di rete.
 */
public class LocalWeatherService implements WeatherService {

    private static final Map<String, Integer> BASE_TEMPS = Map.of(
            "milano",  18,
            "roma",    22,
            "napoli",  24,
            "torino",  16,
            "bologna", 19
    );

    private final Random rnd = new Random(42);

    @Override
    public String getCurrentTemperature(String city) {
        int base = BASE_TEMPS.getOrDefault(city.toLowerCase(), 20);
        int temp = base + rnd.nextInt(5) - 2;   // variazione ±2°C
        return temp + "°C";
    }

    @Override
    public String getForecast(String city, int days) {
        if (days < 1 || days > 7) {
            throw new IllegalArgumentException("days deve essere compreso tra 1 e 7");
        }
        String[] condizioni = {"Soleggiato", "Nuvoloso", "Pioggia", "Temporale", "Sereno"};
        StringBuilder sb = new StringBuilder("Previsioni per " + city + ": ");
        for (int i = 0; i < days; i++) {
            if (i > 0) sb.append(", ");
            sb.append("G").append(i + 1).append("=").append(condizioni[rnd.nextInt(condizioni.length)]);
        }
        return sb.toString();
    }
}
