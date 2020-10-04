package com.cognizant.exception;

public class ItemNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemNotFoundException() {
		super("The Cart Item is not present in cart.");
	}
}
