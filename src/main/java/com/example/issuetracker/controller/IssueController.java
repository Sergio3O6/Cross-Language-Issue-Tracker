package com.example.issuetracker.controller;

import com.example.issuetracker.dto.IssueRequest;
import com.example.issuetracker.model.Issue;
import com.example.issuetracker.model.Status;
import com.example.issuetracker.service.IssueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
     * GET /api/issues            -> all issues.
     * GET /api/issues?status=OPEN -> issues filtered by status.
     *
     * @param status optional query parameter; when omitted, all issues are returned.
     */
    @GetMapping
    public List<Issue> getIssues(@RequestParam(required = false) Status status) {
        return service.findAll(status);
    }

    /**
     * GET /api/issues/{id} -> one issue, or 404 if it doesn't exist.
     */
    @GetMapping("/{id}")
    public Issue getIssue(@PathVariable Long id) {
        return service.findById(id);
    }

    /**
     * POST /api/issues -> create. Returns 201 Created with a Location header.
     * {@code @Valid} triggers the DTO constraints on the request body.
     */
    @PostMapping
    public ResponseEntity<Issue> createIssue(@Valid @RequestBody IssueRequest request,
                                             UriComponentsBuilder uriBuilder) {
        Issue created = service.create(request);
        URI location = uriBuilder.path("/api/issues/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    /**
     * PUT /api/issues/{id} -> update an existing issue. 200 OK.
     */
    @PutMapping("/{id}")
    public Issue updateIssue(@PathVariable Long id, @Valid @RequestBody IssueRequest request) {
        return service.update(id, request);
    }

    /**
     * DELETE /api/issues/{id} -> remove an issue. 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
