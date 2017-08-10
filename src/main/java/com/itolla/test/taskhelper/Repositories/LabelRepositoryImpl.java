package com.itolla.test.taskhelper.Repositories;

import com.itolla.test.taskhelper.models.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
public class LabelRepositoryImpl implements LabelRepositoryCustom {
    @Autowired
    private EntityManager em;

    @Override
    public <S extends Label> S save(S label) {
        return em.merge(label);
    }
}
