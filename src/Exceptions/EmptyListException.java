package Exceptions;
/**
 * Class EmptyListException.
 * Esta clase implementa una excepción que se lanza cuando se quiere operar sobre una lista que está vacía.
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile.
 * 
 */

public class EmptyListException extends Exception{
	public EmptyListException(String msg) {
		super(msg);
	}
} 
