package is.polinomi.builder;

import is.polinomi.Monomio;

import java.util.HashMap;
import java.util.Map;

public class ContenitoreHashMap extends ContenitoreMap{

    @Override
    protected Map<Integer, Monomio> createMap() {
        return new HashMap<>();
    }
}
