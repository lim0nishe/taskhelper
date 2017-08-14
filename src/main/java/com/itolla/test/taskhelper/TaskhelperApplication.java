package com.itolla.test.taskhelper;



import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
@EnableResourceServer
public class TaskhelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskhelperApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
	    return new ModelMapper();
    }
}
