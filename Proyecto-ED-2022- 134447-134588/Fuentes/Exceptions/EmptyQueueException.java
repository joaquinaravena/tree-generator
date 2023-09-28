package Exceptions;
/**
 * Class EmptyQueueException.
 * Esta clase implementa una excepci�n que se lanza cuando se quiere operar sobre una cola que est� vac�a. 
 * @author Alumnos Joaqu�n Aravena y St�fano Graziabile.
 */
public class EmptyQueueException extends Exception {
	public EmptyQueueException(String msg) {
		super(msg);
	}
}
