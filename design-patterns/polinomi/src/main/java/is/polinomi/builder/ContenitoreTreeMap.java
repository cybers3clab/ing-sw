package is.polinomi.builder;

import is.polinomi.Monomio;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class ContenitoreTreeMap extends ContenitoreMap{

    @Override
    protected Map<Integer, Monomio> createMap() {
        return new TreeMap<>(Comparator.reverseOrder());
    }
}
