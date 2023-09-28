package TDALista;

import java.util.Comparator;

/**
 * Class DefaultComparator.
 * Invoca un comparador de elementos de tipo gen�rico E. 
 * @author Alumnos Joaqu�n Aravena y St�fano Graziabile. 
 * 
 * @param <E>
 */
public class DefaultComparator<E> implements Comparator<E> {
	public int compare(E a, E b) throws ClassCastException {
		return((Comparable<E>) a).compareTo(b);
	}
}
