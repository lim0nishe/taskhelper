package com.itolla.test.taskhelper.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itolla.test.taskhelper.model.Label;
import com.itolla.test.taskhelper.service.IssueService;
import com.itolla.test.taskhelper.service.LabelService;
import com.itolla.test.taskhelper.service.ProjectService;
import com.itolla.test.taskhelper.model.Project;
import com.itolla.test.taskhelper.service.UserService;
import com.itolla.test.taskhelper.util.JsonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private IssueService issueService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Project> getProjects(){
        return projectService.getAllProjects();
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonRequest createProject(@RequestBody String jsonString){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.USE_LONG_FOR_INTS, true);
            Map<String, Object> jsonMap = objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>(){});
            Project project = new Project();

            if (jsonMap.containsKey("title"))
                project.setTitle(jsonMap.get("title").toString());
            else
                project.setTitle("default title");
            if (jsonMap.containsKey("owner"))
                project.setOwner(userService.getUserById(Long.parseLong(jsonMap.get("owner").toString())));
            else
                project.setOwner(null);
            List<Long> userIdList = (List<Long>)jsonMap.get("users");

            // avoid NullPointer
            if (userIdList != null)
            for (Long userId : userIdList){
                project.addUser(userService.getUserById(userId));
            }

            projectService.save(project);
            return new JsonRequest("200", "project successfully created");

        }
        catch (ClassCastException e1){
            e1.printStackTrace();
            return new JsonRequest("500", "ClassCastException");
        }
        catch (IOException e2){
            e2.printStackTrace();
            return new JsonRequest("500", "something wrong with json map");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Project getProject(@PathVariable("id") Long id){
        return projectService.getProjectById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public JsonRequest updateProject(@PathVariable("id") Long id, @RequestBody String jsonString){
        Project project = projectService.getProjectById(id);
        if (project == null)
            return new JsonRequest("404", "Project not found");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.USE_LONG_FOR_INTS, true);
            Map<String, Object> jsonMap = objectMapper.readValue(jsonString,
                    new TypeReference<Map<String, Object>>() {});

            if (jsonMap.containsKey("title"))
                project.setTitle(jsonMap.get("title").toString());
            else
                project.setTitle("default");



        }
        catch (IOException e1){
            e1.printStackTrace();
            return new JsonRequest("500", "something wrong with json map");
        }

    }
}
