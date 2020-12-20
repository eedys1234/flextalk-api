package com.flextalk.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum YNCode implements BaseEnumCode<String>{

	UNCHECK("N"),
	CHECK("Y");	
	
	private final String value;

	@Override
	public String getKey() {
		return name();
	}

	@Override
	public String getValue() {
		return this.value;
	}

}
