package com.flextalk.user;

import javax.validation.constraints.NotEmpty;

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

		@NotEmpty(message = "���̵�� �ʼ��Է� ���Դϴ�.")
		private String userId;

		@NotEmpty(message = "��й�ȣ�� �ʼ��Է� ���Դϴ�.")
		private String userPw;		
		
	}
	
	@Getter
	@Setter
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	public static class loginResponse {
		private long userKey;
		private long loginYN;
	}
	
	@Getter
	@Setter
	@ToString
	public static class enrollmentRequest {
		
		@NotEmpty(message = "���̵�� �ʼ��Է� ���Դϴ�.")
		private String userId;
		
		@NotEmpty(message = "��й�ȣ�� �ʼ��Է� ���Դϴ�.")
		private String userPw;

		@NotEmpty(message = "�̸����� �ʼ��Է� ���Դϴ�.")
		private String userEmail;
		
		@NotEmpty
		private String userType;
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
	public static class overlapIdReqeust {
		private String userId;
	}

	@Getter
	@Setter
	@NoArgsConstructor	
	@AllArgsConstructor
	public static class overlapIdResponse { 
		private long overlapYN;
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
