package com.flextalk.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT u FROM User u WHERE u.userId = :user_id and u.userPw = :user_pw")
	public User findByUserIdAndUserPw(@Param("user_id") String user_id, @Param("user_pw") String user_pw);
	
	@Query("SELECT count(u) FROM User u WHERE u.userId = :user_id")
	public Long findByUserId(@Param("user_id") String user_id);

	public User findByUserKey(Long userKey);
}
