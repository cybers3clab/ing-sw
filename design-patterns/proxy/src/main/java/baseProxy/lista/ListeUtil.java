package baseProxy.lista;

import baseProxy.lista.copyonwrite.ListaCopyOnWrite;
import baseProxy.lista.impl.ListaSuArray;
import baseProxy.lista.sicurezza.ListaSicura;

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
