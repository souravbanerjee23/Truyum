package com.cognizant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MenuItemNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(MenuItemNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	String menuNotFoundHandler(MenuItemNotFoundException ex) {
		return ex.getMessage();
	}
}
