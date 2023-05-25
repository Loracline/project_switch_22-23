package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.datamodel_jpa.UserStoryJpa;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.UserStoryDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.infrastructure.jpa.IUserStoryJpaRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = UserStoryRepositoryJpa.class
)
/**
 * This class is responsible for testing the UserStoryRepositoryJpa. It contains test
 * methods to ensure that the
 * repository operations such as save, delete, and find are working as expected.
 */

class UserStoryRepositoryJpaTest {

    @InjectMocks
    UserStoryRepositoryJpa repository;

    @MockBean
    IUserStoryJpaRepository repositoryJpa;

    @MockBean
    UserStoryDomainDataAssembler assembler;

    /**
     * Sets up the test environment by initializing mocks.
     */

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Method: save()
     * Scenario 1: Test to ensure that a user story is saved successfully.
     */

    @Test
    void ensureUserStoryIsSaved() {
        // Arrange
        UserStory userStoryDouble = mock(UserStory.class);
        UserStoryJpa userStoryJpaDouble = mock(UserStoryJpa.class);


        // Act
        when(assembler.toData(userStoryDouble)).thenReturn(userStoryJpaDouble);
        when(repositoryJpa.existsByUsId(userStoryDouble.getUsId())).thenReturn(false);
        when(repositoryJpa.save(userStoryJpaDouble)).thenReturn(userStoryJpaDouble);

        boolean result = repository.save(userStoryDouble);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Test to ensure that a user story is not saved.
     */

    @Test
    void ensureUserStoryIsNotSaved() {
        // Arrange
        UserStory userStoryDouble = mock(UserStory.class);
        UserStoryJpa userStoryJpaDouble = mock(UserStoryJpa.class);


        // Act
        when(assembler.toData(userStoryDouble)).thenReturn(userStoryJpaDouble);
        when(repositoryJpa.existsByUsId(userStoryDouble.getUsId())).thenReturn(true);
        when(repositoryJpa.save(userStoryJpaDouble)).thenReturn(userStoryJpaDouble);

        // Assert
        Exception exception = assertThrows(
                Exception.class,
                () -> repository.save(userStoryDouble),
                "User story ID already exists"
        );

        assertEquals("User story ID already exists", exception.getMessage());
    }

    /**
     * Method: delete()
     * Scenario 1: Test to ensure that a user story is deleted successfully.
     */

    @Test
    void ensureUserStoryIsDeleted() {
        // Arrange
        UsId usIdDouble = mock(UsId.class);

        // Act
        when(repositoryJpa.existsByUsId(usIdDouble.getUserStoryId())).thenReturn(true);
        when(repositoryJpa.deleteByUsId(usIdDouble.getUserStoryId())).thenReturn(true);

        boolean result = repository.delete(usIdDouble);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Test to ensure that a user story is not deleted.
     */

    @Test
    void ensureUserStoryIsNotDeleted() {
        // Arrange
        UsId usIdDouble = mock(UsId.class);

        // Act
        when(repositoryJpa.existsByUsId(usIdDouble.getUserStoryId())).thenReturn(true);
        when(repositoryJpa.deleteByUsId(usIdDouble.getUserStoryId())).thenReturn(false);

        boolean result = repository.delete(usIdDouble);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: Test to ensure that a user story is not deleted and an exception is
     * thrown.
     */

    @Test
    void ensureUserStoryIsNotDeleted_throwsException() {
        // Arrange
        UsId usIdDouble = mock(UsId.class);

        // Act
        when(repositoryJpa.existsByUsId(usIdDouble.getUserStoryId())).thenReturn(false);
        when(repositoryJpa.deleteByUsId(usIdDouble.getUserStoryId())).thenReturn(false);

        // Assert
        Exception exception = assertThrows(
                Exception.class,
                () -> repository.delete(usIdDouble),
                "The User Story is already in the Product Backlog"
        );

        assertEquals("The User Story is already in the Product Backlog",
                exception.getMessage());
    }

    /**
     * Method: getListOfUsWithMatchingIds()
     * Scenario 1: Test to ensure that a list of user stories with matching IDs is
     * retrieved successfully.
     */

    @Test
    void ensureListOfUsWithMatchingIdsIsRetrieved() {
        // Arrange
        UserStoryJpa userStoryJpaDouble = mock(UserStoryJpa.class);
        UserStory userStoryDouble = mock(UserStory.class);
        List<UserStory> expected = new ArrayList<>();
        expected.add(userStoryDouble);

        // Act
        when(repositoryJpa.findAllByUsId(any())).thenReturn(Collections.singletonList(userStoryJpaDouble));
        when(repositoryJpa.save(userStoryJpaDouble)).thenReturn(userStoryJpaDouble);
        when(assembler.toDomain(userStoryJpaDouble)).thenReturn(userStoryDouble);

        List<UserStory> result = new ArrayList<>();
        result.add(userStoryDouble);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Test to ensure that a list of user stories with matching IDs is not
     * retrieved.
     */

    @Test
    void ensureListOfUsWithMatchingIdsIsRetrievedEmpty() {
        // Arrange
        UserStory userStoryDouble = mock(UserStory.class);
        List<UserStoryJpa> expected = new ArrayList<>();

        // Act
        when(repositoryJpa.findAllByUsId(any())).thenReturn(Collections.emptyList());

        Iterable<UserStoryJpa> result =
                repositoryJpa.findAllByUsId(userStoryDouble.getUsId());

        // Assert
        assertEquals(expected, result);
    }
}