package com.flextalk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flextalk.dto.ChatRoomVO;
import com.flextalk.room.ChatRoom;

@Service
public interface ChatRoomService {
	
	public ChatRoomVO.createResponse create(long userKey, String chatroomType, String chatroomName);
	public List<ChatRoom> findAllRoom(long userKey);
	public int remove(long userKey, long chatroomKey);
}
