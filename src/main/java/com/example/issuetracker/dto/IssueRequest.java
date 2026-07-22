package com.example.issuetracker.dto;

import com.example.issuetracker.model.Priority;
import com.example.issuetracker.model.Status;

/**
 * Inbound request body for creating an issue.
 *
 * <p>Design decision — why a DTO instead of binding the {@code Issue} entity directly:
 * <ul>
 *   <li>Server-managed fields ({@code id}, {@code createdDate}) can't be spoofed by a client.</li>
 *   <li>Validation rules live on the API boundary, not on the persistence model.</li>
 *   <li>The wire contract can evolve independently of the database schema.</li>
 * </ul>
 *
 * <p>Implemented as a Java {@code record}: immutable, concise, and Jackson binds JSON to the
 * canonical constructor by component name.
 */
public record IssueRequest(
        String title,
        String description,
        Status status,
        Priority priority,
        String assignee
) {
}
