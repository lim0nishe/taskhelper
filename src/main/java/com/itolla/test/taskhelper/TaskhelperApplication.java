package com.itolla.test.taskhelper;

import com.itolla.test.taskhelper.Repositories.IssueRepository;
import com.itolla.test.taskhelper.Repositories.LabelRepository;
import com.itolla.test.taskhelper.Repositories.ProjectRepository;
import com.itolla.test.taskhelper.Repositories.UserRepository;
import com.itolla.test.taskhelper.Services.IssueService;
import com.itolla.test.taskhelper.Services.LabelService;
import com.itolla.test.taskhelper.Services.ProjectService;
import com.itolla.test.taskhelper.Services.UserService;
import com.itolla.test.taskhelper.models.Issue;
import com.itolla.test.taskhelper.models.Label;
import com.itolla.test.taskhelper.models.Project;
import com.itolla.test.taskhelper.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SpringBootApplication
public class TaskhelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskhelperApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(IssueService issueService, LabelService labelService,
								   ProjectService projectService, UserService userService){
		return (args) -> {
			User user1 = new User("user1", "secret");
			User user2 = new User("user2", "secret");
			User user3 = new User("user3", "secret");
			User user4 = new User("user4", "secret");

			Project project1 = new Project("first project");
			project1.setOwner(user1);
			Project project2 = new Project("second project");
			project2.setOwner(user2);

			project1.addUser(user1);
			project1.addUser(user3);
			project1.addUser(user4);

			project2.addUser(user2);
			project2.addUser(user1);

			Label label1 = new Label("project 1 plebs tasks");
			label1.setProject(project1);
			Label label2 = new Label("project 2 plebs tasks");
			label2.setProject(project2);
			Label label3 = new Label("project 1 boss tasks");
			label3.setProject(project1);
			Label label4 = new Label("project 2 boss tasks");
			label4.setProject(project2);

			Issue issue1 = new Issue("first issue", "do something");
			issue1.setUser(user4);
			issue1.setProject(project1);
			Issue issue2 = new Issue("second issue", "do something");
			issue2.setProject(project1);
			issue2.setUser(user3);
			Issue issue3 = new Issue("issue for boss 1", "do nothing");
			issue3.setUser(user1);
			issue3.setProject(project1);
			Issue issue4 = new Issue("third issue", "do something brilliant");
			issue4.setProject(project2);
			issue4.setUser(user1);
			Issue issue5 = new Issue("issue for boss 2", "do nothing");
			issue5.setUser(user2);
			issue5.setProject(project2);


			label1.addIssue(issue1);
			label1.addIssue(issue2);
			label2.addIssue(issue4);
			label3.addIssue(issue3);
			label4.addIssue(issue5);

			labelService.save(label1);
			labelService.save(label2); // detached entity passed to persist error here
			labelService.save(label3);
			labelService.save(label4);

			issueService.save(issue1);
			issueService.save(issue2);
			issueService.save(issue3);
			issueService.save(issue4);
			issueService.save(issue5);

			projectService.save(project1);
			projectService.save(project2);

			userService.save(user1);
			userService.save(user2);
			userService.save(user3);
			userService.save(user4);
		};
	}
}
