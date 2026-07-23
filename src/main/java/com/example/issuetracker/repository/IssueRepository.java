package com.example.issuetracker.repository;

import com.example.issuetracker.model.Issue;
import com.example.issuetracker.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Data-access layer for {@link Issue}. */
@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    // Derived query backing GET /api/issues?status=OPEN.
    List<Issue> findByStatus(Status status);
}
