package com.flextalk.logger;

public class LoggerManagerFactory {

	private LoggerManagerFactory() {
		
	}
	
	public static LoggerManager createLoggerManager() {
		return LoggerManagerHelper.getInstance();
	}
	
	private static class LoggerManagerHelper {
		private static final LoggerManager INSTANCE = new LoggerManager();
		
		public static LoggerManager getInstance() {
			return INSTANCE;
		}
	}
}
