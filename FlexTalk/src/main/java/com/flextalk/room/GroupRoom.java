package com.flextalk.room;

import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.flextalk.participant.Participant;

@Entity
public class GroupRoom extends ChatRoom {
//	https://www.logicbig.com/how-to/code-snippets/jcode-java-8-streams-collectors-toconcurrentmap.html
//	Stream.concat(participants.stream(), addParticipants.stream())
//	  .collect(Collectors.toConcurrentMap(Participant::getUserId, Function.identity(), (p, q)->p)).values()
//	  .stream().collect(Collectors.toList());

	@Transient
	private static final int GROUP_CNT = 1000;
	
	private GroupRoom() {
		this.chatroomType = RoomType.GROUP;
	}
	
	private GroupRoom(String chatroomName) {
		this();
		this.chatroomName = Objects.requireNonNull(chatroomName);
		this.regDate = new Date();
	}
	
	public static GroupRoom of(String chatroomName) {
		return new GroupRoom(chatroomName);
	}
		

	@Override
	public boolean isVisit(Set<Participant> participants) {		
		return participants.size() + 1 <= GROUP_CNT;
	}

}
