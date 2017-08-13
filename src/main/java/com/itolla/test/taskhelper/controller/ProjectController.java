package com.itolla.test.taskhelper.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itolla.test.taskhelper.model.Issue;
import com.itolla.test.taskhelper.model.Label;
import com.itolla.test.taskhelper.model.User;
import com.itolla.test.taskhelper.repository.IssueRepository;
import com.itolla.test.taskhelper.repository.LabelRepository;
import com.itolla.test.taskhelper.repository.ProjectRepository;
import com.itolla.test.taskhelper.repository.UserRepository;
import com.itolla.test.taskhelper.model.Project;
import com.itolla.test.taskhelper.util.ProjectNotFoundException;
import com.itolla.test.taskhelper.util.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private IssueRepository issueRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Project> getProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createProject(@RequestBody String jsonString){
        try{
            // deserialize json into map
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.USE_LONG_FOR_INTS, true);
            Map<String, Object> jsonMap = objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>(){});
            Project project = new Project();

            if (jsonMap.containsKey("title"))
                project.setTitle(jsonMap.get("title").toString());
            else
                project.setTitle("default title");
            if (jsonMap.containsKey("owner"))
                project.setOwner(userRepository.findOne((Long)jsonMap.get("owner")));
            else
                project.setOwner(null);
            List<Long> userIdList = (List<Long>)jsonMap.get("users");

            // avoid NullPointer
            if (userIdList != null)
            for (Long userId : userIdList){
                project.addUser(userRepository.findOne(userId));
            }

            projectRepository.save(project);
            return ResponseEntity.ok("Project successfully created");

        }
        catch (IOException e2){
            e2.printStackTrace();
            return new ResponseEntity<>("Something wrong with json map", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Project getProject(@PathVariable("id") Long id){
        return projectRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateProject(@PathVariable("id") Long id, @RequestBody String jsonString){
        Project project = projectRepository.findOne(id);
        if (project == null) throw new ProjectNotFoundException();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.USE_LONG_FOR_INTS, true);
            Map<String, Object> jsonMap = objectMapper.readValue(jsonString,
                    new TypeReference<Map<String, Object>>() {});

            if (jsonMap.containsKey("title"))
                project.setTitle(jsonMap.get("title").toString());

            if (jsonMap.containsKey("owner"))
                project.setOwner(userRepository.findOne((Long)jsonMap.get("owner")));

            if (jsonMap.containsKey("users")){
                project.removeAllUsers();
                List<Long> userIdList = (List<Long>)jsonMap.get("users");
                for (Long userId : userIdList){
                    project.addUser(userRepository.findOne(userId));
                }
            }
            projectRepository.save(project);
            return ResponseEntity.ok("User successfully udated");


        }
        catch (IOException e1){
            e1.printStackTrace();
            return new ResponseEntity<>("Something wrong with json map", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}/labels", method = RequestMethod.GET)
    public Page<Label> getProjectLabels(@PathVariable("id") Long id, Pageable pageable){

        // можно убрать, если примлимо, что будет падать внутри запроса
        Project project = projectRepository.findOne(id);

        if (project == null) throw new ProjectNotFoundException();
        return labelRepository.findAllByProjectProjectId(id, pageable);
    }

    @RequestMapping(value = "/{id}/labels", method = RequestMethod.POST)
    public ResponseEntity<String> createLabelForProject(@PathVariable("id") Long projectId, @RequestBody String jsonString){
        Project project = projectRepository.findOne(projectId);
        if (project == null) throw new ProjectNotFoundException();

        try {
            // deserialize json into map
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.USE_LONG_FOR_INTS, true);
            Map<String, Object> jsonMap = objectMapper.readValue(jsonString,
                    new TypeReference<Map<String, Object>>() {});

            Label label = new Label();
            if (jsonMap.containsKey("title"))
                label.setTitle(jsonMap.get("title").toString());

            List<Long> issueIdList = (List<Long>)jsonMap.get("issues");
            // avoid NullPointer
            if(issueIdList != null){
                for (Long issueId : issueIdList){
                    label.addIssue(issueRepository.findOne(issueId));
                }
            }

            // save changes
            label.setProject(project);

            labelRepository.save(label);
            return ResponseEntity.ok("Label successfully created");
        }
        catch (IOException e1){
            e1.printStackTrace();
            return new ResponseEntity<>("Something wrong with json map", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}/issues", method = RequestMethod.GET)
    public Page<Issue> getProjectIssues(@PathVariable("id") Long projectId, Pageable pageable){

        // можно убрать, если примлимо, что будет падать внутри запроса
        Project project = projectRepository.findOne(projectId);

        if (project == null) throw new ProjectNotFoundException();
        return issueRepository.findAllByProjectProjectId(projectId, pageable);
    }

    @RequestMapping(value = "/{id}/issues", method = RequestMethod.POST)
    public ResponseEntity<String> createIssueForProject(@PathVariable("id") Long projectId, @RequestBody String jsonString){
        Project project = projectRepository.findOne(projectId);
        if (project == null) throw new ProjectNotFoundException();

        try{
            // deserialize json into map
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.USE_LONG_FOR_INTS, true);
            Map<String, Object> jsonMap = objectMapper.readValue(jsonString,
                    new TypeReference<Map<String, Object>>() {});

            Issue issue = new Issue();

            if (jsonMap.containsKey("title"))
                issue.setTitle(jsonMap.get("title").toString());

            if (jsonMap.containsKey("description"))
                issue.setDescription(jsonMap.get("description").toString());

            if (jsonMap.containsKey("user")){
                User user = userRepository.findOne((Long)jsonMap.get("user"));
                if (user == null) throw new UserNotFoundException();
                issue.setUser(user);
            }

            List<Long> labelIdList = (List<Long>)jsonMap.get("labels");
            // avoid NullPointer
            if (labelIdList != null){
                for (Long labelId : labelIdList){
                    issue.addLabel(labelRepository.findOne(labelId));
                }
            }

            issue.setProject(project);
            issueRepository.save(issue);
            return ResponseEntity.ok("Issue successfully created");
        }
        catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity<>("Something wrong with json map", HttpStatus.BAD_REQUEST);
        }
    }
}
