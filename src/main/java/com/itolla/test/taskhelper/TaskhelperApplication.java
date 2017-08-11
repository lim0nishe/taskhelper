package com.itolla.test.taskhelper;

import com.itolla.test.taskhelper.service.IssueService;
import com.itolla.test.taskhelper.service.LabelService;
import com.itolla.test.taskhelper.service.ProjectService;
import com.itolla.test.taskhelper.service.UserService;
import com.itolla.test.taskhelper.model.Issue;
import com.itolla.test.taskhelper.model.Label;
import com.itolla.test.taskhelper.model.Project;
import com.itolla.test.taskhelper.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;


@SpringBootApplication
public class TaskhelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskhelperApplication.class, args);
	}

}
