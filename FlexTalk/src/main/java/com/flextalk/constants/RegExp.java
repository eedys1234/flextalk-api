package com.flextalk.constants;

import java.util.regex.Matcher;

public class RegExp {
	
	//����ǥ���� ������ ��!!!
	public static final String ID_REGEXP = "[A-Za-z_][A-Za-z0-9_]{3,11}";
	public static final String EMAIL_REGEXP = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-z] {2,6}$";
	public static final String PW_REGEXP = "";

	private RegExp() {
		new AssertionError();
	}
	
}
