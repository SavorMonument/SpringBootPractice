package com.Jnet.ChatApplication.Websocket;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sessions {

	private static final Sessions INSTANCE = new Sessions();
	private List<WebSocketSession> sessions;

	private Sessions() {

		this.sessions = new ArrayList<>();
	}

	public static Sessions getInstance(){

		return INSTANCE;
	}

	public Iterator<WebSocketSession> getSessionIterator(){

		return sessions.iterator();
	}

	public void removeSession(WebSocketSession session){

		sessions.remove(session);
	}

	public void addSession(WebSocketSession session){

		sessions.add(session);
	}


}
