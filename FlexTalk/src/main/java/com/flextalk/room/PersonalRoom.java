package com.flextalk.room;

import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import com.flextalk.user.Participant;
import com.flextalk.user.User;

//@Entity
//@Table(name = "tb_ChatRoom")
public class PersonalRoom extends ChatRoom {

	private final int PERSONAL_CNT = 2;
	
	private PersonalRoom() {
		this.chatroomType = RoomType.PERSONAL;
	}
	
	private PersonalRoom(String chatroomName) {
		this();
		this.chatroomName = Objects.requireNonNull(chatroomName);
		this.regDate = new Date();
	}
	
	public static PersonalRoom of(String chatroomName) {
		return new PersonalRoom(chatroomName);
	}


	@Override
	public boolean isVisit(Set<Participant> participants) {
		return participants.size() + 1 <= PERSONAL_CNT;
	}
	
}
