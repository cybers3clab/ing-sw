package proxies.common;

/**
 * Subject - interfaccia comune per RealSubject e tutti i Proxy.
 */
public interface DataService {

    /**
     * Recupera dati in base a una query (es. SQL, chiave, URL...).
     *
     * @param query la stringa di ricerca / query
     * @return il risultato come stringa
     */
    String fetchData(String query);

    /**
     * Persiste un valore associato a una chiave.
     *
     * @param key   la chiave
     * @param value il valore da salvare
     */
    void saveData(String key, String value);
}
