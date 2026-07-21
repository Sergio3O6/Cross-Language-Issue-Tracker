package com.example.issuetracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * The core domain entity — a single tracked issue.
 *
 * <p>This is the JPA {@code @Entity} mapped to the {@code issues} table in MySQL. Hibernate
 * creates/updates the table automatically because {@code spring.jpa.hibernate.ddl-auto=update}
 * is set.
 *
 * <p>Design decision: this persistence entity is deliberately kept separate from the inbound
 * request DTO ({@code IssueRequest}). Clients never bind directly to the entity, which
 * keeps generated fields ({@code id}, {@code createdDate}) server-controlled.
 */
@Entity
@Table(name = "issues")
public class Issue {

    /** Surrogate primary key, auto-incremented by MySQL. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Short summary of the issue. Required (validated on the DTO). */
    @Column(nullable = false)
    private String title;

    /** Longer free-text description. Optional; TEXT column for length. */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Current lifecycle state. Stored as its name ("OPEN", ...) rather than an ordinal so that
     * reordering the enum can never silently corrupt existing rows.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    /** Urgency level. Also persisted as a String for the same reason as {@link #status}. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    /**
     * Timestamp set once, by Hibernate, when the row is first inserted.
     * {@code updatable = false} guarantees it is never overwritten on a later PUT.
     */
    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    /** Person the issue is assigned to. Optional (may be unassigned). */
    private String assignee;

    /** JPA requires a no-args constructor. */
    public Issue() {
    }

    /** Convenience constructor used by the service layer and tests. */
    public Issue(String title, String description, Status status, Priority priority, String assignee) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.assignee = assignee;
    }

    // ---- Getters and setters -------------------------------------------------
    // (Standard JavaBean accessors; Jackson uses these to (de)serialize JSON.)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
}
