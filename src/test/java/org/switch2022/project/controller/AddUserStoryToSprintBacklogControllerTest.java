package org.switch2022.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.*;
import org.switch2022.project.dto.UserStoryCreationDto;
import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.factories.*;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.Sprint;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddUserStoryToSprintBacklogControllerTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */
    Project projectOne, projectTwo, projectThree;
    ProjectContainer projectContainer;
    UserStoryDto userStoryDto, userStoryDtoTwo;
    IFactoryPeriod iFactoryPeriod;
    IFactorySprintBacklog iFactorySprintBacklog;
    IFactoryProductBacklog iFactoryProductBacklog;
    IFactoryUserStory iFactoryUserStory;
    Sprint sprint, sprintTwo;
    Company company;
    AddUserStoryToSprintBacklogController addUserStoryToSprintBacklogController;
    UserStoryCreationDto userStoryCreationDto;

    @BeforeEach
    void setUp() {

        //Factories
        iFactoryPeriod = new FactoryPeriod();
        iFactorySprintBacklog = new FactorySprintBacklog();
        iFactoryProductBacklog = new FactoryProductBacklog();
        iFactoryUserStory = new FactoryUserStory();

        //Project
        projectOne = new Project("1A", null, null, null, null, iFactoryProductBacklog,
                iFactoryUserStory, null, null,
                null);
        projectTwo = new Project("1B", null, null, null, null, iFactoryProductBacklog,
                iFactoryUserStory, null, null,
                null);
        projectThree = new Project("1C", null, null, null, null, iFactoryProductBacklog
                , iFactoryUserStory, null, null,
                null);

        //Sprint
        sprint = Sprint.createSprint((LocalDate.now().plusDays(1)), 2, 1, iFactoryPeriod,
                iFactorySprintBacklog);
        sprintTwo = Sprint.createSprint((LocalDate.now()), 2, 2,
                iFactoryPeriod,
                iFactorySprintBacklog);

        //UserStoryDto
        userStoryDto = new UserStoryDto("US001", "null", "PLANNED");
        userStoryDtoTwo = new UserStoryDto("US002", "null", "null");

        // UserStoryCreationDto
        userStoryCreationDto = new UserStoryCreationDto("US001", "Text", "actor", 0);

        //Project Container
        projectContainer = new ProjectContainer();
        projectContainer.addProjectToProjectContainer(projectOne);
        projectContainer.addProjectToProjectContainer(projectTwo);
        projectContainer.addProjectToProjectContainer(projectThree);
        projectOne.addSprint(sprint);
        projectOne.addSprint(sprintTwo);
        projectOne.createUserStory(userStoryCreationDto);

        //Company
        company = new Company(null, null, null,
                projectContainer, null, null, null);

        //Controller
        addUserStoryToSprintBacklogController =
                new AddUserStoryToSprintBacklogController(company);
    }

    //Integration tests

    /**
     * METHOD addUserStoryToSprintBacklog(projectCode, UserStoryNumber, SprintNumber)
     * adds a User Story to Sprint Backlog
     * <p>
     * Scenario 1: verify that a User Story is added to Sprint Backlog. Should return
     * TRUE.
     */
    @Test
    void ensureThatUserStoryIsAddedToSprintBacklogHappyPath() {
        //ACT
        boolean result =
                addUserStoryToSprintBacklogController.addUserStoryToSprintBacklog(
                "1A",
                userStoryDto,
                "s001");

        assertTrue(result);
    }

    /**
     * Scenario 2: verify that a User Story is not added to the Sprint Backlog because the
     * project does not exist.
     * Should return FALSE.
     */
    @Test
    void ensureThatUserStoryIsNotAddedToSprintBacklogBecauseProjectDoesNotExist() {
        //ACT
        boolean result =
                addUserStoryToSprintBacklogController.addUserStoryToSprintBacklog("1B",
                        userStoryDto,
                        "S1");
        assertFalse(result);
    }

    /**
     * Scenario 3: verify that a User Story is not added to the Sprint Backlog because the
     * sprint does not exist.
     * Should return FALSE.
     */
    @Test
    void ensureThatUserStoryIsNotAddedToSprintBacklogBecauseSprintDoesNotExist() {
        //ACT
        boolean result =
                addUserStoryToSprintBacklogController.addUserStoryToSprintBacklog("1A",
                        userStoryDto,
                        "S3");
        assertFalse(result);
    }

    /**
     * Scenario 4: verify that a User Story is not added to the Sprint Backlog because the
     * User Story does not exist.
     * Should return FALSE.
     */
    @Test
    void ensureThatUserStoryIsNotAddedToSprintBacklogBecauseUserStoryDoesNotExist() {
        //ACT
        boolean result =
                addUserStoryToSprintBacklogController.addUserStoryToSprintBacklog("1A",
                        userStoryDtoTwo,
                        "S1");
        assertFalse(result);
    }

    /**
     * Scenario 5: verify that a User Story is not added to the Sprint Backlog because the
     * Project parameter is null.
     * Should return FALSE.
     */
    @Test
    void ensureThatUserStoryIsNotAddedToSprintBacklogBecauseProjectCodeIsNull() {
        //ACT
        boolean result =
                addUserStoryToSprintBacklogController.addUserStoryToSprintBacklog(null,
                        userStoryDto,
                        "S3");
        assertFalse(result);
    }

    /**
     * Scenario 6: verify that a User Story is not added to the Sprint Backlog because the
     * UserStoryDto parameter is null.
     * Should return FALSE.
     */

    @Test
    void ensureThatUserStoryIsNotAddedToSprintBacklogBecauseUserStoryDtoIsNull() {
        //ACT
        boolean result =
                addUserStoryToSprintBacklogController.addUserStoryToSprintBacklog("1A",
                        null,
                        "S3");
        assertFalse(result);
    }

    /**
     * Scenario 7: verify that a User Story is not added to the Sprint Backlog because the
     * Sprint parameter is null.
     * Should return FALSE.
     */

    @Test
    void ensureThatUserStoryIsNotAddedToSprintBacklogBecauseSprintIsNull() {
        //ACT
        boolean result =
                addUserStoryToSprintBacklogController.addUserStoryToSprintBacklog("1A",
                        userStoryDto,
                        null);
        assertFalse(result);
    }

    /**
     * Scenario 8: verifies that User Story is not added to Sprint Backlog the Sprint
     * has already begun. Should return false.
     */
    @Test
    void ensureThatUserStoryIsNotAddedToSprintBacklogBecauseSprintHasAlreadyBegun() {
        //ACT
        boolean result =
                addUserStoryToSprintBacklogController.addUserStoryToSprintBacklog(
                "1A",
                userStoryDto,
                "S002");

        assertFalse(result);
    }

    /**
     * Scenario 9: verifies that User Story is not added to Sprint Backlog because the
     * User Story is already there. Should return false.
     */
    @Test
    void ensureThatUserStoryIsNotAddedToSprintBacklogBecauseUserStoryIsAlreadyThere() {
        //ACT
        addUserStoryToSprintBacklogController.addUserStoryToSprintBacklog("1A",
                userStoryDto, "s001");
        boolean result =
                addUserStoryToSprintBacklogController.addUserStoryToSprintBacklog(
                "1A", userStoryDto, "s001");

        assertFalse(result);
    }

    /**
     * Scenario 10: verifies that User Story is not added to Sprint Backlog because the
     * User Story doesn't have the required status. Should return false.
     */
    @Test
    void ensureThatUserStoryIsNotAddedToSprintBacklogBecauseUserStoryHasNotThePlannedStatus() {
        //ACT
        boolean result =
                addUserStoryToSprintBacklogController.addUserStoryToSprintBacklog(
                "1A", userStoryDtoTwo, "s001");

        assertFalse(result);
    }

    //Unit tests

    /**
     * METHOD addUserStoryToSprintBacklog(projectCode, UserStoryNumber, SprintNumber)
     * adds a User Story to Sprint Backlog
     * <p>
     * Scenario 1: verify that a User Story is added to Sprint Backlog. Should return
     * TRUE.
     */
    @Test
    void ensureThatUserStoryIsAddedToSprintBacklogSuccessfully_unitTest() {
        //Arrange
        Company companyDouble = mock(Company.class);
        UserStoryDto userStoryDtoDouble = mock(UserStoryDto.class);
        AddUserStoryToSprintBacklogController addUserStoryToSprintBacklogController =
                new AddUserStoryToSprintBacklogController(companyDouble);
        when(companyDouble.addUserStoryToSprintBacklog(any(), any(), any())).thenReturn(true);

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
    void ensureThatUserStoryIsNotAddedToSprintBacklog_uniteTest() {
        //Arrange
        Company companyDouble = mock(Company.class);
        UserStoryDto userStoryDtoDouble = mock(UserStoryDto.class);
        AddUserStoryToSprintBacklogController addUserStoryToSprintBacklogController =
                new AddUserStoryToSprintBacklogController(companyDouble);
        when(companyDouble.addUserStoryToSprintBacklog(any(), any(), any())).thenReturn(false);

        //Act
        boolean result =
                addUserStoryToSprintBacklogController.addUserStoryToSprintBacklog("P3",
                        userStoryDtoDouble, "S3");

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: verify that a User Story is not added to the Sprint Backlog because the
     * Project parameter is null.
     * Should return FALSE.
     */

    @Test
    void ensureThatUserStoryIsNotAddedToSprintBecauseProjectIsNull_uniteTest() {
        //Arrange
        Company companyDouble = mock(Company.class);
        AddUserStoryToSprintBacklogController addUserStoryToSprintBacklogController =
                new AddUserStoryToSprintBacklogController(companyDouble);
        UserStoryDto userStoryDtoDouble = mock(UserStoryDto.class);

        //Act
        boolean result =
                addUserStoryToSprintBacklogController.addUserStoryToSprintBacklog(null,
                        userStoryDtoDouble, "S3");

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: verify that a User Story is not added to the Sprint Backlog because the
     * UserStoryDto parameter is null.
     * Should return FALSE.
     */
    @Test
    void ensureThatUserStoryIsNotAddedToSprintBacklogBecauseUserStoryDtoIsNull_UnitTest() {
        //Arrange
        Company companyDouble = mock(Company.class);
        AddUserStoryToSprintBacklogController addUserStoryToSprintBacklogController =
                new AddUserStoryToSprintBacklogController(companyDouble);

        //Act
        boolean result =
                addUserStoryToSprintBacklogController.addUserStoryToSprintBacklog("P3",
                        null, "S3");

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: verify that a User Story is not added to the Sprint Backlog because the
     * Sprint parameter is null.
     * Should return FALSE.
     */

    @Test
    void ensureThatUserStoryIsNotAddedToSprintBacklogBecauseSprintIsNull_UnitTest() {
        //Arrange
        Company companyDouble = mock(Company.class);
        UserStoryDto userStoryDtoDouble = mock(UserStoryDto.class);
        AddUserStoryToSprintBacklogController addUserStoryToSprintBacklogController =
                new AddUserStoryToSprintBacklogController(companyDouble);

        //Act
        boolean result =
                addUserStoryToSprintBacklogController.addUserStoryToSprintBacklog("P3",
                        userStoryDtoDouble, null);

        //Assert
        assertFalse(result);
    }
}