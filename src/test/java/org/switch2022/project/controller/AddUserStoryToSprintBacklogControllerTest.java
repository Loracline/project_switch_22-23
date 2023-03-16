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
    UserStoryDto userStoryDto;
    IFactoryPeriod iFactoryPeriod;
    IFactorySprintBacklog iFactorySprintBacklog;
    IFactoryProductBacklog iFactoryProductBacklog;
    IFactoryUserStory iFactoryUserStory;
    Sprint sprint;
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
        projectOne = new Project("1A", null, null, null, null, iFactoryProductBacklog, iFactoryUserStory, null, null,
                null);
        projectTwo = new Project("1B", null, null, null, null, iFactoryProductBacklog, iFactoryUserStory, null, null,
                null);
        projectThree = new Project("1C", null, null, null, null, iFactoryProductBacklog, iFactoryUserStory, null, null,
                null);

        //Sprint
        sprint = Sprint.createSprint(LocalDate.now(), 2, "S1", iFactoryPeriod, iFactorySprintBacklog);

        //UserStoryDto
        userStoryDto = new UserStoryDto("US001", "null", "null");

        // UserStoryCreationDto
        userStoryCreationDto = new UserStoryCreationDto("US001", "Text", "actor", 0);

        //Project Container
        projectContainer = new ProjectContainer();
        projectContainer.addProjectToProjectContainer(projectOne);
        projectContainer.addProjectToProjectContainer(projectTwo);
        projectContainer.addProjectToProjectContainer(projectThree);
        projectOne.addSprint(sprint);
        projectOne.createUserStory(userStoryCreationDto);

        //Company
        company = new Company(null, null, null,
                projectContainer, null, null, null);

        //Controller
        addUserStoryToSprintBacklogController = new AddUserStoryToSprintBacklogController(company);
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
        boolean result= addUserStoryToSprintBacklogController.addUserStoryToSprintBacklog(
                "1A",
                userStoryDto,
                "S1");

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
        boolean result=
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
        boolean result=
                addUserStoryToSprintBacklogController.addUserStoryToSprintBacklog("1A",
                userStoryDto,
                "S3");
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
    void ensureThatUserStoryIsAddedToSprintBacklogSuccessfully() {
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
    void ensureThatUserStoryIsNotAddedToSprintBacklog() {
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
}