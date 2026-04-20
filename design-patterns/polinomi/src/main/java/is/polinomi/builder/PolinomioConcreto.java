package is.polinomi.builder;


import is.polinomi.Monomio;
import is.polinomi.Polinomio;

import java.util.*;

public class PolinomioConcreto implements Polinomio {
    private final Monomio[] monomials;


    public PolinomioConcreto(){
        monomials= new Monomio[0];
    }
     PolinomioConcreto(Monomio[] ma) {
        monomials = ma;

    }

    @Override
    public int size() {
        return monomials.length;
    }

    @Override
    public Polinomio add(Monomio m) {
        PolinomioBuilderIF builder = new Builder();
        for (Monomio mon : monomials) {
            builder.add(mon);
        }
        builder.add(m);
        return builder.getPolinomio();
    }

    @Override
    public Polinomio add(Polinomio p) {
        PolinomioBuilderIF builder = new Builder();
        for (Monomio mon : monomials) {
            builder.add(mon);
        }
        for (Monomio mon : p) {
            builder.add(mon);
        }
        return builder.getPolinomio();
    }

    @Override
    public Polinomio mul(Monomio monomial) {
        PolinomioBuilderIF builder = new Builder();
        for (Monomio m : monomials) {
            builder.add(m.mul(monomial));
        }
        return builder.getPolinomio();
    }

    @Override
    public Polinomio mul(double s) {
        return mul(new Monomio(s, 0));
    }

    @Override
    public Polinomio mul(Polinomio p) {
        PolinomioBuilderIF builder = new Builder();
        for (Monomio m1 : monomials) {
            for (Monomio m2 : p) {
                builder.add(m1.mul(m2));
            }
        }
        return builder.getPolinomio();
    }

    @Override
    public Polinomio derive() {
        PolinomioBuilderIF builder = new Builder();
        for (Monomio monomial : monomials)
            if (monomial.grado() > 0)
                builder.add(new Monomio(monomial.coeff() * monomial.grado(), monomial.grado() - 1));
        return builder.getPolinomio();
    }

    @Override
    public Iterator<Monomio> iterator() {
        return new PIterator();
    }


    public final String toString() {
        return Polinomio.toString(this);
    }
    public static class Builder implements PolinomioBuilderIF {
        private static ContenitoreFactory containerFactory = new ContenitoreHashMapFactory();

        static void setContainerFactory(ContenitoreFactory cFactory){
            containerFactory=cFactory;

        }
        private final ContenitoreMonomi container = containerFactory.createContainer();
        @Override
        public PolinomioBuilderIF add(Monomio m) {
            Objects.requireNonNull(m);
             container.add(m);
             return this;
        }

        @Override
        public PolinomioConcreto getPolinomio() {
            Monomio[] am=new Monomio[container.size()];

            int i=0;
            for(Monomio m:container) {
                am[i++] = m;
            }
            Arrays.sort(am, Comparator.reverseOrder());
            return new PolinomioConcreto(am);
        }
    }

    private class PIterator implements Iterator<Monomio> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index<monomials.length;
        }

        @Override
        public Monomio next() {

            if (index == monomials.length)
                throw new NoSuchElementException();

            return monomials[index++];
        }
    }
}
