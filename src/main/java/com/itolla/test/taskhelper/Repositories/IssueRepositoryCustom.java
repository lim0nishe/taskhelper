package com.itolla.test.taskhelper.Repositories;

import com.itolla.test.taskhelper.models.Issue;

public interface IssueRepositoryCustom {
    <S extends Issue> S save(S issue);
}
