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

    /**
     * Update an existing issue in place.
     *
     * <p>Note {@code createdDate} is intentionally left untouched — it is server-owned and
     * marked {@code updatable = false} on the entity.
     */
    public Issue update(Long id, IssueRequest request) {
        Issue issue = repository.findById(id).orElseThrow();
        issue.setTitle(request.title());
        issue.setDescription(request.description());
        issue.setStatus(request.status());
        issue.setPriority(request.priority());
        issue.setAssignee(request.assignee());
        return repository.save(issue);
    }

    /**
     * Delete an issue by id.
     */
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
