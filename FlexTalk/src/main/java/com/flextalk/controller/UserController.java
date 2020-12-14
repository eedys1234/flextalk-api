package com.flextalk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flextalk.constants.ResCodes;
import com.flextalk.dto.UserVO;
import com.flextalk.dto.UserVO.findResponse;
import com.flextalk.service.UserService;
import com.flextalk.util.ApiResponse;

@RestController
@RequestMapping(value = "/v1", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/login")
	public ResponseEntity<ApiResponse> login(
			@RequestBody UserVO.loginRequest request) {		
		
		int result = userService.login(request.getUserId(), request.getUserPw());
		
		return new ResponseEntity<ApiResponse>(
				ApiResponse.of(ResCodes.OK, new UserVO.loginResponse(result)), HttpStatus.OK);
	}
	
	@PostMapping(value = "/enrollment")
	public ResponseEntity<ApiResponse> enroll(
			@RequestBody UserVO.enrollmentRequest request) {
		
		int result = userService.enroll(request.getUserId(), request.getUserPw(), request.getUserEmail());
		
		return new ResponseEntity<>(
				ApiResponse.of(ResCodes.OK, new UserVO.enrollmentResponse(result)), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/id")
	public ResponseEntity<ApiResponse> findId(
			@RequestBody UserVO.findRequest request) {

		String userId = userService.findUserId(request.getUserEmail());
		UserVO.findResponse response = new findResponse();
		response.setUserId(userId);
		
		return new ResponseEntity<ApiResponse>(
				ApiResponse.of(ResCodes.OK, response), HttpStatus.OK);
	}
	
	@GetMapping(value = "/pw")
	public ResponseEntity<ApiResponse> findPw(
			@RequestBody UserVO.findRequest request) {
		
		int result = userService.findUserPw(request.getUserId(), request.getUserEmail());
		UserVO.findResponse response = new findResponse();
		
		return new ResponseEntity<ApiResponse>(
				ApiResponse.of(ResCodes.OK, response), HttpStatus.OK);
	}
}
