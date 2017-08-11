package com.itolla.test.taskhelper.repository;

import com.itolla.test.taskhelper.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository <Label, Long> {
}
