package com.itolla.test.taskhelper.Repositories;

import com.itolla.test.taskhelper.models.User;

public interface UserRepositoryCustom {
    <S extends User> S save(S user);
}
