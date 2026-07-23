package com.example.issuetracker.exception;

/** Thrown when an issue id does not exist; mapped to HTTP 404. */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public static ResourceNotFoundException issue(Long id) {
        return new ResourceNotFoundException("Issue not found with id " + id);
    }
}
