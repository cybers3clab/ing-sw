package is.polinomi.bridge;

import is.polinomi.Monomio;
import is.polinomi.Polinomio;

public class PolinomioIntegrable extends PolinomioConcreto {


	public PolinomioIntegrable(Polinomio p){
			super(p);
	}
	public PolinomioIntegrable(){

	}
	public PolinomioIntegrable integrate(){
		PolinomioIntegrable ris= new PolinomioIntegrable();
		for(Monomio m:this){
			double c= m.coeff();
			int g=m.grado();
			
			Monomio mint= new Monomio(c/(g+1),g+1);
			ris.addImpl(mint);
		}
		return ris;
	}
	
	
}
