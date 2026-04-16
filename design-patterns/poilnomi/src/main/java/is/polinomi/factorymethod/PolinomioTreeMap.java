package is.polinomi.factorymethod;

import is.polinomi.Monomio;
import is.polinomi.Polinomio;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeMap;

import static java.lang.Math.abs;


public class PolinomioTreeMap extends PolinomioAstratto {

    protected final TreeMap<Integer, Monomio> map = new TreeMap<>(Comparator.reverseOrder());

    public PolinomioTreeMap() {

    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addMod(Monomio m) {
        if (abs(m.coeff()) < Polinomio.EPS)
            return;
        Integer key = m.grado();
        Monomio m1 = map.get(key);
        if (m1 == null) {
            map.put(key, m);
            return;
        }
        Monomio ris = m1.add(m);
        if (abs(ris.coeff()) < Polinomio.EPS)
            map.remove(key);
        else
            map.put(key, ris);

    }

    /**
     * Metodo factory per la creazione di un'istanza di {@link Polinomio}
     *
     * @return un'istanza di una classe concreta che implementa l'interfaccia
     * {@link Polinomio}
     */
    @Override
    protected PolinomioAstratto create() {
        return new PolinomioTreeMap();
    }

    @Override
    public Iterator<Monomio> iterator() {
        return new PIterator();
    }



    class PIterator implements Iterator<Monomio> {
        private final Iterator<Monomio> it = map.values().iterator();

        @Override
        public boolean hasNext() {

            return it.hasNext();
        }

        @Override
        public Monomio next() {

            return it.next();
        }


    }
}