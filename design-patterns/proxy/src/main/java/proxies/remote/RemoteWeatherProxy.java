package proxies.remote;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * Proxy Remoto - astrazione della comunicazione di rete.
 * <p>
 * Il client usa l'interfaccia {@link WeatherService} come se il servizio
 * fosse locale. Tutta la logica HTTP (costruzione URL, serializzazione,
 * gestione timeout, retry, errori di rete) è incapsulata qui.
 *
 * <p>In questa implementazione didattica:</p>
 * <ul>
 *   <li>Se la chiamata HTTP riesce (server disponibile) usa la risposta remota.</li>
 *   <li>Se il server non è raggiungibile, cade in un fallback locale
 *       (utile per eseguire la demo senza rete).</li>
 * </ul>
 *
 * Usa {@code java.net.http.HttpClient} introdotto in Java 11 e stabile in Java 21.
 */
public class RemoteWeatherProxy implements WeatherService {

    private final String baseUrl;
    private final HttpClient httpClient;

    /** Fallback locale usato in assenza di connessione (solo per la demo). */
    private final WeatherService localFallback = new LocalWeatherService();

    /**
     * @param baseUrl URL base del servizio remoto (es. "https://api.meteo.example.com")
     */
    public RemoteWeatherProxy(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .build();
    }


    // Implementazione di WeatherService - logica HTTP nascosta al client
    @Override
    public String getCurrentTemperature(String city) {
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String url = baseUrl + "/temperature?city=" + encodedCity;

        System.out.println("  [RemoteProxy] GET " + url);

        try {
            return doRequesst(url);
        } catch (Exception e) {
            // In un sistema reale: logging strutturato + retry con backoff
            System.out.println("  [RemoteProxy] Server non raggiungibile ("
                    + e.getClass().getSimpleName() + ") -> uso fallback locale");
            return localFallback.getCurrentTemperature(city);
        }
    }

    @Override
    public String getForecast(String city, int days) {
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String url = baseUrl + "/forecast?city=" + encodedCity + "&days=" + days;

        System.out.println("  [RemoteProxy] GET " + url);

        try {
            return doRequesst(url);

        } catch (Exception e) {
            System.out.println("  [RemoteProxy] Server non raggiungibile -> uso fallback locale");
            return localFallback.getForecast(city, days);
        }
    }

    private String doRequesst(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(
                request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("  [RemoteProxy] Risposta HTTP 200 OK");
            return response.body();
        }
        throw new RuntimeException("HTTP error: " + response.statusCode());
    }
}
