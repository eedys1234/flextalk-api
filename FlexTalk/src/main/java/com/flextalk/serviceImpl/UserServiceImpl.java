package com.flextalk.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flextalk.constants.RegExp;
import com.flextalk.exception.InValidUserIdException;
import com.flextalk.exception.InValidUserPwException;
import com.flextalk.pattern.PatternChecker;
import com.flextalk.repository.UserRepository;
import com.flextalk.service.UserService;
import com.flextalk.user.User;
import com.flextalk.util.ExceptionUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public int login(String userId, String userPw) {
		
		ExceptionUtil.check("".equals(userId), InValidUserIdException.class);
		
		ExceptionUtil.check("".equals(userPw), InValidUserPwException.class);

		userRepository.login(new User(userId, userPw));
		return 0;
	}

	@Override
	public int enroll(String userId, String userPw) {
		
		PatternChecker patternChecker = new PatternChecker();
		
		ExceptionUtil.check(!patternChecker.valid(RegExp.ID_REGEXP, userId), InValidUserIdException.class);

		ExceptionUtil.check(!patternChecker.valid(RegExp.PW_REGEXP, userPw), InValidUserPwException.class);
		
		userRepository.enroll(new User(userId, userPw));

		return 1;
	}

}
