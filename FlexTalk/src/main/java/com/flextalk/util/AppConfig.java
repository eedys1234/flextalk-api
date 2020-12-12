package com.flextalk.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.flextalk.room.ChatRoomHolder;

@Configuration
public class AppConfig {

	@Bean
	public ChatRoomHolder getChatRoomHolder() {
		return new ChatRoomHolder();
	}
}
