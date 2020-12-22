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
		private String user_id;

		@NotEmpty(message = "��й�ȣ�� �ʼ��Է� ���Դϴ�.")
		private String user_pw;		
		
	}
	
	@Getter
	@Setter
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	public static class loginResponse {
		private long user_key;
		private long login_YN;
	}
	
	@Getter
	@Setter
	@ToString
	public static class enrollmentRequest {
		
		@NotEmpty(message = "���̵�� �ʼ��Է� ���Դϴ�.")
		private String user_id;
		
		@NotEmpty(message = "��й�ȣ�� �ʼ��Է� ���Դϴ�.")
		private String user_pw;

		@NotEmpty(message = "�̸����� �ʼ��Է� ���Դϴ�.")
		private String user_email;
		
		@NotEmpty
		private String user_type;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString
	public static class enrollmentResponse {
		private int enroll_YN;
	}
	
	@Getter
	@Setter
	@ToString
	public static class overlapIdReqeust {
		private String user_id;
	}

	@Getter
	@Setter
	@NoArgsConstructor	
	@AllArgsConstructor
	public static class overlapIdResponse { 
		private long overlap_YN;
	}
	
	@Getter
	@Setter
	@ToString
	public static class findRequest {
		
		private String user_id;
		private String user_email;
	}
	
	@Getter
	@Setter
	@ToString
	public static class findResponse {
		
		private String user_id;
		private String user_pw_Link;
	}
}
