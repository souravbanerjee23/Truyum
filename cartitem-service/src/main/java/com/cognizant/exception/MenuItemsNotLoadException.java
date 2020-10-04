package com.cognizant.exception;

public class MenuItemsNotLoadException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuItemsNotLoadException() {
		super("MenuItems can not be Loaded.");
	}
}
