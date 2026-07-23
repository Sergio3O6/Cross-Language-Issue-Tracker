package com.example.issuetracker.service;

import com.example.issuetracker.dto.IssueRequest;
import com.example.issuetracker.exception.ResourceNotFoundException;
import com.example.issuetracker.model.Issue;
import com.example.issuetracker.model.Status;
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
     * List all issues, optionally filtered by status.
     *
     * @param status if non-null, only issues in that status are returned;
     *               if null, every issue is returned.
     */
    public List<Issue> findAll(Status status) {
        if (status != null) {
            return repository.findByStatus(status);
        }
        return repository.findAll();
    }

    /**
     * Fetch a single issue or fail with a 404-mapped exception.
     */
    public Issue findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.issue(id));
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
        Issue issue = findById(id); // reuses the 404 behaviour
        issue.setTitle(request.title());
        issue.setDescription(request.description());
        issue.setStatus(request.status());
        issue.setPriority(request.priority());
        issue.setAssignee(request.assignee());
        return repository.save(issue);
    }

    /**
     * Delete an issue, or 404 if it never existed.
     */
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw ResourceNotFoundException.issue(id);
        }
        repository.deleteById(id);
    }
}
