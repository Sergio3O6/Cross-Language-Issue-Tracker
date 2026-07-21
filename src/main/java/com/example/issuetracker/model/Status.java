package com.example.issuetracker.model;

/**
 * Allowed lifecycle states for an {@link Issue}.
 *
 * <p>Design decision: modelling status as an {@code enum} (rather than a free-text String)
 * gives us a compile-time constraint on the backend and a fixed set of values the WPF client
 * can present in a dropdown. The values are persisted as their names
 * (see {@code @Enumerated(EnumType.STRING)} on the entity) so the database is human-readable.
 */
public enum Status {
    OPEN,
    IN_PROGRESS,
    RESOLVED,
    CLOSED
}
