package is.polinomi.builder;


import is.polinomi.Monomio;
import is.polinomi.Polinomio;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.Math.abs;

class ContenitoreArray implements ContenitoreMonomi {

    static final int MIN_CAP = 10;
    private Monomio[] mon;
    private int size;
    ContenitoreArray(){
        this(MIN_CAP);
    }
    ContenitoreArray(int cap){
        mon = new Monomio[Math.max(cap, MIN_CAP)];
    }

    @Override
    public void add(Monomio m) {
        if (abs(m.coeff()) < Polinomio.EPS)
            // se il coefficiente e' da considerarsi zero non aggiunge il
            // monomio
            return;
        // cerca indice i per l'inserimento di m nell'array mon
        int i = 0;
        while (i < size && mon[i].compareTo(m) < 0)
            i++;
        // se gia' esiste somma ed eventualmente rimuovi
        if (i < size && mon[i].compareTo(m) == 0) {
            mon[i] = mon[i].add(m);// somma i due monomi
            if (abs(mon[i].coeff()) < Polinomio.EPS) {
                // se il risultato della somma e' da considerarsi zero rimuovi
                // il
                // monomio in posizione i effetuando uno shift sinistro
                if (size - (i + 1) >= 0) System.arraycopy(mon, i + 1, mon, i, size - (i + 1));
                mon[size - 1] = null;
                size--;
            }
            return;
        }
        if (size == mon.length)// se l'array e' pieno occorre riallocare
            reallocate();
        // fai scorrere gli elementi da i a size-1 di un posto a destra (shift
        // destro)
        if (size - i >= 0) System.arraycopy(mon, i, mon, i + 1, size - i);
        // inserisci m
        mon[i] = m;
        size++; // conta questa aggiunta
    }

    private void reallocate() {
        Monomio[] newMon = new Monomio[mon.length * 2];
        // copia gli elementi nel nuovo array
        System.arraycopy(mon, 0, newMon, 0, mon.length);
        mon = newMon;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Monomio> iterator() {
        return new AIterator();
    }



    private class AIterator implements Iterator<Monomio> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index<size;
        }

        @Override
        public Monomio next() {

            if (index == mon.length)
                throw new NoSuchElementException();

            return mon[index++];
        }
    }
}
