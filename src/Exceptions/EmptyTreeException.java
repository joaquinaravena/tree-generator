package Exceptions;
/**
 * Class EmptyTreeException. 
 * Esta clase implementa una excepci�n que se lanza cuando se quiere operar sobre un �rbol que est� vac�o.
 * @author Alumnos Joaqu�n Aravena y St�fano Graziabile.
 */
public class EmptyTreeException extends Exception{
	public EmptyTreeException(String msj) {
		super(msj);
	}
}
