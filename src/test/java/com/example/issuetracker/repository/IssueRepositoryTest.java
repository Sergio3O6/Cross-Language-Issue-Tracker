package com.example.issuetracker.repository;

import com.example.issuetracker.model.Issue;
import com.example.issuetracker.model.Priority;
import com.example.issuetracker.model.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/** Repository slice tests against in-memory H2. */
@DataJpaTest
class IssueRepositoryTest {

    @Autowired
    private IssueRepository repository;

    @Test
    void findByStatus_returnsOnlyMatchingIssues() {
        repository.save(new Issue("Login broken", "500 on submit", Status.OPEN, Priority.HIGH, "alice"));
        repository.save(new Issue("Typo on homepage", "Footer year wrong", Status.OPEN, Priority.LOW, "bob"));
        repository.save(new Issue("Old bug", "Already fixed", Status.CLOSED, Priority.MEDIUM, "carol"));

        List<Issue> open = repository.findByStatus(Status.OPEN);

        assertThat(open).hasSize(2)
                .allMatch(i -> i.getStatus() == Status.OPEN);
    }

    @Test
    void save_populatesIdAndCreatedDate() {
        Issue saved = repository.save(
                new Issue("New issue", "desc", Status.OPEN, Priority.MEDIUM, "dave"));

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getCreatedDate()).isNotNull();
    }
}
