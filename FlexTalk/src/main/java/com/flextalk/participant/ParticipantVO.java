package com.flextalk.participant;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class ParticipantVO {

	@Builder
	@Getter
	@ToString
	public static class visitRequest {
		
		private long[] user_keys;
		private long chatroom_key;
	}
	
}
