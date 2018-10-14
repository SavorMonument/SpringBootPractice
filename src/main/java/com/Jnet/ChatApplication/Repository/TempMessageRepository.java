package com.Jnet.ChatApplication.Repository;

import com.Jnet.ChatApplication.Model.ChatMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TempMessageRepository implements ChatMessageRepository {

	private List<ChatMessage> messages = new ArrayList<>();

	@Override
	public List<ChatMessage> findAllByOrderByCreateDateAsc() {
		return null;
	}

	@Override
	public <S extends ChatMessage> List<S> saveAll(Iterable<S> iterable) {
		return null;
	}

	@Override
	public List<ChatMessage> findAll() {

		List<ChatMessage> copy = new ArrayList<>();

		for(ChatMessage m : messages){
			copy.add(m.copy());
		}

		return copy;
	}

	@Override
	public void deleteAll() {

		messages = new ArrayList<>();
	}

	@Override
	public <S extends ChatMessage> S save(S s) {

		messages.add(s);
		return s;
	}


	@Override
	public List<ChatMessage> findAll(Sort sort) { return null; }

	@Override
	public <S extends ChatMessage> S insert(S s) {
		return null;
	}

	@Override
	public <S extends ChatMessage> List<S> insert(Iterable<S> iterable) {
		return null;
	}

	@Override
	public <S extends ChatMessage> List<S> findAll(Example<S> example) {
		return null;
	}

	@Override
	public <S extends ChatMessage> List<S> findAll(Example<S> example, Sort sort) {
		return null;
	}

	@Override
	public Page<ChatMessage> findAll(Pageable pageable) {
		return null;
	}

	@Override
	public Optional<ChatMessage> findById(String s) {
		return Optional.empty();
	}

	@Override
	public boolean existsById(String s) {
		return false;
	}

	@Override
	public Iterable<ChatMessage> findAllById(Iterable<String> iterable) {
		return null;
	}

	@Override
	public long count() {
		return 0;
	}

	@Override
	public void deleteById(String s) {

	}

	@Override
	public void delete(ChatMessage chatMessage) {

	}

	@Override
	public void deleteAll(Iterable<? extends ChatMessage> iterable) {

	}
	@Override
	public <S extends ChatMessage> Optional<S> findOne(Example<S> example) {
		return Optional.empty();
	}

	@Override
	public <S extends ChatMessage> Page<S> findAll(Example<S> example, Pageable pageable) {
		return null;
	}

	@Override
	public <S extends ChatMessage> long count(Example<S> example) {
		return 0;
	}

	@Override
	public <S extends ChatMessage> boolean exists(Example<S> example) {
		return false;
	}
}
