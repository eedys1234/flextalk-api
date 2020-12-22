package com.flextalk.room;

import java.util.Objects;

import com.flextalk.exception.NotCreateRoomException;
import com.flextalk.room.ChatRoom.ChatRoomType;
import com.flextalk.util.ExceptionUtil;

public enum ChatRoomValidator {
	
	ROOM {
		
		@Override
		public void valid(ChatRoom chatRoom) {
			ExceptionUtil.check(Objects.isNull(chatRoom), NotCreateRoomException.class);
		}
	},
	ROOM_TYPE {
		
		@Override
		public void valid(ChatRoom chatRoom) {
			ExceptionUtil.check(!(ChatRoomType.GROUP.equals(chatRoom.getChatroom_type()) || 
					ChatRoomType.PERSONAL.equals(chatRoom.getChatroom_type())), NotCreateRoomException.class);
		}
	};
	
	
	
	public abstract void valid(ChatRoom chatRoom);
}
