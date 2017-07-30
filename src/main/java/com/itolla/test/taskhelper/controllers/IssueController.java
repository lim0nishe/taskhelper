package com.itolla.test.taskhelper.controllers;

import com.itolla.test.taskhelper.Services.IssueService;
import com.itolla.test.taskhelper.models.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Issue> getIssues(){
        return issueService.getAllIssues();
    }

    // проверить, подставляется ли путь, который я указал в аннотации класса
    // возможно не будет работать с оберткой Long, хотя наврят ли. Проверить
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Issue getIssue(@PathVariable("id") Long id){
        return issueService.getIssueById(id);
    }
}
