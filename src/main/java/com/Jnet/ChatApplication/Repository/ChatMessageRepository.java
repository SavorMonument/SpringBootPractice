package com.Jnet.ChatApplication.Repository;

import com.Jnet.ChatApplication.Model.ChatMessage;
import com.Jnet.ChatApplication.Model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, Integer> {

	List<ChatMessage> findByAuthor(User user);
}
