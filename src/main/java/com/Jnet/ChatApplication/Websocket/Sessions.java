package com.Jnet.ChatApplication.Websocket;
import com.google.gson.JsonObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

/**
 * Holds the current active sessions and handles message sending
 */
public class Sessions implements MessageSender {

	private static final Sessions INSTANCE = new Sessions();

	private Set<WebSocketSession> sessions = new HashSet<>();

	private Sessions() {

	}

	public static Sessions getInstance(){

		return INSTANCE;
	}

	@Override
	public void sendMessageToAll(JsonObject message) {

		TextMessage textMessage = new TextMessage(message.toString().getBytes());

		sendMessageToAll(textMessage);
	}

	public void sendMessageToAll(TextMessage message) {

		for (WebSocketSession s: sessions){

			try {
				s.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void removeSession(WebSocketSession session){
		assert sessions.contains(session) : "No such session";

		sessions.remove(session);
	}

	public void addSession(WebSocketSession session){
		assert !sessions.contains(session) : "Duplicate session";

		sessions.add(session);
	}
}
