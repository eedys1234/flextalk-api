package com.flextalk.room;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.flextalk.constants.ResCodes;
import com.flextalk.constants.XHeader;
import com.flextalk.exception.EmptyInputValueException;
import com.flextalk.util.ApiResponse;
import com.flextalk.util.ExceptionUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
@AllArgsConstructor
public class ChatRoomController {
		
	@Autowired
	private final ChatRoomService chatRoomService;
	
	@Autowired
	private final ChatRoomHolder chatRoomHolder;
	/**
	 * Room »ý¼º
	 * @return
	 */
	@PostMapping(value = "v1/room/{userKey}")
	public ResponseEntity<ApiResponse> create(
			@PathVariable("userKey") long userKey,
			@RequestBody @Valid ChatRoomVO.createReqeust request, 
			Errors errors,
			UriComponentsBuilder componentsBuilder) {
		
		ExceptionUtil.check(errors.hasErrors(), EmptyInputValueException.class);
				
		ChatRoomFactory factory = chatRoomHolder.createFactory();
		ChatRoom chatRoom = factory.createRoom(request.getChatroom_type(), request.getChatroom_name());

		Arrays.asList(ChatRoomValidator.ROOM, ChatRoomValidator.ROOM_TYPE).forEach(validator->{
			validator.valid(chatRoom);
		});
		
		ChatRoomVO.createResponse response = chatRoomService.create(userKey, chatRoom);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(componentsBuilder.path("/{chatroomKey}").buildAndExpand(response.getChatroom_key()).toUri());

		return new ResponseEntity<>(
				ApiResponse.of(ResCodes.OK, response), httpHeaders, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "v1/rooms/{userKey}")
	public ResponseEntity<ApiResponse> findAllRoom(
			@RequestParam("page") int pageNo,
			@RequestParam("size") int size,
			@PathVariable("userKey") long userKey) {
		
		List<ChatRoomVO.chatRoomInfo> chatRooms = chatRoomService.findRooms(userKey, pageNo, size);
		
		return new ResponseEntity<>(ApiResponse.of(ResCodes.OK, new ChatRoomVO.findRoomsResponse(chatRooms)), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "v1/room/{chatroomKey}")
	public ResponseEntity<ApiResponse> remove(
			@RequestHeader(XHeader.X_USER_ID) long userKey,
			@PathVariable("chatroomKey") long chatroomKey) {
		
		chatRoomService.remove(userKey, chatroomKey);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	
}
