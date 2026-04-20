package is.polinomi.factorymethod;

import static java.lang.Math.abs;

import is.polinomi.Monomio;
import is.polinomi.Polinomio;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Fornisce un'implementazione dell'interfaccia {@link Polinomio} basata su un
 * array di oggetti di tipo {@link Monomio}
 * <p>
 * I monomi costituenti il polinomio sono memorizzati all'interno dell'array
 * secondo l'ordinamento definito dalla classe {@link Monomio}. Al momento della
 * costruzione e' possibile psecificare la dimensione dell'array che
 * inizialmente sara' utilizzato. Se non c'e' spazio a sufficienza per
 * aggiungere un nuovo monomio l'array viene sostituito con un altro di
 * dimensione doppia.
 * </p>
 * 
 * @author Angelo Furfaro
 * 
 */

public class PolinomioArray extends PolinomioAstratto {
	private Monomio[] mon;
	private int size;
	static final int MIN_CAP = 10;

	/**
	 * Costruttore di default.
	 */
	public PolinomioArray() {
		this(MIN_CAP);
	}

	/**
	 * 
	 * Questo costruttore riceve la dimensione dell'array iniziale.
	 * 
	 * @param cap
	 *            la dimensione dell'array iniziale
	 */
	public PolinomioArray(int cap) {
		mon = new Monomio[Math.max(cap,MIN_CAP)];
	}

	/**
	 * @see is.polinomi.Polinomio#add(is.polinomi.Monomio)
	 */
	@Override
	public void addMod(Monomio m) {
		if (abs(m.coeff()) < EPS)
			// se il coefficiente e' da considerarsi zero non aggiunge il
			// monomio
			return;
		// cerca indice i per l'inserimento di m nell'array mon
		int i = 0;
		while (i < size && mon[i].compareTo(m) > 0)
			i++;
		// se gia' esiste somma ed eventualmente rimuovi
		if (i < size && mon[i].compareTo(m) == 0) {
			mon[i] = mon[i].add(m);// somma i due monomi
			if (abs(mon[i].coeff()) < EPS) {
				// se il risultato della somma e' da considerarsi zero rimuovi
				// il
				// monomio in posizione i effetuando uno shift sinistro
				if (size - (i + 1) >= 0) System.arraycopy(mon, i + 1, mon, i , size - (i + 1));
				mon[size - 1] = null;
				size--;
			}
			return;
		}
		if (size == mon.length)// se l'array e' pieno occorre riallocare
			rialloca();
		// fai scorrere gli elementi da i a size-1 di un posto a destra (shift
		// destro)
		if (size - i >= 0) System.arraycopy(mon, i, mon, i + 1, size - i);
		// inserisci m
		mon[i] = m;
		size++; // conta questa aggiunta
	}

	private void rialloca() {
		// crea un nuvo array di dimensione doppia
		Monomio[] nuovoMon = new Monomio[mon.length * 2];
		// copia gli elementi nel nuovo array
		System.arraycopy(mon, 0, nuovoMon, 0, mon.length);
		mon = nuovoMon;
	}

	@Override
	protected PolinomioArray create() {
		return new PolinomioArray();
	}

	@Override
	public Iterator<Monomio> iterator() {

		return new PolinomioArrayIterator();
	}

	@Override
	public int size() {

		return size;
	}

	/**
	 * Restituisce la dimensione dell'array attualmente utilizzato
	 * 
	 * @return la dimensione dell'array attualmente utilizzato
	 */
	public int capacity() {
		return mon.length;
	}

	/**
	 * Rialloca l'array in modo da avere la dimensione strettamente necessaria a
	 * contenere i monomi
	 */
	public void trimToSize() {
		Monomio[] nuovoMon = new Monomio[size];
		System.arraycopy(mon, 0, nuovoMon, 0, size);
		mon = nuovoMon;
	}

	private final class PolinomioArrayIterator implements Iterator<Monomio> {
		int index = 0;// cursore

		@Override
		public boolean hasNext() {

			return index < size;
		}

		@Override
		public Monomio next() {
			if (index == size)
				throw new NoSuchElementException();

			Monomio p = mon[index];
			index++;

			return p;
		}


	}

}
