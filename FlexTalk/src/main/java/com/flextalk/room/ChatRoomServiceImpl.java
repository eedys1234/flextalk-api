package com.flextalk.room;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flextalk.exception.NotCreateRoomException;
import com.flextalk.user.Participant;
import com.flextalk.user.User;
import com.flextalk.user.UserRepository;
import com.flextalk.util.ExceptionUtil;

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
	
	@Autowired
	private ChatRoomHolder roomHolder;
	
	@Override
	@Transactional
	public ChatRoomVO.createResponse create(long userKey, String chatroomType, String chatroomName) {
		
		//ChatRoom ���丮 ��ü ����
		ChatRoomFactory factory = roomHolder.createFactory();
		ChatRoom room = factory.createRoom(chatroomType, chatroomName);

		ExceptionUtil.check(Objects.isNull(room), NotCreateRoomException.class);

		//����� ���� �������� 
		User user = userRepository.findByUserKey(userKey);
		
		//������ ����
		Participant participant = Participant.of(room, user);

		room.addParticipant(participant);
		//ä�ù� ����
		ChatRoom savedRoom = chatRoomRepository.save(room);	
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
