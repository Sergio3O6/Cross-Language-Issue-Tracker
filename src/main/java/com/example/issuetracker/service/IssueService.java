package com.example.issuetracker.service;

import com.example.issuetracker.dto.IssueRequest;
import com.example.issuetracker.model.Issue;
import com.example.issuetracker.repository.IssueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business-logic layer for issues.
 *
 * <p>Design decision — why a service between controller and repository:
 * <ul>
 *   <li>Controllers stay thin and declarative (just HTTP concerns).</li>
 *   <li>Domain rules live in one place and are easy to unit-test.</li>
 * </ul>
 */
@Service
public class IssueService {

    private final IssueRepository repository;

    // Constructor injection (preferred over field injection: immutable + test-friendly).
    public IssueService(IssueRepository repository) {
        this.repository = repository;
    }

    /**
     * List all issues.
     */
    public List<Issue> findAll() {
        return repository.findAll();
    }

    /**
     * Create a new issue from a request DTO.
     */
    public Issue create(IssueRequest request) {
        Issue issue = new Issue(
                request.title(),
                request.description(),
                request.status(),
                request.priority(),
                request.assignee());
        return repository.save(issue);
    }
}
