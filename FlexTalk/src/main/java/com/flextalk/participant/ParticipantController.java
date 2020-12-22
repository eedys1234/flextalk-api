package com.flextalk.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flextalk.constants.ResCodes;
import com.flextalk.util.ApiResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@AllArgsConstructor
@Slf4j
public class ParticipantController {
	
	@Autowired
	private final ParticipantService participantRepository;
	
	@PostMapping(value = "/v1/participant")
	public ResponseEntity<ApiResponse> visit(
			@RequestBody ParticipantVO.visitRequest request) {
		
		
		participantRepository.visit(request.getUser_keys(), request.getChatroom_key());
		
		return new ResponseEntity<ApiResponse>(ApiResponse.of(ResCodes.OK), HttpStatus.OK);
	}
	
}
