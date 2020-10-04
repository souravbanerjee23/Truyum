package com.cognizant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Menu Item Not Found!")
public class MenuItemNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MenuItemNotFoundException(Long id) {
		super("Could not find the Menu with id "+ id);
	}
}
