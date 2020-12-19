package com.flextalk.user;

import java.util.Arrays;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.flextalk.constants.ResCodes;
import com.flextalk.exception.EmptyInputValueException;
import com.flextalk.pattern.PatternChecker;
import com.flextalk.user.UserVO.findResponse;
import com.flextalk.util.ApiResponse;
import com.flextalk.util.ExceptionUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PatternChecker patternChecker;
	
	@PostMapping(value = "/login")
	public ResponseEntity<ApiResponse> login(
			@RequestBody @Valid UserVO.loginRequest request, Errors errors) {		

		ExceptionUtil.check(errors.hasErrors(), EmptyInputValueException.class);
		
		User user = User.builder()
						.userId(request.getUserId())
						.userPw(request.getUserPw())
						.build();
		
		Arrays.asList(UserValidator.ID, UserValidator.PW).forEach(validator -> {
			validator.valid(patternChecker, user);
		});
		
		Long result = userService.login(user);
		log.debug("result = {}", result.longValue());
		return new ResponseEntity<ApiResponse>(
				ApiResponse.of(ResCodes.OK, new UserVO.loginResponse(result.longValue())), HttpStatus.OK);
	}
	
	@PostMapping(value = "/enrollment")
	public ResponseEntity<ApiResponse> enroll(
			@RequestBody @Valid UserVO.enrollmentRequest request,
			UriComponentsBuilder componentsBuilder, 
			Errors errors) {

		ExceptionUtil.check(errors.hasErrors(), EmptyInputValueException.class);
		
		User user = User.builder()
						.userId(request.getUserId())
						.userPw(request.getUserPw())
						.userEmail(request.getUserEmail())
						.build();
		
		Arrays.asList(UserValidator.ID, UserValidator.PW, UserValidator.EMAIL).forEach(validator->{
			validator.valid(patternChecker, user);
		});
		
		User result_user = userService.enroll(user);
		UserVO.enrollmentResponse response = new UserVO.enrollmentResponse(!Objects.isNull(result_user.getUserKey())?1:0);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(componentsBuilder.path("/{userKey}").buildAndExpand(result_user.getUserKey()).toUri());
		
		return new ResponseEntity<>(
				ApiResponse.of(ResCodes.CREATED, response), httpHeaders, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/id-overlap/{user_id}")
	public ResponseEntity<ApiResponse> overlapId(
			@PathVariable("user_id") String userId) {
		
		User user = User.builder()
						.userId(userId)
						.build();
	
		Arrays.asList(UserValidator.ID).forEach(validator->{
			validator.valid(patternChecker, user);
		});
		
		Long result = userService.overlap(user);
		
		return new ResponseEntity<>(ApiResponse.of(ResCodes.OK, new UserVO.overlapIdResponse(result)), HttpStatus.OK);
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
