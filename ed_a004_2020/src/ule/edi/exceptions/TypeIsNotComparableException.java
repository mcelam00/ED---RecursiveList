package ule.edi.exceptions;

public class TypeIsNotComparableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6009933231911477507L;
	/**
	 * Es interesante proporcionar un mensaje que explique la causa de la excepción
	 * 
	 * @param hint información sobre la causa de la excepción
	 */
	public TypeIsNotComparableException() {
		super("El tipo de los elementos no es comparable."); //Llamamos al constructor de la clase padre que tiene un par�metro que es el mensaje.
	}
	
}
