package com.Jnet;

import com.Jnet.ChatApplication.Websocket.MessageReceiver;
import com.Jnet.ChatApplication.Websocket.MessageSender;
import com.Jnet.ChatApplication.Websocket.Sessions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.concurrent.Executors;

@Configuration
@EnableJpaRepositories("com.Jnet.ChatApplication.Repository")
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

//	@Bean
//	@Qualifier("ArrayListTestRepository")
//	@Scope("singleton")
//	public ChatMessageRepository testRepository(){
//
//		return new ListMessageRepository();
//	}

	@Bean
	@Scope("singleton")
	public Sessions sessions(){

		return Sessions.getInstance();
	}

	@Bean
	public TaskScheduler taskScheduler(){

		return new ConcurrentTaskScheduler(Executors.newSingleThreadScheduledExecutor());
	}

	@Bean
	public MessageSender messageSender(){

		return Sessions.getInstance();
	}

}
