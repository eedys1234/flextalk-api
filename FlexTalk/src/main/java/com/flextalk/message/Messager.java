package com.flextalk.message;

import java.util.function.Consumer;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Messager {
	
	private enum MessageTypes { FIlE, NORMAL, HIDDEN };
	private MessageTypes type;
	private String roomId;
	private String userId;
	private String filePath;
	private String content;
	
	private Messager() {
		
	}
	
	public Messager setMessageType(MessageTypes type) {
		this.type = type;
		return this;
	}
	
	public Messager setRoomId(String roomId) { 
		this.roomId = roomId;
		return this;
	}
	
	public Messager setUserId(String userId) { 
		this.userId = userId;
		return this;
	}
	
	public Messager setContent(String content) {
		this.content = content;
		return this;
	}
	
	public Messager setFilePath(String filePath) {
		this.filePath = filePath;
		return this;
	}
	
	public static void build(Consumer<Messager> block) {
		Messager message = new Messager();
		block.accept(message);
	}
	
	public Consumer<Messager> set(MessageTypes messageType, String roomId, String userId, String content) {
		return message -> message.setMessageType(messageType)
							   	.setRoomId(roomId)
							   	.setUserId(userId)
							   	.setContent(content);
	}
}
