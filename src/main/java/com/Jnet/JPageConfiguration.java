package com.Jnet;

import com.Jnet.ChatApplication.Controller.ChatApplicationController;
import com.Jnet.ChatApplication.Model.ChatMessage;
import com.Jnet.ChatApplication.Repository.ChatMessageRepository;
import com.Jnet.ChatApplication.Repository.TempMessageRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
//@EnableWebMvc
public class JPageConfiguration {

	@Bean
//	@Qualifier("MainPageTemplateResolver")
	public ITemplateResolver mainPageTempleateResolver(){

		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();

		resolver.setPrefix("classpath:/templates/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode("HTML5");
		resolver.setCacheable(false);
		resolver.setOrder(0);

		return resolver;
	}

	@Bean
//	@Qualifier("ChatApplicationTemplateResolver")
	public ITemplateResolver ChatTempleateResolver(){

		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();

		resolver.setPrefix("classpath:/templates/ChatApplication/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode("HTML5");
		resolver.setCacheable(false);
		resolver.setOrder(1);

		return resolver;
	}

	@Bean
	@Qualifier("ArrayListTestRepository")
	public ChatMessageRepository testRepository(){

		return new TempMessageRepository();
	}
}
