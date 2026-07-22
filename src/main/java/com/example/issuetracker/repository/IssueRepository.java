package com.example.issuetracker.repository;

import com.example.issuetracker.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Data-access layer for {@link Issue}.
 *
 * <p>By extending {@link JpaRepository} we inherit CRUD methods for free
 * ({@code findAll}, {@code findById}, {@code save}, {@code deleteById}, ...),
 * so no boilerplate implementation is needed — Spring Data generates it at runtime.
 */
@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
}
