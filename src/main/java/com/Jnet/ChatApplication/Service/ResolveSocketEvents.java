package com.Jnet.ChatApplication.Service;

import com.Jnet.ChatApplication.Model.ChatMessage;
import com.Jnet.ChatApplication.Model.User;
import com.Jnet.ChatApplication.Repository.ChatMessageRepository;
import com.Jnet.ChatApplication.Repository.UserRepository;
import com.Jnet.ChatApplication.Websocket.MessageReceiver;
import com.Jnet.ChatApplication.Websocket.MessageSender;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

@Service
public class ResolveSocketEvents implements MessageReceiver {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ChatMessageRepository chatMessageRepository;

	@Autowired
	private MessageSender messageSender;

	@PostConstruct
	private void test(){

		userRepo.save(new User("Alex"));
		userRepo.save(new User("placeholder"));
	}

	@Override
	public void newMessageEvent(@NotNull String payload) {

		//TODO: Make the user registration check more verbose
		try{
			JsonObject jSonMessage = parsePayload(payload);
			ChatMessage chatMessage;

			chatMessage = ChatMessage.jSonToChatMessage(jSonMessage);

			checkUserRegistration(chatMessage.getAuthor().getUsername());
			//If the user is registered link him to the message
			chatMessage.setAuthor(userRepo.findByUsername(chatMessage.getAuthor().getUsername()));

			chatMessageRepository.save(chatMessage);
			transformAndSendChatMessage(chatMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private JsonObject parsePayload(@NotNull String payload) {

		JsonObject jsonMessage = null;

		try {
			jsonMessage = new JsonParser().parse(payload).getAsJsonObject();
		} catch (Exception e){
			throw new IllegalArgumentException("Not a valid json object\n" + payload);
		}

		return jsonMessage;
	}

	private void checkUserRegistration(String username){

		if (null == userRepo.findByUsername(username))
			throw new IllegalStateException("User not registered: " + username);
	}


	private void transformAndSendChatMessage(ChatMessage message){
		assert null != message : "Received null as ChatMessage";

		JsonObject jSonMessage = ChatMessage.ChatMessageToJson(message);

		messageSender.sendMessageToAll(jSonMessage);
	}

//	private class JsonToChatMessageTemplate {
//
//		String username;
//		String content;
//
//		JsonToChatMessageTemplate(@NotNull JsonObject jSon){
//
//			username = jSon.get("username").toString();
//			content = jSon.get("content").toString();
//		}
//
//		JsonToChatMessageTemplate(@NotNull ChatMessage chatMessage){
//
//			User user = new User("");
//			username = chatMessage.author.getUsername();
//			content = chatMessage.content;
//		}
//
//
//		JsonObject buildJsonObject(){
//
//			JsonObject jsonObject = new JsonObject();
//
//			jsonObject.addProperty("username", username);
//			jsonObject.addProperty("content", content);
//
//			return jsonObject;
//			//return (new JsonParser().parse(new Gson().toJson(this))).getAsJsonObject();
//		}
//
//		ChatMessage buildChatMessage(){
//
//			ChatMessage chatMessage = new ChatMessage();
//			User user = resolveUser(username);
//
//			chatMessage.author = user;
//			chatMessage.content = content;
//
//			return chatMessage;
//		}
//
//		private User resolveUser(String username) {
//
//			User user = userRepo.findByUsername(username);
//
//			if (null == user){
//
//				user = new User(username);
//				userRepo.save(user);
//			}
//
//			return user;
//		}
//	}

}
