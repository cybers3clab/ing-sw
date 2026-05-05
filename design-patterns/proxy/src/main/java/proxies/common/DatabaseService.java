package proxies.common;

import java.util.HashMap;
import java.util.Map;

/**
 * RealSubject - implementazione reale del DataService.
 * Simula un accesso a database: il costruttore è lento (simula apertura
 * connessione) e ogni operazione stampa cosa fa effettivamente.
 */
public class DatabaseService implements DataService {

    /** Semplice store in-memory per simulare persistenza. */
    private final Map<String, String> store = new HashMap<>();

    /**
     * Costruttore costoso: simula l'apertura di una connessione al DB.
     * In produzione qui ci sarebbero pool di connessioni, handshake TLS, ecc.
     */
    public DatabaseService() {
        System.out.println("  [DatabaseService] >>> Connessione al database in corso...");
        simulaOperazioneCostosa(400);
        System.out.println("  [DatabaseService] >>> Connessione stabilita.\n");

        // Dati di esempio
        store.put("utenti",   "Mario Rossi, Lucia Bianchi, Carlo Verdi");
        store.put("prodotti", "Laptop, Mouse, Tastiera");
        store.put("ordini",   "ORD-001, ORD-002, ORD-003");
    }

    @Override
    public String fetchData(String query) {
        System.out.println("  [DatabaseService] fetchData(\"" + query + "\")");
        simulaOperazioneCostosa(100);

        // Ricerca: se la query contiene una chiave nota, la restituisce
        for (Map.Entry<String, String> entry : store.entrySet()) {
            if (query.toLowerCase().contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "Nessun risultato per: " + query;
    }

    @Override
    public void saveData(String key, String value) {
        System.out.println("  [DatabaseService] saveData(\"" + key + "\", \"" + value + "\")");
        simulaOperazioneCostosa(80);
        store.put(key, value);
        System.out.println("  [DatabaseService] Dati salvati con successo.");
    }

    /** Simula latenza I/O (usare solo a scopo didattico). */
    private void simulaOperazioneCostosa(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
