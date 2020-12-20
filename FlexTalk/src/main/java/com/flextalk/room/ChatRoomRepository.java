package com.flextalk.room;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
	
	@Query("SELECT chat.chatroomKey, chat.chatroomName, chat.chatroomType, chat.regDate, part.isBookmark, part.isAlaram, part.isMaster "
			+ "FROM ChatRoom chat JOIN FETCH Participant part "
			+ "ON chat.chatroomKey = part.room.chatroomKey "
			+ "WHERE part.user.userKey = :user_key "
			+ "ORDER BY chat.regDate DESC")
	public List<Object[]> findRooms(@Param("user_key") long userKey, Pageable pageable);

}
