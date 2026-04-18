package is.polinomi.builder;

import is.polinomi.Monomio;
import is.polinomi.Polinomio;

import java.util.Iterator;
import java.util.Map;

import static java.lang.Math.abs;

abstract class ContenitoreMap implements  ContenitoreMonomi{
    protected final Map<Integer, Monomio> map;

    protected abstract Map<Integer, Monomio> createMap();

    public ContenitoreMap() {
        map = createMap();
    }

    @Override
    public void add(Monomio m) {
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

    @Override
    public int size() {
        return map.size();
    }

    @Override

    public Iterator<Monomio> iterator() {
         return new MIterator();
    }
    class MIterator implements Iterator<Monomio> {
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
