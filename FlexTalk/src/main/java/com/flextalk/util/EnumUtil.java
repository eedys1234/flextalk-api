package com.flextalk.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class EnumUtil {

	private EnumUtil() {
		new AssertionError();
	}
	
	public static <T, R> Optional<T> filterEnumType(Class<T> type, R input, Predicate<T> cases) {
		
		if(type.isEnum()) {
			Function<R, Predicate<T>> condition = value -> cases;
			return Arrays.stream(type.getEnumConstants())
					.filter(condition.apply(input))
					.findFirst();			
		}
		
		return Optional.ofNullable(null);
	}
}
