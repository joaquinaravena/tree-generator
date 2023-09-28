package Exceptions;
/**
 * Class BoundaryViolationException.
 * Esta clase implementa una excepción que se lanza cuando, al recorrer una lista, se exceden los límites de la misma.
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile.
 *  
 */
public class BoundaryViolationException extends Exception {
	public BoundaryViolationException(String msg) {
		super(msg);
	}
}
