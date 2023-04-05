package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.UsId;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UsRepositoryTest {

    /**
     * Method: add(userStory).
     * Adds a userStory to the userStories Repository.
     * <br>
     * Scenario 01: verify if a userStory is added to the list of userStories, if the list is not empty.
     * <p>
     * Expected result: userStory is added.
     */

    @Test
    void ensureUserStoryIsAdded() {
        // Arrange
        UserStory userStoryDouble = mock(UserStory.class);
        UsRepository usRepositoryDouble = new UsRepository();
        UsRepository emptyUsRepositoryDouble = new UsRepository();

        // Act
        usRepositoryDouble.add(userStoryDouble);
        boolean result = usRepositoryDouble.equals(emptyUsRepositoryDouble);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 02: verify if a userStory is not added to the list of userStories,
     * if the list already has the userStory in question.
     * <br>
     * Expected result: userStory is not added and exception is thrown.
     */

    @Test
    void ensureUserStoryIsNotAdded() {
        // Arrange
        UserStory userStoryDouble = mock(UserStory.class);
        UsRepository usRepositoryDouble = new UsRepository();
        usRepositoryDouble.add(userStoryDouble);

        // Act
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usRepositoryDouble.add(userStoryDouble);
        });

        // Assert
        assertEquals("User story ID already exists", exception.getMessage());
    }

    /**
     * Method: delete(userStory).
     * Deletes a userStory from the userStories Repository.
     * <br>
     * Scenario 01: verify if a userStory is deleted from the list of userStories,
     * if the list contains the userStory.
     * <p>
     * Expected result: userStory is deleted.
     */

    @Test
    void ensureUserStoryIsDeleted() {
        // Arrange
        UsId usIdDouble = mock(UsId.class);
        UserStory userStoryDouble = mock(UserStory.class);
        UsRepository usRepositoryDouble = new UsRepository();
        UsRepository emptyUsRepositoryDouble = new UsRepository();
        usRepositoryDouble.add(userStoryDouble);

        // Act
        when(userStoryDouble.getUsId()).thenReturn(usIdDouble);
        usRepositoryDouble.delete(usIdDouble);
        boolean result = usRepositoryDouble.equals(emptyUsRepositoryDouble);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: verify if a userStory is not deleted from the list of userStories,
     * because the userStory does not exist.
     * <p>
     * Expected result: userStory is not deleted and exception is thrown.
     */

    @Test
    void ensureUserStoryIsNotDeletedUserStoryDoesNotExist() {
        // Arrange
        UsId usIdDouble = mock(UsId.class);
        UserStory userStoryDouble = mock(UserStory.class);
        UsRepository usRepositoryDouble = new UsRepository();
        usRepositoryDouble.add(userStoryDouble);


        when(userStoryDouble.getUsId()).thenReturn(mock(UsId.class));

        // Act
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usRepositoryDouble.delete(usIdDouble);
        });

        // Assert
        assertEquals("User story does not exist", exception.getMessage());
    }

    /**
     * Method: getUsWithMatchingIds(usId).
     * Retrieves a list of userStories matching the list of ids given to the userStories Repository.
     * <br>
     * Scenario 01: verify if a list of userStories is retrieved with given IDs.
     * <p>
     * Expected result: list of userStories is retrieved.
     */

    @Test
    void ensureListOfUsIsRetrieved() {
        UsId usIdDouble = mock(UsId.class);
        UserStory userStoryDouble = mock(UserStory.class);
        UsRepository usRepositoryDouble = new UsRepository();
        usRepositoryDouble.add(userStoryDouble);

        List<UsId> userStoryIds = new ArrayList<>();
        userStoryIds.add(usIdDouble);

        List<UserStory> userStories = new ArrayList<>();
        userStories.add(0, userStoryDouble);

        when(userStoryDouble.getUsId()).thenReturn(usIdDouble);

        boolean result = usRepositoryDouble.getListOfUsWithMatchingIds(userStoryIds)
                .equals(userStories);

        assertTrue(result);
    }

    /**
     * Scenario 02: verify if a list of userStories is not retrieved with given IDs.
     * <p>
     * Expected result: list of userStories is not retrieved, exception is thrown.
     */

    @Test
    void ensureListOfUsIsNotRetrieved() {
        UsId usIdDouble = mock(UsId.class);
        UserStory userStoryDouble = mock(UserStory.class);
        UsRepository usRepositoryDouble = new UsRepository();

        List<UsId> userStoryIds = new ArrayList<>();
        userStoryIds.add(usIdDouble);

        List<UserStory> userStories = new ArrayList<>();

        when(userStoryDouble.getUsId()).thenReturn(usIdDouble);

        // Act
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usRepositoryDouble.getListOfUsWithMatchingIds(userStoryIds)
                    .equals(userStories);
        });

        // Assert
        assertEquals("User story list does not contain userStories matching given IDs",
                exception.getMessage());
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal UsRepository objects are the same.
     */

    @Test
    public void ensureTwoUsIdInstancesHashcodeAreTheSame() {
        // Arrange
        UsRepository usRepositoryOne = new UsRepository();
        UsRepository usRepositoryTwo = new UsRepository();

        // Act
        int repositoryOneHashCode = usRepositoryOne.hashCode();
        int repositoryTwoHashCode = usRepositoryTwo.hashCode();

        // Assert
        assertEquals(repositoryOneHashCode, repositoryTwoHashCode);
    }

    /**
     * Scenario 2: Two different UsRepository objects are not the same.
     */

    @Test
    public void ensureTwoUsIdInstancesHashcodeAreNotTheSame() {
        // Arrange
        UsRepository usRepositoryOne = new UsRepository();
        UsRepository usRepositoryThree = new UsRepository();
        UserStory userStoryDouble = mock(UserStory.class);
        usRepositoryThree.add(userStoryDouble);

        // Act
        int repositoryOneHashCode = usRepositoryOne.hashCode();
        int repositoryThreeHashCode = usRepositoryThree.hashCode();

        // Assert
        assertNotEquals(repositoryOneHashCode, repositoryThreeHashCode);
    }
}