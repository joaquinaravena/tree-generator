package TDACola;

import Exceptions.EmptyQueueException;
/**
 * Class ColaConArregloCircular. 
 * Implementa una cola con arreglo circular.
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile.
 * @param <E>
 */

public class ColaConArregloCircular<E> implements Queue<E> {
	
	//Atributos de instancia
	private int i;				//proximo elemento a eliminar
	private int f;				//proximo elemento a insertar
	private E[] cola;
	
	private static final int longitud = 10;
	/**
	 * El constructor crea una cola con una pila vacía, e inicializa los punteros de insertar y eliminar en 0. 
	 */
	//Constructores
	public ColaConArregloCircular() {
		i = 0;
		f = 0;
		cola = (E[]) new Object[longitud];
	}
	
	//Métodos
	public boolean isEmpty() {
		return i == f;
	}
	public int size() {
		return(cola.length - i + f) % cola.length;
	}
	public void enqueue(E e) {
		if(cola.length - 1 == size()) {			//si el arreglo está lleno
			E[] aux = copiar(i);
			f = size();
			i = 0;
			cola = aux; 
		}
		cola[f] = e; f=(f+1) % cola.length; 
	}
	/**
	 * A partir de una cola existente, crea una nueva con el doble de longitud que esta, insertando al principio
	 * los elementos de la vieja cola, reemplazándola finalmente por la nueva. 
	 * @param m Puntero con el próximo elemento a eliminar de la cola.
	 * @return Cola aumentada de tamaño. 
	 */
	private E[] copiar(int m) {
		E[] aux = (E[]) new Object[2 * cola.length]; 
		for(int j = 0; j < size(); j++) { 
			aux[j] = cola[m];
			m = (m+1) % cola.length;
		}
		return aux; 
	}
	public E dequeue() throws EmptyQueueException {
		if(isEmpty())
			throw new EmptyQueueException("La cola está vacía.");
		E aux = cola[i];
		cola[i] = null;
		i = (i+1) % cola.length;
		
		return aux; 
	}
	
	public E front() throws EmptyQueueException {
		if(isEmpty())
			throw new EmptyQueueException("La cola está llena.");
		return cola[i]; 
	}
}
