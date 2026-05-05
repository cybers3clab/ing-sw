package proxies.demo;

import proxies.caching.CachingProxyDataService;
import proxies.common.DataService;
import proxies.common.DatabaseService;
import proxies.protection.ProtectionProxyDataService;
import proxies.protection.Role;
import proxies.protection.SecurityContext;
import proxies.remote.RemoteWeatherProxy;
import proxies.remote.WeatherService;
import proxies.virtual.VirtualProxyDataService;

/**
 * Classe demo che esegue tutti gli esempi del Pattern Proxy.
 *
 */
public class Main {

    public static void main(String[] args) {
        //demoVirtualProxy();
        demoProtectionProxy();
        //demoRemoteProxy();
        //demoCachingProxy();
    }


    // 1. PROXY VIRTUALE

    private static void demoVirtualProxy() {
        titolo("1. PROXY VIRTUALE - Lazy Initialization");

        VirtualProxyDataService proxy = new VirtualProxyDataService();

        System.out.println("Proxy creato. Il DatabaseService NON è ancora inizializzato.");
        System.out.println("isInitialized() = " + proxy.isInitialized());
        separatore();

        System.out.println("Prima chiamata a fetchData -> il DB viene inizializzato ora:");
        String result1 = proxy.fetchData("utenti");
        System.out.println("  Risultato: " + result1);
        System.out.println("isInitialized() = " + proxy.isInitialized());
        separatore();

        System.out.println("Seconda chiamata -> il DB è già pronto, nessuna re-inizializzazione:");
        String result2 = proxy.fetchData("prodotti");
        System.out.println("  Risultato: " + result2);
        separatore();

        System.out.println("Scrittura tramite proxy:");
        proxy.saveData("categorie", "Elettronica, Abbigliamento");
    }


    // 2. PROXY DI PROTEZIONE

    private static void demoProtectionProxy() {
        titolo("2. PROXY DI PROTEZIONE - Controllo Accessi");

        DatabaseService db = new DatabaseService();

        SecurityContext adminCtx = new SecurityContext("mario.rossi", Role.ADMIN);
        SecurityContext userCtx  = new SecurityContext("lucia.bianchi", Role.USER);
        SecurityContext guestCtx = new SecurityContext("ospite", Role.GUEST);

        DataService adminProxy = new ProtectionProxyDataService(db, adminCtx);
        DataService userProxy  = new ProtectionProxyDataService(db, userCtx);
        DataService guestProxy = new ProtectionProxyDataService(db, guestCtx);

        // --- ADMIN: lettura e scrittura consentite ---
        System.out.println("-- ADMIN: fetchData --");
        System.out.println("  Risultato: " + adminProxy.fetchData("ordini"));
        separatore();

        System.out.println("-- ADMIN: saveData --");
        adminProxy.saveData("nuovi_ordini", "ORD-004, ORD-005");
        separatore();

        // --- USER: lettura consentita, scrittura negata ---
        System.out.println("-- USER: fetchData (consentita) --");
        System.out.println("  Risultato: " + userProxy.fetchData("prodotti"));
        separatore();

        System.out.println("-- USER: saveData (negata) --");
        tentaOperazione(() -> userProxy.saveData("prodotti", "Nuovo prodotto"));
        separatore();

        // --- GUEST: lettura su dati normali ok, su dati riservati negata ---
        System.out.println("-- GUEST: fetchData normale (consentita) --");
        System.out.println("  Risultato: " + guestProxy.fetchData("prodotti"));
        separatore();

        System.out.println("-- GUEST: fetchData riservata (negata) --");
        tentaOperazione(() -> guestProxy.fetchData("admin_log"));
        separatore();

        System.out.println("-- GUEST: saveData (negata) --");
        tentaOperazione(() -> guestProxy.saveData("cfg", "valore_malevolo"));
    }


    // 3. PROXY REMOTO

    private static void demoRemoteProxy() {
        titolo("3. PROXY REMOTO - Astrazione della Rete");

        // In produzione: baseUrl = URL reale del servizio
        // Qui si usa un URL fittizio -> il proxy cade sul fallback locale
        WeatherService proxy = new RemoteWeatherProxy("https://api.meteo.example.invalid");

        System.out.println("Il client usa WeatherService senza sapere se è remoto o locale.\n");

        System.out.println("Temperatura corrente a Milano:");
        String temp = proxy.getCurrentTemperature("Milano");
        System.out.println("  Risultato: " + temp);
        separatore();

        System.out.println("Previsioni 5 giorni per Roma:");
        String forecast = proxy.getForecast("Roma", 5);
        System.out.println("  Risultato: " + forecast);
    }


    // 4. PROXY CACHE (o SMART REFERENCE)

    private static void demoCachingProxy() {
        titolo("4. PROXY CACHE - Memoizzazione con TTL");

        DatabaseService db    = new DatabaseService();
        // TTL di 5 secondi per velocizzare la demo (in produzione: minuti/ore)
        CachingProxyDataService proxy = new CachingProxyDataService(db, 5L);

        System.out.println("Dimensione cache iniziale: " + proxy.cacheSize());
        separatore();

        System.out.println("Prima fetchData(\"utenti\") -> MISS atteso:");
        System.out.println("  Risultato: " + proxy.fetchData("utenti"));
        System.out.println("Dimensione cache: " + proxy.cacheSize());
        separatore();

        System.out.println("Seconda fetchData(\"utenti\") -> HIT atteso:");
        System.out.println("  Risultato: " + proxy.fetchData("utenti"));
        separatore();

        System.out.println("fetchData(\"prodotti\") -> MISS atteso (chiave diversa):");
        System.out.println("  Risultato: " + proxy.fetchData("prodotti"));
        System.out.println("Dimensione cache: " + proxy.cacheSize());
        separatore();

        System.out.println("saveData(\"utenti\", ...) -> invalida la cache per \"utenti\":");
        proxy.saveData("utenti", "Mario Rossi, Lucia Bianchi, Nuovo Utente");
        System.out.println("Dimensione cache dopo saveData: " + proxy.cacheSize());
        separatore();

        System.out.println("fetchData(\"utenti\") dopo saveData -> MISS (cache invalidata):");
        System.out.println("  Risultato: " + proxy.fetchData("utenti"));
        separatore();

        System.out.println("Attendo 6 secondi per far scadere il TTL...");
        try { Thread.sleep(6_000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        System.out.println("fetchData(\"prodotti\") -> MISS atteso (TTL scaduto):");
        System.out.println("  Risultato: " + proxy.fetchData("prodotti"));
    }


    // Metodi di utilità di formattazione output


    private static void titolo(String testo) {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.printf ("║  %-56s║%n", testo);
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    private static void separatore() {
        System.out.println("  ──────────────────────────────────────────────────────────");
    }

    /**
     * Esegue un'operazione che ci si aspetta lanci {@link SecurityException},
     * catturandola e stampandone il messaggio senza interrompere la demo.
     */
    private static void tentaOperazione(Runnable op) {
        try {
            op.run();
            System.out.println("  Operazione completata senza errori.");
        } catch (SecurityException e) {
            System.out.println("  *** SecurityException catturata: " + e.getMessage());
        }
    }
}
