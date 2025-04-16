package is.lista;

import is.lista.copyonwrite.ListaCopyOnWrite;
import is.lista.impl.ListaSuArray;
import is.lista.sicurezza.ListaSicura;

public class ListeUtil {

    public static <E> Lista<E> creaLista(int dim, boolean copyOnWrite) {
        Lista<E> miaLista = new ListaSuArray<>(dim);
        if (!copyOnWrite)
            return miaLista;
        else
            return new ListaCopyOnWrite<>(miaLista);
    }

    public static <E> Lista<E> creaListaSicura(int dim, int nread, int nwrite, boolean copyOnWrite) {
        Lista<E> miaLista = new ListaSuArray<>(dim);

        if (copyOnWrite)
            miaLista = new ListaCopyOnWrite<>(miaLista);

        return new ListaSicura<>(miaLista, nread, nwrite);
    }

}
