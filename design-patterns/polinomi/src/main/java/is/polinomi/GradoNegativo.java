package is.polinomi;

/**
 * Eccezione sollevata dal costruttore {@link Monomio#Monomio(double, int)} se
 * il grado specificato &egrave; negativo
 * 
 * @author Angelo Furfaro
 * 
 */
public class GradoNegativo extends RuntimeException {

	/**
	 * @param g
	 *            il grado negativo specificato
	 */
	public GradoNegativo(int g) {
		super("Grado negativo:" + g);
	}
}
