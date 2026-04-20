package is.polinomi.factorymethod;

import static java.lang.Math.abs;

import is.polinomi.Monomio;
import is.polinomi.Polinomio;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;


/**
 * 
 * Fornisce un'implementazione dell'interfaccia {@link Polinomio} basata su una
 * lista di tipo {@link java.util.LinkedList}
 * 
 * I monomi costituenti il polinomio sono memorizzati all'interno della lista
 * concatenata secondo l'ordinamento definito dalla classe Monomio.
 * 
 * @author Angelo Furfaro
 * 
 */
public class PolinomioLL extends PolinomioAstratto {
	private final  LinkedList<Monomio> monomi = new LinkedList<>();

	@Override
	protected PolinomioLL create() {

		return new PolinomioLL();
	}

	@Override
	public int size() {

		return monomi.size();
	}

	@Override
	public void addMod(Monomio m) {
		// se il coefficiente e' da considerarsi zero non aggiunge il monomio
		if (abs(m.coeff()) < Polinomio.EPS)
			return;

		ListIterator<Monomio> it = monomi.listIterator();
		while (it.hasNext()) {// scandisce la lista alla ricerca della posizione
			// di inserimento
			Monomio m1 = it.next();
			// ha trovato un monomio dello stesso grado
			if (m1.compareTo(m) == 0) {

				// somma il monomio ricevuto con quello gia' presente
				Monomio m2 = m1.add(m);
				// se il risultato ha coefficiente diverso da "zero" sostituisce
				// il nuovo monomio con il vecchio altrimenti rimuove
				if (abs(m2.coeff()) >= Polinomio.EPS)
					it.set(m2);
				else
					it.remove();
				return;
			} else if (m1.compareTo(m) < 0) {
				// i monomi successivi hanno grado minore
				it.previous();
				break;
			}

		}
		it.add(m); // aggiunge il monomio nella posizione corrente
	}

	@Override
	public Iterator<Monomio> iterator() {

		return new PIterator();
	}

	private class PIterator implements Iterator<Monomio> {
		private final Iterator<Monomio> it = monomi.iterator();

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
