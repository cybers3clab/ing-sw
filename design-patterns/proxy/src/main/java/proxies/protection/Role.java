package proxies.protection;

/**
 * Ruoli disponibili nel sistema di controllo accessi.
 */
public enum Role {

    /** Accesso completo: lettura e scrittura. */
    ADMIN,

    /** Accesso in lettura; scrittura negata. */
    USER,

    /** Solo lettura su dati non riservati. */
    GUEST
}
