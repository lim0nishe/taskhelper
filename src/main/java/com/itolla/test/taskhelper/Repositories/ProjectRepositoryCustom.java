package com.itolla.test.taskhelper.Repositories;

import com.itolla.test.taskhelper.models.Project;

public interface ProjectRepositoryCustom {
    <S extends Project> S save(S project);
}
