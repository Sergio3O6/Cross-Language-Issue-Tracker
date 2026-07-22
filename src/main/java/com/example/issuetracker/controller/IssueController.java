package com.example.issuetracker.controller;

import com.example.issuetracker.model.Issue;
import com.example.issuetracker.service.IssueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST endpoints for issues.
 *
 * <p>Design decision — REST conventions used here:
 * <ul>
 *   <li>Collection at {@code /api/issues}.</li>
 *   <li>HTTP verbs map to operations: GET read.</li>
 * </ul>
 * The controller stays thin: it delegates all logic to {@link IssueService}.
 */
@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final IssueService service;

    public IssueController(IssueService service) {
        this.service = service;
    }

    /**
     * GET /api/issues -> all issues.
     */
    @GetMapping
    public List<Issue> getIssues() {
        return service.findAll();
    }
}
