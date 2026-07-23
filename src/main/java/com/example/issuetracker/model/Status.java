package com.example.issuetracker.model;

/** Lifecycle states for an {@link Issue}; persisted by name via @Enumerated(STRING). */
public enum Status {
    OPEN,
    IN_PROGRESS,
    RESOLVED,
    CLOSED
}
