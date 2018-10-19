package com.Jnet.ChatApplication.Websocket;

import com.google.gson.JsonObject;

//The Sessions class implements this
public interface MessageSender {

	void sendMessageToAll(JsonObject message);
}
