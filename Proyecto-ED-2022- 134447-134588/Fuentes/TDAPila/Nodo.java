package TDAPila;

/**
 * 
 * Class Nodo. 
 * Impelementa un nodo simplemente enlazado.  
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile
 * @param <E>
 */
public class Nodo<E>  {

	//Atributos de instancia
	private E elemento;
	private Nodo<E> siguiente;

/**
 * Crea un Nodo simplemente enlazado.  
 * @param item Elemento del nodo.
 * @param sig Nodo sucesor al Nodo a crear.
 */
	//Constructores
	public Nodo(E item, Nodo<E> sig) {
		elemento = item;
		siguiente = sig; 
	}
	
	//Setters
	/**
	 * Asigna un elemento al Nodo. 
	 * @param elemento Elemento que se asigna al Nodo. 
	 */
	public void setElemento(E elemento) {
		this.elemento = elemento;
	}
	/**
	 * Asigna un Nodo siguiente al Nodo. 
	 * @param siguiente Nodo siguiente al Nodo. 
	 */
	public void setSiguiente(Nodo<E> siguiente) {
		this.siguiente = siguiente;
	}
	
	//Getters
	/**
	 * Examina el elemento del Nodo.
	 * @return Elemento del Nodo. 
	 */
	public E getElemento() {
		return elemento;
	}
	/**
	 * Examina el Nodo sucesor al Nodo
	 * @return Nodo siguiente al Nodo. 
	 */
	public Nodo<E> getSiguiente() {
		return siguiente;
	}
}
