package org.switch2022.project.controller;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.*;
import org.switch2022.project.dto.ProjectCreationDto;
import org.switch2022.project.dto.ProjectDto;
import org.switch2022.project.dto.SprintCreationDto;
import org.switch2022.project.factories.*;
import org.switch2022.project.model.BusinessSector;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateSprintControllerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running
     * the tests below.
     */

    BusinessSector businessSector;
    ProjectCreationDto projectOneDTO, projectTwoDTO;
    ProjectDto projectDto;
    SprintCreationDto sprintCreationDtoOne;
    BusinessSectorContainer businessSectorContainer;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    ProjectTypologyContainer projectTypologyContainer;
    ProjectContainer projectContainer;
    CustomerContainer customerContainer;
    Company company;
    CreateSprintController createSprintController;
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
        factorySprint = new FactorySprint();
        factorySprintBacklog = new FactorySprintBacklog();
        factoryPeriod = new FactoryPeriod();

        //Dto
        LocalDate startDate = LocalDate.now();
        sprintCreationDtoOne = new SprintCreationDto(startDate, 3, 1);

        projectDto = new ProjectDto("AA001", "software development " +
                "management", "Peter", "228674498",
                "Fixed cost", "Fishing");

        //Containers
        projectTypologyContainer = new ProjectTypologyContainer();
        customerContainer = new CustomerContainer();
        businessSector = new BusinessSector("fishing");
        businessSectorContainer = new BusinessSectorContainer();

        projectOneDTO = new ProjectCreationDto("AA001", "software development " +
                "management", "Peter", "228674498",
                "Fixed cost", "Fishing");
        projectTwoDTO = new ProjectCreationDto("AA004", "software development " +
                "management", "Mary",
                "228674498", "Fixed cost", "Sports");

        projectContainer = new ProjectContainer();
        projectContainer.registerProject(projectOneDTO, projectTypologyContainer, customerContainer,
                businessSectorContainer, factoryProductBacklog, factoryUserStory, factoryProject, factoryPeriod, factorySprintBacklog, factorySprint);

        company = new Company(accountContainer, profileContainer, businessSectorContainer,
                projectContainer, projectTypologyContainer, null, customerContainer);

        createSprintController = new CreateSprintController(company);
    }

    @AfterEach
    void tearDown() {
        accountContainer = null;
        profileContainer = null;
        businessSectorContainer = null;
        projectTypologyContainer = null;
        projectContainer = null;
        projectOneDTO = null;
        projectTwoDTO = null;
        customerContainer = null;
        company = null;
    }


    /**
     * METHOD createSprint with isolation
     * Scenario 1: it should return true
     */
    @Test
    void ensureSprintIsCreatedSuccessfully_WithIsolation() {
        //Assert
        ProjectDto projectDtoDouble = mock(ProjectDto.class);
        SprintCreationDto sprintCreationDtoDouble = mock(SprintCreationDto.class);
        Company companyDouble = mock(Company.class);
        CreateSprintController createSprintControllerDouble = new CreateSprintController(companyDouble);
        when(companyDouble.createSprint(projectDtoDouble, sprintCreationDtoDouble)).thenReturn(true);
        //Act
        boolean result = createSprintControllerDouble.createSprint(projectDtoDouble, sprintCreationDtoDouble);

        //Arrange
        assertTrue(result);
    }

    /**
     * METHOD createSprint with isolation
     * Scenario 2: it should return false
     */
    @Test
    void ensureSprintIsNotCreatedSuccessfully_WithIsolation() {
        //Assert
        ProjectDto projectDtoDouble = mock(ProjectDto.class);
        SprintCreationDto sprintCreationDtoDouble = mock(SprintCreationDto.class);
        Company companyDouble = mock(Company.class);
        CreateSprintController createSprintControllerDouble = new CreateSprintController(companyDouble);
        when(companyDouble.createSprint(projectDtoDouble, sprintCreationDtoDouble)).thenReturn(false);
        //Act
        boolean result = createSprintControllerDouble.createSprint(projectDtoDouble, sprintCreationDtoDouble);

        //Arrange
        assertFalse(result);
    }

    /**
     * METHOD createSprint
     * Scenario 1: it should return false when a Sprint is created because the project doesn't exist.
     */

    @Test
    void ensureSprintIsCreatedUnsuccessfully_noProject() {
        //Arrange
        ProjectDto projectDtoTwo = new ProjectDto("A004", "software development " +
                "management", "Mary",
                "228674498", "Fixed cost", "Sports");

        //Act
        boolean result = createSprintController.createSprint(projectDtoTwo, sprintCreationDtoOne);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 2: it should return false when a Sprint is created.
     * because the projectDto is null.
     */
    @Test
    void ensureSprintIsCreatedUnsuccessfully_ProjectDtoNull() {
        //Arrange
        ProjectDto projectDtoTwo = null;
        //Act
        boolean result = createSprintController.createSprint(projectDtoTwo, sprintCreationDtoOne);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: it should return false when the sprintCreationDto is null.
     */

    @Test
    void ensureSprintIsCreatedUnsuccessfully_SprintCreationDtoNull() {
        //Arrange
        SprintCreationDto sprintCreationDtoNull = null;
        //Act
        boolean result = createSprintController.createSprint(projectDto, sprintCreationDtoNull);
        //Assert
        assertFalse(result);
    }


    /**
     * Scenario 4: it should return false when the projectDto and sprintCreationDto are null.
     */

    @Test
    void ensureSprintIsCreatedUnsuccessfully_NullInput() {
        //Arrange
        ProjectDto projectDtoNull = null;
        SprintCreationDto sprintCreationDtoNull = null;
        //Act
        boolean result = createSprintController.createSprint(projectDtoNull, sprintCreationDtoNull);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: it should return false when the projectDto and sprintCreationDto have empty or null fields.
     */

    @Test
    void ensureSprintIsCreatedUnsuccessfully_EmptyFields() {
        //Arrange
        ProjectDto projectDtoEmpty = new ProjectDto("", "", "", "", "",
                "");
        SprintCreationDto sprintCreationDtoEmpty = new SprintCreationDto(null, 0, 0);
        //Act
        boolean result = createSprintController.createSprint(projectDtoEmpty, sprintCreationDtoEmpty);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 6: it should return true when a Sprint is created.
     */

    @Test
    void ensureSprintIsCreatedSuccessfully() {
        //Arrange
        LocalDate startDate = LocalDate.of(2024, 3, 19);
        SprintCreationDto sprintCreationDto = new SprintCreationDto(startDate, 3 ,2);
        company.createSprint(projectDto, sprintCreationDto);
        //Act
        boolean result = createSprintController.createSprint(projectDto, sprintCreationDtoOne);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 7: fail to create a Sprint if that Sprint already exists.
     * should return false.
     */

    @Test
    void ensureSprintIsNotCreatedIfAlreadyExists() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        SprintCreationDto sprintCreationDto = new SprintCreationDto(startDate, 3, 1);
        company.createSprint(projectDto, sprintCreationDto);
        //Act
        boolean result = createSprintController.createSprint(projectDto, sprintCreationDto);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 8: fail to create a Sprint because the Period is overlapping with a existing Sprint.
     * should return false.
     */
    @Test
    void ensureSprintIsNotCreatedBecausePeriodIsOverlapping() {
        //Arrange
        LocalDate startDate = LocalDate.of(2023,3,20);
        SprintCreationDto sprintCreationDto = new SprintCreationDto(startDate, 2, 2);
        company.createSprint(projectDto, sprintCreationDto);
        //Act
        boolean result = createSprintController.createSprint(projectDto, sprintCreationDto);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 9: fail to create a Sprint because the Period date is set before the Present date.
     * should return false.
     */
    @Test
    void ensureSprintIsNotCreatedBecausePeriodDateIsBeforePresentDate() {
        //Arrange
        LocalDate startDate = LocalDate.of(2022,3,20);
        SprintCreationDto sprintCreationDto = new SprintCreationDto(startDate, 2, 2);
        company.createSprint(projectDto, sprintCreationDto);
        //Act
        boolean result = createSprintController.createSprint(projectDto, sprintCreationDto);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 10: fail to create a Sprint because the Sprint Number already exists but the date is valid.
     * should return false.
     */
    @Test
    void ensureSprintIsNotCreatedBecauseSprintNumberAlreadyExistsButDateIsValid() {
        //Arrange
        LocalDate startDate = LocalDate.of(2024,3,20);
        SprintCreationDto sprintCreationDto = new SprintCreationDto(startDate, 2, 1);
        company.createSprint(projectDto, sprintCreationDto);
        //Act
        boolean result = createSprintController.createSprint(projectDto, sprintCreationDto);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 11: fail to create a Sprint because the start date is the same as the end date of an existing sprint.
     * should return false.
     */
    @Test
    void ensureSprintIsNotCreatedBecauseSprintDateIsTheSameAsEndDateOfAnExistingSprint() {
        //Arrange
        LocalDate startDate = LocalDate.of(2023,4,7);
        SprintCreationDto sprintCreationDto = new SprintCreationDto(startDate, 3, 1);
        company.createSprint(projectDto, sprintCreationDto);
        //Act
        boolean result = createSprintController.createSprint(projectDto, sprintCreationDto);
        //Assert
        assertFalse(result);
    }
}