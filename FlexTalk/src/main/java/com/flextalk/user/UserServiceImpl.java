package com.flextalk.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flextalk.constants.RegExp;
import com.flextalk.exception.DuplicationUserIdException;
import com.flextalk.exception.InValidUserIdException;
import com.flextalk.exception.InValidUserPwException;
import com.flextalk.pattern.PatternChecker;
import com.flextalk.util.ExceptionUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Long login(User user) {
		return userRepository.findByUserIdAndUserPw(user.getUserId(), user.getUserPw());		
	}

	@Override
	public User enroll(User user) {		
		
		Long result = overlap(user);
		
		ExceptionUtil.check(result > 0, DuplicationUserIdException.class);

		return userRepository.save(user);
	}
	
	@Override
	public Long overlap(User user) {
		return userRepository.findByUserId(user.getUserId());
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
