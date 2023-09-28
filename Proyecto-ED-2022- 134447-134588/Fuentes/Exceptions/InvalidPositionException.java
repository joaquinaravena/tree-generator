package Exceptions;
/**
 * Class InvalidPositionException. 
 * Esta clase implementa una excepción que se lanza cuando se ingresa una posición que no es válida.
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile.
 */
public class InvalidPositionException extends Exception {
	public InvalidPositionException(String msg) {
		super(msg);
	}
}
