package com.flextalk.repository;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Repository;

import com.flextalk.room.ChatRoom;
import com.flextalk.user.Participant;

@Repository
public class ChatRoomRepository {

	
	/*
	 * ����� userKey ä�ù��� �������� �Լ�
	 * 
	 */
	public List<ChatRoom> findAllRoom(String userKey) {
		return null;
	}
	
	
	/*
	 * 
	 * 
	 */
	public int save(ChatRoom room) {		
		return 1;
	}
	
	public int remove(long chatroomKey) {
		return 1;
	}
}
