package com.flextalk.common;

public class YNCodeConverter extends AbstractBaseEnumConverter<YNCode, String> {

	@Override
	protected YNCode[] getValueList() {
		return YNCode.values();
	}

}
