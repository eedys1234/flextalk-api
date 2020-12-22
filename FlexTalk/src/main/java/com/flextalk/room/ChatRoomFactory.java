package com.flextalk.room;
import java.util.Optional;

import com.flextalk.exception.NotCreateRoomException;
import com.flextalk.room.ChatRoom.ChatRoomType;
import com.flextalk.util.EnumUtil;

public class ChatRoomFactory {
	
	/**
	 * 채팅방 생성함수
	 * @param chatroomType
	 * @param userKey
	 * @param roomName
	 * @return
	 */
	public ChatRoom createRoom(String chatroomType, String roomName) {

		Optional<ChatRoomType> optional = EnumUtil.filterEnumType(ChatRoomType.class, chatroomType, type -> type.getValue().equals(chatroomType));
		
		return getRoom(optional.orElseThrow(()-> new NotCreateRoomException()), roomName);
	}
		
	private ChatRoom getRoom(ChatRoomType roomType, String roomName) {

		switch(roomType) {
			case PERSONAL : {
				return PersonalRoom.of(roomName);
			}
			case GROUP : {
				return GroupRoom.of(roomName);				
			}
			default : {
				return null;
			}
		}				
	}	
	
}
