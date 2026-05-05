package proxies.protection;

/**
 * Contesto di sicurezza immutabile che rappresenta l'identità dell'utente.
 * <p>
 * Usa i {@code record} di Java.
 * L'immutabilità è ideale per i contesti di sicurezza.
 */
public record SecurityContext(String username, Role role) {

    /**
     * Compact constructor: valida gli argomenti all'atto della costruzione.
     * I compact constructor nei record omettono la lista di parametri.
     */
    public SecurityContext {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username non può essere vuoto");
        }
        if (role == null) {
            throw new IllegalArgumentException("role non può essere null");
        }
    }

    /** Verifica se l'utente ha il ruolo specificato. */
    public boolean hasRole(Role required) {
        return this.role == required;
    }

    /** Verifica se l'utente ha almeno il livello di privilegio indicato. */
    public boolean hasAtLeastRole(Role minimum) {
        // Ordinamento: ADMIN > USER > GUEST (basato sulla posizione nell'enum)
        return this.role.ordinal() <= minimum.ordinal();
    }
}
