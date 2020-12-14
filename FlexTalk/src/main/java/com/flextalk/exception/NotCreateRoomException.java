package com.flextalk.exception;

import com.flextalk.constants.ResCodes;

public class NotCreateRoomException extends RuntimeException {
	public NotCreateRoomException() {
		super(ResCodes.NOT_CREATE_ROOM.getMessage());
	}
}
