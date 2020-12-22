package com.flextalk.participant;

import org.springframework.stereotype.Service;

@Service
public interface ParticipantService {
	
	public Participant visit(long[] user_keys, long chatroom_key);
}
