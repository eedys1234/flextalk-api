package com.flextalk.exception;

public class InValidUserPwException extends RuntimeException {
	
	public InValidUserPwException() {
		super("비밀번호 형식이 옳바르지 않습니다.");
	}

}
