package com.flextalk.room;
import java.util.Optional;

import com.flextalk.exception.NotCreateRoomException;
import com.flextalk.room.ChatRoom.RoomType;
import com.flextalk.user.Participant;
import com.flextalk.user.User;
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

		Optional<RoomType> optional = EnumUtil.filterEnumType(RoomType.class, chatroomType, type -> type.getValue().equals(chatroomType));
		
		optional.orElseThrow(()-> new NotCreateRoomException());
		
		return getRoom(optional.get(), roomName);
	}
		
	private ChatRoom getRoom(RoomType roomType, String roomName) {

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
