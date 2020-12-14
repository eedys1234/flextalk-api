package com.flextalk.exception;

import com.flextalk.constants.ResCodes;

public class DuplicationUserIdException extends RuntimeException {
	
	public DuplicationUserIdException() {
		super(ResCodes.DUPLICATION_INPUT_VALUE.getMessage());
	}
}
