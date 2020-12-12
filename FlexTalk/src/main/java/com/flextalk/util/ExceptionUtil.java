package com.flextalk.util;

public class ExceptionUtil {

	private ExceptionUtil() {
		new AssertionError();
	}
	
	public static <T extends RuntimeException> void check(boolean condition, Class<T> exceptionClass) throws T {
		
		final T exception;
		
		if(condition) {			
			try {
				exception = exceptionClass.getConstructor().newInstance();							
			}catch(Exception e) {
				throw new RuntimeException(e);
			}
			throw exception;
		}
	}

	public static <T extends RuntimeException> void check(boolean condition, String message, Class<T> exceptionClass) throws T {
		
		final T exception;
		
		if(condition) {			
			try {
				exception = exceptionClass.getConstructor(String.class).newInstance(message);							
			}catch(Exception e) {
				throw new RuntimeException(e);
			}
			throw exception;
		}
	}

}
