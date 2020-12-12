package com.flextalk.exception;

public class DuplicationEmailException extends RuntimeException {

	public DuplicationEmailException() {
		super("중복된 Email이 존재합니다.");
	}
}
