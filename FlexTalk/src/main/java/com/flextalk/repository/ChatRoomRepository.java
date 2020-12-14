package com.flextalk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flextalk.room.ChatRoom;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

	public List<ChatRoom> findAllRoom(long userKey);
//	
//	/*
//	 * ����� userKey ä�ù��� �������� �Լ�
//	 * 
//	 */
//	public List<ChatRoom> findAllRoom(String userKey) {
//		return null;
//	}
//	
//	
//	/*
//	 * 
//	 * 
//	 */
//	public int save(ChatRoom room) {		
//		return 1;
//	}
//	
//	public int remove(long chatroomKey) {
//		return 1;
//	}
}
