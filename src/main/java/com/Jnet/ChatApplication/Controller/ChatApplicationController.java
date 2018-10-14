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

//	@ModelAttribute("ChatMessage")
//	public ChatMessage getChatMessageObject() {
//
//		return new ChatMessage();
//	}

//	@MessageMapping("/hello")
//	@SendTo("/topic/greetings")
//	public ChatMessage chatMessage(ChatMessage message)
//			throws Exception {
//
//		chatMessageRepository.save(message);
//
//		return message;
//	}

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String chatPage(Model model){

		List<ChatMessage> chatMessages = chatMessageRepository.findAll();
		chatMessages = getListNLastElem(chatMessages, 20);

		model.addAttribute("messages", chatMessages);
    	return "ChatApplication/ChatPage";
	}

//	@RequestMapping(value = "/postMessage", method = RequestMethod.POST)
//	//@ResponseStatus(HttpStatus.ACCEPTED)
//	public RedirectView chatMessage(HttpServletRequest request, @ModelAttribute("ChatMessage")ChatMessage message, Model model){
//
//		message.setAuthor(request.getLocalAddr());
//		if (!message.getContent().isEmpty()) {
//			chatMessageRepository.save(message);
//		}
//
//		RedirectView view = new RedirectView("/chat");
//		return view;
//	}

	private<T> List<T> getListNLastElem(List<T> elem, int num){

		List<T> lastElemList = new ArrayList<>();

		int lastElemIndex = elem.size() - 1;
		for (int i = lastElemIndex; ((i >= 0) && (i > (lastElemIndex - num))); i--)
		{
			lastElemList.add(elem.get(i));
		}

		return lastElemList;
	}
}
