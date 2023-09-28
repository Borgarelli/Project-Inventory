package com.as2group.crm.exceptions;

public class ComputerNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public ComputerNotFoundException(Long id) {
        super("Computer not found with ID: " + id);
    }
}
