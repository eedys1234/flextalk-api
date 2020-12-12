package com.flextalk.util;

import com.flextalk.constants.ResCodes;
import lombok.ToString;

@ToString
public class ApiResponse {

	private int status;
	private String message;
	private Object body;
	
	private ApiResponse(int status, String message, Object body) {
		this.status = status;
		this.message = message;
		this.body = body;
	}

	public static ApiResponse of(int status, String message) {
		return new ApiResponse(status, message, null);
	}
	
	public static ApiResponse of(ResCodes resCodes) {
		return new ApiResponse(resCodes.getStatus(), resCodes.getMessage(), null);
	}
	
	public static ApiResponse of(int status, String message, Object body) {
		return new ApiResponse(status, message, body);		
	}

	public static ApiResponse of(ResCodes resCodes, Object body) {
		return new ApiResponse(resCodes.getStatus(), resCodes.getMessage(), body);		
	}

}
