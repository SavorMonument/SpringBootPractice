package com.Jnet;

import com.Jnet.ChatApplication.Repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.Jnet.ChatApplication.Repository")
public class JPageApplication {

	public static void main(String[] args) {

		SpringApplication.run(JPageApplication.class, args);

	}
}
