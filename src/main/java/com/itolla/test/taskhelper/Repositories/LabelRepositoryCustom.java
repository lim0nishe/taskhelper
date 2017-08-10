package com.itolla.test.taskhelper.Repositories;

import com.itolla.test.taskhelper.models.Label;

public interface LabelRepositoryCustom {
    <S extends Label> S save(S label);
}
