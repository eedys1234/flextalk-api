package com.flextalk.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flextalk.exception.NotCreateRoomException;
import com.flextalk.repository.ChatRoomRepository;
import com.flextalk.room.ChatRoom;
import com.flextalk.room.ChatRoomFactory;
import com.flextalk.room.ChatRoomHolder;
import com.flextalk.service.ChatRoomService;
import com.flextalk.util.RetryCaller;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

	@Autowired
	private ChatRoomRepository chatRoomRepository;
	
	@Autowired
	private ChatRoomHolder roomHolder;
	
	@Override
	@Transactional
	public int create(long userKey, String chatroomType, String chatroomName) {
		
		//ChatRoom 蒲配府 按眉 积己
		ChatRoomFactory factory = roomHolder.createFactory();
		ChatRoom room = factory.createRoom(chatroomType, userKey, chatroomName);

		//盲泼规 积己
		chatRoomRepository.save(room);	
		
		//曼咯磊 积己
		return 1;		
	}

	@Override
	@Transactional(readOnly = true)
	public List<ChatRoom> findAllRoom(String userId) {
		
		List<ChatRoom> listRoom = chatRoomRepository.findAllRoom(userId);
		return listRoom;
	}

	@Override
	@Transactional
	public int remove(long userKey, long chatroomKey) {
		
		//Valid checking
		
		
		return chatRoomRepository.remove(chatroomKey);
	}

}
