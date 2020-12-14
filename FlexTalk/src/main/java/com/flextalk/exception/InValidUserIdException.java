package com.flextalk.exception;

import com.flextalk.constants.ResCodes;

public class InValidUserIdException extends RuntimeException {

	public InValidUserIdException() {
		super(ResCodes.INVALID_INPUT_VALUE.getMessage());
	}

}
