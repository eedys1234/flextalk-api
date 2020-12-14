package com.flextalk.exception;

import com.flextalk.constants.ResCodes;

public class DuplicationEmailException extends RuntimeException {

	public DuplicationEmailException() {
		super(ResCodes.DUPLICATION_INPUT_VALUE.getMessage());
	}
}
