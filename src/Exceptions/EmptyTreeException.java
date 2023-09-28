package Exceptions;
/**
 * Class EmptyTreeException. 
 * Esta clase implementa una excepción que se lanza cuando se quiere operar sobre un árbol que está vacío.
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile.
 */
public class EmptyTreeException extends Exception{
	public EmptyTreeException(String msj) {
		super(msj);
	}
}
