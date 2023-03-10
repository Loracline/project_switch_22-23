package org.switch2022.project.model;

import org.junit.jupiter.api.Test;
import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.factories.FactoryPeriod;
import org.switch2022.project.factories.IFactoryPeriod;
import org.switch2022.project.utils.Effort;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.switch2022.project.model.UserStory.createUserStory;


class SprintTest {

    /**
     * METHOD estimateEffortUserStory(userStoryDto, effort)
     * <p>
     * Scenario 1: sets the effort of a UserStory.
     */

    @Test
    void ensureEffortIsIsSetForUserStory() {
        //Arrange
        LocalDate date = LocalDate.of(2021, 9, 13);
        FactoryPeriod factoryPeriod = new IFactoryPeriod();
        Sprint sprint = Sprint.createSprint(date, 2, "S55", factoryPeriod);

        UserStory userStory = createUserStory("US001", "Manager",
                "I want to create a profile");
        sprint.addUserStoryToSprintBacklog(userStory);

        UserStoryDto userStoryDto = new UserStoryDto("US001", "Manager",
                "I want to create a profile");
        Effort effort = Effort.THREE;

        //Act
        boolean result = sprint.estimateEffortUserStory(userStoryDto, effort);

        //Assert
        assertTrue(result);
    }

    /**
     +     * METHOD addUserStoryToSprintBacklog(userStory)
     +     * adds a User Story to the Product Backlog
     +     * <p>
     +     * Scenario 1: verify if a User Story is added to Product Backlog. Should return True.
     +     */
    @Test
    void ensureThatUserStoryIsAddedToSprintBacklog() {
        //Arrange
        UserStory userStoryDouble = mock(UserStory.class);
        FactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        Sprint sprint = Sprint.createSprint(LocalDate.of(2023, 3, 9),
                3, "S035", factoryPeriodDouble);

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
        SprintBacklog sprintBacklogDouble = mock(SprintBacklog.class);
        Sprint sprint = Sprint.createSprint(LocalDate.of(2023, 3, 9),
               3, "S035", factoryPeriodDouble);
        sprint.addUserStoryToSprintBacklog(userStoryDouble);
        when(sprintBacklogDouble.addUserStory(userStoryDouble)).thenReturn(false);

        //Act
        boolean result = sprint.addUserStoryToSprintBacklog(userStoryDouble);

        //Assert
        assertFalse(result);
        }
}