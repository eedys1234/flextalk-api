package com.flextalk.constants;

import lombok.Getter;

@Getter
public enum ResCodes {

	OK(200, "C0000", "��û ����"),
	INVALID_INPUT_VALUE(400, "C0001", "�Է��Ķ���� �� ����"),
	DUPLICATION_INPUT_VALUE(400, "C0002", "�ߺ��� ��"),
	
	NOT_CREATE_ROOM(400, "F0000", "ä�ù� ���� �Ұ�"),
	NOT_FORM_ID(400, "F0001", "���̵� ���� ����ġ"),
	NOT_FORM_PW(400, "F0002", "��й�ȣ ���� ����ġ");
	
	private int status;
	private String code;
	private String message;
	
	ResCodes(int status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
}
