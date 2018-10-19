package com.Jnet;

import com.Jnet.ChatApplication.Model.User;
import com.Jnet.ChatApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JPageApplication {

	public static void main(String[] args) {

		SpringApplication.run(JPageApplication.class, args);
	}
}
