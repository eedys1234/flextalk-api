package com.flextalk.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.flextalk.room.ChatRoom;
import com.flextalk.user.User;
import com.flextalk.util.EnumUtil;

import lombok.Getter;

@Entity
@Table(name = "tb_FT_Message")
@Getter
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_key")
	private Long messageKey;
		
	private String message;
	
	@Enumerated(EnumType.STRING)
	private MessageType messageType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chatroom_key")
	private ChatRoom room;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_key")
	private User user;
	
	@OneToMany(mappedBy = "message", fetch = FetchType.LAZY)
	private List<MessageRead> readList = new ArrayList<>();

	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate;
	
	@Enumerated(EnumType.STRING)
	private DeleteType is_delete;
		
	public enum MessageType {
		ENTER("0"),
		LEAVE("1"),
		FILE("2"),
		NORMAL("3"),
		DELETED("4"),
		HIDDEN("5");
		
		private String value;
		
		MessageType(String value) {
			this.value = value;
		}

		public String getKey() {
			return name();
		}
		
		public String getValue() {
			return this.value;
		}		
	}
	
	private enum DeleteType {
		UNDELETE("0"),
		DELETE("1");
		
		private String value;
		
		DeleteType(String value) {
			this.value = value;
		}
		
		public String getKey() {
			return name();
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
	private Message(ChatRoom room, User user, String message, String messageType) {
		this.room = Objects.requireNonNull(room);
		this.user = Objects.requireNonNull(user);
		this.message = message;
		this.is_delete = DeleteType.UNDELETE;
		this.regDate = new Date();		
		
		this.messageType = getMessageType(messageType);
	}

	public static Message of(ChatRoom room, User user, String message) {
		return new Message(room, user, message, "");
	}
	
	public static Message of(ChatRoom room, User user, String message, String messageType) {
		return new Message(room, user, message, messageType);
	}
			
	private MessageType getMessageType(String messageType) {
		
		Optional<MessageType> optional = EnumUtil.filter(MessageType.class, messageType, type->type.getValue().equals(messageType));
				
		//예외 처리
		optional.orElseThrow(() -> new RuntimeException());
		
		return optional.get();
	}
	
	public void setMessageRead(MessageRead read) {
		this.readList.add(read);		
	}
	 

}
