package com.esteban.camel.exception;

public class IllegalAmountOfPencilsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalAmountOfPencilsException() {
		super("Solo se pueden procesar órdenes con 144 o mas lápices");
	}

	public IllegalAmountOfPencilsException(String message) {
		super(message);
	}
	
	
	
}
