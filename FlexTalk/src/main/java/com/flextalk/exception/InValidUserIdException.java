package com.flextalk.exception;

public class InValidUserIdException extends RuntimeException {

	public InValidUserIdException() {
		super("아이디 형식이 옳바르지 않습니다.");
	}

}
