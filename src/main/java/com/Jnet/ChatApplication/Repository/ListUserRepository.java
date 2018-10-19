package com.Jnet.ChatApplication.Repository;

import com.Jnet.ChatApplication.Model.ChatMessage;
import com.Jnet.ChatApplication.Model.User;

import java.util.*;

public class ListUserRepository {//implements UserRepository {

	private Set<User> users = new HashSet<>();

	public List<ChatMessage> findAllByOrderByCreateDateAsc() {
		return null;
	}

	public <S extends User> void saveAll(Iterable<S> iterable) {

		for(S elem: iterable){

			addUser(elem);
		}
	}

	public <S extends User> void addUser(S s) {
		assert (null != s) : "Null param";

		if (!users.add(s)){
			throw new IllegalArgumentException("User already exists: " + s);
		}
	}

	public void deleteAll() {

		users = new HashSet<>();
	}

	public <S extends User> void removeUser(S s) {

		 if (!users.remove(s)){
			 throw new IllegalArgumentException("No such user: " + s);
		}
	}

	public boolean findByUsername(String username) {

		Iterator<User> it = users.iterator();
		boolean isAvailable = true;

		while (it.hasNext()){
			User user = it.next();

			if (user.getUsername().equals(username))
				isAvailable = false;
		}

		return !isAvailable;
	}
}
