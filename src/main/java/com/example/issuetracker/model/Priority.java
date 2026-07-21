package com.example.issuetracker.model;

/**
 * Priority levels for an {@link Issue}, ordered from least to most urgent.
 *
 * <p>Like {@link Status}, this is an enum so the value set is validated on the server
 * and mirrored exactly by the C# client enum.
 */
public enum Priority {
    LOW,
    MEDIUM,
    HIGH,
    CRITICAL
}
