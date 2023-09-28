package Exceptions;
/**
 * Class BoundaryViolationException.
 * Esta clase implementa una excepci�n que se lanza cuando, al recorrer una lista, se exceden los l�mites de la misma.
 * @author Alumnos Joaqu�n Aravena y St�fano Graziabile.
 *  
 */
public class BoundaryViolationException extends Exception {
	public BoundaryViolationException(String msg) {
		super(msg);
	}
}
