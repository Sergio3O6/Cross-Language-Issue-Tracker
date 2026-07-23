package com.example.issuetracker.repository;

import com.example.issuetracker.model.Issue;
import com.example.issuetracker.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data-access layer for {@link Issue}.
 *
 * <p>By extending {@link JpaRepository} we inherit CRUD methods for free
 * ({@code findAll}, {@code findById}, {@code save}, {@code deleteById}, ...),
 * so no boilerplate implementation is needed — Spring Data generates it at runtime.
 */
@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    /**
     * Derived query used by the status filter (GET /api/issues?status=OPEN).
     *
     * <p>Spring Data parses the method name and builds the query
     * {@code SELECT i FROM Issue i WHERE i.status = :status} automatically.
     */
    List<Issue> findByStatus(Status status);
}
