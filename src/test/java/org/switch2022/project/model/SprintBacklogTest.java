package org.switch2022.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SprintBacklogTest {

    /** METHOD addUserStory(userstory)
     * adds a User Story is added to Sprint Backlog
     *
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

    /** METHOD hasUserStory(userStoryNumber)
     * verifies if any User Story in the Sprint Backlog has a giving user story number.
     *
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

    /** Scenario 2: checks that a User Story has not the giving User Story Number.
     *
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
}