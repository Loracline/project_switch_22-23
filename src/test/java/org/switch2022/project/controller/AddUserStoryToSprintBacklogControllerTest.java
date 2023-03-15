package org.switch2022.project.controller;

import org.junit.jupiter.api.Test;
import org.switch2022.project.container.Company;
import org.switch2022.project.dto.UserStoryDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddUserStoryToSprintBacklogControllerTest {

    //Unit tests
    /**
     * METHOD addUserStoryToSprintBacklog(projectCode, UserStoryNumber, SprintNumber)
     * adds a User Story to Sprint Backlog
     *
     * Scenario 1: verify that a User Story is added to Sprint Backlog. Should return
     * TRUE.
     */

    @Test
    void ensureThatUserStoryIsAddedToSprintBacklogSuccessfully() {
        //Arrange
        Company companyDouble = mock(Company.class);
        UserStoryDto userStoryDtoDouble = mock(UserStoryDto.class);
        AddUserStoryToSprintBacklogController addUserStoryToSprintBacklogController =
                new AddUserStoryToSprintBacklogController(companyDouble);
        when(companyDouble.addUserStoryToSprintBacklog(any(),any(),any())).thenReturn(true);

        //Act
        boolean result =
                addUserStoryToSprintBacklogController.addUserStoryToSprintBacklog("P3",
                        userStoryDtoDouble, "S3");

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: verify that a User Story is not added to the Sprint Backlog. Should
     * return FALSE.
     */

    @Test
    void ensureThatUserStoryIsNotAddedToSprintBacklog() {
        //Arrange
        Company companyDouble = mock(Company.class);
        UserStoryDto userStoryDtoDouble = mock(UserStoryDto.class);
        AddUserStoryToSprintBacklogController addUserStoryToSprintBacklogController =
                new AddUserStoryToSprintBacklogController(companyDouble);
        when(companyDouble.addUserStoryToSprintBacklog(any(),any(),any())).thenReturn(false);

        //Act
        boolean result =
                addUserStoryToSprintBacklogController.addUserStoryToSprintBacklog("P3",
                        userStoryDtoDouble, "S3");

        //Assert
        assertFalse(result);
    }
}