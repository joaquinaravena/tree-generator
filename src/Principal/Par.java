package Principal;
/**
 * Class Par. 
 * Esta clase modela un par gen�rico (K, V).
 * @author Alumnos Joaqu�n Aravena y St�fano Graziabile.
 *
 * @param <K>
 * @param <V>
 */
public class Par<K,V> {
	protected K rotulo;
	protected V grado;
		
	/**
	 * Encapsula un par con los par�metros ingresados.
	 * @param alfa
	 * @param beta
	 */
	public Par(K alfa, V beta) {
		this.rotulo = alfa;
		this.grado = beta;
	}
	/**
	 * Examina el r�tulo del par.
	 * @return R�tulo del par
	 */
	public K getRotulo() {
		return rotulo;
	}
	/**
	 * Examina el grado del par.
	 * @return Grado del par.
	 */
	public V getGrado() {
		return grado;
	}
	/**
	 * Asigna el valor pasado por par�metro al r�tulo del par.
	 * @param k
	 */
	public void setRotulo(K k) {
		rotulo = k;
	}
	/**
	 * Asigna el valor pasado por par�metro al grado del par.
	 * @param v
	 */
	public void setGrado(V v) {
		grado = v;
	}
	/**
	 * 
	 * @return String con el r�tulo y el grado del par.
	 */
	public String toString() {
		return "[" + getRotulo() + ", " + getGrado() +"]";
	}	
}
