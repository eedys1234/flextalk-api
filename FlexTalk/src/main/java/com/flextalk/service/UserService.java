package com.flextalk.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	public int login(String userId, String userPw);
	public int enroll(String userId, String userPw);
}
