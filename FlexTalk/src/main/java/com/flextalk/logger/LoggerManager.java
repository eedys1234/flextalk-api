package com.flextalk.logger;

import java.util.LinkedList;
import java.util.List;

public class LoggerManager implements Logger {
	
	private List<Logger> loggers;
	
	public LoggerManager() {
		loggers = new LinkedList<Logger>();
	}
	
	public boolean add(Logger logger) {
		return loggers.add(logger);
	}
	
	public boolean remove(Logger logger) {
		return loggers.remove(logger);
	}

	@Override
	public Logger info(String data) {
		loggers.stream().forEach(logger->logger.info(data));
		return this;
	}

	@Override
	public Logger debug(String data) {
		loggers.stream().forEach(logger->logger.debug(data));
		return this;
	}

	@Override
	public Logger error(Exception e) {
		loggers.stream().forEach(logger->logger.error(e));
		return this;
	}

}
