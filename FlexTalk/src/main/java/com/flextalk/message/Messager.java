//package com.flextalk.message;
//
//import java.util.function.Consumer;
//
//import lombok.Getter;
//import lombok.ToString;
//
//@Getter
//@ToString
//public class Messager {
//	
//	public enum MessageType {
//
//		ENTER("0"),
//		LEAVE("1"),
//		FILE("2"),
//		NORMAL("3"),
//		DELETED("4"),
//		HIDDEN("5");
//		
//		private String value;
//		
//		MessageType(String value) {
//			this.value = value;
//		}
//
//		public String getKey() {
//			return name();
//		}
//		
//		public String getValue() {
//			return this.value;
//		}		
//		
//	}
//	
//	private MessageType type;
//	private String roomId;
//	private String userId;
//	private String filePath;
//	private String content;
//	
//	private Messager() {
//		
//	}
//	
//	public Messager setMessageType(MessageType type) {
//		this.type = type;
//		return this;
//	}
//	
//	public Messager setRoomId(String roomId) { 
//		this.roomId = roomId;
//		return this;
//	}
//	
//	public Messager setUserId(String userId) { 
//		this.userId = userId;
//		return this;
//	}
//	
//	public Messager setContent(String content) {
//		this.content = content;
//		return this;
//	}
//	
//	public Messager setFilePath(String filePath) {
//		this.filePath = filePath;
//		return this;
//	}
//	
//	public static void build(Consumer<Messager> block) {
//		Messager message = new Messager();
//		block.accept(message);
//	}
//	
//	public Consumer<Messager> set(MessageType messageType, String roomId, String userId, String content) {
//		return message -> message.setMessageType(messageType)
//							   	.setRoomId(roomId)
//							   	.setUserId(userId)
//							   	.setContent(content);
//	}
//}
