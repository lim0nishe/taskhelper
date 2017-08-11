package com.itolla.test.taskhelper.repository;

import com.itolla.test.taskhelper.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("issueRepository")
public interface IssueRepository extends JpaRepository<Issue, Long>{
}
