package com.flextalk.exception;

public class NotCreateRoomException extends RuntimeException {
	public NotCreateRoomException() {
		super("채팅방을 생성하지 못했습니다.");
	}
}
