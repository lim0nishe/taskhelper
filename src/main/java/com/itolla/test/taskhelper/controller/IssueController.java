package com.itolla.test.taskhelper.controller;

import com.itolla.test.taskhelper.dto.IssueDto;
import com.itolla.test.taskhelper.repository.IssueRepository;
import com.itolla.test.taskhelper.repository.ProjectRepository;
import com.itolla.test.taskhelper.model.Issue;
import com.itolla.test.taskhelper.util.IssueNotFoundException;
import com.itolla.test.taskhelper.util.JsonResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<IssueDto> getIssues(){
        List<Issue> issues = issueRepository.findAll();

        // convert to DTO
        return issues.stream().map(issue -> modelMapper.map(issue, IssueDto.class)).collect(Collectors.toList());

    }

    @RequestMapping(value = "/{projectId}/{issueId}", method = RequestMethod.GET)
    public IssueDto getIssue(@PathVariable("projectId") Long projectId, @PathVariable("issueId") Long issueId){
        return modelMapper.map(issueRepository.findOne(issueId), IssueDto.class);
    }

    @RequestMapping(value = "/{projectId}/{issueId}", method = RequestMethod.PUT)
    public IssueDto updateIssue(@PathVariable("projectId") Long projectId, @PathVariable("issueId") Long issueId,
                                      @RequestBody IssueDto issueDto){
        Issue issue = issueRepository.findOne(issueId);
        if (issue == null) throw new IssueNotFoundException();

        Issue updatedIssue = modelMapper.map(issueDto, Issue.class);

        // set id to make save method merge old issue
        updatedIssue.setId(issueId);
        updatedIssue.setProject(projectRepository.findOne(projectId));

        issueRepository.save(updatedIssue);
        return modelMapper.map(updatedIssue, IssueDto.class);

    }

    @RequestMapping(value = "/{projectId}/{issueId}", method = RequestMethod.DELETE)
    public JsonResponse deleteIssue(@PathVariable("projectId") Long projectId, @PathVariable("issueId") Long issueId){
        Issue issue = issueRepository.findOne(issueId);
        if (issue == null) throw new IssueNotFoundException();

        issueRepository.delete(issue);

        return new JsonResponse("200", "Issue successfully deleted");
    }

}
