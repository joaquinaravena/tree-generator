package TDAArbol;
import TDALista.*;
/**
 * Class TNodo. 
 * Esta clase implementa los TNodo que se utilizarán el la implementación del TDA Árbol. 
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile.
 * @param <E>
 */
public class TNodo<E> implements Position<E>{
	private E element;
	protected PositionList<TNodo<E>> hijos;
	private TNodo<E> padre;
	/**
	 * Crea un nodo con su elemento y una referencia a su nodo padre. 
	 * @param el Elemento del nuevo nodo.
	 * @param p Nodo padre del nuevo nodo. 
	 */
	public TNodo(E el, TNodo<E> p)
	  { element=el;
	    padre=p;
	    hijos= new ListaDE<TNodo<E>>();
	    }
	/**
	 * Examina el nodo padre del nodo. 
	 * @return El Nodo padre del nodo. 
	 */
	public TNodo<E> getPadre(){
		return padre;
	}
	/**
	 * Examina el elemento del nodo.
	 * @return El elemento del nodo. 
	 */
	public E element() {
		return element;
	}
	/**
	 * Examina la lista de hijos del nodo.
	 * @return La lista de hijos del nodo. 
	 */
	protected PositionList<TNodo<E>> hijos(){
		return hijos;
	}
	/**
	 * Asigna un elemento al nodo. 
	 * @param e Elemento a asignar. 
	 */
	public void setElement(E e) {
		element = e;
	}
	/**
	 * Asigna un nodo padre al nodo.
	 * @param p Padre a asignar del nodo. 
	 */
	public void setPadre(TNodo<E> p) {
		padre = p;
	}
	
}
