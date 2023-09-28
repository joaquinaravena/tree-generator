package Exceptions;
/**
 * Class EmptyStackException. 
 * Esta clase implementa una excepción que se lanza cuando se quiere operar sobre una pila que está vacía. 
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile.
 */

public class EmptyStackException extends Exception {
	public EmptyStackException(String msg) {
		super(msg);
	}
}
