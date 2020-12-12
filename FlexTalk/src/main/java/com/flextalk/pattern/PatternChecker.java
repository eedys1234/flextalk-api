package com.flextalk.pattern;

import java.util.regex.Matcher;

public class PatternChecker {
	
	private PatternStore patternStore;
	
	public PatternChecker() {
		this.patternStore = PatternSingleTon.getInstance();
	}
	
	private Matcher getMatcher(String regExp, String input) {		
		return patternStore.getPattern(regExp).matcher(input);
	}
	
	public boolean valid(String regExp, String input) {		
		return getMatcher(regExp, input).find();
	}
	
	public String returnFilterString(String regExp, String input) {
		return input.replace(regExp, "");
	}
	
	private static class PatternSingleTon {
		private static final PatternStore INSTANCE = new PatternStore();
		
		public static PatternStore getInstance() {
			return INSTANCE;
		}		
	}
}
