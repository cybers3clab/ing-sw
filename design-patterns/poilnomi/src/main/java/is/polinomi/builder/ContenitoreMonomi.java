package is.polinomi.builder;

import is.polinomi.Monomio;

interface ContenitoreMonomi extends Iterable<Monomio> {
    void add(Monomio mon);

    int size();

}



