package com.flextalk.util.tailcall;

import java.util.stream.Stream;

@FunctionalInterface
public interface ITailCall<T> {
	
	ITailCall<T> apply();
	
	//default �޼ҵ�
	default boolean isComplete() { 
		return false;
	}
	
	//default �޼ҵ�
	default T result() {
		//Exception �߰��ʿ�
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
