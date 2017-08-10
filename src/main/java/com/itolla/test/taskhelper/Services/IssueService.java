package com.itolla.test.taskhelper.Services;

import com.itolla.test.taskhelper.models.Issue;

import java.util.List;

public interface IssueService {
    Issue getIssueById(Long id);
    List<Issue> getAllIssues();
    Issue save(Issue issue);

    // может вызвать detache object passed to persist
    // если так, перегрузить JpaRepository
    Issue update(Issue issue);
}
