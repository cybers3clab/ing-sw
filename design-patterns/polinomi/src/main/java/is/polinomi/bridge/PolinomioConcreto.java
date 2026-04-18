package is.polinomi.bridge;

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
public class PolinomioConcreto implements Polinomio {

    // setta il factory predefinito
    private static PolinomioImplFactory factory = new PolinomioLLFactory();
    //bridge
    private final PolinomioImpl polinomioImpl;

    public PolinomioConcreto() {
        polinomioImpl = factory.createPolinomioImpl();
    }

    public PolinomioConcreto(Polinomio p) {

        polinomioImpl = factory.createPolinomioImpl();
        for (Monomio m : p)
            polinomioImpl.addMonomio(m);
    }

    // consente di modificare il factory
    public static void setPolinomioImplFactory(PolinomioImplFactory factory) {
        PolinomioConcreto.factory = factory;
    }

    @Override
    public int size() {
        return polinomioImpl.size();
    }


    protected void addImpl(Monomio m) {
        polinomioImpl.addMonomio(m);

    }

    @Override
    public Polinomio add(Monomio m) {
        PolinomioConcreto res = new PolinomioConcreto();
        boolean added = false;
        for (Monomio mon : this) {
            if (mon.grado() == m.grado()) {
                Monomio sum = mon.add(m);
                if (Math.abs(sum.coeff()) > EPS)
                    res.addImpl(sum);
                added = true;
            } else res.addImpl(mon);
        }
        if (!added) res.addImpl(m);


        return res;
    }

    /*
     * (non-Javadoc)
     *
     * @see poo.polinomi.Polinomio#add(poo.polinomi.Polinomio)
     */
    @Override
    public Polinomio add(Polinomio p) {

        PolinomioConcreto somma = new PolinomioConcreto();// crea un nuovo
        // polinomio
        for (Monomio m : this)
            somma.addImpl(m);// aggiunge ciascun monomio di this al polinomio somma

        for (Monomio m : p)
            somma.addImpl(m);// aggiunge ciascun monomio di p al polinomio somma

        return somma;
    }

    /*
     * (non-Javadoc)
     *
     * @see poo.polinomi.Polinomio#mul(poo.polinomi.Monomio)
     */
    @Override
    public Polinomio mul(Monomio m) {
        PolinomioConcreto prodotto = new PolinomioConcreto();// crea un nuovo
        // polinomio
        for (Monomio m1 : this)
            // moltiplica ciascun monomio m1 di this per m ed aggiunge il
            // risultato al polinomio prodotto
            prodotto.addImpl(m1.mul(m));
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
        PolinomioConcreto prodotto = new PolinomioConcreto();// crea un nuovo

        //copia i monomi di this nel nuovo polinomio
        for (Monomio m1: this)
            for(Monomio m2: p)
                prodotto.addImpl(m1.mul(m2));

        return prodotto;
    }

    /*
     * (non-Javadoc)
     *
     * @see poo.polinomi.Polinomio#derive()
     */
    @Override
    public Polinomio derive() {
        PolinomioConcreto p = new PolinomioConcreto();// crea un nuovo
        // polinomio
        for (Monomio m : polinomioImpl) {
            int grado = m.grado();
            if (grado > 0) {// se il grado e' positivo calcola la derivata del
                // monomioF
                p.addImpl(new Monomio(m.coeff() * grado, grado - 1));
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
    public Iterator<Monomio> iterator() {

        return polinomioImpl.iterator();
    }
}
