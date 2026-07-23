package com.example.issuetracker.service;

import com.example.issuetracker.dto.IssueRequest;
import com.example.issuetracker.exception.ResourceNotFoundException;
import com.example.issuetracker.model.Issue;
import com.example.issuetracker.model.Status;
import com.example.issuetracker.repository.IssueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/** Business logic for issues; keeps HTTP concerns out of the data layer. */
@Service
public class IssueService {

    private final IssueRepository repository;

    public IssueService(IssueRepository repository) {
        this.repository = repository;
    }

    public List<Issue> findAll(Status status) {
        if (status != null) {
            return repository.findByStatus(status);
        }
        return repository.findAll();
    }

    public Issue findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.issue(id));
    }

    public Issue create(IssueRequest request) {
        Issue issue = new Issue(
                request.title(),
                request.description(),
                request.status(),
                request.priority(),
                request.assignee());
        return repository.save(issue);
    }

    public Issue update(Long id, IssueRequest request) {
        Issue issue = findById(id); // createdDate is preserved (updatable = false)
        issue.setTitle(request.title());
        issue.setDescription(request.description());
        issue.setStatus(request.status());
        issue.setPriority(request.priority());
        issue.setAssignee(request.assignee());
        return repository.save(issue);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw ResourceNotFoundException.issue(id);
        }
        repository.deleteById(id);
    }
}
