package proxies.virtual;

import proxies.common.DataService;
import proxies.common.DatabaseService;

/**
 * Proxy Virtuale (Lazy Initialization).
 *
 * Rimanda la creazione del RealSubject (DatabaseService) fino alla prima
 * chiamata effettiva. Utile quando l'oggetto reale è costoso da costruire
 * e potrebbe non essere necessario per ogni esecuzione del programma.
 *
 * <p>Struttura GoF:</p>
 * <pre>
 *   Client --> VirtualProxyDataService (Proxy) --> DatabaseService (RealSubject)
 *                    implements DataService              implements DataService
 * </pre>
 */
public class VirtualProxyDataService implements DataService {
    /**
     * Riferimento al RealSubject: null finché non viene richiesto.
     * La parola chiave {@code volatile} garantisce la visibilità
     * corretta in ambienti multi-thread (Java Memory Model).
     */
    private volatile DatabaseService realService = null;

    // Lazy initialization thread-safe (Double-Checked Locking)
    /**
     * Restituisce il RealSubject, creandolo se non ancora inizializzato.
     * Il Double-Checked Locking evita la sincronizzazione costosa
     * ad ogni accesso, limitandola solo alla prima creazione.
     *
     * @return l'istanza di DatabaseService
     */
    private DatabaseService getRealService() {
        if (realService == null) {                      // 1° controllo: senza lock
            synchronized (this) {
                if (realService == null) {              // 2° controllo: con lock
                    System.out.println("  [VirtualProxy] Prima richiesta ricevuta -> istanzio il RealSubject");
                    realService = new DatabaseService();
                }
            }
        }
        return realService;
    }

    // Implementazione di DataService -> delega al RealSubject (lazy)
    @Override
    public String fetchData(String query) {
        System.out.println("  [VirtualProxy] fetchData intercettato");
        return getRealService().fetchData(query);
    }

    @Override
    public void saveData(String key, String value) {
        System.out.println("  [VirtualProxy] saveData intercettato");
        getRealService().saveData(key, value);
    }
    
    /** Restituisce true se il RealSubject è già stato inizializzato. */
    public boolean isInitialized() {
        return realService != null;
    }
}
