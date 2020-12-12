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

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public int login(String userId, String userPw) {
		
		userRepository.login(new User(userId, userPw));
		return 0;
	}

	@Override
	public int enroll(String userId, String userPw) {
		
		PatternChecker patternChecker = new PatternChecker();
		
		if(!patternChecker.valid(RegExp.ID_REGEXP, userId)) {
			throw new InValidUserIdException();
		}
		
		if(!patternChecker.valid(RegExp.PW_REGEXP, userPw)) {
			throw new InValidUserPwException();
		}
		
		//생성 로직 추가
		userRepository.enroll(new User(userId, userPw));

		return 1;
	}

}
