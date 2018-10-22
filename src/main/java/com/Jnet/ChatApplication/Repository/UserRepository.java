package com.Jnet.ChatApplication.Repository;

import com.Jnet.ChatApplication.Model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUsername(String username);
	User findBySessionId(String sessionId);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE users SET username = ?1 WHERE username = ?2", nativeQuery = true)
	void setUsernameByUsername(String replacement, String original);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE users SET text_Color = ?1 WHERE username = ?2", nativeQuery = true)
	void setTextColorByUsername(String color, String username);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE users SET username = ?1 WHERE ID = ?2", nativeQuery = true)
	void setUsernameByID(String replacement, Integer ID);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE users SET text_Color = ?1 WHERE ID = ?2", nativeQuery = true)
	void setTextColorByID(String color, Integer ID);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE users SET session_Id = ?1 WHERE ID = ?2", nativeQuery = true)
	void setSessionIdByID(String sessionId, Integer ID);
}
