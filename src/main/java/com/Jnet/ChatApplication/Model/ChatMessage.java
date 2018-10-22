package com.Jnet.ChatApplication.Model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class ChatMessage {

	@Id
	@Getter
	@GeneratedValue
    private int ID;

	@ManyToOne
	@NotNull
	@Getter
	@Setter
    private User author;

	@NotNull
	@Getter
	@Setter
    private String content;

	public ChatMessage() {
	}

	public ChatMessage(User author, String content) {

        this.content = content;
        this.author = author;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ChatMessage that = (ChatMessage) o;
		return Objects.equals(ID, that.ID);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID);
	}

	@Override
	public String toString() {

		return String.format("Chat Message: \n" +
							"ID = %d\n" +
							"Author = %s\n" +
							"content = %s\n", ID, author.getUsername(), content);
	}

	public static ChatMessage jSonToChatMessage(@NotNull JsonObject jSon){

		ChatMessage chatMessage = new ChatMessage();
		chatMessage.author = User.jSonToUser(jSon.getAsJsonObject("user"));
		JsonElement elem;

		if (null != (elem = jSon.get("content"))){
			chatMessage.content = elem.getAsString();
		} else
			throw new IllegalArgumentException("Not a valid Json message" + jSon.toString());

		return chatMessage;
	}

	public static JsonObject ChatMessageToJson(@NotNull ChatMessage message){
		assert (null != message.author) && (null != message.content): "message has null members";

		JsonObject jSonMessage = new JsonObject();

		jSonMessage.addProperty("user", User.UserToJson(message.author).toString());
		jSonMessage.addProperty("content", message.getContent());

		return jSonMessage;
	}
}
