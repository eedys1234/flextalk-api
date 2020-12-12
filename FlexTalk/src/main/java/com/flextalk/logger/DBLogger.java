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
		//구현필요
		return this;
	}

}
