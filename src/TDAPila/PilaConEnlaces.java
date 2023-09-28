package TDAPila;

import Exceptions.EmptyStackException;

/**
 *
 * Class PilaConEnlaces. 
 * Implementa una Pila con Nodos enlazados.
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile.
 * @param <E>
 */
public class PilaConEnlaces<E> implements Stack<E> {

	//Atributos de instancia
	protected Nodo<E> head;
	protected int tamaño;
	
	//Constructores
	/**
	 * Crea una nueva pila vacía. 
	 */
	public PilaConEnlaces() {
		head = null;
		tamaño = 0;
	}
	
	//Métodos
	
	public int size() {
		return tamaño;
	}
	public boolean isEmpty() {
		return tamaño == 0;
	}
	public E top() throws EmptyStackException {
		if(isEmpty())
			throw new EmptyStackException("La pila está vacía. ");
		return head.getElemento();
	}
	public void push(E item) {					
		Nodo<E> aux = new Nodo<E>(item,head);		
		head = aux; 							
		tamaño++;
	}
	public E pop() throws EmptyStackException {
		E aux;
		if(isEmpty())
			throw new EmptyStackException("La pila está vacía. ");
		aux = head.getElemento(); 
		head = head.getSiguiente();
		tamaño--;
		
		return aux;
	}
}
