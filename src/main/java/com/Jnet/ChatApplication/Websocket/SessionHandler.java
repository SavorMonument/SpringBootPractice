package com.Jnet.ChatApplication.Websocket;
import com.Jnet.ChatApplication.Model.ChatMessage;
import com.google.gson.Gson;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SessionHandler {

	private static final SessionHandler INSTANCE = new SessionHandler();
	private List<WebSocketSession> sessions;

	private SessionHandler() {

		this.sessions = new ArrayList<>();
		WebSocketSession session;
	}

	public static SessionHandler getInstance(){
		return INSTANCE;
	}

	public Iterator<WebSocketSession> getSessionIterator(){

		return sessions.iterator();
	}

	public void sendMessageToAll(TextMessage message, String author) throws IOException {

		ChatMessage.ChatMessageBuilder chatMessageB = new ChatMessage.ChatMessageBuilder();
		chatMessageB.setContent(message.getPayload())
				.setAuthor(author);

		sendMessageToAll(chatMessageB.createChatMessage());
	}

	public void sendMessageToAll(ChatMessage chatMessage) throws IOException {

		Gson parser = new Gson();
		String JsonMessage = parser.toJson(chatMessage);

		for(WebSocketSession s: sessions){

			s.sendMessage(new TextMessage(JsonMessage.getBytes()));
		}
	}

	public void removeSession(WebSocketSession session){

		sessions.remove(session);
	}

	public void addSession(WebSocketSession session){

		sessions.add(session);
	}

}
