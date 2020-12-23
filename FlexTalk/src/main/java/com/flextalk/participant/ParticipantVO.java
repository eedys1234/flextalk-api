package com.flextalk.participant;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class ParticipantVO {

	@Getter
	@ToString
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class inviteRequest {
		
		@NotEmpty
		private List<Long> user_keys;

		@NotEmpty
		private long chatroom_key;
	}
	
}
