package com.flextalk.logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileLogger implements Logger {

	@Override
	public Logger info(String data) {
		log.info("[{}] {}", now(), data);
		return this;
	}

	@Override
	public Logger debug(String data) {
		log.debug("[{}] {}", now(), data);
		return this;
	}

	@Override
	public Logger error(Exception e) {
		log.error("[{}] error = {}", now(), e.getMessage());
		return this;
	}

}
