package proxies.remote;

/**
 * Subject per il Proxy Remoto.
 *
 * Il client usa questa interfaccia locale senza sapere se il servizio
 * è in-process o remoto (HTTP, RMI, gRPC, ecc.).
 */
public interface WeatherService {

    /**
     * Restituisce la temperatura corrente per la città specificata.
     *
     * @param city nome della città (es. "Milano")
     * @return temperatura come stringa (es. "22°C")
     */
    String getCurrentTemperature(String city);

    /**
     * Restituisce le previsioni meteo per i prossimi giorni.
     *
     * @param city nome della città
     * @param days numero di giorni di previsione (1-7)
     * @return stringa con il riepilogo delle previsioni
     */
    String getForecast(String city, int days);
}
