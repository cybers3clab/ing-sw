package is.polinomi.bridge;

class PolinomioArrayFactory implements PolinomioImplFactory {

	@Override
	public PolinomioImpl createPolinomioImpl() {

		return new PolinomioArray();
	}

}