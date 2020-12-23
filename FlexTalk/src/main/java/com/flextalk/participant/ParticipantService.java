package com.flextalk.participant;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ParticipantService {
	
	public int invite(List<Long> user_keys, long chatroom_key);
}
