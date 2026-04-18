package is.polinomi.builder;

import is.polinomi.Monomio;

public interface PolinomioBuilderIF {

    PolinomioBuilderIF add(Monomio m);

    PolinomioConcreto getPolinomio();
}
