package com.flextalk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
	
	@Getter
	@Setter
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	public static class loginResponse {
		private int loginYN;
	}
	
	@Getter
	@Setter
	@ToString
	public static class enrollmentRequest {
		
		private String userId;
		private String userPw;
		private String userEmail;
		
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString
	public static class enrollmentResponse {
		private int enrollYN;
	}
	
	@Getter
	@Setter
	@ToString
	public static class findRequest {
		
		private String userId;
		private String userEmail;
	}
	
	@Getter
	@Setter
	@ToString
	public static class findResponse {
		
		private String userId;
		private String userPwLink;
	}
}
