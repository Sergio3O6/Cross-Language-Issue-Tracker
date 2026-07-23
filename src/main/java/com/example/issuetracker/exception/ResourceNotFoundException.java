package com.example.issuetracker.exception;

/**
 * Thrown when an issue id does not exist.
 *
 * <p>The service layer throws this; {@link GlobalExceptionHandler} translates it into a
 * clean HTTP 404 response so controllers stay free of error-handling boilerplate.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    /** Convenience factory for the common "Issue 42 not found" case. */
    public static ResourceNotFoundException issue(Long id) {
        return new ResourceNotFoundException("Issue not found with id " + id);
    }
}
