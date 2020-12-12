package com.flextalk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flextalk.room.ChatRoom;

@Service
public interface ChatRoomService {
	
	public int create(long userKey, String chatroomType, String chatroomName);
	public List<ChatRoom> findAllRoom(String userId);
	public int remove(long userKey, long chatroomKey);
}
