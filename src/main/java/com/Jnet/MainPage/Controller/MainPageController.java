package com.Jnet.MainPage.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class MainPageController {

	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String mainPage(){

		return "MainPage/PortalPage";
	}

	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public RedirectView redirect(@RequestParam() String option){

		RedirectView view = new RedirectView("/");

		switch (option){
			case "chat":{
				view.setUrl("/chat");
			} break;
		}

		return view;
	}


//	@RequestMapping(value = "/", method = RequestMethod.POST)
//	public RedirectView redirect(@RequestBody String option){
}
