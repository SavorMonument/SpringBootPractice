package com.Jnet.ChatApplication.Websocket;

import com.Jnet.ChatApplication.Model.ChatMessage;
import com.Jnet.ChatApplication.Repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.function.Consumer;

@ServerEndpoint("/websocket")
public class MessageHandler extends TextWebSocketHandler{

	@Autowired
	private Sessions sessions;

	@Autowired
	private ChatMessageRepository repository;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);

		sessions.addSession(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);

		sessions.getSessionIterator().forEachRemaining(new Consumer<WebSocketSession>() {
			@Override
			public void accept(WebSocketSession s) {

				try {
					s.sendMessage(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);

		sessions.removeSession(session);
	}
}
