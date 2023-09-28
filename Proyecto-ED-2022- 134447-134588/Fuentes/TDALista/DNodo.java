package TDALista;

/**
 * Class DNodo. 
 * Implementa un nodo doblemente enlazado que se utilizará en la implementación de TDA'S con estructura doblemente
 * enlazada. 
 * @author Alumnos Joaquín Aravena, Stéfano Graziabile. 
 * @param <E>
 */
public class DNodo<E> implements Position<E> {
	//Atributos de instancia
	private DNodo<E> siguiente;
	private DNodo<E> anterior;
	private E head; 
	
	/**
	 * Crea un Nodo doblemente enlazado.
	 * @param element Elemento del Nodo. 
	 * @param ant Nodo predecesor al Nodo. 
	 * @param sig Nodo sucesor al Nodo. 
	 */
	public DNodo(E element, DNodo<E> ant, DNodo<E> sig) {
		siguiente = sig;
		anterior = ant;
		head = element; 
	}
	/**
	 * Crea un Nodo con referencias nulas. 
	 */
	public DNodo() {
		siguiente = null;
		anterior = null;
		head = null;
	}

	public E element() {
		return head; 
	}
	/**
	 * Examina el Nodo anterior al Nodo.
	 * @return Nodo anterior al Nodo.
	 */
	public DNodo<E> getPrev() {
		return anterior;
	}
	/**
	 * Examina el Nodo siguiente al Nodo
	 * @return Nodo siguiente al Nodo.
	 */
	public DNodo<E> getNext() {
		return siguiente;
	}
	/**
	 * Crea un Nodo con elemento e y lo asigna como siguiente del Nodo.
	 * @param e Elemento del Nodo a insertar. 
	 */
	public void setPrev(DNodo<E> e) {
		anterior = e;
	}
	/**
	 * Crea un Nodo con elemento e y lo asigna como anterior del Nodo.
	 * @param e Elemento del Nodo a insertar. 
	 */
	public void setNext(DNodo<E> e) {
		siguiente = e;
	}
	/**
	 * Asigna un elemento al Nodo. 
	 * @param head Elemento asignado al Nodo. 
	 */
	public void setElement(E head) {
		this.head = head;
	}
}
 