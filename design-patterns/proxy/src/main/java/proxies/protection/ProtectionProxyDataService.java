package proxies.protection;

import proxies.common.DataService;

/**
 * Proxy di Protezione (Protection / Access-Control Proxy).
 * <p>
 * Controlla che il chiamante abbia i permessi necessari prima di
 * delegare l'operazione al RealSubject. Il client non può accedere
 * direttamente al RealSubject: interagisce sempre tramite questo proxy.
 *
 * <p>Regole implementate:</p>
 * <ul>
 *   <li>ADMIN -> lettura e scrittura su tutto</li>
 *   <li>USER  -> lettura su dati non riservati; scrittura negata</li>
 *   <li>GUEST -> solo lettura su query non "admin"; scrittura negata</li>
 * </ul>
 */
public class ProtectionProxyDataService implements DataService {

    private final DataService realService;
    private final SecurityContext context;

    /**
     * @param realService il RealSubject da proteggere
     * @param context     il contesto di sicurezza dell'utente corrente
     */
    public ProtectionProxyDataService(DataService realService,
                                      SecurityContext context) {
        this.realService = realService;
        this.context     = context;
    }

    @Override
    public String fetchData(String query) {
        System.out.println("  [ProtProxy] fetchData richiesto da '"
                + context.username() + "' (ruolo: " + context.role() + ")");

        // I GUEST non possono accedere a risorse riservate ("admin")
        if (context.hasRole(Role.GUEST) && containsKeywordRiservata(query)) {
            throw new SecurityException(
                    "Accesso negato: il ruolo GUEST non può accedere a '" + query + "'");
        }

        System.out.println("  [ProtProxy] Accesso in lettura consentito -> delego al RealSubject");
        return realService.fetchData(query);
    }

    @Override
    public void saveData(String key, String value) {
        System.out.println("  [ProtProxy] saveData richiesto da '"
                + context.username() + "' (ruolo: " + context.role() + ")");

        // Solo ADMIN può scrivere
        if (!context.hasRole(Role.ADMIN)) {
            throw new SecurityException(
                    "Accesso negato: solo ADMIN può modificare i dati. "
                    + "Ruolo attuale: " + context.role());
        }

        System.out.println("  [ProtProxy] Scrittura autorizzata -> delego al RealSubject");
        realService.saveData(key, value);
    }

    /** Controlla se la query contiene parole chiave riservate. */
    private boolean containsKeywordRiservata(String query) {
        String lower = query.toLowerCase();
        return lower.contains("admin") || lower.contains("password")
                || lower.contains("segreto");
    }
}
