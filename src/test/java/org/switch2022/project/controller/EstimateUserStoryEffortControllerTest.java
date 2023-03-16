package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.*;
import org.switch2022.project.dto.*;
import org.switch2022.project.factories.*;
import org.switch2022.project.utils.Effort;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EstimateUserStoryEffortControllerTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running
     * the tests below.
     */

    UserStoryDto userStoryDtoOne, userStoryDtoTwo;
    ProjectDto projectDtoOne, projectDtoTwo;
    SprintCreationDto sprintCreationDto;
    ProjectCreationDto projectCreationDto;
    UserStoryCreationDto userStoryCreationDto;
    BusinessSectorContainer businessSectorContainer;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    ProjectTypologyContainer projectTypologyContainer;
    ProjectContainer projectContainer;
    CustomerContainer customerContainer;
    Company company;
    EstimateUserStoryEffortController estimateUserStoryEffortController;
    IFactoryProject factoryProject;
    IFactoryUserStory factoryUserStory;
    IFactoryProductBacklog factoryProductBacklog;
    IFactoryPeriod factoryPeriod;
    IFactorySprintBacklog factorySprintBacklog;
    IFactorySprint factorySprint;

    @BeforeEach
    void setUp() {
        //Dto
        userStoryDtoOne = new UserStoryDto("US001",
                "I want to create a profile", "planned");
        userStoryDtoTwo = new UserStoryDto("US001",
                "I want to create a profile", "planned");
        projectDtoOne = new ProjectDto("AA001", "software development" + "management",
                "Peter", "228674498",
                "Fixed cost", "Fishing");
        projectDtoTwo = new ProjectDto("AA002", "software development" + "management",
                "Peter", "228674498",
                "Fixed cost", "Fishing");
        projectCreationDto = new ProjectCreationDto("AA001",
                "software development " +
                        "management", "Peter", "228674498",
                "Fixed cost", "Fishing");
        sprintCreationDto = new SprintCreationDto(LocalDate.now(), 2,
                "S1");
        userStoryCreationDto = new UserStoryCreationDto("US001",
                "I want to create a " +
                        "profile", "planned", 0);

        //Interfaces implemented
        factoryProductBacklog = new FactoryProductBacklog();
        factoryProject = new FactoryProject();
        factoryUserStory = new FactoryUserStory();
        factoryPeriod = new FactoryPeriod();
        factorySprintBacklog = new FactorySprintBacklog();
        factorySprint = new FactorySprint();

        //Containers
        projectTypologyContainer = new ProjectTypologyContainer();
        customerContainer = new CustomerContainer();
        businessSectorContainer = new BusinessSectorContainer();
        projectContainer = new ProjectContainer();
        projectContainer.registerProject(projectCreationDto, projectTypologyContainer, customerContainer,
                businessSectorContainer, factoryProductBacklog, factoryUserStory, factoryProject,
                factoryPeriod,
                factorySprintBacklog, factorySprint);
        projectContainer.createSprint(sprintCreationDto, projectDtoOne);
        /*        projectContainer.createUserStory(projectDtoOne, userStoryCreationDto);*/
        company = new Company(accountContainer, profileContainer, businessSectorContainer,
                projectContainer, projectTypologyContainer, null, customerContainer);
        estimateUserStoryEffortController = new EstimateUserStoryEffortController(company);
    }

    @AfterEach
    void tearDown() {
        userStoryDtoOne = null;
        userStoryDtoTwo = null;
        projectDtoOne = null;
        projectDtoTwo = null;
        projectCreationDto = null;
        sprintCreationDto = null;
        businessSectorContainer = null;
        projectTypologyContainer = null;
        projectContainer = null;
        customerContainer = null;
        company = null;
        estimateUserStoryEffortController = null;
    }

    /**
     * Method ensureEstimateEffortUserStory (userStoryDto, effort, projectCode)
     * Scenario 1: Verifies that the estimateEffortUserStory() method of the
     * EstimateUserStoryEffortController class can correctly estimate the effort
     * for a user story.
     * Expected result: true, indicating that the estimation was successful.
     */

    @Test
    void ensureUserStoryEffortIsEstimatedSuccessfully() {
        projectContainer.createUserStory(projectDtoOne, userStoryCreationDto);
        projectContainer.addUserStoryToSprintBacklog(projectDtoOne.code,
                "US001", "S1");
        boolean result = estimateUserStoryEffortController.estimateEffortUserStory(userStoryDtoOne,
                Effort.TWO, projectDtoOne);
        assertTrue(result);
    }

    /**
     * Scenario 2: Verifies that the estimateEffortUserStory(userStoryDto, effort, projectCode)
     * method of the EstimateUserStoryEffortController class can't correctly estimate the effort for
     * a user story, because userStoryNumber doesn't exist.
     * Expected result: false, indicating that the estimation was unsuccessful.
     */

    @Test
    void ensureEstimateEffortUserStoryUnsuccessfullyUserStoryNotFound() {
        projectContainer.createUserStory(projectDtoOne, userStoryCreationDto);
        boolean result = estimateUserStoryEffortController.estimateEffortUserStory(userStoryDtoOne,
                Effort.TWO, projectDtoOne);
        assertFalse(result);
    }

    /**
     * Scenario 3: Verifies that the estimateEffortUserStory(userStoryDto, effort, projectCode)
     * method of the EstimateUserStoryEffortController class can't correctly estimate the effort
     * for a user story, because project isn't registered in the system.
     * Expected result: false, indicating that the estimation was unsuccessful.
     */

    @Test
    void ensureEstimateEffortUserStoryUnsuccessfullyProjectCodeNotFound() {
        boolean result = estimateUserStoryEffortController.estimateEffortUserStory(userStoryDtoOne,
                Effort.TWO, projectDtoTwo);
        assertFalse(result);
    }

    /**
     * Scenario 4: Verifies that the estimateEffortUserStory(userStoryDto, effort, projectCode)
     * method of the EstimateUserStoryEffortController class can't correctly estimate the effort for
     * a user story, because userStory is null.
     * Expected result: false, indicating that the estimation was unsuccessful.
     */

    @Test
    void ensureUserStoryEffortIsNotEstimatedUserStoryIsNull() {
        boolean result = estimateUserStoryEffortController.estimateEffortUserStory(userStoryDtoTwo,
                Effort.TWO, projectDtoTwo);
        assertFalse(result);
    }
}