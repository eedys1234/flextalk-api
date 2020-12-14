package com.flextalk.serviceImpl;

import java.util.Objects;

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

		return userRepository.findByUserIdAndUserPw(userId, userPw);		
	}

	@Override
	public int enroll(String userId, String userPw, String userEmail) {
		
		PatternChecker patternChecker = new PatternChecker();
		
		ExceptionUtil.check(!patternChecker.valid(RegExp.ID_REGEXP, userId), InValidUserIdException.class);

		ExceptionUtil.check(!patternChecker.valid(RegExp.PW_REGEXP, userPw), InValidUserPwException.class);
		
		User user = userRepository.save(new User(userId, userPw, userEmail));
		
		return !Objects.isNull(user)?1:0;
	}

	@Override
	public String findUserId(String userEmail) {
		return null;
	}

	@Override
	public int findUserPw(String userId, String userEmail) {
		return 0;
	}

}
