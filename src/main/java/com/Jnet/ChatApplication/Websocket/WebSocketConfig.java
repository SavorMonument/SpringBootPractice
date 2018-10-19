package com.Jnet.ChatApplication.Websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Autowired
	private Sessions sessions;

	@Autowired
	private MessageReceiver messageReceiver;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {

		SocketMapper handler = new SocketMapper(sessions, messageReceiver);

		webSocketHandlerRegistry.addHandler(handler, "/websocket");
	}
}
