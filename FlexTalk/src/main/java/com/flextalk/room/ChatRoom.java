package com.flextalk.room;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.flextalk.common.BaseEnumCode;
import com.flextalk.message.Message;
import com.flextalk.participant.Participant;

import lombok.Getter;
import lombok.ToString;

@Getter
@Entity
@Table(name = "tb_ChatRoom")
public abstract class ChatRoom {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long chatroomKey;
	
	@Column(name = "chatroom_name", nullable = false)
	protected String chatroomName;
	
	@Column(name = "chatroom_type", nullable = false, columnDefinition = "char")
	@Convert(converter = ChatRoomConverter.class)
	protected RoomType chatroomType;
	
	protected enum RoomType implements BaseEnumCode<String> {
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
	
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, 
			CascadeType.REMOVE, 
	})	
	protected Set<Participant> participants = new HashSet<Participant>();
	
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
	protected List<Message> message = new ArrayList<Message>();
	
	public abstract boolean isVisit(Set<Participant> participants);
	
	public boolean removeParticipant(Participant participant) {

		if(participants.size() > 1) {
			return participants.remove(participant);
		}
		else {
			//방 삭제 코드 
//			participants.clear();			s
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
