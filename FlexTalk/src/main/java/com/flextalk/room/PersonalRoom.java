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
public class PersonalRoom extends ChatRoom {

	private final int PERSONAL_CNT = 2;
	
	private PersonalRoom() {
		this.chatroomType = RoomType.PERSONAL;
	}
	
	private PersonalRoom(Long userKey, String chatroomName, Set<Participant> participants) {
		this();
		this.chatroomName = Objects.requireNonNull(chatroomName);
		this.participants = Objects.requireNonNull(participants);
//		userKey = Objects.requireNonNull(userKey);

		this.participants.add(Participant.of(this));
		this.regDate = new Date();
	}
	
	public static PersonalRoom of(Long userKey, String chatroomName) {
		return new PersonalRoom(userKey, chatroomName, Collections.emptySet());
	}

	public static PersonalRoom of(Long userKey, String chatroomName, Set<Participant> participants) {
		return new PersonalRoom(userKey, chatroomName, participants);
	}

	@Override
	public boolean isVisit(Set<Participant> participants) {
		return participants.size() + 1 <= PERSONAL_CNT;
	}
	
}
