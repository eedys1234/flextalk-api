package com.flextalk.room;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.flextalk.participant.Participant;

@Entity
//@Table(name = "tb_ChatRoom")
public class PersonalRoom extends ChatRoom {

	@Transient
	private final int PERSONAL_CNT = 2;
	
	private PersonalRoom() {
		this.chatroom_type = ChatRoomType.PERSONAL;
	}
	
	private PersonalRoom(String chatroomName) {
		this();
		this.chatroom_name = Objects.requireNonNull(chatroomName);
		this.reg_date = new Date();
	}
	
	public static PersonalRoom of(String chatroomName) {
		return new PersonalRoom(chatroomName);
	}


	@Override
	public boolean isVisit(Set<Participant> participants) {
		return participants.size() + 1 <= PERSONAL_CNT;
	}
	
}
