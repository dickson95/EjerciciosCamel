package com.esteban.camel.exceptions;

public class NoTransformUpperCaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoTransformUpperCaseException(){
		super("No se puede transformar a letras mayúsculas");
	}
	
	public NoTransformUpperCaseException(String message){
		super(message);
	}
}
