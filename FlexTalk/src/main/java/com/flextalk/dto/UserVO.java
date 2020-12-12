package com.flextalk.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class UserVO {

	@Getter
	@Setter
	@ToString
	public static class loginRequest {
		
		private String userId;
		private String userPw;		
		
	}
	
	public static class loginResponse {
		
	}
	
	@Getter
	@Setter
	@ToString
	public static class enrollmentRequest {
		
		private String userId;
		private String userPw;
		
	}
	
	public static class enrollmentResponse {
		
	}
}
