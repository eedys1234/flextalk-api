package com.flextalk.logger;

public class DBLogger implements Logger {

	@Override
	public Logger info(String data) {
		return this;
	}

	@Override
	public Logger debug(String data) {
		return this;
	}

	@Override
	public Logger error(Exception e) {
		//�����ʿ�
		return this;
	}

}
