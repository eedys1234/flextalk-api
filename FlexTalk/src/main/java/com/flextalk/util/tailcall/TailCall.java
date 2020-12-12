package com.flextalk.util.tailcall;

public class TailCall {

	public static <T> ITailCall<T> call(ITailCall<T> nextCall) {
		return nextCall;
	}
	
	public static <T> ITailCall<T> done(T value) {
		return new ITailCall<T>() {

			@Override
			public ITailCall<T> apply() {
				throw new Error("");
			}

			@Override
			public boolean isComplete() {
				return true;
			}

			@Override
			public T result() {
				return value;
			}
			
		};
	}
}
