package Exceptions;
/**
 * Class InvalidOperationException. 
 * Esta clase implementa una excepción que se lanza si se quiere realizar una operación inválida sobre un árbol 
 * general. 
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile. 
 */
public class InvalidOperationException extends Exception {
	public InvalidOperationException(String msj) {
		super(msj);
	}
}
