package com.flextalk.room;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.flextalk.user.Participant;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(exclude = "participants")
@EqualsAndHashCode(exclude = "participants")
@MappedSuperclass
public abstract class ChatRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long chatroomKey;
	
	@Column(name = "chatroom_name", nullable = false)
	protected String chatroomName;
	
	@Column(name = "chatroom_type", nullable = false)
	protected RoomType chatroomType;
	
	protected enum RoomType {
		PERSONAL("0"),
		GROUP("1");
		
		private String value;

		RoomType(String value) {
			this.value = value;
		}
		
		public String getKey() {
			return name();
		}
		
		public String getValue() {
			return this.value;
		}
	}

	@Temporal(TemporalType.TIMESTAMP)
	protected Date regDate;
	
	@OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = {
			CascadeType.PERSIST, 
			CascadeType.REMOVE, 
	})
	protected Set<Participant> participants;
	
	public abstract boolean isVisit(Set<Participant> participants);
		
	public boolean removeParticipant(Participant participant) {

		if(participants.size() > 1) {
			return participants.remove(participant);
		}
		else {
			//방 삭제 코드 
			participants.clear();			
			return false;
		}
	}

	public boolean addParticipant(Participant participant) {
		if(isVisit(participants))
			return participants.add(participant);
		
		return false;
	}

	public int getParticipantCnt() {
		return participants.size();
	}
	
	public Set<Participant> getParticipantList() {
		return participants;
	}
}
