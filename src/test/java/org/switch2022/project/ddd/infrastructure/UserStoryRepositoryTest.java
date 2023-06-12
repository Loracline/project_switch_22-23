package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryRepositoryTest {

    /**
     * Method: add(userStory).
     * Adds a userStory to the userStories Repository.
     * <br>
     * Scenario 01: verify if a userStory is added to the list of userStories, if the list is not empty.
     * <p>
     * Expected result: userStory is added.
     */

    @Test
    void ensureUserStoryIsAdded() throws Exception {
        // Arrange
        UserStory userStoryDouble = mock(UserStory.class);
        UserStoryRepository usRepositoryDouble = new UserStoryRepository();
        UserStoryRepository emptyUsRepositoryDouble = new UserStoryRepository();

        // Act
        usRepositoryDouble.save(userStoryDouble);
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
    void ensureUserStoryIsNotAdded() throws Exception {
        // Arrange
        UserStory userStoryDouble = mock(UserStory.class);
        UserStoryRepository usRepositoryDouble = new UserStoryRepository();
        usRepositoryDouble.save(userStoryDouble);

        // Act
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usRepositoryDouble.save(userStoryDouble);
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
    void ensureUserStoryIsDeleted() throws Exception {
        // Arrange
        UsId usIdDouble = mock(UsId.class);
        UserStory userStoryDouble = mock(UserStory.class);
        UserStoryRepository usRepositoryDouble = new UserStoryRepository();
        UserStoryRepository emptyUsRepositoryDouble = new UserStoryRepository();
        usRepositoryDouble.save(userStoryDouble);

        // Act
        when(userStoryDouble.getUsId()).thenReturn("usIdDouble");
        when(usIdDouble.getUserStoryId()).thenReturn("usIdDouble");
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
    void ensureUserStoryIsNotDeletedUserStoryDoesNotExist() throws Exception {
        // Arrange
        UsId usIdDouble = mock(UsId.class);
        UserStory userStoryDouble = mock(UserStory.class);
        UserStoryRepository usRepositoryDouble = new UserStoryRepository();
        usRepositoryDouble.save(userStoryDouble);


        when(userStoryDouble.getUsId()).thenReturn("ID");

        // Act
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usRepositoryDouble.delete(usIdDouble);
        });

        // Assert
        assertEquals("The User Story is already in the Product Backlog", exception.getMessage());
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
    void ensureListOfUsIsRetrieved() throws Exception {
        UsId usIdDouble = mock(UsId.class);
        UserStory userStoryDouble = mock(UserStory.class);
        UserStoryRepository usRepositoryDouble = new UserStoryRepository();
        usRepositoryDouble.save(userStoryDouble);

        List<UsId> userStoryIds = new ArrayList<>();
        userStoryIds.add(usIdDouble);

        List<UserStory> userStories = new ArrayList<>();
        userStories.add(0, userStoryDouble);

        when(userStoryDouble.getUsId()).thenReturn("usIdDouble");
        when(usIdDouble.getUserStoryId()).thenReturn("usIdDouble");
        boolean result = usRepositoryDouble.getListOfUsWithMatchingIds(userStoryIds)
                .equals(userStories);

        assertTrue(result);
    }

    /**
     * Scenario 02: verify if a list of empty userStories is retrieved.
     * <p>
     * Expected result: list of empty userStories is retrieved.
     */

    @Test
    void ensureListOfEmptyUsIsRetrieved() {
        UsId usIdDouble = mock(UsId.class);
        UserStory userStoryDouble = mock(UserStory.class);
        UserStoryRepository usRepositoryDouble = new UserStoryRepository();

        List<UsId> userStoryIds = new ArrayList<>();
        userStoryIds.add(usIdDouble);

        List<UserStory> userStories = new ArrayList<>();

        when(userStoryDouble.getUsId()).thenReturn("usIdDouble");

        boolean result = usRepositoryDouble.getListOfUsWithMatchingIds(userStoryIds)
                .equals(userStories);

        assertTrue(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal UsRepository objects are the same.
     */

    @Test
    public void ensureTwoUsRepositoryInstancesHashcodeAreTheSame() {
        // Arrange
        UserStoryRepository usRepositoryOne = new UserStoryRepository();
        UserStoryRepository usRepositoryTwo = new UserStoryRepository();

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
    public void ensureTwoUsRepositoryInstancesHashcodeAreNotTheSame() throws Exception {
        // Arrange
        UserStoryRepository usRepositoryOne = new UserStoryRepository();
        UserStoryRepository usRepositoryThree = new UserStoryRepository();
        UserStory userStoryDouble = mock(UserStory.class);
        usRepositoryThree.save(userStoryDouble);

        // Act
        int repositoryOneHashCode = usRepositoryOne.hashCode();
        int repositoryThreeHashCode = usRepositoryThree.hashCode();

        // Assert
        assertNotEquals(repositoryOneHashCode, repositoryThreeHashCode);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Two UsRepository objects are the equal.
     */

    @Test
    public void ensureTwoUsRepositoryInstancesAreEqual() {
        // Arrange
        UserStoryRepository usRepositoryOne = new UserStoryRepository();

        // Assert
        assertEquals(usRepositoryOne, usRepositoryOne);
    }

    /**
     * Scenario 2: Two UsRepository objects are not equal.
     */

    @Test
    public void ensureTwoUsRepositoryInstancesAreNotEqual() {
        // Arrange
        UserStoryRepository usRepositoryOne = new UserStoryRepository();
        Object object = new Object();

        // Assert
        assertNotEquals(usRepositoryOne, object);
    }

    /**
     * Scenario 3: Two UsRepository objects are not equal because one of them is null.
     */
    @Test
    public void ensureTwoUsRepositoryInstancesAreNotEqualBecauseOneItIsNull() throws Exception {
        // Arrange
        UserStoryRepository usRepository = new UserStoryRepository();
        UserStoryRepository other = null;

        // Act
        boolean result = usRepository.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Method: existsByUsId(usId).
     * Verifies if a user story with a given ID exists in the repository of user stories.
     * <br>
     * Scenario 01: verify that a userStory with a given usId exists in the list of userStories.
     * Expected result: true.
     */
    @Test
    void ensureItReturnsTrueIfUserStoryWithGiveUsIdExistsInTheRepository() {
        //ARRANGE
        UserStoryRepository repository = new UserStoryRepository();
        UsId usId = new UsId("P01", "1");

        UserStory userStory = mock(UserStory.class);
        repository.save(userStory);
        when(userStory.getUsId()).thenReturn("p01_1");


        //ACT
        boolean result = repository.existsByUsId(usId);
        //ASSERT
        assertTrue(result);
    }

    /**
     * Method: existsByUsId(usId).
     * Verifies if a user story with a given ID exists in the repository of user stories.
     * <br>
     * Scenario 02: verify that a userStory with a given usId does not exist in the list of userStories.
     * Expected result: false.
     */
    @Test
    void ensureItReturnsFalseIfUserStoryWithGiveUsIdDoesNotExistInTheRepository() {
        //ARRANGE
        UserStoryRepository repository = new UserStoryRepository();
        UsId usId = new UsId("P01", "2");

        UserStory userStory = mock(UserStory.class);
        repository.save(userStory);
        when(userStory.getUsId()).thenReturn("p01_1");


        //ACT
        boolean result = repository.existsByUsId(usId);
        //ASSERT
        assertFalse(result);
    }

    /**
     * Method: findById(usId)
     * Returns an Optional with the desired User Story based on its UsId.
     *
     * Scenario 1: Returns an Optional with the User Story.
     */
    @Test
    void ensureThatAUserStoryOptionalIsReturnedBasedOnTheGivenId() {
        //ARRANGE
        UserStoryRepository repository = new UserStoryRepository();
        UsId usId = new UsId("P01", "2");

        UserStory userStory = mock(UserStory.class);
        repository.save(userStory);
        when(userStory.hasUsId(usId)).thenReturn(true);
        Optional<UserStory> expected = Optional.of(userStory);

        //ACT
        Optional<UserStory> result = repository.findByUsId(usId);

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: returns an Optional with null object because the User Story list is
     * empty
     */
    @Test
    void ensureThatEmptyOptionalIsReturnedBecauseTheUserStoryListIsEmpty() {
        //ARRANGE
        UserStoryRepository repository = new UserStoryRepository();
        UsId usId = new UsId("P01", "2");

        Optional<UserStory> expected = Optional.ofNullable(null);

        //ACT
        Optional<UserStory> result = repository.findByUsId(usId);

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: returns an Optional with null object because the user story was not
     * found.
     */
    @Test
    void ensureThatAnEmptyOptionalIsReturnedBecauseTheUserStoryDoesNotExist() {
        //ARRANGE
        UserStoryRepository repository = new UserStoryRepository();
        UsId usId = new UsId("P01", "2");

        UserStory userStory = mock(UserStory.class);
        repository.save(userStory);
        when(userStory.hasUsId(usId)).thenReturn(false);
        Optional<UserStory> expected = Optional.ofNullable(null);

        //ACT
        Optional<UserStory> result = repository.findByUsId(usId);

        //ASSERT
        assertEquals(expected, result);
    }
}