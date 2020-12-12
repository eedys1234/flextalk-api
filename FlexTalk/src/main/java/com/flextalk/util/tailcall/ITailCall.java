package com.flextalk.util.tailcall;

import java.util.stream.Stream;

@FunctionalInterface
public interface ITailCall<T> {
	
	ITailCall<T> apply();
	
	//default 메소드
	default boolean isComplete() { 
		return false;
	}
	
	//default 메소드
	default T result() {
		//Exception 추가필요
		throw new Error("");
	}
	
	default T invoke() {
		return Stream.iterate(this, ITailCall::apply)
					 .filter(ITailCall::isComplete)
					 .findFirst()
					 .get()
					 .result();
					
	}
}
