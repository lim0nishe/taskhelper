package com.itolla.test.taskhelper.Repositories;

import com.itolla.test.taskhelper.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
public class ProjectRepositoryImpl implements ProjectRepositoryCustom {
    @Autowired
    private EntityManager em;

    @Override
    public <S extends Project> S save(S project) {
        return em.merge(project);
    }
}
