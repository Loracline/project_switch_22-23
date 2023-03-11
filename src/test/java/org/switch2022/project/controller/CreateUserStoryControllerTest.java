package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.*;
import org.switch2022.project.dto.ProjectCreationDto;
import org.switch2022.project.dto.ProjectDto;
import org.switch2022.project.dto.UserStoryCreationDto;
import org.switch2022.project.factories.*;
import org.switch2022.project.model.BusinessSector;

import static org.junit.jupiter.api.Assertions.*;

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

    @BeforeEach
    void setUp() {
        //Interfaces implemented
        factoryProductBacklog = new FactoryProductBacklog();
        factoryProject = new FactoryProject();
        factoryUserStory = new FactoryUserStory();

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
                businessSectorContainer, factoryProductBacklog, factoryUserStory, factoryProject);

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
     * METHOD createUserStory
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
}