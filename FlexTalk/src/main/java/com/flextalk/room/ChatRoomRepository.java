package com.flextalk.room;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
	
	@Query("SELECT chat.chatroom_key, chat.chatroom_name, chat.chatroom_type, chat.reg_date, part.is_bookmark, part.is_alaram, part.is_master "
			+ "FROM ChatRoom chat JOIN FETCH Participant part "
			+ "ON chat.chatroom_key = part.room.chatroom_key "
			+ "WHERE part.user.user_key = :user_key "
			+ "ORDER BY chat.reg_date DESC")
	public List<Object[]> findChatRooms(@Param("user_key") long user_key, Pageable pageable);

	
	@Query("SELECT chat FROM ChatRoom chat WHERE chat.chatroom_key = :chatroom_key")
	public ChatRoom findByChatRoom(@Param("chatroom_key") long chatroom_key);
}
