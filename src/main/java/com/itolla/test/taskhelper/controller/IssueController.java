package com.itolla.test.taskhelper.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itolla.test.taskhelper.model.User;
import com.itolla.test.taskhelper.repository.IssueRepository;
import com.itolla.test.taskhelper.repository.LabelRepository;
import com.itolla.test.taskhelper.repository.UserRepository;
import com.itolla.test.taskhelper.model.Issue;
import com.itolla.test.taskhelper.util.IssueNotFoundException;
import com.itolla.test.taskhelper.util.JsonRequest;
import com.itolla.test.taskhelper.util.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Issue> getIssues(){
        return issueRepository.findAll();
    }

    @RequestMapping(value = "/{projectId}/{issueId}", method = RequestMethod.GET)
    public Issue getIssue(@PathVariable("projectId") Long projectId, @PathVariable("issueId") Long issueId){
        return issueRepository.findOne(issueId);
    }

    @RequestMapping(value = "/{projectId}/{issueId}", method = RequestMethod.PUT)
    public JsonRequest updateIssue(@PathVariable("projectId") Long projectId, @PathVariable("issueId") Long issueId,
                                   @RequestBody String jsonString){
        Issue issue = issueRepository.findOne(issueId);
        if (issue == null) throw new IssueNotFoundException();

        try {
            // deserialize json into map
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.USE_LONG_FOR_INTS, true);
            Map<String, Object> jsonMap = objectMapper.readValue(jsonString,
                    new TypeReference<Map<String, Object>>() {});

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
                issue.removeAllLabels();
                for (Long labelId : labelIdList){
                    issue.addLabel(labelRepository.findOne(labelId));
                }
            }

            issueRepository.save(issue);
            return new JsonRequest(202, "issue successfully updated");
        }
        catch (IOException e){
            e.printStackTrace();
            return new JsonRequest(400, "something wrong with json map");
        }
    }

    @RequestMapping(value = "/{projectId}/{issueId}", method = RequestMethod.DELETE)
    public JsonRequest deleteIssue(@PathVariable("projectId") Long projectId, @PathVariable("issueId") Long issueId){
        Issue issue = issueRepository.findOne(issueId);
        if (issue == null) throw new IssueNotFoundException();

        issue.setUser(null);
        issue.setProject(null);
        issue.removeAllLabels();
        issueRepository.delete(issue);

        return new JsonRequest(200, "Issue successfully deleted");
    }
}
