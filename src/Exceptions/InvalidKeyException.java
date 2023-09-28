package Exceptions;
/**
 * Class InvalidKeyException. 
 * Esta clase implementa una excepción que se lanza cuando se quiere ingresar una clave que no es válida. 
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile.
 */
public class InvalidKeyException extends Exception {
	public InvalidKeyException(String msg) {
		super(msg);
	}

}
