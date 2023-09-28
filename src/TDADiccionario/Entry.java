package TDADiccionario;
/**
 * Interface Entry.
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile. 
 *
 * @param <K>
 * @param <V>
 */
public interface Entry<K,V> {
	/**
	 * Consulta la clave de la entrada.
	 * @return Clave de la entrada. 
	 */
	public K getKey();
	/**
	 * Consulta el valor de la entrada.
	 * @return Valor de la entrada. 
	 */
	public V getValue();
}
