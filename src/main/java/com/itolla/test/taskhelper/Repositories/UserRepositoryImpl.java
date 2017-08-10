package com.itolla.test.taskhelper.Repositories;

import com.itolla.test.taskhelper.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public <S extends User> S save(S user) {
        return em.merge(user);
    }
}
