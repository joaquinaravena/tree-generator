package TDAPila;

import Exceptions.EmptyStackException;

/**
 *
 * Class PilaConEnlaces. 
 * Implementa una Pila con Nodos enlazados.
 * @author Alumnos Joaqu�n Aravena y St�fano Graziabile.
 * @param <E>
 */
public class PilaConEnlaces<E> implements Stack<E> {

	//Atributos de instancia
	protected Nodo<E> head;
	protected int tama�o;
	
	//Constructores
	/**
	 * Crea una nueva pila vac�a. 
	 */
	public PilaConEnlaces() {
		head = null;
		tama�o = 0;
	}
	
	//M�todos
	
	public int size() {
		return tama�o;
	}
	public boolean isEmpty() {
		return tama�o == 0;
	}
	public E top() throws EmptyStackException {
		if(isEmpty())
			throw new EmptyStackException("La pila est� vac�a. ");
		return head.getElemento();
	}
	public void push(E item) {					
		Nodo<E> aux = new Nodo<E>(item,head);		
		head = aux; 							
		tama�o++;
	}
	public E pop() throws EmptyStackException {
		E aux;
		if(isEmpty())
			throw new EmptyStackException("La pila est� vac�a. ");
		aux = head.getElemento(); 
		head = head.getSiguiente();
		tama�o--;
		
		return aux;
	}
}
