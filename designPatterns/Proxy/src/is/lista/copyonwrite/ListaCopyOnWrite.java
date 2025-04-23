package is.lista.copyonwrite;

import is.lista.Lista;
import java.lang.ref.Cleaner;

public final class ListaCopyOnWrite<E> implements Lista<E> {
    private static final Cleaner CLEANER = Cleaner.create();
    private final ReferenceCounterHolder<E> refHolder;


    public ListaCopyOnWrite(Lista<E> l) {
        if (l instanceof ListaCopyOnWrite)
            throw new IllegalArgumentException("Proxy multipli non ammessi");

        refHolder = new ReferenceCounterHolder<>(new ReferenceCounter<>(l));
        CLEANER.register(this, refHolder);

    }

    private ListaCopyOnWrite(ReferenceCounter<E> ref) {
        this.refHolder = new ReferenceCounterHolder<>(ref);
        CLEANER.register(this, refHolder);
    }

    private void copyIfNecessary() {
        refHolder.setReferenceCounter(refHolder.getReferenceCounter().copyIfNecessary());
    }

    @Override
    public int size() {
        return refHolder.getReferenceCounter().getLista().size();
    }

    @Override
    public boolean contiene(E dato) {
        return refHolder.getReferenceCounter().getLista().contiene(dato);
    }

    @Override
    public void aggiungi(int index, E dato) throws IndexOutOfBoundsException {
        copyIfNecessary();
        refHolder.getReferenceCounter().getLista().aggiungi(index, dato);
    }

    @Override
    public void aggiungi(E dato) {
        copyIfNecessary();
        refHolder.getReferenceCounter().getLista().aggiungi(dato);
    }

    @Override
    public void rimuovi(int index) throws IndexOutOfBoundsException {
        copyIfNecessary();
        refHolder.getReferenceCounter().getLista().rimuovi(index);
    }

    @Override
    public boolean rimuovi(E dato) {
        copyIfNecessary();
        return refHolder.getReferenceCounter().getLista().rimuovi(dato);
    }

    @Override
    public E dammiElemento(int index) throws IndexOutOfBoundsException {
        return refHolder.getReferenceCounter().getLista().dammiElemento(index);
    }

    @Override
    public Lista<E> copia() {
        refHolder.getReferenceCounter().incr();
        return new ListaCopyOnWrite<>(refHolder.getReferenceCounter());
    }

    @Override
    public String toString() {
        return refHolder.getReferenceCounter().getLista().toString();
    }


    private final static class ReferenceCounterHolder<E> implements Runnable {
        ReferenceCounter<E> referenceCounter;

        public ReferenceCounterHolder(ReferenceCounter<E> referenceCounter) {
            this.referenceCounter = referenceCounter;
        }

        public ReferenceCounter<E> getReferenceCounter() {
            return referenceCounter;
        }

        public void setReferenceCounter(ReferenceCounter<E> referenceCounter) {
            this.referenceCounter = referenceCounter;
        }

        public void run() {
            System.out.println(referenceCounter.counter);
            referenceCounter.decr();
        }
    }

    private static final class ReferenceCounter<E> {
        private final Lista<E> lista;
        private int counter;

        public ReferenceCounter(Lista<E> l) {
            counter = 1;
            lista = l;
        }

        public Lista<E> getLista() {
            return lista;
        }

        public void decr() {
            counter--;
        }

        public void incr() {
            counter++;
        }

        public ReferenceCounter<E> copyIfNecessary() {
            if (counter == 1) {
                return this;
            }
            ReferenceCounter<E> cr = new ReferenceCounter<>(lista.copia());
            counter--;
            return cr;
        }
    }
}
