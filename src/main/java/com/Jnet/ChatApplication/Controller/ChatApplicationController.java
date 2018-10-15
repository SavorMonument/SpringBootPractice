package com.Jnet.ChatApplication.Controller;

import com.Jnet.ChatApplication.Model.ChatMessage;
import com.Jnet.ChatApplication.Repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatApplicationController {

    @Autowired
	@Qualifier("ArrayListTestRepository")
    private ChatMessageRepository chatMessageRepository;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String chatPage(Model model){

    	return "ChatApplication/ChatPage";
	}
}
