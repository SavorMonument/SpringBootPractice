package com.Jnet.ChatApplication.Websocket;

import com.Jnet.ChatApplication.Repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Autowired
	@Qualifier("ArrayListTestRepository")
	private ChatMessageRepository chatMessageRepository;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {

		MessageHandler handler = new MessageHandler(chatMessageRepository);
		webSocketHandlerRegistry.addHandler(handler, "/websocket");
	}
}
