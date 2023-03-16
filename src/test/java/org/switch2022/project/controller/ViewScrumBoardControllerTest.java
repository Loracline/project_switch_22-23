package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.*;
import org.switch2022.project.dto.*;

import org.switch2022.project.factories.*;
import org.switch2022.project.model.SprintBacklog;
import org.switch2022.project.model.UserStory;
import org.switch2022.project.utils.Status;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ViewScrumBoardControllerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running
     * the tests below.
     */

    ProjectCreationDto projectCreationDto;
    ProjectDto projectDto, wrongCodeProjectDto;
    UserStoryCreationDto userStoryCreationDtoOne, userStoryCreationDtoTwo;
    UserStoryDto userStoryDtoOne, userStoryDtoTwo;

    BusinessSectorContainer businessSectorContainer;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    ProjectTypologyContainer projectTypologyContainer;
    ProjectContainer projectContainer;
    CustomerContainer customerContainer;
    Company company;
    ViewScrumBoardController viewScrumBoardController;
    IFactoryProject factoryProject;
    IFactoryUserStory factoryUserStory;
    IFactoryProductBacklog factoryProductBacklog;

    IFactoryPeriod factoryPeriod;
    IFactorySprintBacklog factorySprintBacklog;
    IFactorySprint factorySprint;

    @BeforeEach
    void setUp() {
        //Interfaces implemented
        factoryProductBacklog = new FactoryProductBacklog();
        factoryProject = new FactoryProject();
        factoryUserStory = new FactoryUserStory();
        factoryPeriod = new FactoryPeriod();
        factorySprintBacklog = new FactorySprintBacklog();
        factorySprint = new FactorySprint();

        //Dtos
        userStoryCreationDtoOne = new UserStoryCreationDto("US001",
                "I want to create a project", "Manager", 0);
        userStoryCreationDtoTwo = new UserStoryCreationDto("US002",
                "I want to create a project", "Manager", 1);

        userStoryDtoOne = new UserStoryDto("US001",
                "I want to create a project", "Planned");
        userStoryDtoTwo = new UserStoryDto("US002",
                "I want to create a user story", "Planned");

        projectDto = new ProjectDto("AA001", "software development " +
                "management", "Peter", "228674498",
                "Fixed cost", "Fishing");

        wrongCodeProjectDto = new ProjectDto("AA020", "software development " +
                "management", "Peter", "228674498",
                "Fixed cost", "Fishing");

        projectCreationDto = new ProjectCreationDto("AA001", "software development " +
                "management", "Peter", "228674498",
                "Fixed cost", "Fishing");

        //Containers
        projectTypologyContainer = new ProjectTypologyContainer();
        customerContainer = new CustomerContainer();
        businessSectorContainer = new BusinessSectorContainer();
        projectContainer = new ProjectContainer();

        customerContainer.addCustomer(projectCreationDto.customerName,
                projectCreationDto.customerNif);
        projectTypologyContainer.createProjectTypology(projectCreationDto.projectTypology);
        businessSectorContainer.createBusinessSector(projectCreationDto.projectTypology);

        projectContainer.registerProject(projectCreationDto, projectTypologyContainer,
                customerContainer,
                businessSectorContainer, factoryProductBacklog, factoryUserStory, factoryProject,
                factoryPeriod, factorySprintBacklog, factorySprint);

        projectContainer.createUserStory(projectDto, userStoryCreationDtoOne);
        projectContainer.createUserStory(projectDto, userStoryCreationDtoTwo);

        company = new Company(accountContainer, profileContainer, businessSectorContainer,
                projectContainer, projectTypologyContainer, null, customerContainer);

        viewScrumBoardController = new ViewScrumBoardController(company);
    }

    @AfterEach
    void tearDown() {
        factoryProductBacklog = null;
        factoryProject = null;
        factoryUserStory = null;
        factoryPeriod = null;
        factorySprintBacklog = null;
        factorySprint = null;
        userStoryCreationDtoOne = null;
        projectDto = null;
        wrongCodeProjectDto = null;
        accountContainer = null;
        profileContainer = null;
        projectTypologyContainer = null;
        customerContainer = null;
        businessSectorContainer = null;
        projectContainer = null;
        projectCreationDto = null;
        company = null;
        viewScrumBoardController = null;
    }

    /**
     * Method getScrumBoard()- With Isolation
     * Scenario 1: Is returned the Scrum Board from the current sprint
     */
    @Test
    void ensureIsReturnedTheScrumBoardOfCurrentSprint_WithIsolation() {
        //Arrange
        Company company = mock(Company.class);
        ViewScrumBoardController controller = new ViewScrumBoardController(company);

        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        Optional<SprintBacklog> sprintBacklogOptional = Optional.ofNullable(sprintBacklog);
        when(company.getScrumBoard(any(), any(), any())).thenReturn(sprintBacklogOptional);

        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);
        when(userStoryOne.getUserStoryNumber()).thenReturn("US001");
        when(userStoryOne.getUserStoryText()).thenReturn("I want to create a project");
        when(userStoryOne.getStatus()).thenReturn(Status.PLANNED);

        when(userStoryTwo.getUserStoryNumber()).thenReturn("US002");
        when(userStoryTwo.getUserStoryText()).thenReturn("I want to create a user story");
        when(userStoryTwo.getStatus()).thenReturn(Status.PLANNED);

        List<UserStory> userStories = new ArrayList<>();
        userStories.add(userStoryOne);
        userStories.add(userStoryTwo);
        when(sprintBacklog.getUserStoriesCopy(any())).thenReturn(userStories);


        List<UserStoryDto> expected = new ArrayList<>();
        expected.add(userStoryDtoOne);
        expected.add(userStoryDtoTwo);

        //Act
        List<UserStoryDto> result = controller.getScrumBoard(projectDto, LocalDate.now(), null);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Is returned an empty list because there is no sprint in the given date - With Isolation
     */
    @Test
    void ensureIsReturnedAnEmptyListBecauseThereIsNoSprint_WithIsolation() {
        //Arrange
        Company company = mock(Company.class);
        ViewScrumBoardController controller = new ViewScrumBoardController(company);

        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        Optional<SprintBacklog> sprintBacklogOptional = Optional.ofNullable(null);
        when(company.getScrumBoard(any(), any(), any())).thenReturn(sprintBacklogOptional);

        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);
        when(userStoryOne.getUserStoryNumber()).thenReturn("US001");
        when(userStoryOne.getUserStoryText()).thenReturn("I want to create a project");
        when(userStoryOne.getStatus()).thenReturn(Status.PLANNED);

        when(userStoryTwo.getUserStoryNumber()).thenReturn("US002");
        when(userStoryTwo.getUserStoryText()).thenReturn("I want to create a user story");
        when(userStoryTwo.getStatus()).thenReturn(Status.PLANNED);

        List<UserStory> userStories = new ArrayList<>();
        userStories.add(userStoryOne);
        userStories.add(userStoryTwo);
        when(sprintBacklog.getUserStoriesCopy(any())).thenReturn(userStories);


        List<UserStoryDto> expected = new ArrayList<>();

        //Act
        List<UserStoryDto> result = controller.getScrumBoard(projectDto, null, null);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method getScrumBoard()
     * Scenario 1: Is returned the Scrum Board from the current sprint
     */
    @Test
    void ensureIsReturnedTheScrumBoardOfCurrentSprint() {
        //Arrange

        LocalDate startDate = LocalDate.of(2022, 1, 1);
        SprintCreationDto sprintCreationDto = new SprintCreationDto(startDate, 3, "SP001");
        projectContainer.createSprint(sprintCreationDto, projectDto);
        projectContainer.addUserStoryToSprintBacklog(projectDto.code, "US001", "SP001");
        LocalDate date = LocalDate.of(2022, 1, 2);
        List<UserStoryDto> expected = new ArrayList<>();
        expected.add(userStoryDtoOne);
        //Act
        List<UserStoryDto> result = viewScrumBoardController.getScrumBoard(projectDto, date, factoryUserStory);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Is returned an empty list because there is no sprint in the given date
     */
    @Test
    void ensureIsReturnedAnEmptyListBecauseThereIsNoSprint() {
        //Arrange

        LocalDate startDate = LocalDate.of(2022, 1, 1);
        SprintCreationDto sprintCreationDto = new SprintCreationDto(startDate, 3, "SP001");
        projectContainer.createSprint(sprintCreationDto, projectDto);
        projectContainer.addUserStoryToSprintBacklog(projectDto.code, "US001", "SP001");
        LocalDate date = LocalDate.of(2022, 3, 2);
        List<UserStoryDto> expected = new ArrayList<>();

        //Act
        List<UserStoryDto> result = viewScrumBoardController.getScrumBoard(projectDto, date, factoryUserStory);
        //Assert
        assertEquals(expected, result);
    }


}