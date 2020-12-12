package com.flextalk.file;

@FunctionalInterface
public interface IFileWriter<T, R extends Throwable> {
	void accept(T input) throws R;
}
