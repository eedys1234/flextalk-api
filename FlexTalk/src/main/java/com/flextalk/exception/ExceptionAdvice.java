package com.flextalk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flextalk.constants.ResCodes;
import com.flextalk.util.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

	@ExceptionHandler(DuplicationUserIdException.class)
	public ResponseEntity<ApiResponse> handleDuplicationUserIdException(DuplicationUserIdException e) {
		log.error("DuplicationUserIdException", e.getMessage());
		return new ResponseEntity<ApiResponse>(ApiResponse.of(ResCodes.DUPLICATION_INPUT_VALUE), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DuplicationEmailException.class)
	public ResponseEntity<ApiResponse> handleInvalidUserIdException(DuplicationEmailException e) {
		log.error("DuplicationEmailException", e.getMessage());
		return new ResponseEntity<ApiResponse>(ApiResponse.of(ResCodes.DUPLICATION_INPUT_VALUE), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotCreateRoomException.class)
	public ResponseEntity<ApiResponse> handleNotCreateRoomException(NotCreateRoomException e) {
		log.error("NotCreateRoomException", e.getMessage());
		return new ResponseEntity<ApiResponse>(ApiResponse.of(ResCodes.NOT_CREATE_ROOM), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InValidUserIdException.class)
	public ResponseEntity<ApiResponse> handleInValidUserIdException(InValidUserIdException e) {
		log.error("InValidUserIdException", e.getMessage());
		return new ResponseEntity<ApiResponse>(ApiResponse.of(ResCodes.NOT_FORM_ID), HttpStatus.BAD_REQUEST);		
	}

	@ExceptionHandler(InValidUserPwException.class)
	public ResponseEntity<ApiResponse> handleInValidUserPwException(InValidUserPwException e) {
		log.error("InValidUserPwException", e.getMessage());
		return new ResponseEntity<ApiResponse>(ApiResponse.of(ResCodes.NOT_FORM_PW), HttpStatus.BAD_REQUEST);		
	}

}
