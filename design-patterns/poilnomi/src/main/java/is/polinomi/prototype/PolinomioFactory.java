package is.polinomi.prototype;

public final class PolinomioFactory {

    private PolinomioFactory(){}

    private static PolinomioAstratto  prototype;

    static {
        registerPrototype(new PolinomioArray());
    }
    static synchronized void registerPrototype(PolinomioAstratto p){
        prototype=p;
    }

    public  static synchronized PolinomioAstratto createPolinomio() {
        return  prototype.clone();
    }
}
