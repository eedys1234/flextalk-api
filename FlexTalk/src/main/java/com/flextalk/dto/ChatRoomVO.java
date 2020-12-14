package com.flextalk.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ChatRoomVO {

	@Getter
	@Setter
	@ToString
	public static class createReqeust {

		private String chatroom_name;
		private String chatroom_type;		
	}

	@Getter
	@Setter
	@ToString
	@AllArgsConstructor
	public static class createResponse {
		private Long chatroom_key;
	}
	
	@Getter
	@Setter
	@ToString
	public static class findAllResponse {
		private List<chatRoomInfo> list;
	}
	
	@Getter
	@Setter
	@ToString
	public static class chatRoomInfo {		
		private long chatroom_key;
		private String chatroom_name;
		private int participants_cnt;
		private String chatroom_type;
		private String is_master;
		private String is_alaram;
		private String is_bookmark;
	}
}
