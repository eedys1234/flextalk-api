package com.flextalk.room;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import com.flextalk.exception.NotCreateRoomException;
import com.flextalk.room.ChatRoom.RoomType;

public class ChatRoomFactory {
	
	/**
	 * 채팅방 생성함수
	 * @param chatroomType
	 * @param userKey
	 * @param roomName
	 * @return
	 */
	public ChatRoom createRoom(String chatroomType, long userKey, String roomName) {

		Optional<RoomType> optional = filter(chatroomType, type -> type.getValue().equals(chatroomType));
		
		optional.orElseThrow(()-> new NotCreateRoomException());
		
		return getRoom(optional.get(), userKey, roomName);
	}
	
	private Optional<RoomType> filter(String chatroomType, Predicate<RoomType> predicate) {
		
		Function<String, Predicate<RoomType>> condition = roomtype -> predicate;

		return Arrays.stream(RoomType.class.getEnumConstants())
				.filter(condition.apply(chatroomType))
				.findFirst();
	}
	
	private ChatRoom getRoom(RoomType roomType, long userKey, String roomName) {

		switch(roomType) {
			case PERSONAL : {
				return PersonalRoom.of(userKey, roomName);
			}
			case GROUP : {
				return GroupRoom.of(userKey, roomName);				
			}
			default : {
				return null;
			}
		}				
	}	
	
}
