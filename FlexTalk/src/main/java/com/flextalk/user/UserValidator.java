package com.flextalk.user;

import com.flextalk.constants.RegExp;
import com.flextalk.exception.InValidUserEmailException;
import com.flextalk.exception.InValidUserIdException;
import com.flextalk.exception.InValidUserPwException;
import com.flextalk.pattern.PatternChecker;
import com.flextalk.util.ExceptionUtil;

public enum UserValidator {
		
	ID {
		
		@Override
		public void valid(PatternChecker patternChecker, User user) {
			ExceptionUtil.check(!patternChecker.valid(RegExp.ID_REGEXP, user.getUser_id()), InValidUserIdException.class);
		}
	},
	PW {
		
		@Override
		public void valid(PatternChecker patternChecker, User user) {
			ExceptionUtil.check(!patternChecker.valid(RegExp.PW_REGEXP, user.getUser_pw()), InValidUserPwException.class);			
		}
		
	},
	EMAIL {
		
		@Override
		public void valid(PatternChecker patternChecker, User user) {
			ExceptionUtil.check(!patternChecker.valid(RegExp.EMAIL_REGEXP, user.getUser_email()), InValidUserEmailException.class);			
		}
	};
	
	public abstract void valid(PatternChecker patternChecker, User user);
}
