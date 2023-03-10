package org.switch2022.project.model;

import org.junit.jupiter.api.Test;
import org.switch2022.project.factories.FactoryPeriod;
import org.switch2022.project.factories.FactorySprintBacklog;
import org.switch2022.project.factories.IFactorySprintBacklog;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SprintTest {

    /**
     * METHOD addUserStoryToSprintBacklog(userStory)
     * adds a User Story to the Product Backlog
     * <p>
     * Scenario 1: verify if a User Story is added to Product Backlog. Should return True.
     */

    @Test
    void ensureThatUserStoryIsAddedToSprintBacklog() {
        //Arrange
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        FactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);

        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        when(factorySprintBacklogDouble.createSprintBacklog()).thenReturn(sprintBacklog);

        Sprint sprint = Sprint.createSprint(LocalDate.of(2023, 3, 9),
                3, "S035", factoryPeriodDouble, factorySprintBacklogDouble);

        UserStory userStoryDouble = mock(UserStory.class);
        when(sprintBacklog.addUserStory(userStoryDouble)).thenReturn(true);

        //Act
        boolean result = sprint.addUserStoryToSprintBacklog(userStoryDouble);


        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: verify if a User Story is not added to Product Backlog. Should
     * return false.
     */

    @Test
    void ensureThatUserStoryIsNotAddedToSprintBacklog() {
        //Arrange
        UserStory userStoryDouble = mock(UserStory.class);
        FactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        IFactorySprintBacklog factorySprintBacklogDouble =
                mock(FactorySprintBacklog.class);
        when(factorySprintBacklogDouble.createSprintBacklog()).thenReturn(sprintBacklog);
        Sprint sprint = Sprint.createSprint(LocalDate.of(2023, 3, 9),
                3, "S035", factoryPeriodDouble, factorySprintBacklogDouble);
        sprint.addUserStoryToSprintBacklog(userStoryDouble);
        when(sprintBacklog.addUserStory(userStoryDouble)).thenReturn(false);

        //Act
        boolean result = sprint.addUserStoryToSprintBacklog(userStoryDouble);

        //Assert
        assertFalse(result);
    }

    /**
     * METHOD hasSprintNumber(sprintNumber)
     * Verifies if Sprint has a given Sprint Number.
     * <p>
     * Scenario 1: returns True
     */
    @Test
    void ensureThatReturnsTrueIfSprintHasSprintNumber() {
        //ARRANGE
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        FactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);

        Sprint sprint = Sprint.createSprint(LocalDate.of(2023, 3, 9),
                3, "S1", factoryPeriodDouble, factorySprintBacklogDouble);

        //ACT
        boolean result = sprint.hasSprintNumber("S1");

        //ASSERT
        assertTrue(result);
    }

    /**
     * Scenario 2: returns False
     */
    @Test
    void ensureThatReturnsFalseIfSprintDoesNotHaveSprintNumber() {
        //ARRANGE
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        FactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);

        Sprint sprint = Sprint.createSprint(LocalDate.of(2023, 3, 9),
                3, "S2", factoryPeriodDouble, factorySprintBacklogDouble);

        //ACT
        boolean result = sprint.hasSprintNumber("S1");

        //ASSERT
        assertFalse(result);
    }
}