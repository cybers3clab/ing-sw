package is.polinomi.bridge;

class PolinomioLLFactory implements PolinomioImplFactory {

    @Override
    public PolinomioImpl createPolinomioImpl() {

        return new PolinomioLL();
    }

}
