package com.itolla.test.taskhelper.Services;

import com.itolla.test.taskhelper.Repositories.IssueRepository;
import com.itolla.test.taskhelper.models.Issue;
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
}
