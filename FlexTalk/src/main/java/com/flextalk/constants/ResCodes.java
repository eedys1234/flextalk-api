package com.flextalk.constants;

import lombok.Getter;

@Getter
public enum ResCodes {

	OK(200, "C0000", "요청 성공"),
	INVALID_INPUT_VALUE(400, "C0001", "입력파라미터 값 누락"),
	DUPLICATION_INPUT_VALUE(400, "C0002", "중복된 값"),
	
	NOT_CREATE_ROOM(400, "F0000", "채팅방 생성 불가"),
	NOT_FORM_ID(400, "F0001", "아이디 형식 불일치"),
	NOT_FORM_PW(400, "F0002", "비밀번호 형식 불일치");
	
	private int status;
	private String code;
	private String message;
	
	ResCodes(int status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
}
