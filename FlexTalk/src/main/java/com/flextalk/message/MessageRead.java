package com.flextalk.message;

import java.util.Date;

import javax.persistence.Column;
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

import com.flextalk.user.User;

import lombok.Getter;

@Entity
@Table(name = "tb_Message_Read")
@Getter
public class MessageRead {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "read_key")
	private Long readKey;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "message_key")
	private Message message;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_key")
	private User user;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date readDate;
	
	private MessageRead(Message message, User user) {
		this.message = message;
		this.user = user;
		this.readDate = new Date();
	}
	
	public static MessageRead of(Message message, User user) {
		return new MessageRead(message, user);
	}
	

	
	
}
