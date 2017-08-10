package com.itolla.test.taskhelper.Repositories;

import com.itolla.test.taskhelper.models.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("issueRepository")
public interface IssueRepository extends JpaRepository<Issue, Long>, IssueRepositoryCustom {
}
