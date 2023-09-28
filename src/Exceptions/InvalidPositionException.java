package Exceptions;
/**
 * Class InvalidPositionException. 
 * Esta clase implementa una excepci�n que se lanza cuando se ingresa una posici�n que no es v�lida.
 * @author Alumnos Joaqu�n Aravena y St�fano Graziabile.
 */
public class InvalidPositionException extends Exception {
	public InvalidPositionException(String msg) {
		super(msg);
	}
}
