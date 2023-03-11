package org.switch2022.project.model;

import org.junit.jupiter.api.Test;
import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.utils.Effort;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SprintBacklogTest {

    /**
     * Method equals(Object o).
     * <p>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameSprintBacklogEqualsItself() {
        // Arrange
        SprintBacklog reference = new SprintBacklog();
        SprintBacklog other = reference;
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify if two objects of the same class are different from
     * each other.
     */
    @Test
    void ensureTwoSprintBacklogsAreNotTheSame() {
        // Arrange
        SprintBacklog reference = new SprintBacklog();
        UserStory userStoryDouble = mock(UserStory.class);
        reference.addUserStory(userStoryDouble);
        SprintBacklog other = new SprintBacklog();
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Verify if a UserStory and a different type of object are not
     * the same.
     */
    @Test
    void ensureSprintBacklogsAreNotEqualOtherTypeOfObject() {
        // Arrange
        SprintBacklog reference = new SprintBacklog();
        UserStory userStoryDouble = mock(UserStory.class);
        reference.addUserStory(userStoryDouble);
        Object other = new SprintBacklog();
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: Verify if a SprintBacklog and a null object are not the same.
     */
    @Test
    void ensureSprintBacklogDoesNotEqualNull() {
        // Arrange
        SprintBacklog reference = new SprintBacklog();
        SprintBacklog other = null;
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Method HashCode
     * <p>
     * Scenario 1: Two SprintBacklog objects are the same.
     */
    @Test
    public void ensureTwoSprintBacklogsHashcodeAreTheSame() {
        // Arrange
        SprintBacklog sprintBacklogOne = new SprintBacklog();
        SprintBacklog sprintBacklogTwo = new SprintBacklog();

        // Act
        int sprintBacklogOneHashCode = sprintBacklogOne.hashCode();
        int sprintBacklogTwoHashCode = sprintBacklogTwo.hashCode();

        // Assert
        assertEquals(sprintBacklogOneHashCode, sprintBacklogTwoHashCode);
    }

    /**
     * Scenario 2: Two SprintBacklog objects are not the same.
     */
    @Test
    public void ensureTwoSprintBacklogsHashcodeAreNotTheSame() {
        // Arrange
        SprintBacklog sprintBacklogOne = new SprintBacklog();
        SprintBacklog sprintBacklogTwo = new SprintBacklog();

        UserStory userStory = (new UserStory.UserStoryBuilder("US001").build());
        sprintBacklogTwo.addUserStory(userStory);
        // Act
        int sprintBacklogOneHashCode = sprintBacklogOne.hashCode();
        int sprintBacklogTwoHashCode = sprintBacklogTwo.hashCode();

        // Assert
        assertNotEquals(sprintBacklogOneHashCode, sprintBacklogTwoHashCode);
    }

    /**
     * METHOD addUserStory(userStory)
     * adds a User Story is added to Sprint Backlog
     * <p>
     * Scenario 1: verify if a User Story is added to Sprint Backlog if it is not
     * already there. Should return True.
     */
    @Test
    void ensureThatUserStoryIsSuccessfullyAddedToSprintBacklog() {
        //Arrange
        UserStory userStoryDouble = mock(UserStory.class);
        SprintBacklog sprintBacklog = new SprintBacklog();

        //Act
        boolean result = sprintBacklog.addUserStory(userStoryDouble);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: verify if a User Story is not added to Sprint Backlog if it is
     * already there. Should return false.
     */
    @Test
    void ensureThatUserStoryIsNotAddedToSprintBacklog() {
        //Arrange
        UserStory userStoryDouble = mock(UserStory.class);
        SprintBacklog sprintBacklog = new SprintBacklog();
        sprintBacklog.addUserStory(userStoryDouble);

        //Act
        boolean result = sprintBacklog.addUserStory(userStoryDouble);

        //Assert
        assertFalse(result);
    }

    /**
     * METHOD hasUserStory(userStoryNumber)
     * <p>
     * Scenario 1: checks that a User Story has the giving User Story Number.
     */
    @Test
    void ensureThatTheUserStoryHasTheGivingUserStoryNumber() {
        //Arrange
        UserStory userStoryDouble = mock(UserStory.class);
        SprintBacklog sprintBacklog = new SprintBacklog();
        sprintBacklog.addUserStory(userStoryDouble);
        when(userStoryDouble.hasUserStoryNumber("US001")).thenReturn(true);

        //Act
        boolean result = sprintBacklog.hasUserStory("US001");

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: checks that a User Story has not the giving User Story Number.
     */
    @Test
    void ensureThatTheUserStoryHasNotTheGivingUserStoryNumber() {
        //Arrange
        UserStory userStoryDouble = mock(UserStory.class);
        SprintBacklog sprintBacklog = new SprintBacklog();
        sprintBacklog.addUserStory(userStoryDouble);
        when(userStoryDouble.hasUserStoryNumber("US001")).thenReturn(false);

        //Act
        boolean result = sprintBacklog.hasUserStory("US001");

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: checks that there is no User Story with the giving User Story Number
     * because container is empty.
     */
    @Test
    void ensureThatThereIsNoUserStoryWithGivingNumberBecauseContainerIsEmpty() {
        //Arrange
        UserStory userStoryDouble = mock(UserStory.class);
        SprintBacklog sprintBacklog = new SprintBacklog();
        when(userStoryDouble.hasUserStoryNumber("US001")).thenReturn(false);

        //Act
        boolean result = sprintBacklog.hasUserStory("US001");

        //Assert
        assertFalse(result);
    }

    /**
     * METHOD estimateEffortUserStory(userStoryDto, effort)
     * <p>
     * Scenario 1: sets the effort of a UserStory.
     */
    @Test
    void ensureEffortIsSetForUserStoryTestOne() {
        //Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        sprintBacklog.addUserStory((new UserStory.UserStoryBuilder("US001").build()));
        sprintBacklog.addUserStory((new UserStory.UserStoryBuilder("US002").build()));
        UserStoryDto userStoryDto = new UserStoryDto("US002", "Manager",
                "I want to create a profile");
        Effort effort = Effort.THREE;

        //Act
        boolean result = sprintBacklog.estimateEffortUserStory(userStoryDto, effort);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: set the effort of a UserStory.
     */
    @Test
    void ensureEffortIsSetForUserStoryTestTwo() {
        //Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        sprintBacklog.addUserStory(new UserStory.UserStoryBuilder("US001").build());
        sprintBacklog.addUserStory(new UserStory.UserStoryBuilder("US002").build());
        sprintBacklog.addUserStory(new UserStory.UserStoryBuilder("US003").build());
        UserStoryDto userStoryDto = new UserStoryDto("US003", "Manager",
                "I want to create a profile");
        Effort effort = Effort.THREE;

        //Act
        boolean result = sprintBacklog.estimateEffortUserStory(userStoryDto, effort);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 3: does not set the effort of a UserStory.
     */
    @Test
    void ensureEffortIsNotSetForUserStory() {
        //Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        sprintBacklog.addUserStory(new UserStory.UserStoryBuilder("US001").build());
        sprintBacklog.addUserStory(new UserStory.UserStoryBuilder("US002").build());
        UserStoryDto userStoryDto = new UserStoryDto("US003", "Manager",
                "I want to create a profile");
        Effort effort = Effort.THREE;

        //Act
        boolean result = sprintBacklog.estimateEffortUserStory(userStoryDto, effort);

        //Assert
        assertFalse(result);
    }
}