package proxies.caching;

import proxies.common.DataService;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Proxy Cache - memoizzazione con TTL (Time-To-Live).
 *
 * Intercetta le chiamate a {@code fetchData}: se il risultato è già
 * in cache e non è scaduto, lo restituisce senza interrogare il RealSubject.
 * Le operazioni di scrittura ({@code saveData}) invalidano la voce
 * corrispondente per mantenere la coerenza.
 */
public class CachingProxyDataService implements DataService {

    private final DataService realService;
    private final long ttlSeconds;


    /** Struttura interna della cache: query -> voce con valore e timestamp. */
    private final Map<String, CacheEntry> cache = new HashMap<>();


    /**
     * Voce della cache: incapsula valore e timestamp di creazione.
     * Il record è immutabile per design: ogni aggiornamento crea una nuova voce.
     */
    private record CacheEntry(String value, Instant createdAt) {

        /**
         * @param ttlSeconds secondi di validità della voce
         * @return true se la voce è scaduta
         */
        boolean isExpired(long ttlSeconds) {
            return Instant.now().isAfter(createdAt.plusSeconds(ttlSeconds));
        }
    }


    /**
     * @param realService il RealSubject a cui delegare in caso di cache miss
     * @param ttlSeconds  durata in secondi di ogni voce in cache
     */
    public CachingProxyDataService(DataService realService, long ttlSeconds) {
        this.realService = realService;
        this.ttlSeconds  = ttlSeconds;
    }


    @Override
    public String fetchData(String query) {
        CacheEntry entry = cache.get(query);

        if (entry != null && !entry.isExpired(ttlSeconds)) {
            // Cache HIT: restituisco il risultato senza chiamare il DB
            System.out.println("  [CacheProxy] HIT  -> \"" + query + "\" (scade tra "
                    + remainingSeconds(entry) + "s)");
            return entry.value();
        }

        // Cache MISS: delegare al RealSubject e memorizzare il risultato
        String reason = (entry == null) ? "non presente" : "scaduta";
        System.out.println("  [CacheProxy] MISS -> \"" + query + "\" (" + reason
                + ") -> chiamo il RealSubject");

        String result = realService.fetchData(query);

        // Salvo il risultato in cache con il timestamp corrente
        cache.put(query, new CacheEntry(result, Instant.now()));
        System.out.println("  [CacheProxy] Risultato memorizzato in cache (TTL=" + ttlSeconds + "s)");

        return result;
    }


    @Override
    public void saveData(String key, String value) {
        // Invalida la cache per la chiave modificata: garantisce consistenza
        if (cache.containsKey(key)) {
            cache.remove(key);
            System.out.println("  [CacheProxy] Cache invalidata per chiave \"" + key + "\"");
        }
        realService.saveData(key, value);
    }


    /** Numero di voci attualmente in cache (comprese le scadute non ancora evisse). */
    public int cacheSize() {
        return cache.size();
    }


    /** Svuota l'intera cache. */
    public void clearCache() {
        cache.clear();
        System.out.println("  [CacheProxy] Cache svuotata.");
    }


    /** Rimuove esplicitamente le voci scadute (eviction manuale). */
    public void evictExpired() {
        int before = cache.size();
        cache.entrySet().removeIf(e -> e.getValue().isExpired(ttlSeconds));
        int removed = before - cache.size();
        System.out.println("  [CacheProxy] Eviction: rimossi " + removed + " elementi scaduti.");
    }


    private long remainingSeconds(CacheEntry entry) {
        long elapsed = Instant.now().getEpochSecond() - entry.createdAt().getEpochSecond();
        return Math.max(0, ttlSeconds - elapsed);
    }
}
