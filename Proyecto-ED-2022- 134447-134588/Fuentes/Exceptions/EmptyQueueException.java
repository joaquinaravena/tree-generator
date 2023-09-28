package Exceptions;
/**
 * Class EmptyQueueException.
 * Esta clase implementa una excepción que se lanza cuando se quiere operar sobre una cola que está vacía. 
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile.
 */
public class EmptyQueueException extends Exception {
	public EmptyQueueException(String msg) {
		super(msg);
	}
}
