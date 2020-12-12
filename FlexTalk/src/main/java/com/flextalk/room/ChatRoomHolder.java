package com.flextalk.room;

import java.util.function.Supplier;

import org.springframework.stereotype.Component;

public class ChatRoomHolder {
	
	private Supplier<ChatRoomFactory> factory = () -> createAndCache();
	
	public ChatRoomHolder() {
		
	}
	
	public ChatRoomFactory createFactory() {
		return factory.get();
	}
	
	private synchronized ChatRoomFactory createAndCache() {
		
		class ChatRoomFactoryHolder implements Supplier<ChatRoomFactory> {
			private final ChatRoomFactory instance = new ChatRoomFactory();

			public ChatRoomFactory get() {
				return instance;
			}
		}
		
		if(!ChatRoomFactoryHolder.class.isInstance(factory)) {
			factory = new ChatRoomFactoryHolder();
		}
		
		return factory.get();		
	}
}
