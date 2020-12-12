package com.flextalk.room;

import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.flextalk.user.Participant;

@Entity
@Table(name = "tb_FT_ChatRoom")
public class GroupRoom extends ChatRoom {
//	https://www.logicbig.com/how-to/code-snippets/jcode-java-8-streams-collectors-toconcurrentmap.html
//	Stream.concat(participants.stream(), addParticipants.stream())
//	  .collect(Collectors.toConcurrentMap(Participant::getUserId, Function.identity(), (p, q)->p)).values()
//	  .stream().collect(Collectors.toList());

	private static final int GROUP_CNT = 1000;
	
	private GroupRoom() {
		this.chatroomType = RoomType.GROUP;
	}
	
	private GroupRoom(Long userKey, String chatroomName, Set<Participant> participants) {
		this();
		this.chatroomName = Objects.requireNonNull(chatroomName);
		this.participants = Objects.requireNonNull(participants);
		userKey = Objects.requireNonNull(userKey);
		
		this.participants.add(Participant.of(this));
		this.regDate = new Date();
	}
	
	public static GroupRoom of(Long userKey, String chatroomName) {
		return new GroupRoom(userKey, chatroomName, Collections.emptySet());
	}
	
	public static GroupRoom of(Long userKey, String chatroomName, Set<Participant> participants) {
		return new GroupRoom(userKey, chatroomName, participants);
	}

	@Override
	public boolean isVisit(Set<Participant> participants) {		
		return participants.size() + 1 <= GROUP_CNT;
	}

}
