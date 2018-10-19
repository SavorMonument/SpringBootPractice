package com.Jnet.ChatApplication.Repository;

import com.Jnet.ChatApplication.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUsername(String username);
}
