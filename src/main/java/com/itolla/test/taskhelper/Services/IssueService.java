package com.itolla.test.taskhelper.Services;

import com.itolla.test.taskhelper.models.Issue;

import java.util.List;

public interface IssueService {
    Issue getIssueById(Long id);
    List<Issue> getAllIssues();
}
