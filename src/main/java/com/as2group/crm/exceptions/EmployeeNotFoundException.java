package com.as2group.crm.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public EmployeeNotFoundException(Long id) {
        super("Employee not found with ID: " + id);
    }
}
