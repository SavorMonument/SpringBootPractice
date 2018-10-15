package com.Jnet.ChatApplication.Websocket;

import com.Jnet.ChatApplication.Model.ChatMessage;
import com.Jnet.ChatApplication.Repository.ChatMessageRepository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.function.Consumer;

//@ServerEndpoint("/websocket")
public class MessageHandler extends TextWebSocketHandler{

	private final SessionHandler sessions = SessionHandler.getInstance();
	private final ChatMessageRepository repository;

	public MessageHandler(ChatMessageRepository repository) {

		this.repository = repository;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);

		sessions.addSession(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);

		sessions.sendMessageToAll(message, "idk yet");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);

		sessions.removeSession(session);
	}
}
