package Exceptions;
/**
 * Class InvalidKeyException. 
 * Esta clase implementa una excepci�n que se lanza cuando se quiere ingresar una clave que no es v�lida. 
 * @author Alumnos Joaqu�n Aravena y St�fano Graziabile.
 */
public class InvalidKeyException extends Exception {
	public InvalidKeyException(String msg) {
		super(msg);
	}

}
