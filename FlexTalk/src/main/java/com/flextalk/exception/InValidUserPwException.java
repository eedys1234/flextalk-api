package com.flextalk.exception;

import com.flextalk.constants.ResCodes;

public class InValidUserPwException extends RuntimeException {
	
	public InValidUserPwException() {
		super(ResCodes.INVALID_INPUT_VALUE.getMessage());
	}

}
