package Exceptions;
/**
 * Class EmptyListException.
 * Esta clase implementa una excepci�n que se lanza cuando se quiere operar sobre una lista que est� vac�a.
 * @author Alumnos Joaqu�n Aravena y St�fano Graziabile.
 * 
 */

public class EmptyListException extends Exception{
	public EmptyListException(String msg) {
		super(msg);
	}
} 
