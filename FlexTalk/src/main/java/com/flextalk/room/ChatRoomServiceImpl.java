package com.flextalk.room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flextalk.common.YNCode;
import com.flextalk.participant.Participant;
import com.flextalk.room.ChatRoom.RoomType;
import com.flextalk.room.ChatRoomVO.chatRoomInfo;
import com.flextalk.user.User;
import com.flextalk.user.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ChatRoomServiceImpl implements ChatRoomService {

	@Autowired
	private ChatRoomRepository chatRoomRepository;
	
	@Autowired
	private UserRepository userRepository;
		
	@Override
	@Transactional
	public ChatRoomVO.createResponse create(long userKey, ChatRoom chatRoom) {
		
		//사용자 정보 가져오기 
		User user = userRepository.findByUserKey(userKey);
		
		//참여자 생성
		Participant participant = Participant.of(chatRoom, user);
		participant.setupMaster(YNCode.CHECK);
		chatRoom.addParticipant(participant);
		
		//채팅방 생성
		ChatRoom savedRoom = chatRoomRepository.save(chatRoom);	
		ChatRoomVO.createResponse response = new ChatRoomVO.createResponse(savedRoom.getChatroomKey());
				
		return response;		
	}

	@Override
	@Transactional(readOnly = true)
	public List<ChatRoomVO.chatRoomInfo> findRooms(long userKey, int pageNo, int size) {
		
		Pageable pageable = new PageRequest(pageNo - 1, size);
		List<Object[]> chatRooms = chatRoomRepository.findRooms(userKey, pageable);
		List<ChatRoomVO.chatRoomInfo> NewChatRooms = new ArrayList<>();
		for(Object[] roomInfo : chatRooms) {
			NewChatRooms.add(
			chatRoomInfo.builder()
						.chatroom_key((long)roomInfo[0])
						.chatroom_name((String)roomInfo[1])
						.chatroom_type(((RoomType)roomInfo[2]).getValue())
						.chatroom_date((Date)roomInfo[3])
						.is_bookmark(((YNCode)roomInfo[4]).getValue())
						.is_alaram(((YNCode)roomInfo[5]).getValue())
						.is_master(((YNCode)roomInfo[6]).getValue())
						.build()
			);
		}

		return NewChatRooms;
	}

	@Override
	@Transactional
	public int remove(long userKey, long chatroomKey) {
		
		//Valid checking
		chatRoomRepository.delete(chatroomKey);
		return 0;
		
	}

}
