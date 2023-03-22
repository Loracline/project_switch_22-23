package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.*;
import org.switch2022.project.dto.ProjectCreationDto;
import org.switch2022.project.dto.ProjectDto;
import org.switch2022.project.dto.SprintCreationDto;
import org.switch2022.project.dto.UserStoryCreationDto;
import org.switch2022.project.factories.*;
import org.switch2022.project.model.BusinessSector;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateUserStoryControllerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running
     * the tests below.
     */


    BusinessSector businessSector;
    ProjectCreationDto projectOneDTO, projectTwoDTO;
    ProjectDto projectDto;
    UserStoryCreationDto userStoryCreationDtoOne;
    BusinessSectorContainer businessSectorContainer;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    ProjectTypologyContainer projectTypologyContainer;
    ProjectContainer projectContainer;
    CustomerContainer customerContainer;
    Company company;
    CreateUserStoryController createUserStoryController;
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
        userStoryCreationDtoOne = new UserStoryCreationDto("US001",
                "I want to create a project", "Manager", 0);
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
                businessSectorContainer, factoryProductBacklog, factoryUserStory, factoryProject, factoryPeriod,
                factorySprintBacklog, factorySprint);

        company = new Company(accountContainer, profileContainer, businessSectorContainer,
                projectContainer, projectTypologyContainer, null, customerContainer);

        createUserStoryController = new CreateUserStoryController(company);
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
     * METHOD createUserStory with isolation
     * Scenario 1: it should return true
     */
    @Test
    void ensureUserStoryIsCreatedSuccessfully_WithIsolation() {
        //Assert
        ProjectDto projectDtoDouble = mock(ProjectDto.class);
        UserStoryCreationDto userStoryCreationDtoDouble = mock(UserStoryCreationDto.class);
        Company companyDouble = mock(Company.class);
        CreateUserStoryController createUserStoryControllerDouble = new CreateUserStoryController(companyDouble);
        when(companyDouble.createUserStory(projectDtoDouble, userStoryCreationDtoDouble)).thenReturn(true);
        //Act
        boolean result = createUserStoryControllerDouble.createUserStory(projectDtoDouble, userStoryCreationDtoDouble);

        //Arrange
        assertTrue(result);
    }

    /**
     * METHOD createUserStory with isolation
     * Scenario 2: it should return false
     */
    @Test
    void ensureUserStoryIsNotCreatedSuccessfully_WithIsolation() {
        //Assert
        ProjectDto projectDtoDouble = mock(ProjectDto.class);
        UserStoryCreationDto userStoryCreationDtoDouble = mock(UserStoryCreationDto.class);
        Company companyDouble = mock(Company.class);
        CreateUserStoryController createUserStoryControllerDouble = new CreateUserStoryController(companyDouble);
        when(companyDouble.createUserStory(projectDtoDouble, userStoryCreationDtoDouble)).thenReturn(false);
        //Act
        boolean result = createUserStoryControllerDouble.createUserStory(projectDtoDouble, userStoryCreationDtoDouble);

        //Arrange
        assertFalse(result);
    }

    /**
     * METHOD createUserStory without isolation
     * Scenario 1: it should return true when an userStory is created
     * empty ProductBacklog and empty SprintBacklog
     */

    @Test
    void ensureUserStoryIsCreatedSuccessfully_emptyProductBacklog() {
        //Act
        boolean result = createUserStoryController.createUserStory(projectDto, userStoryCreationDtoOne);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: it should return false when an userStory is created
     * because the project doesn't exist
     */

    @Test
    void ensureUserStoryIsCreatedUnsuccessfully_noProject() {
        //Arrange
        ProjectDto projectDtoTwo = new ProjectDto("A004", "software development " +
                "management", "Mary",
                "228674498", "Fixed cost", "Sports");

        //Act
        boolean result = createUserStoryController.createUserStory(projectDtoTwo, userStoryCreationDtoOne);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: it should return false when an userStory is created
     * because the projectDto is null
     */
    @Test
    void ensureUserStoryIsCreatedUnsuccessfully_ProjectDtoNull() {
        //Arrange
        ProjectDto projectDtoTwo = null;

        //Act
        boolean result = createUserStoryController.createUserStory(projectDtoTwo, userStoryCreationDtoOne);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: it should return false when an userStory is created
     * because the userStoryCreationDto is null
     */
    @Test
    void ensureUserStoryIsCreatedUnsuccessfully_UserStoryCreationDtoNull() {
        //Arrange
        UserStoryCreationDto userStoryCreationDto = null;

        //Act
        boolean result = createUserStoryController.createUserStory(projectDto, userStoryCreationDto);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: it should return false when an userStory is created
     * because the userStory is already in the productBacklog, insensitive to spaces and upperCase
     */

    @Test
    void ensureUserStoryIsCreatedUnsuccessfully_UserStoryRepeated() {
        //Arrange
        UserStoryCreationDto userStoryCreationDto = new UserStoryCreationDto(" us001 ",
                "I want to create a project", "Manager", 0);
        company.createUserStory(projectDto, userStoryCreationDto);

        //Act
        boolean result = createUserStoryController.createUserStory(projectDto, userStoryCreationDtoOne);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: it should return true when an userStory is created
     */

    @Test
    void ensureUserStoryIsCreatedSuccessfully() {
        //Arrange
        UserStoryCreationDto userStoryCreationDto = new UserStoryCreationDto("US002",
                "I want to create a project", "Manager", 0);
        company.createUserStory(projectDto, userStoryCreationDto);

        //Act
        boolean result = createUserStoryController.createUserStory(projectDto, userStoryCreationDtoOne);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 6: it should return false when an userStory is created
     * because the userStory priority is higher than the list size
     */

    @Test
    void ensureUserStoryIsCreatedUnsuccessfully_PriorityOutOfIndex() {
        //Arrange
        UserStoryCreationDto userStoryCreationDto = new UserStoryCreationDto(" us002",
                "I want to create a project", "Manager", 10);
        company.createUserStory(projectDto, userStoryCreationDtoOne);

        //Act
        boolean result = createUserStoryController.createUserStory(projectDto, userStoryCreationDto);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 7: it should return false when an userStory is created
     * because the userStory is already in a sprint
     */
    @Test
    void ensureUserStoryIsCreatedUnsuccessfully_PresentInTheSprint() {
        //Arrange
        company.createUserStory(projectDto, userStoryCreationDtoOne);
        LocalDate startDate = LocalDate.now();
        SprintCreationDto sprintCreationDto = new SprintCreationDto(startDate, 3, 1);
        projectContainer.createSprint(sprintCreationDto, projectDto);
        projectContainer.addUserStoryToSprintBacklog(projectDto.code, " us001", "s001");

        //Act
        boolean result = createUserStoryController.createUserStory(projectDto, userStoryCreationDtoOne);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 8: it should catch an exception when an userStory is created
     * because the userStoryNumber can't be a null
     */
    @Test
    void ensureUserStoryIsCreatedUnsuccessfully_UserStoryNumberNull() {
        Exception exception = assertThrows(Exception.class, () -> {
            UserStoryCreationDto userStoryCreationDtoNull =  new UserStoryCreationDto(null,
                    "I want to create a user Story", "Manager", 0);
            company.createUserStory(projectDto,userStoryCreationDtoNull);
        });

        String expectedMessage = "This value can't be null.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    /**
     * Scenario 9: it should catch an exception when an userStory is created
     * because the userStoryText can't be a null
     */
    @Test
    void ensureUserStoryIsCreatedUnsuccessfully_UserStoryTextNull() {
        Exception exception = assertThrows(Exception.class, () -> {
            UserStoryCreationDto userStoryCreationDtoNull =  new UserStoryCreationDto("US001",
                    null, "Manager", 0);
            company.createUserStory(projectDto,userStoryCreationDtoNull);
        });

        String expectedMessage = "This value can't be null.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    /**
     * Scenario 10: it should catch an exception when an userStory is created
     * because the userStoryActor can't be a null
     */
    @Test
    void ensureUserStoryIsCreatedUnsuccessfully_ActorNull()  {
        Exception exception = assertThrows(Exception.class, () -> {
            UserStoryCreationDto userStoryCreationDtoNull =  new UserStoryCreationDto("US001",
                    "I want to create a user Story", null, 0);
            company.createUserStory(projectDto,userStoryCreationDtoNull);
        });

        String expectedMessage = "This value can't be null.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    /**
     * Scenario 11: it should return true when an userStory is created
     * with a sprint with userStory
     */
    @Test
    void ensureUserStoryIsCreatedSuccessfully_WithSprint() {
        //Arrange
        UserStoryCreationDto userStoryCreationDto = new UserStoryCreationDto(" us002",
                "I want to create a project", "Manager", 0);
        LocalDate startDate = LocalDate.now();
        SprintCreationDto sprintCreationDto = new SprintCreationDto(startDate, 3, 1);
        projectContainer.createSprint(sprintCreationDto, projectDto);
        projectContainer.addUserStoryToSprintBacklog(projectDto.code, " us001", "sP001");

        //Act
        boolean result = createUserStoryController.createUserStory(projectDto, userStoryCreationDto);
        //Assert
        assertTrue(result);
    }
}