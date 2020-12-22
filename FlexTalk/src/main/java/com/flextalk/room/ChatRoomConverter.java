package com.flextalk.room;

import com.flextalk.common.AbstractBaseEnumConverter;
import com.flextalk.room.ChatRoom.ChatRoomType;

public class ChatRoomConverter extends AbstractBaseEnumConverter<ChatRoomType, String>{

	@Override
	protected ChatRoomType[] getValueList() {
		return ChatRoomType.values();
	}

}
