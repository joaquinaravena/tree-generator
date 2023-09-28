package Exceptions;
/**
 * Class InvalidOperationException. 
 * Esta clase implementa una excepci�n que se lanza si se quiere realizar una operaci�n inv�lida sobre un �rbol 
 * general. 
 * @author Alumnos Joaqu�n Aravena y St�fano Graziabile. 
 */
public class InvalidOperationException extends Exception {
	public InvalidOperationException(String msj) {
		super(msj);
	}
}
