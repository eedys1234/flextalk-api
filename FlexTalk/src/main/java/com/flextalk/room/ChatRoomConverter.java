package com.flextalk.room;

import com.flextalk.common.AbstractBaseEnumConverter;
import com.flextalk.room.ChatRoom.RoomType;

public class ChatRoomConverter extends AbstractBaseEnumConverter<RoomType, String>{

	@Override
	protected RoomType[] getValueList() {
		return RoomType.values();
	}

}
