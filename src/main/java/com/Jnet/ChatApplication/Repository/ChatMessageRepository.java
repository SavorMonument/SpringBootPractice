package com.Jnet.ChatApplication.Repository;

import com.Jnet.ChatApplication.Model.ChatMessage;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Primary
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    List<ChatMessage> findAllByOrderByCreateDateAsc();
}
