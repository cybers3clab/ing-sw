package is;

import is.lista.Lista;
import is.lista.ListeUtil;

public class Test {


    public static void main(String[] args) {

        final boolean copyOnWrite = true;
        Lista<String> l = ListeUtil.creaListaSicura(1_000_000, 10, 10, copyOnWrite);

        l.aggiungi("1");
        l.rimuovi("1");
        l.aggiungi("35");
        l.aggiungi("21");
        l.aggiungi("41");

        System.out.println(l);

        System.out.println("---------------");

        Lista<String> l1 = ListeUtil.creaLista(10_000_000, copyOnWrite);
        for (int i = 0; i < 1_000_000; i++)
            l1.aggiungi("Test:" + i);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1_000; i++) {
            var ln = l1.copia();

            ln.dammiElemento(4);
            if (Math.random() < 0.01)
                ln.aggiungi("33");


        }
        System.out.println("\nTempo impiegato: " + (System.currentTimeMillis() - startTime) + " ms");

    }

}
