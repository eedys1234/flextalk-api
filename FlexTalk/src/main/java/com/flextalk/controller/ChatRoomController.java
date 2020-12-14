package com.flextalk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flextalk.constants.ResCodes;
import com.flextalk.constants.XHeader;
import com.flextalk.dto.ChatRoomVO;
import com.flextalk.service.ChatRoomService;
import com.flextalk.user.Participant;
import com.flextalk.util.ApiResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class ChatRoomController {
		
	@Autowired
	private ChatRoomService chatRoomService;
	/**
	 * Room »ý¼º
	 * @return
	 */
	@PostMapping(value = "/room")
	public ResponseEntity<ApiResponse> create(
			@RequestHeader(XHeader.X_USER_ID) long userKey,
			@RequestBody ChatRoomVO.createReqeust request) {
		
		ChatRoomVO.createResponse response = chatRoomService.create(userKey, request.getChatroom_type(), request.getChatroom_name());
		return new ResponseEntity<>(
				ApiResponse.of(ResCodes.OK, response), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/rooms/{userKey}")
	public ResponseEntity<ApiResponse> findAllRoom(
			@PathVariable("userKey") long userKey) {
		
		chatRoomService.findAllRoom(userKey);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/room/{chatroomKey}")
	public ResponseEntity<ApiResponse> remove(
			@RequestHeader(XHeader.X_USER_ID) long userKey,
			@PathVariable("chatroomKey") long chatroomKey) {
		
		chatRoomService.remove(userKey, chatroomKey);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	
}
