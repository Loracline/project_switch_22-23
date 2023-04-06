package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.*;
import org.switch2022.project.dto.*;
import org.switch2022.project.factories.*;

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
    ProjectDto projectDtoOne, projectDtoTwo, projectDtoThree;
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
    LocalDate date;

    @BeforeEach
    void setUp() {
        //Date
        date = (LocalDate.now().plusDays(1));

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
        projectDtoThree = new ProjectDto("AA002", "software development" + "management",
                "Peter", "228674498",
                "Fixed cost", "Fishing");
        projectCreationDto = new ProjectCreationDto("AA001",
                "software development " +
                        "management", "Peter", "228674498",
                "Fixed cost", "Fishing");
        sprintCreationDto = new SprintCreationDto(date, 2,
                1);
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
        projectContainer.registerProject(projectCreationDto, projectTypologyContainer,
                customerContainer,
                businessSectorContainer, factoryProductBacklog, factoryUserStory,
                factoryProject,
                factoryPeriod,
                factorySprintBacklog, factorySprint);
        projectContainer.createSprint(sprintCreationDto, projectDtoOne);
        company = new Company(accountContainer, profileContainer, businessSectorContainer,
                projectContainer, projectTypologyContainer, null, customerContainer);
        estimateUserStoryEffortController =
                new EstimateUserStoryEffortController(company);
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
     * Method ensureEstimateEffortUserStory (userStoryDto, effort, projectDto)
     * Scenario 1: Verifies that the estimateEffortUserStory() method of the
     * EstimateUserStoryEffortController class can correctly estimate the effort
     * for a user story.
     * Expected result: true, indicating that the estimation was successful.
     */

    @Test
    void ensureUserStoryEffortIsEstimatedSuccessfully() {
        LocalDate todayDate = mock(LocalDate.class);
        when(todayDate.isAfter(date)).thenReturn(false);
        when(todayDate.isEqual(date)).thenReturn(false);
        projectContainer.createUserStory(projectDtoOne, userStoryCreationDto);
        projectContainer.addUserStoryToSprintBacklog(projectDtoOne.code,
                "US001", "s001");
        boolean result =
                estimateUserStoryEffortController.estimateEffortUserStory(userStoryDtoOne,
                2, projectDtoOne, todayDate);
        assertTrue(result);
    }

    /**
     * Scenario 2: Verifies that the estimateEffortUserStory(userStoryDto, effort,
     * projectDto)
     * method of the EstimateUserStoryEffortController class can't correctly estimate
     * the effort for
     * a user story, because userStoryNumber doesn't exist.
     * Expected result: false, indicating that the estimation was unsuccessful.
     */

    @Test
    void ensureEstimateEffortUserStoryUnsuccessfullyUserStoryNotFound() {
        LocalDate todayDate = mock(LocalDate.class);
        when(todayDate.isAfter(date)).thenReturn(false);
        when(todayDate.isEqual(date)).thenReturn(false);
        projectContainer.createUserStory(projectDtoOne, userStoryCreationDto);
        boolean result =
                estimateUserStoryEffortController.estimateEffortUserStory(userStoryDtoOne,
                2, projectDtoOne, todayDate);
        assertFalse(result);
    }

    /**
     * Scenario 3: Verifies that the estimateEffortUserStory(userStoryDto, effort,
     * projectDto)
     * method of the EstimateUserStoryEffortController class can't correctly estimate
     * the effort
     * for a user story, because project isn't registered in the system.
     * Expected result: false, indicating that the estimation was unsuccessful.
     */

    @Test
    void ensureEstimateEffortUserStoryUnsuccessfullyProjectNotFound() {
        LocalDate todayDate = mock(LocalDate.class);
        when(todayDate.isAfter(date)).thenReturn(false);
        when(todayDate.isEqual(date)).thenReturn(false);
        boolean result =
                estimateUserStoryEffortController.estimateEffortUserStory(userStoryDtoOne,
                2, projectDtoTwo, todayDate);
        assertFalse(result);
    }

    /**
     * Scenario 4: Verifies that the estimateEffortUserStory(userStoryDto, effort,
     * projectDto)
     * method of the EstimateUserStoryEffortController class can't correctly estimate
     * the effort for
     * a user story, because userStory is null.
     * Expected result: false, indicating that the estimation was unsuccessful.
     */

    @Test
    void ensureUserStoryEffortIsNotEstimatedUserStoryIsNull() {
        LocalDate todayDate = mock(LocalDate.class);
        when(todayDate.isAfter(date)).thenReturn(false);
        when(todayDate.isEqual(date)).thenReturn(false);
        boolean result =
                estimateUserStoryEffortController.estimateEffortUserStory(userStoryDtoTwo,
                2, projectDtoOne, todayDate);
        assertFalse(result);
    }

    /**
     * Scenario 5: Verifies that the estimateEffortUserStory(userStoryDto, effort,
     * projectDto)
     * method of the EstimateUserStoryEffortController class can't correctly estimate
     * the effort for
     * a user story, because project is null.
     * Expected result: false, indicating that the estimation was unsuccessful.
     */

    @Test
    void ensureUserStoryEffortIsNotEstimatedProjectIsNull() {
        LocalDate todayDate = mock(LocalDate.class);
        when(todayDate.isAfter(date)).thenReturn(false);
        when(todayDate.isEqual(date)).thenReturn(false);
        boolean result =
                estimateUserStoryEffortController.estimateEffortUserStory(userStoryDtoTwo,
                2, projectDtoThree, todayDate);
        assertFalse(result);
    }

    /**
     * Scenario 6: Verifies that the estimateEffortUserStory() method of the
     * EstimateUserStoryEffortController class can't correctly estimate the effort
     * for a user story because sprint is already finished
     * Expected result: false.
     */

    @Test
    void ensureUserStoryEffortIsEstimatedUnsuccessfully_SprintFinished() {
        LocalDate todayDate = mock(LocalDate.class);
        when(todayDate.isAfter(date)).thenReturn(true);
        when(todayDate.isEqual(date)).thenReturn(false);
        projectContainer.createUserStory(projectDtoOne, userStoryCreationDto);
        projectContainer.addUserStoryToSprintBacklog(projectDtoOne.code,
                "US001", "s001");
        boolean result =
                estimateUserStoryEffortController.estimateEffortUserStory(userStoryDtoOne,
                2, projectDtoOne, todayDate);
        assertFalse(result);
    }

    /**
     * Scenario 7: Verifies that the estimateEffortUserStory() method of the
     * EstimateUserStoryEffortController class can't correctly estimate the effort
     * for a user story because sprint startDate is the same as the date to change the
     * effort
     * Expected result: false.
     */

    @Test
    void ensureUserStoryEffortIsEstimatedUnsuccessfully_SprintStartDateIsToday() {
        LocalDate todayDate = mock(LocalDate.class);
        when(todayDate.isAfter(date)).thenReturn(false);
        when(todayDate.isEqual(date)).thenReturn(true);
        projectContainer.createUserStory(projectDtoOne, userStoryCreationDto);
        projectContainer.addUserStoryToSprintBacklog(projectDtoOne.code,
                "US001", "s001");
        boolean result =
                estimateUserStoryEffortController.estimateEffortUserStory(userStoryDtoOne,
                2, projectDtoOne, todayDate);
        assertFalse(result);
    }

    /**
     * Scenario 8: Verifies that the estimateEffortUserStory() method of the
     * EstimateUserStoryEffortController class can't correctly estimate the effort
     * for a user story because the effort given doesn't match the fibonacci sequence
     * Expected result: false.
     */

    @Test
    void ensureUserStoryEffortIsEstimatedUnsuccessfully_EffortUnacceptable() {
        LocalDate todayDate = mock(LocalDate.class);
        when(todayDate.isAfter(date)).thenReturn(false);
        when(todayDate.isEqual(date)).thenReturn(false);
        projectContainer.createUserStory(projectDtoOne, userStoryCreationDto);
        projectContainer.addUserStoryToSprintBacklog(projectDtoOne.code,
                "US001", "s001");
        boolean result =
                estimateUserStoryEffortController.estimateEffortUserStory(userStoryDtoOne, 4
                , projectDtoOne, todayDate);
        assertFalse(result);
    }

    /**
     * Scenario 9: Verifies that the estimateEffortUserStory(userStoryDto, effort,
     * projectDto)
     * method of the EstimateUserStoryEffortController class can correctly estimate the
     * effort
     * for a user story.
     * Expected result: true, indicating that the estimation was successful.
     */

    @Test
    void ensureUserStoryEffortIsEstimatedSuccessfullyWithIsolation() {
        //Assert
        LocalDate todayDate = mock(LocalDate.class);
        UserStoryDto userStoryDtoDouble = mock(UserStoryDto.class);
        ProjectDto projectDtoDouble = mock(ProjectDto.class);
        Company companyDouble = mock(Company.class);
        EstimateUserStoryEffortController estimateUserStoryEffortControllerDouble =
                new EstimateUserStoryEffortController(companyDouble);
        when(companyDouble.estimateEffortUserStory(userStoryDtoDouble, 2,
                projectDtoDouble, todayDate)).thenReturn(true);
        //Act
        boolean result =
                estimateUserStoryEffortControllerDouble.estimateEffortUserStory(userStoryDtoDouble,
                2, projectDtoDouble, todayDate);

        //Arrange
        assertTrue(result);
    }

    /**
     * Scenario 10: Verifies that the estimateEffortUserStory(userStoryDto, effort,
     * projectDto)
     * method of the EstimateUserStoryEffortController class can't correctly estimate
     * the effort
     * for a user story.
     * Expected result: false, indicating that the estimation was unsuccessful.
     */

    @Test
    void ensureUserStoryEffortIsEstimatedUnSuccessfullyWithIsolation() {
        //Assert
        LocalDate todayDate = mock(LocalDate.class);
        UserStoryDto userStoryDtoDouble = mock(UserStoryDto.class);
        ProjectDto projectDtoDouble = mock(ProjectDto.class);
        Company companyDouble = mock(Company.class);
        EstimateUserStoryEffortController estimateUserStoryEffortControllerDouble =
                new EstimateUserStoryEffortController(companyDouble);
        when(companyDouble.estimateEffortUserStory(userStoryDtoDouble, 2,
                projectDtoDouble, LocalDate.now())).thenReturn(false);
        //Act
        boolean result =
                estimateUserStoryEffortControllerDouble.estimateEffortUserStory(userStoryDtoDouble,
                2, projectDtoDouble, todayDate);

        //Arrange
        assertFalse(result);
    }
}
