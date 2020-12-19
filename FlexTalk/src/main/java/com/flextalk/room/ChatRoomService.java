package com.flextalk.room;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ChatRoomService {
	
	public ChatRoomVO.createResponse create(long userKey, String chatroomType, String chatroomName);
	public List<ChatRoom> findAllRoom(long userKey);
	public int remove(long userKey, long chatroomKey);
}
