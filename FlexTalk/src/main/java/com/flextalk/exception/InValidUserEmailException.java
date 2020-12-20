package com.flextalk.exception;

import com.flextalk.constants.ResCodes;

public class InValidUserEmailException extends RuntimeException {

	public InValidUserEmailException() {
		super(ResCodes.INVALID_INPUT_VALUE.getMessage());
	}
}
