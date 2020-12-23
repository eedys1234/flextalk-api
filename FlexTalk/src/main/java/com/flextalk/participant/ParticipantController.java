package com.flextalk.participant;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.flextalk.constants.ResCodes;
import com.flextalk.exception.EmptyInputValueException;
import com.flextalk.util.ApiResponse;
import com.flextalk.util.ExceptionUtil;

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
			@RequestBody @Valid ParticipantVO.inviteRequest request,
			Errors errors,
			UriComponentsBuilder b) {
		
		ExceptionUtil.check(errors.hasErrors(), EmptyInputValueException.class);
		
		ExceptionUtil.check(request.getUser_keys().size() >= 1000, IllegalArgumentException.class);
		
		participantRepository.invite(request.getUser_keys(), request.getChatroom_key());
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.setLocation(b.path("/api/v1/participant").buildAndExpand("").toUri());
		return new ResponseEntity<ApiResponse>(ApiResponse.of(ResCodes.CREATED), httpHeader, HttpStatus.CREATED);
	}
	
	
	
}
