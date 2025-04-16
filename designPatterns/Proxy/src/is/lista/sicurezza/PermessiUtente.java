package is.lista.sicurezza;

public final class PermessiUtente {

    private int numeroLetture;
    private int numeroScritture;

    public PermessiUtente(int numeroLetture, int numeroScritture) {
        if (numeroLetture < 0 || numeroScritture < 0)
            throw new IllegalArgumentException();
        this.numeroLetture = numeroLetture;
        this.numeroScritture = numeroScritture;
    }

    public int getNumeroLetture() {
        return numeroLetture;
    }

    public int getNumeroScritture() {
        return numeroScritture;
    }

    public void decrementaLetture() {
        if (numeroLetture > 0)
            numeroLetture--;
    }

    public void decrementaScriture() {
        if (numeroScritture > 0)
            numeroScritture--;
    }

}
