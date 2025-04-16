package is.lista;

public interface Lista<E> {

    int size();

    boolean contiene(E dato);

    void aggiungi(int index, E dato) throws IndexOutOfBoundsException;

    void aggiungi(E dato);

    void rimuovi(int index) throws IndexOutOfBoundsException;

    boolean rimuovi(E dato);

    E dammiElemento(int index) throws IndexOutOfBoundsException;

    Lista<E> copia();

}
