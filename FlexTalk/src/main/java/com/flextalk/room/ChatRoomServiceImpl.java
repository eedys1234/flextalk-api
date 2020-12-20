package com.flextalk.room;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flextalk.common.YNCode;
import com.flextalk.participant.Participant;
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
	public List<ChatRoom> findAllRoom(long userKey) {
		
		List<ChatRoom> listRoom = new ArrayList<ChatRoom>();
//		List<ChatRoom> listRoom = chatRoomRepository.findAllRoom(userKey);
		return listRoom;
	}

	@Override
	@Transactional
	public int remove(long userKey, long chatroomKey) {
		
		//Valid checking
		chatRoomRepository.delete(chatroomKey);
		return 0;
		
	}

}
