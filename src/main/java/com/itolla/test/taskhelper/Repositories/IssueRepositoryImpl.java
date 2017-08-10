package com.itolla.test.taskhelper.Repositories;

import com.itolla.test.taskhelper.models.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
public class IssueRepositoryImpl implements IssueRepositoryCustom{
    @Autowired
    private EntityManager em;

    @Override
    public <S extends Issue> S save(S issue) {
        return em.merge(issue);
    }
}

