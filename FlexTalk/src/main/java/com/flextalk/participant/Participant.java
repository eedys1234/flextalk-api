package com.flextalk.participant;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.flextalk.common.BaseEnumCode;
import com.flextalk.common.YNCode;
import com.flextalk.common.YNCodeConverter;
import com.flextalk.exception.NotAddParticipantException;
import com.flextalk.room.ChatRoom;
import com.flextalk.user.User;
import com.flextalk.util.ExceptionUtil;

import lombok.Getter;
import lombok.ToString;

@Getter
@Entity
@Table(name = "tb_participant")
@ToString(exclude = {"user", "room"})
public class Participant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "participant_key")
	private Long participantKey;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_key")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chatroom_key")
	private ChatRoom room;
	
	@Column(name = "is_bookmark", nullable = false, columnDefinition = "char")
	@Convert(converter = YNCodeConverter.class)
	private YNCode isBookmark;

	@Column(name = "is_alaram", nullable = false, columnDefinition = "char")
	@Convert(converter = YNCodeConverter.class)
	private YNCode isAlaram;
	
	@Column(name ="is_master", nullable = false, columnDefinition = "char")
	@Convert(converter = YNCodeConverter.class)
	private YNCode isMaster;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date partDate;
		
	public Participant(ChatRoom room, User user) {

		ExceptionUtil.check(!addRoom(room), NotAddParticipantException.class);
		
		this.user = Objects.requireNonNull(user);
		this.isMaster = YNCode.UNCHECK;
		this.isAlaram = YNCode.UNCHECK;
		this.isBookmark = YNCode.UNCHECK;
		this.partDate = new Date();
		
		//양방향 조회 가능
		this.room.getParticipantList().add(this);
	}
	
	public static Participant of(ChatRoom room, User user) {
		return new Participant(room, user);
	}

	public void setupBookmark(YNCode isBookmark) {
		this.isBookmark = isBookmark;
	}

	public void setupAlaram(YNCode isAlaram) {
		this.isAlaram = isAlaram;
	}

	public void setupMaster(YNCode isMaster) {
		this.isMaster = isMaster;
	}
	
	public boolean addRoom(ChatRoom room) {
		this.room = Objects.requireNonNull(room);
		return room.addParticipant(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((participantKey == null) ? 0 : participantKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;			
		}
		
		else if(!(obj instanceof Participant)) {
			return false; 
		}
		
		Participant other = (Participant) obj;		
		return Long.compare(this.participantKey, other.participantKey) == 0;
	}
	
}
