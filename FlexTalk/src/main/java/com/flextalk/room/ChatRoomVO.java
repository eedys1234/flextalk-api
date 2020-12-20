package com.flextalk.room;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ChatRoomVO {

	@Getter
	@Setter
	@ToString
	public static class createReqeust {

		@NotEmpty
		private String chatroom_name;

		@NotEmpty
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
	@AllArgsConstructor
	public static class findRoomsResponse {
		private List<chatRoomInfo> chatRoom_list;
	}
	
	@Getter
	@Setter
	@ToString
	@Builder
	public static class chatRoomInfo {		
		private long chatroom_key;
		private String chatroom_name;
		private String chatroom_type;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date chatroom_date;
		private String is_master;
		private String is_alaram;
		private String is_bookmark;
	}
}
