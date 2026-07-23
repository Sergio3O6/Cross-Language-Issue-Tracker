package com.example.issuetracker.repository;

import com.example.issuetracker.model.Issue;
import com.example.issuetracker.model.Priority;
import com.example.issuetracker.model.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Repository slice tests.
 *
 * <p>{@code @DataJpaTest} boots only the JPA layer against an in-memory H2 database
 * (configured in src/test/resources/application.properties), so these tests are fast
 * and need no MySQL server. Here we verify the derived query {@code findByStatus}.
 */
@DataJpaTest
class IssueRepositoryTest {

    @Autowired
    private IssueRepository repository;

    @Test
    void findByStatus_returnsOnlyMatchingIssues() {
        // given: two OPEN issues and one CLOSED issue
        repository.save(new Issue("Login broken", "500 on submit", Status.OPEN, Priority.HIGH, "alice"));
        repository.save(new Issue("Typo on homepage", "Footer year wrong", Status.OPEN, Priority.LOW, "bob"));
        repository.save(new Issue("Old bug", "Already fixed", Status.CLOSED, Priority.MEDIUM, "carol"));

        // when: filtering by OPEN
        List<Issue> open = repository.findByStatus(Status.OPEN);

        // then: only the two OPEN issues come back
        assertThat(open).hasSize(2)
                .allMatch(i -> i.getStatus() == Status.OPEN);
    }

    @Test
    void save_populatesIdAndCreatedDate() {
        // when: saving a new issue
        Issue saved = repository.save(
                new Issue("New issue", "desc", Status.OPEN, Priority.MEDIUM, "dave"));

        // then: the server-managed fields are populated
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getCreatedDate()).isNotNull();
    }
}
