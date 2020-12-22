package com.flextalk.participant;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flextalk.room.ChatRoom;
import com.flextalk.room.ChatRoomRepository;
import com.flextalk.user.User;
import com.flextalk.user.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ParticipantServiceImpl implements ParticipantService {

	@Autowired
	private final ParticipantRepository participantRepository;
	
	@Autowired
	private final ChatRoomRepository chatRoomRepository;
	
	@Autowired
	private final UserRepository userRepository;
	
	@Override
	@Transactional
	public Participant visit(long[] user_keys, long chatroom_key) {
		
//		ChatRoom chatRoom = chatRoomRepository.findBychatroomKey(chatroom_key);
		
//		List<User> users = userRepository.findUsers(user_keys);
		
		
		return null;
	}

}
