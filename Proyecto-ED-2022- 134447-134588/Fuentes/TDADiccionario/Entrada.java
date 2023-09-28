package TDADiccionario;
/**
 * Class Entrada. 
 * Esta clase implementa una Entrada que será utilizada en el TDA Diccionario. 
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile. 
 * @param <K>
 * @param <V>
 */

public class Entrada<K,V> implements Entry<K,V> {
	//Atributos de instancia
	private K clave;
	private V valor;
	
	/**
	 * El contructor crea una entrada con una clave y un valor ingresador por parámetro. 
	 * @param clave
	 * @param valor
	 */
	public Entrada(K clave, V valor) {
		this.clave = clave;
		this.valor = valor;
	}

	public K getKey() {
		return clave;
	}
	public V getValue() {
		return valor;
	}
	/**
	 * Establece una nueva clave a la entrada. 
	 * @param key Clave a establecer. 
	 */
	public void setKey(K key) {
		clave = key;
	}
	/**
	 * Establece un nuevo valor a la entrada.
	 * @param value Valor a establecer. 
	 */
	public void setValue(V value) {
		valor = value; 
	}
	

}
