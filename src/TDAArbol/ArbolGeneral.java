package TDAArbol;
import java.util.Iterator;

import TDALista.*;
import Exceptions.*;

/**
 * Class ArbolGeneral. 
 * Esta clase implementa el TDA �rbol extendiendo a la interfaz Tree propuesta por la c�tedra. 
 * @author Alumnos Joaqu�n Aravena y St�fano Graziabile.
 * @param <E>
 */
public class ArbolGeneral<E> implements Tree<E> {
	protected int size;
	protected TNodo<E> root;
	/**
	 * Crea un �rbol vac�o. 
	 */
	public ArbolGeneral() {
		size = 0;
		root = null;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public Iterator<E> iterator(){
		PositionList<E> lista = new ListaDE<E>();
		if(size>0)
			recPreorden(root,lista);
		return (new ElementIterator<E>(lista));
	}
	
	public Iterable<Position<E>> positions(){
		PositionList<Position<E>> lista = new ListaDE<Position<E>>();
		if(size > 0)
			recPreordenPosition(root, lista);
		return lista;
	}
	
	
	public E replace(Position<E> v, E e) throws InvalidPositionException{
		TNodo<E> nuevo = checkPosition(v);
		E ret = nuevo.element();
		nuevo.setElement(e);
		return ret;
	}
	
	public Position<E> root() throws EmptyTreeException {
		if(root == null)
			throw new EmptyTreeException("El �rbol est� vac�o.");
		return root;
	}
	
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TNodo<E> nodo = checkPosition(v);
		if(nodo == root)
			throw new BoundaryViolationException("La posici�n corresponde a la ra�z. ");
		return nodo.getPadre();
	}
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException{
		TNodo<E> nodo = checkPosition(v);
		PositionList<Position<E>> ret = new ListaDE();
		for(TNodo<E> p:nodo.hijos()) {
			ret.addLast(p);
		}
		return ret;
	}
	public boolean isInternal(Position<E> v) throws InvalidPositionException{
		TNodo<E> nodo = checkPosition(v);
		return !nodo.hijos().isEmpty();
	}
	public boolean isExternal(Position<E> v) throws InvalidPositionException{
		TNodo<E> nodo = checkPosition(v);
		return nodo.hijos().isEmpty();
	}
	public boolean isRoot(Position<E> v) throws InvalidPositionException{
		TNodo<E> nodo = checkPosition(v);
		return nodo == root;
	}
	public void createRoot(E e) throws InvalidOperationException{
		if(root != null)
			throw new InvalidOperationException("Ya existe una ra�z");
		root = new TNodo<E>(e, null);
		size++;
	}
	public Position<E> addFirstChild(Position<E> p, E e) throws	InvalidPositionException{
		TNodo<E> nodo = checkPosition(p);
		if(isEmpty()) 
			throw new InvalidPositionException("�rbol vac�o");
		nodo.hijos().addFirst(new TNodo(e, nodo));
		Position<E> ret = null;
		try {
			ret = nodo.hijos().first().element();
		} catch (EmptyListException ex) {
			System.out.println(ex.getMessage());
		}
		size++;
		return ret;
	}
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException{
		TNodo<E> nodo = checkPosition(p);
		if(isEmpty()) 
			throw new InvalidPositionException("�rbol vac�o");
		nodo.hijos().addLast(new TNodo(e, nodo));
		Position<E> ret = null;
		try {
			ret = nodo.hijos().last().element();
		} catch (EmptyListException ex) {
			System.out.println(ex.getMessage());
		}
		size++;
		return ret;
	}
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException{
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hermano = checkPosition(rb);
		if(isEmpty()) 
			throw new InvalidPositionException("�rbol vac�o");
		Iterator<Position<TNodo<E>>> it = padre.hijos().positions().iterator();
		boolean encontre = false;
		Position<E> ret = null;
		
		while(it.hasNext() && !encontre) {
			Position<TNodo<E>> n = it.next();
			if(n.element() == hermano) {
				padre.hijos().addBefore(n, new TNodo<E>(e, padre));
				encontre = true;
				try {
					ret = padre.hijos().prev(n).element();
				} catch (InvalidPositionException | BoundaryViolationException ex) {
					System.out.println(ex.getMessage());
				}
			}
		}
		if(!encontre)
			throw new InvalidPositionException("El segundo par�metro ingresado no es hijo del primero");
		size++;
		return ret;
	}
	public Position<E> addAfter (Position<E> p, Position<E> lb, E e) throws InvalidPositionException{
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hermano = checkPosition(lb);
		if(isEmpty()) 
			throw new InvalidPositionException("�rbol vac�o");
		Iterator<Position<TNodo<E>>> it = padre.hijos().positions().iterator();
		Position<E> ret = null;
		boolean encontre = false;
		
		while(it.hasNext() && !encontre) {
			Position<TNodo<E>> n = it.next();
			if(n.element() == hermano) {
				encontre = true;
				padre.hijos().addAfter(n, new TNodo<E>(e, padre));
				try {
					ret = padre.hijos().next(n).element();
				}catch(InvalidPositionException|BoundaryViolationException ex) {
					System.out.println(ex.getMessage());
				}
			}
		}
		if(!encontre)
			throw new InvalidPositionException("El segundo par�metro ingresado no es hijo del primero");
		size++;
		return ret;
	}
	public void removeExternalNode (Position<E> p) throws InvalidPositionException{
		TNodo<E> nodo = checkPosition(p);
		if(isInternal(nodo))
			throw new InvalidPositionException("El nodo no es externo");
		if(nodo == root)
			root = null;
		else {
			Iterator<Position<TNodo<E>>> it = nodo.getPadre().hijos().positions().iterator();
			boolean encontre = false;
			while(it.hasNext() && !encontre) {
				Position<TNodo<E>> n = it.next();
				if(n.element() == nodo) {
					encontre = true;
					nodo.getPadre().hijos().remove(n);
				}
			}
		}
		size--;
	}
				
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> pos = checkPosition(p);
		if(!isInternal(pos))
			throw new InvalidPositionException("Nodo no interno");
		if(pos == root ) { 
			if(root.hijos().size()==1) {
				try {
					root=pos.hijos().first().element();
				}catch(EmptyListException e) {
					throw new InvalidPositionException("Error");
				}
					root.setPadre(null);
					size--;
			}
			else 
				throw new InvalidPositionException("Raiz con m�s de un hijo");
		}
		else {
			TNodo<E> padre=pos.getPadre();
			PositionList<TNodo<E>> listaHijosPadre = padre.hijos();
			Iterator<Position<TNodo<E>>> it = listaHijosPadre.positions().iterator();
			Position<TNodo<E>> aux = null;
			boolean encontre = false;
			while(it.hasNext() && !encontre) {
				aux = it.next();
				if(aux.element()==pos)
					encontre = true;
			}
			if (!encontre)
				throw new InvalidPositionException("No existe tal nodo");
			for(TNodo<E> h: pos.hijos()) {
				h.setPadre(padre);
				listaHijosPadre.addBefore(aux, h);
				}
			listaHijosPadre.remove(aux);
			size--;
		}
	}
	
	public void removeNode(Position<E> p) throws InvalidPositionException{
		if(isInternal(p))
			removeInternalNode(p);
		else
			removeExternalNode(p);
	}
	
	//Metodos auxiliares
		/**
		 * Realiza un recorrido preorden del �rbol. Se agrega la ra�z del �rbol a la lista que pasa  
		 * como par�metro  y luego se recorren en preorden cada uno de los sub�rboles derivados de 
		 * la ra�z que pasa como par�metro. 
		 * @param r Ra�z del �rbol a recorrer.
		 * @param l Lista donde se agregan los nodos del recorrido. 
		 */
		private void recPreorden(TNodo<E> r, PositionList<E> l) {
			l.addLast(r.element());
			for(TNodo<E> p: r.hijos())
				recPreorden(p, l);
		}
		/**
		 * Realiza un recorrido de posiciones en preorden del �rbol. Se agrega la posici�n de la ra�z del �rbol a 
		 * la lista que pasa como par�metro y luego se recorren en preorden cada uno de los sub�rboles derivados 
		 * de la ra�z que pasa como par�metro. 
		 * 
		 * @param r Ra�z del �rbol a recorrer. 
		 * @param l Lista donde se agregan las posiciones del recorrido. 
		 */
		private void recPreordenPosition(TNodo<E> r, PositionList<Position<E>> l) {
		l.addLast(r);
		for(TNodo<E> p: r.hijos())
			recPreordenPosition(p,l);
		}
		/**
		 * Chequea que la posici�n ingresada por par�metro sea v�lida. 
		 * @param v Posici�n a validar.
		 * @return El nodo de la posici�n ya validada. 
		 * @throws InvalidPositionException Si el �rbol est� vaci�, si la posici�n ingresada por par�metro es nula,
		 * o si la posici�n corresponde a otro �rbol. 
		 */
		private TNodo<E> checkPosition(Position<E> v) throws InvalidPositionException{
			TNodo<E> nodo = null;
			if(size == 0) throw new InvalidPositionException("Arbol vacio");
			if(v == null) throw new InvalidPositionException("Posicion invalidada");
			try {
				nodo = (TNodo<E>) v;
			}catch (ClassCastException e) {
				throw new InvalidPositionException("Posicion Invalida");
			}
			return nodo;
		}
}