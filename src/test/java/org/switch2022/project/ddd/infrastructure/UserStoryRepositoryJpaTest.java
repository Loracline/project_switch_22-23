package org.switch2022.project.ddd.infrastructure;

import org.h2.engine.User;
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
import static org.mockito.ArgumentMatchers.anyString;
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
        UsId usIdOne = new UsId("p001","us001");
        UsId usIdTwo = new UsId("p001","us002");
        List<UsId> usId = new ArrayList<>();
        usId.add(usIdOne);
        usId.add(usIdTwo);

        UserStory userStoryDoubleOne = mock(UserStory.class);
        UserStory userStoryDoubleTwo = mock(UserStory.class);

        List<UserStory> expected = new ArrayList<>();
        expected.add(userStoryDoubleOne);
        expected.add(userStoryDoubleTwo);

        List<String> userStoryIds = new ArrayList<>();
        userStoryIds.add("p001_us001");
        userStoryIds.add("p001_us002");

        List<UserStoryJpa> userStoryJpas = new ArrayList<>();
        UserStoryJpa userStoryJpaDouble = mock(UserStoryJpa.class);
        UserStoryJpa userStoryJpaDoubleTwo = mock(UserStoryJpa.class);
        userStoryJpas.add(userStoryJpaDouble);
        userStoryJpas.add(userStoryJpaDoubleTwo);

        when(repositoryJpa.findAllByUsIdIn(userStoryIds)).thenReturn(userStoryJpas);
        when(assembler.toDomain(userStoryJpaDouble)).thenReturn(userStoryDoubleOne);
        when(assembler.toDomain(userStoryJpaDoubleTwo)).thenReturn(userStoryDoubleTwo);


        // Act
        List<UserStory> result = repository.getListOfUsWithMatchingIds(usId);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Test to ensure that a list of user stories with no matching IDs is
     * retrieved empty.
     */

    @Test
    void ensureListOfUsWithMatchingIdsIsRetrievedEmpty() {
        // Arrange
        List<UsId> usId = new ArrayList<>();

        UserStory userStoryDoubleOne = mock(UserStory.class);
        UserStory userStoryDoubleTwo = mock(UserStory.class);

        List<String> userStoryIds = new ArrayList<>();
        userStoryIds.add("p001_us001");
        userStoryIds.add("p001_us002");

        List<UserStoryJpa> userStoryJpas = new ArrayList<>();
        UserStoryJpa userStoryJpaDouble = mock(UserStoryJpa.class);
        UserStoryJpa userStoryJpaDoubleTwo = mock(UserStoryJpa.class);
        userStoryJpas.add(userStoryJpaDouble);
        userStoryJpas.add(userStoryJpaDoubleTwo);

        when(repositoryJpa.findAllByUsIdIn(userStoryIds)).thenReturn(userStoryJpas);
        when(assembler.toDomain(userStoryJpaDouble)).thenReturn(userStoryDoubleOne);
        when(assembler.toDomain(userStoryJpaDoubleTwo)).thenReturn(userStoryDoubleTwo);

        List<UserStory> expected = new ArrayList<>();

        // Act
        List<UserStory> result = repository.getListOfUsWithMatchingIds(usId);

        // Assert
        assertEquals(expected, result);
    }
}