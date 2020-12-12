package com.flextalk.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ChatRoomVO {

	@Getter
	@Setter
	@ToString
	public static class createReqeust {

		private String chatroomName;
		private String chatroomType;		
	}

	@Getter
	@Setter
	@ToString
	public static class createResponse {
		private Long chatroomKey;
	}
}
