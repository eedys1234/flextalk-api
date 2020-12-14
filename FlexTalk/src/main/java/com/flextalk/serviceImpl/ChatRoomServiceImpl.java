package com.flextalk.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flextalk.dto.ChatRoomVO;
import com.flextalk.exception.NotCreateRoomException;
import com.flextalk.repository.ChatRoomRepository;
import com.flextalk.room.ChatRoom;
import com.flextalk.room.ChatRoomFactory;
import com.flextalk.room.ChatRoomHolder;
import com.flextalk.service.ChatRoomService;
import com.flextalk.util.ExceptionUtil;
import com.flextalk.util.RetryCaller;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

	@Autowired
	private ChatRoomRepository chatRoomRepository;
	
	@Autowired
	private ChatRoomHolder roomHolder;
	
	@Override
	@Transactional
	public ChatRoomVO.createResponse create(long userKey, String chatroomType, String chatroomName) {
		
		//ChatRoom 蒲配府 按眉 积己
		ChatRoomFactory factory = roomHolder.createFactory();
		ChatRoom room = factory.createRoom(chatroomType, userKey, chatroomName);

		ExceptionUtil.check(Objects.isNull(room), NotCreateRoomException.class);

		//盲泼规 积己
		ChatRoom savedRoom = chatRoomRepository.save(room);	
		ChatRoomVO.createResponse response = new ChatRoomVO.createResponse(savedRoom.getChatroomKey());
				
		//曼咯磊 积己
		return response;		
	}

	@Override
	@Transactional(readOnly = true)
	public List<ChatRoom> findAllRoom(long userKey) {
		
		List<ChatRoom> listRoom = chatRoomRepository.findAllRoom(userKey);
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
