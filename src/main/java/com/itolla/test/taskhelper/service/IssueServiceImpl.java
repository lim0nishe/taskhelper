package com.itolla.test.taskhelper.service;

import com.itolla.test.taskhelper.repository.IssueRepository;
import com.itolla.test.taskhelper.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("issueService")
public class IssueServiceImpl implements IssueService {

    @Autowired
    IssueRepository issueRepository;


    @Override
    public Issue getIssueById(Long id) {
        return issueRepository.findOne(id);
    }

    @Override
    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    @Override
    public Issue save(Issue issue) {
        return issueRepository.save(issue);
    }

    @Override
    public Issue update(Issue issue) {
        return issueRepository.save(issue);
    }

}
