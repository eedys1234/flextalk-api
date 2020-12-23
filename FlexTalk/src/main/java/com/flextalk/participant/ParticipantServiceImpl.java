package com.flextalk.participant;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flextalk.exception.NotCreateRoomException;
import com.flextalk.room.ChatRoom;
import com.flextalk.room.ChatRoomRepository;
import com.flextalk.user.User;
import com.flextalk.user.UserRepository;
import com.flextalk.util.ExceptionUtil;

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
	@Transactional(rollbackFor = Exception.class)
	public int invite(List<Long> user_keys, long chatroom_key) {
		
		ChatRoom chatRoom = chatRoomRepository.findByChatRoom(chatroom_key);		
		ExceptionUtil.check(Objects.isNull(chatRoom), NotCreateRoomException.class);
		
		List<User> users = userRepository.findAll(user_keys);		
//		List<User> users = userRepository.findAll(Arrays.stream(user_keys).boxed().collect(Collectors.toList()));		
		ExceptionUtil.check(users.size() != user_keys.size(), IllegalArgumentException.class);
		
		List<Participant> participants = users.stream().map(user->Participant.of(chatRoom, user)).collect(Collectors.toList());
		
		return participantRepository.save(participants).size();		
	}

}
