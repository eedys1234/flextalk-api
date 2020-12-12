package com.flextalk.util;

import java.util.function.Supplier;

public class RetryCaller {
	
	private final int RETRY_COUNT = 5;
	private int retry;
	
	public RetryCaller() {
		this.retry = RETRY_COUNT;
	}
	
	public RetryCaller(int retry) {
		this.retry = retry;
	}
	
	public <T> T call(Supplier<T> caller, RuntimeException run) throws InterruptedException {
		
		T output;
		while(retry-- > 0) {
			try {
				output = caller.get();
				return output;
			}
			catch(Exception e) {
				Thread.sleep(50);
			}
		}
		throw run;
	}
	
	public <T, R> T call(Supplier<T> caller, Supplier<R> closer, RuntimeException run) throws InterruptedException {
		
		T output;
		while(retry-- > 0) {
			try {
				output = caller.get();
				return output;
			}
			catch(Exception e) {
				Thread.sleep(50);
			}
			finally {
				closer.get();
			}
		}
		throw run;
	}
}
