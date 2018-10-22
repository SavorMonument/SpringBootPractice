package com.Jnet.ChatApplication.Controller;

import com.Jnet.ChatApplication.Model.User;
import com.Jnet.ChatApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/chat")
public class ChatApplicationController {

	@Autowired
	private UserRepository userRepository;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String chatPage(HttpServletRequest request, HttpServletResponse response){

    	return "ChatApplication/ChatPage";
	}

	@RequestMapping(value = "/set-user", method = RequestMethod.POST)
	public RedirectView setUser(@RequestBody MultiValueMap<String, String> val, @RequestBody String strVal, HttpSession session,
								HttpServletResponse response){

		Map<String, String> values = val.toSingleValueMap();

		if (areUserValues(values)){

			User oldUser;
			User receivedUser = parseUserFromMapValues(values);

			if (null != (oldUser = userRepository.findBySessionId(session.getId()))) {

				userRepository.setUsernameByID(receivedUser.getUsername(), oldUser.getID());
				userRepository.setTextColorByID(receivedUser.getTextColor(), oldUser.getID());
				userRepository.setSessionIdByID(session.getId(), oldUser.getID());

			} else {

				User user = new User();

				user.setUsername(receivedUser.getUsername());
				user.setTextColor(receivedUser.getTextColor());
				user.setSessionId(session.getId());

				userRepository.save(user);
			}

			response.addCookie(new Cookie("username", receivedUser.getUsername()));
			response.addCookie(new Cookie("textColor", receivedUser.getTextColor()));
		}

		return new RedirectView("/chat");
	}

	private User parseUserFromMapValues(Map<String, String> values) {
    	assert areUserValues(values) : "Incorrect values";

    	User user = new User();

    	user.setUsername(values.get("username"));
    	user.setTextColor(values.get("textColor"));

		return user;
	}

	private boolean areUserValues(Map<String, String> values){

    	boolean result = false;

    	if (null != values){

    		Set<String> keys = values.keySet();
    		if (hasAllUserFields(keys) && !isAnyValueEmpty(values.values())){

    			result = true;
			}
		}

		return result;
	}

	private boolean isAnyValueEmpty(Collection<String> values) {

		return values.contains("");
	}

	private boolean hasAllUserFields(Set<String> keys) {

		return keys.contains("username") && keys.contains("textColor");
	}
}
