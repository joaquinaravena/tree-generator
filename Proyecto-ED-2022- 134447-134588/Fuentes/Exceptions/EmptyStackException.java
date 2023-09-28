package Exceptions;
/**
 * Class EmptyStackException. 
 * Esta clase implementa una excepci�n que se lanza cuando se quiere operar sobre una pila que est� vac�a. 
 * @author Alumnos Joaqu�n Aravena y St�fano Graziabile.
 */

public class EmptyStackException extends Exception {
	public EmptyStackException(String msg) {
		super(msg);
	}
}
