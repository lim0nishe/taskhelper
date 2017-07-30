package com.itolla.test.taskhelper.Repositories;

import com.itolla.test.taskhelper.models.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository <Label, Long>{
}
