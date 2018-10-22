package com.Jnet.ChatApplication.Model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Getter
	@GeneratedValue
	private int ID;

	@Column(unique = true)
	@NotNull
	@Getter
	@Setter
	private String username;

	@Column(unique = true)
	@Getter
	@Setter
	private String sessionId;

	@Getter
	@Setter
	private String textColor = "black";

	public User() {
	}

	public User(String name) {

		this.username = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(username, user.username);
	}

	@Override
	public int hashCode() {

		return Objects.hash(username);
	}

	public static User jSonToUser(@NotNull JsonObject json){

		User user = new User();
		JsonElement elem;

		elem = json.get("username");
		if (null !=	elem) {

			user.username = elem.getAsString();
		} else
			throw new IllegalArgumentException("Not a valid User object" + json.toString());

		return user;
	}

	public static JsonObject UserToJson(@NotNull User user){
		assert (null != user.username) : "User has null username";

		JsonObject jSonUser = new JsonObject();

		jSonUser.addProperty("username", user.getUsername());
		jSonUser.addProperty("textColor", user.getTextColor());

		return jSonUser;
	}
}
