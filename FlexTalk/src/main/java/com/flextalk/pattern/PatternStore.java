package com.flextalk.pattern;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * Pattern ���� store
 * ���Խ��� ���� Pattern ��ü�� �ѹ� ����� ���� ������ ���� ����
 * @author ����ȯ
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
