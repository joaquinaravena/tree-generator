package TDALista;

import java.util.Iterator;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;

/**
 * Class ListaDE. 
 * Implementa una Lista con Nodos doblemente enlazados.
 * @author Alumnos Joaqu�n Aravena y St�fano Graziabile. 
 * @param <E>
 */
public class ListaDE<E> implements PositionList<E> {
	//Atributos de instancia
	protected DNodo<E> header;
	protected DNodo<E> trailer;
	protected int tama�o;
	
	//Constructor
	/**
	 * Crea una lista vac�a con dos nodos centinela. 
	 */
	public ListaDE() {
		header = new DNodo<E>();
		trailer = new DNodo<E>();
		header.setNext(trailer);
		trailer.setPrev(header);
		tama�o = 0;
	}
	public int size() {
		return tama�o;
	}
	public boolean isEmpty() {
		return tama�o == 0;
	}
	/**
	 * Chequea que la posici�n pasada por par�metro sea v�lida. 
	 * @param p Posici�n a chequear. 
	 * @return Nodo en la posici�n pasada por par�metro.
	 * @throws InvalidPositionException si la posici�n es alguno de los nodos centinela, si la posici�n es nula,
	 * si la posici�n fue eliminada previamente, o si la posici�n corresponde a otra lista.  
	 */
	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		try {
		if(p == header || p == trailer) throw new InvalidPositionException("Posici�n centinela.");
		if(p == null) throw new InvalidPositionException("Posici�n nula. ");
		if(p.element() == null) throw new InvalidPositionException("Posici�n eliminada previamente. ");
		return (DNodo<E>) p; }
		catch(ClassCastException e) {
			throw new InvalidPositionException("La posici�n corresponde a otra lista. ");
		}
	}
	/**
	 * Agrega un Nodo nuevo con elemento e entre los Nodos que pasan como par�metro. 
	 * @param e Elemento del nodo a insertar. 
	 * @param pred Nodo antecesor al nuevo nodo.
	 * @param succ Nodo sucesor al nuevo nodo.
	 * @return Nodo insertado. 
	 */
	private Position<E> addBetween(E e, DNodo<E> pred, DNodo<E> succ) { //pred tiene que ser el anterior de succ
		DNodo<E> newest = new DNodo<E>(e,pred,succ);
		pred.setNext(newest);
		succ.setPrev(newest);
		return newest; 
	}
	public Position<E> first() throws EmptyListException { 
		if(isEmpty())
			throw new EmptyListException("Lista vac�a. ");
		return header.getNext(); 
	}
	public Position<E> last() throws EmptyListException {
		if(isEmpty())
			throw new EmptyListException("Lista vac�a. ");
		return trailer.getPrev();
	}
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> n = checkPosition(p); 
		if(n.getNext() == trailer)
			throw new BoundaryViolationException("�ltima posici�n de la lista. ");
		return n.getNext(); 
	}
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> n = checkPosition(p);
		if(n.getPrev() == header)
			throw new BoundaryViolationException("Primer posici�n de la lista. ");
		return n.getPrev();
	}
	public void addFirst(E e) {
		addBetween(e,header,header.getNext()); 
		tama�o++;
	}
	public void addLast(E e) {
		addBetween(e,trailer.getPrev(), trailer);
		tama�o++;
	}
	public void addAfter(Position<E> p, E e) throws InvalidPositionException {
		DNodo<E> n = checkPosition(p);
		addBetween(e,n,n.getNext()); 
		tama�o++;
	}
	public void addBefore(Position<E> p, E e) throws InvalidPositionException {
		DNodo<E> n = checkPosition(p);
		addBetween(e,n.getPrev(),n); 
		tama�o++;
	}
	public E remove(Position<E> p) throws InvalidPositionException {
		DNodo<E> n = checkPosition(p);
		DNodo<E> predecesor = n.getPrev();
		DNodo<E> succesor = n.getNext();
		predecesor.setNext(succesor);		//une los nodos que rodean a n
		succesor.setPrev(predecesor);
		tama�o--;
		E retornar = n.element();
		n.setElement(null);
		n.setNext(null);
		n.setPrev(null);
		return retornar;
	}
	public E set(Position<E> p, E e) throws InvalidPositionException {
		DNodo<E> n = checkPosition(p);
		E retornar = n.element();
		n.setElement(e);
		return retornar; 
	}
	public Iterator<E> iterator() {
		return (new ElementIterator<E>(this));
	}
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> p = new ListaDE<Position<E>>();
		try {
		if(!isEmpty()) {
			Position<E> pos = first();
			while(pos != last()) {
				p.addLast(pos);
				pos = next(pos); }
			p.addLast(pos); }}
		catch(EmptyListException e) {
			System.out.println(e.getMessage());	
		}
		catch(BoundaryViolationException e) {
			System.out.println(e.getMessage());	
		}
		catch(InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
		return p;
	}
}
 