package is.polinomi.prototype;

import is.polinomi.Monomio;
import is.polinomi.Polinomio;

import java.util.Iterator;


/**
 *
 * Questa classe astratta fornisce un'implementazione parziale dell'interfaccia
 * {@link Polinomio}. I metodi concreti codificano gli algoritmi per le
 * corrispondenti operazioni algebriche tra polinomi e si basano sull'uso degli
 * iteratori.
 *
 * @author Angelo Furfaro
 *
 */
public abstract class PolinomioAstratto implements Polinomio, Cloneable {


    protected abstract void addMod(Monomio monomio);

    @Override
    public Polinomio add(Monomio m) {
        PolinomioAstratto p = PolinomioFactory.createPolinomio();
        boolean added = false;
        for (Monomio mon : this) {
            if (mon.grado() == m.grado()) {
                Monomio sum = mon.add(m);
                if (Math.abs(sum.coeff()) > EPS)
                    p.addMod(sum);
                added = true;
            } else p.addMod(mon);
        }
        if (!added) p.addMod(m);
        return p;
    }


    /*
     * (non-Javadoc)
     *
     * @see poo.polinomi.Polinomio#add(poo.polinomi.Polinomio)
     */
    @Override
    public Polinomio add(Polinomio p) {
        PolinomioAstratto somma = PolinomioFactory.createPolinomio();// crea un nuovo polinomio
        for (Monomio m : this)
            somma.addMod(m);// aggiunge ciascun monomio di this al polinomio somma

        for (Monomio m : p)
            somma.addMod(m);// aggiunge ciascun monomio di p al polinomio somma

        return somma;
    }

    /*
     * (non-Javadoc)
     *
     * @see poo.polinomi.Polinomio#mul(poo.polinomi.Monomio)
     */
    @Override
    public Polinomio mul(Monomio m) {
        PolinomioAstratto prodotto = PolinomioFactory.createPolinomio();// crea un nuovo polinomio
        for (Monomio m1 : this)
            // moltiplica ciascun monomio m1 di this per m ed aggiunge il
            // risultato al polinomio prodotto
            prodotto.addMod(m1.mul(m));
        return prodotto;
    }

    /*
     * (non-Javadoc)
     *
     * @see poo.polinomi.Polinomio#mul(double)
     */
    @Override
    public Polinomio mul(double s) {
        // moltiplica this per il monomio di grado zero avente coefficiente s
        return mul(new Monomio(s, 0));
    }

    /*
     * (non-Javadoc)
     *
     * @see poo.polinomi.Polinomio#mul(poo.polinomi.Polinomio)
     */
    @Override
    public Polinomio mul(Polinomio p) {
        PolinomioAstratto prodotto = PolinomioFactory.createPolinomio();// crea un nuovo PolinomioF

        for (Monomio m1 : this)
            for (Monomio m2 : p)
                prodotto.addMod(m1.mul(m2));

        return prodotto;
    }

    /*
     * (non-Javadoc)
     *
     * @see poo.polinomi.Polinomio#derive()
     */
    @Override
    public Polinomio derive() {
        PolinomioAstratto p = PolinomioFactory.createPolinomio();// crea un nuovo polinomio
        for (Monomio m : this) {
            int grado = m.grado();
            if (grado > 0) {// se il grado e' positivo calcola la derivata del
                // monomioF
                p.addMod(new Monomio(m.coeff() * grado, grado - 1));
            }
        }
        return p;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {
       return Polinomio.toString(this);
    }

    @Override
    public PolinomioAstratto clone() {

        try {
            return (PolinomioAstratto) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }

    }
}
