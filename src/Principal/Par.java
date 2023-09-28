package Principal;
/**
 * Class Par. 
 * Esta clase modela un par genérico (K, V).
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile.
 *
 * @param <K>
 * @param <V>
 */
public class Par<K,V> {
	protected K rotulo;
	protected V grado;
		
	/**
	 * Encapsula un par con los parámetros ingresados.
	 * @param alfa
	 * @param beta
	 */
	public Par(K alfa, V beta) {
		this.rotulo = alfa;
		this.grado = beta;
	}
	/**
	 * Examina el rótulo del par.
	 * @return Rótulo del par
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
	 * Asigna el valor pasado por parámetro al rótulo del par.
	 * @param k
	 */
	public void setRotulo(K k) {
		rotulo = k;
	}
	/**
	 * Asigna el valor pasado por parámetro al grado del par.
	 * @param v
	 */
	public void setGrado(V v) {
		grado = v;
	}
	/**
	 * 
	 * @return String con el rótulo y el grado del par.
	 */
	public String toString() {
		return "[" + getRotulo() + ", " + getGrado() +"]";
	}	
}
