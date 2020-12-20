package com.flextalk.room;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ChatRoomService {
	
	public ChatRoomVO.createResponse create(long userKey, ChatRoom chatRoom);
	public List<ChatRoomVO.chatRoomInfo> findRooms(long userKey, int pageNo, int size);
	public int remove(long userKey, long chatroomKey);
}
