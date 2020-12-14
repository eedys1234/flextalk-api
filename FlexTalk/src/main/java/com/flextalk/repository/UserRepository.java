package com.flextalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flextalk.user.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public int findByUserIdAndUserPw(String userId, String userPw);
}
