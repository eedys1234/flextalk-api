package com.flextalk.pattern;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * Pattern 관리 store
 * 정규식을 위한 Pattern 객체를 한번 만들어 놓고 여러번 쓰기 위함
 * @author 이정환
 *
 */
public class PatternStore {
		
	private Function<String, Pattern> pattern = regexp -> createAndCachePattern(regexp);
	
	PatternStore() {
		
	}
	
	public Pattern getPattern(String regexp) {
		return pattern.apply(regexp);
	}
		
	private synchronized Pattern createAndCachePattern(String regexp) {		

		class PatternFactory implements Function<String, Pattern> {

			private final Map<String, Pattern> store = new ConcurrentHashMap<String, Pattern>();
			@Override
			public Pattern apply(String regexp) {
				return store.computeIfAbsent(regexp, key->Pattern.compile(key));
			}
		}
		
		if(!PatternFactory.class.isInstance(pattern)) {
			pattern = new PatternFactory();
		}

		return pattern.apply(regexp);
	}
}
