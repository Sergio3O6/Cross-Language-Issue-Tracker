package com.example.issuetracker.dto;

import com.example.issuetracker.model.Priority;
import com.example.issuetracker.model.Status;

/** Request body for creating an issue; kept separate so clients can't set id/createdDate. */
public record IssueRequest(
        String title,
        String description,
        Status status,
        Priority priority,
        String assignee
) {
}
