package com.Jnet.ChatApplication.Websocket;

import com.google.gson.JsonObject;

//Implement this interface and pass the class to the SocketMapper
public interface MessageReceiver {

	public void newMessageEvent(String payload);
}
