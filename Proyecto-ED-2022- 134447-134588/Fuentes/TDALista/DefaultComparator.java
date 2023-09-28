package TDALista;

import java.util.Comparator;

/**
 * Class DefaultComparator.
 * Invoca un comparador de elementos de tipo genérico E. 
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile. 
 * 
 * @param <E>
 */
public class DefaultComparator<E> implements Comparator<E> {
	public int compare(E a, E b) throws ClassCastException {
		return((Comparable<E>) a).compareTo(b);
	}
}
