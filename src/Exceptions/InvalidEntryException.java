package Exceptions;
/**
 * Class InvalidEntryException. 
 * Esta clase implementa una excepci�n que se lanza cuando se quiere ingresar una entrada que no es v�lida. 
 * @author Alumnos Joaqu�n Aravena y St�fano Graziabile.
 */
public class InvalidEntryException extends Exception {
	public InvalidEntryException(String msg) {
		super(msg);
	}
}
