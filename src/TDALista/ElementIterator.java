package TDALista;

import java.util.Iterator;

import java.util.*;
import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;

/**
 * Class ElementIterator. 
 * Esta clase impelementa un iterador de elementos para una lista, implelentando la interfaz Iterator de Java. 
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile.
 * @param <E>
 */

public class ElementIterator<E> implements Iterator<E> {
	//Atributos de instancia
	protected PositionList<E> lista;
	protected Position<E> cursor;
	
	/**
	 * El contructor crea un iterador de elementos de la lista que ingresa como parámetro.
	 * @param L Lista a la que se le generará el iterador. 
	 */
	//Constructores
	public ElementIterator(PositionList<E> L) {
		lista = L;
		if(lista.isEmpty())
			cursor = null;
		else
			try {
			cursor = lista.first(); }
			catch(EmptyListException e) {
				System.out.println("Lista vacía. "); 
			}
	}
	/**
	 * Consulta si el cursor tiene siguiente elemento. 
	 * @return Verdadero si tiene siguiente. Falso si no lo tiene. 
	 */
	public boolean hasNext() {
		return cursor != null; 
	}
	/**
	 * Consulta el elemento siguiente y corre una posición el cursor. 
	 * @return El elemento siguiente.
	 * @throws NoSuchElementException Si el cursor no tiene siguiente. 
	 */
	public E next() throws NoSuchElementException {
		if(cursor == null)
			throw new NoSuchElementException("Eror: no hay siguiente. ");
		E retornar = cursor.element();
		try {
		if(cursor == lista.last())
			cursor = null;
		else
			cursor = lista.next(cursor);
		}
		catch(EmptyListException e) {
			System.out.println(e.getMessage());
		}
		catch(BoundaryViolationException e) {
			System.out.println(e.getMessage());
		}
		catch(InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
		return retornar; 
	}
}
