package TDALista;

/**
 * Class Nodo. 
 * Implementa un nodo simplemente enlazado. 
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile
 * @param <E>
 */
public class Nodo<E> implements Position<E> {

		private Nodo<E> siguiente; 
		private E head; 
		/**
		 * Crea un Nodo simplemente enlazado con sus atributos nulos. 
		 */
		public Nodo() {
			siguiente = null;
			head = null;
		}
		/**
		 * Crea un Nodo simplemente enlazado.  
		 * @param head Elemento del nodo.
		 * @param sig Nodo sucesor al Nodo a crear.
		 */
		public Nodo(E head, Nodo<E> sig) {
			siguiente = sig;
			this.head = head;
		}
		/**
		 * Examina el elemento del Nodo.
		 * @return Elemento del Nodo. 
		 */
		public E element() {
			return head; 
		}
		/**
		 * Examina el Nodo sucesor al Nodo
		 * @return Nodo siguiente al Nodo. 
		 */
		public Nodo<E> getSiguiente() {
			return siguiente;
		}
		/**
		 * Asigna un Nodo siguiente al Nodo. 
		 * @param siguiente Nodo siguiente al Nodo. 
		 */
		public void setSiguiente(Nodo<E> siguiente) {
			this.siguiente = siguiente;
		}
		/**
		 * Examina el elemento del Nodo.
		 * @return Elemento del Nodo. 
		 */
		public E getHead() {
			return head;
		}
		/**
		 * Asgina un elemento al Nodo. 
		 * @param head Elemento que se asigna al Nodo. 
		 */
		public void setHead(E head) {
			this.head = head;
		}
}
