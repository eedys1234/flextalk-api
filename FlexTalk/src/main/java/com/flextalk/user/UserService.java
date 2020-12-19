package com.flextalk.user;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	public Long login(User user);
	public Long overlap(User user);
	public User enroll(User user);
	public String findUserId(String userEmail);
	public int findUserPw(String userId, String userEmail);
}
