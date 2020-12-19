package com.flextalk.exception;

import com.flextalk.constants.ResCodes;

public class NotAddParticipantException extends RuntimeException {

	public NotAddParticipantException() {
		super(ResCodes.NOT_ADD_PARTICIPATN.getMessage());
	}
}
