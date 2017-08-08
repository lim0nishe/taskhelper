package com.itolla.test.taskhelper;

import com.itolla.test.taskhelper.Repositories.IssueRepository;
import com.itolla.test.taskhelper.Repositories.LabelRepository;
import com.itolla.test.taskhelper.Repositories.ProjectRepository;
import com.itolla.test.taskhelper.Repositories.UserRepository;
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
	public CommandLineRunner setup(IssueRepository issueRepository, LabelRepository labelRepository,
								   ProjectRepository projectRepository, UserRepository userRepository){
		return (args) -> {
			User user1 = new User("user1", "secret");
			User user2 = new User("user2", "secret");
			User user3 = new User("user3", "secret");
			User user4 = new User("user4", "secret");

			Project project1 = new Project("first project", user1);
			Project project2 = new Project("second project", user2);

			project1.addUser(user1);
			project1.addUser(user3);
			project1.addUser(user4);

			project2.addUser(user2);
			project2.addUser(user1);

			Label label1 = new Label("project 1 plebs tasks", project1);
			Label label2 = new Label("project 2 plebs tasks", project2);
			Label label3 = new Label("project 1 boss tasks", project1);
			Label label4 = new Label("project 2 boss tasks", project2);

			Issue issue1 = new Issue("first issue", "do something", user4, project1);
			Issue issue2 = new Issue("second issue", "do something", user3, project1);
			Issue issue3 = new Issue( "issue for boss 1", "do nothing", user1, project1);
			Issue issue4 = new Issue("third issue", "do something brilliant", user1, project2);
			Issue issue5 = new Issue("issue for boss 2", "do nothing", user2, project2);

			label1.addIssue(issue1);
			label1.addIssue(issue2);
			label2.addIssue(issue4);
			label3.addIssue(issue3);
			label4.addIssue(issue5);

			labelRepository.save(label1);
			labelRepository.save(label2);
			labelRepository.save(label3);
			labelRepository.save(label4);

			issueRepository.save(issue1);
			issueRepository.save(issue2);
			issueRepository.save(issue3);
			issueRepository.save(issue4);
			issueRepository.save(issue5);

			projectRepository.save(project1);
			projectRepository.save(project2);

			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);
			userRepository.save(user4);

		};
	}
}
