package com.Jnet.ChatApplication.Repository;

import com.Jnet.ChatApplication.Model.ChatMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ListMessageRepository{// implements ChatMessageRepository {

	private List<ChatMessage> messages = new ArrayList<>();

	public List<ChatMessage> findAllByOrderByCreateDateAsc() {
		return null;
	}

	public <S extends ChatMessage> List<S> saveAll(Iterable<S> iterable) {
		return null;
	}

	public List<ChatMessage> findAll() {

		List<ChatMessage> copy = new ArrayList<>();

		for(ChatMessage m : messages){

			ChatMessage messageCopy = new ChatMessage();

			messageCopy.setAuthor(m.getAuthor());
			messageCopy.setContent(m.getContent());

			copy.add(messageCopy);
		}

		return copy;
	}

	public void deleteAll() {

		messages = new ArrayList<>();
	}

	public <S extends ChatMessage> S save(S s) {

		messages.add(s);
		return s;
	}
}
