package Exceptions;
/**
 * Class InvalidEntryException. 
 * Esta clase implementa una excepción que se lanza cuando se quiere ingresar una entrada que no es válida. 
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile.
 */
public class InvalidEntryException extends Exception {
	public InvalidEntryException(String msg) {
		super(msg);
	}
}
