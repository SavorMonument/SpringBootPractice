package com.Jnet.ChatApplication.Websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class SocketMapper extends TextWebSocketHandler {

	private final Sessions sessions;
	private final MessageReceiver messageReceiver;

	public SocketMapper(Sessions sessions, MessageReceiver messageResolver) {

		this.sessions = sessions;
		this.messageReceiver = messageResolver;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);

		sessions.addSession(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
		super.handleTextMessage(session, textMessage);

		messageReceiver.newMessageEvent(textMessage.getPayload());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);

		sessions.removeSession(session);
	}
}
