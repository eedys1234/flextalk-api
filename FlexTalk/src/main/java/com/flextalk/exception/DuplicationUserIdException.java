package com.flextalk.exception;

public class DuplicationUserIdException extends RuntimeException {
	
	public DuplicationUserIdException() {
		super("UserId가 중복됩니다.");
	}
}
