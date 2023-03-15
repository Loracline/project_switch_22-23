package org.switch2022.project.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.dto.*;
import org.switch2022.project.dto.mapper.ProjectCreationMapper;
import org.switch2022.project.factories.*;
import org.switch2022.project.model.*;
import org.switch2022.project.utils.Effort;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProjectContainerTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */

    Project projectOne;
    ProjectCreationDto projectOneDTO, projectTwoDTO;
    ProjectTypology typology;
    Customer customer;
    BusinessSector businessSector;
    ProjectTypologyContainer projectTypologyContainer;
    BusinessSectorContainer businessSectorContainer;
    ProjectContainer projectContainer;
    CustomerContainer customerContainer;
    Company company;
    IFactoryProject factoryProject;
    IFactoryUserStory factoryUserStory;
    IFactoryProductBacklog factoryProductBacklog;
    IFactoryPeriod factoryPeriod;
    IFactorySprintBacklog factorySprintBacklog;
    IFactorySprint factorySprint;


    @BeforeEach
    void setUp() {
        projectOne = new Project("AA001", "Aptoide",
                new Customer("ISEP", "228674498"),
                new ProjectTypology("Fixed cost"),
                new BusinessSector("fishing"));

        projectTypologyContainer = new ProjectTypologyContainer();
        customerContainer = new CustomerContainer();
        businessSectorContainer = new BusinessSectorContainer();

        projectOneDTO = new ProjectCreationDto("AA001", "Aptoide",
                "ISEP", "228674498", "Fixed cost",
                "fishing");
        projectTwoDTO = new ProjectCreationDto("AA002", "Aptoide",
                "ISEP", "228674498", "Fixed cost",
                "fishing");
        factoryProductBacklog = new FactoryProductBacklog();
        factoryProject = new FactoryProject();
        factoryUserStory = new FactoryUserStory();
        factoryPeriod = new FactoryPeriod();
        factorySprintBacklog = new FactorySprintBacklog();
        factorySprint = new FactorySprint();
        projectContainer = new ProjectContainer();
        projectContainer.registerProject(projectOneDTO, projectTypologyContainer,
                customerContainer,
                businessSectorContainer, factoryProductBacklog, factoryUserStory,
                factoryProject, factoryPeriod, factorySprintBacklog, factorySprint);


    }

    @AfterEach
    void tearDown() {
        projectOne = null;
        businessSectorContainer = null;
        businessSector = null;
        projectTypologyContainer = null;
        typology = null;
        projectContainer = null;
        projectOneDTO = null;
        customerContainer = null;
        customer = null;
        company = null;
    }

    /**
     * This test verifies that a new project is registered and added to the
     * container successfully.
     */
    @Test
    void ensureProjectIsRegisteredSuccessfully() {
        // Arrange
        boolean expected = true;

        // Act
        boolean result = projectContainer.registerProject(projectTwoDTO,
                projectTypologyContainer, customerContainer,
                businessSectorContainer, factoryProductBacklog, factoryUserStory,
                factoryProject, factoryPeriod, factorySprintBacklog, factorySprint);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * This test verifies that a project is not registered because it already
     * exists in the container.
     */
    @Test
    void ensureProjectIsNotRegisteredBecauseItAlreadyExists() {
        // Arrange
        boolean expected = false;

        // Act
        boolean result = projectContainer.registerProject(projectOneDTO,
                projectTypologyContainer, customerContainer,
                businessSectorContainer, factoryProductBacklog, factoryUserStory,
                factoryProject, factoryPeriod, factorySprintBacklog, factorySprint);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD createUserStory
     * Scenario1: this test creates an userStory in a given project
     * and return true
     */
    @Test
    void ensureUserStoryIsCreatedInAProject() {
        //Arrange
        UserStoryCreationDto userStoryCreationDtoDouble = mock(
                UserStoryCreationDto.class);
        ProjectDto projectDtoDouble = mock(ProjectDto.class);
        when(projectDtoDouble.getProjectCode()).thenReturn("P001");
        ProjectContainer projectContainerTest = new ProjectContainer();
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        ProjectTypologyContainer projectTypologyContainerDouble = mock(
                ProjectTypologyContainer.class);
        CustomerContainer costumerContainerDouble = mock(CustomerContainer.class);
        BusinessSectorContainer businessSectorContainerDouble = mock(
                BusinessSectorContainer.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(
                FactoryProductBacklog.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklogDouble = mock(
                FactorySprintBacklog.class);
        IFactorySprint factorySprintDouble = mock(FactorySprint.class);
        Project projectDouble = mock(Project.class);
        IFactoryProject factoryProjectDouble = mock(FactoryProject.class);
        when(factoryProjectDouble.createProject(any(), any(), any(), any(), any(), any(),
                any(), any(), any())).
                thenReturn(projectDouble);
        when(projectDouble.createUserStory(userStoryCreationDtoDouble)).thenReturn(true);
        when(projectDouble.hasProjectCode("P001")).thenReturn(true);
        projectContainerTest.registerProject(projectCreationDtoDouble,
                projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble,
                factoryProductBacklogDouble,
                factoryUserStoryDouble, factoryProjectDouble, factoryPeriodDouble,
                factorySprintBacklogDouble, factorySprintDouble);
        //Act
        boolean result = projectContainerTest.createUserStory(projectDtoDouble,
                userStoryCreationDtoDouble);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario2: this test doesn't create an userStory in a given project
     * due to project not be in the container
     */
    @Test
    void ensureUserStoryIsNotCreatedInAProject() {
        //Arrange
        UserStoryCreationDto userStoryCreationDtoDouble = mock(
                UserStoryCreationDto.class);
        ProjectDto projectDtoDouble = mock(ProjectDto.class);
        when(projectDtoDouble.getProjectCode()).thenReturn("P001");
        ProjectContainer projectContainerTest = new ProjectContainer();
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        ProjectTypologyContainer projectTypologyContainerDouble = mock(
                ProjectTypologyContainer.class);
        CustomerContainer costumerContainerDouble = mock(CustomerContainer.class);
        BusinessSectorContainer businessSectorContainerDouble = mock(
                BusinessSectorContainer.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(
                FactoryProductBacklog.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklogDouble = mock(
                FactorySprintBacklog.class);
        IFactorySprint factorySprintDouble = mock(FactorySprint.class);
        Project projectDouble = mock(Project.class);
        IFactoryProject factoryProjectDouble = mock(FactoryProject.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        Customer costumerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        when(factoryProjectDouble.createProject(projectCreationDtoDouble, costumerDouble,
                projectTypologyDouble, businessSectorDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryPeriodDouble,
                factorySprintBacklogDouble, factorySprintDouble)).thenReturn(
                projectDouble);
        projectContainerTest.registerProject(projectCreationDtoDouble,
                projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryProjectDouble,
                factoryPeriodDouble, factorySprintBacklogDouble, factorySprintDouble);
        when(projectDouble.createUserStory(userStoryCreationDtoDouble)).thenReturn(true);
        //Act
        boolean result = projectContainerTest.createUserStory(projectDtoDouble,
                userStoryCreationDtoDouble);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario3: this test doesn't create an userStory in a given project
     * due to userStory be repeated
     */
    @Test
    void ensureUserStoryIsNotCreatedInAProject_RepeatedUserStory() {
        //Arrange
        UserStoryCreationDto userStoryCreationDtoDouble = mock(
                UserStoryCreationDto.class);
        ProjectDto projectDtoDouble = mock(ProjectDto.class);
        when(projectDtoDouble.getProjectCode()).thenReturn("P001");
        ProjectContainer projectContainerTest = new ProjectContainer();
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        ProjectTypologyContainer projectTypologyContainerDouble = mock(
                ProjectTypologyContainer.class);
        CustomerContainer costumerContainerDouble = mock(CustomerContainer.class);
        BusinessSectorContainer businessSectorContainerDouble = mock(
                BusinessSectorContainer.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(
                FactoryProductBacklog.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklogDouble = mock(
                FactorySprintBacklog.class);
        IFactorySprint factorySprintDouble = mock(FactorySprint.class);
        Project projectDouble = mock(Project.class);
        IFactoryProject factoryProjectDouble = mock(FactoryProject.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        Customer costumerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        when(factoryProjectDouble.createProject(projectCreationDtoDouble, costumerDouble,
                projectTypologyDouble, businessSectorDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryPeriodDouble,
                factorySprintBacklogDouble, factorySprintDouble)).thenReturn(
                projectDouble);
        projectContainerTest.registerProject(projectCreationDtoDouble,
                projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryProjectDouble,
                factoryPeriodDouble, factorySprintBacklogDouble, factorySprintDouble);
        when(projectDouble.createUserStory(userStoryCreationDtoDouble)).thenReturn(false);
        //Act
        boolean result = projectContainerTest.createUserStory(projectDtoDouble,
                userStoryCreationDtoDouble);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario4: this test doesn't create an userStory in a given project
     * due to project be a null
     */
    @Test
    void ensureUserStoryIsNotCreatedInAProject_ProjectNull() {
        //Arrange
        UserStoryCreationDto userStoryCreationDtoDouble = mock(
                UserStoryCreationDto.class);
        ProjectDto projectDtoDouble = mock(ProjectDto.class);
        when(projectDtoDouble.getProjectCode()).thenReturn("P001");
        ProjectContainer projectContainerTest = new ProjectContainer();
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        ProjectTypologyContainer projectTypologyContainerDouble = mock(
                ProjectTypologyContainer.class);
        CustomerContainer costumerContainerDouble = mock(CustomerContainer.class);
        BusinessSectorContainer businessSectorContainerDouble = mock(
                BusinessSectorContainer.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(
                FactoryProductBacklog.class);
        IFactoryProject factoryProjectDouble = mock(FactoryProject.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklogDouble = mock(
                FactorySprintBacklog.class);
        IFactorySprint factorySprintDouble = mock(FactorySprint.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        Customer costumerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        when(factoryProjectDouble.createProject(projectCreationDtoDouble, costumerDouble,
                projectTypologyDouble, businessSectorDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryPeriodDouble,
                factorySprintBacklogDouble, factorySprintDouble)).thenReturn(null);
        projectContainerTest.registerProject(projectCreationDtoDouble,
                projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryProjectDouble,
                factoryPeriodDouble, factorySprintBacklogDouble, factorySprintDouble);
        //Act
        boolean result = projectContainerTest.createUserStory(projectDtoDouble,
                userStoryCreationDtoDouble);
        //Assert
        assertFalse(result);
    }

    /**
     * Method ensureEstimateEffortUserStory (userStoryDto, effort, projectCode)
     * Scenario 1: Verifies that the estimateEffortUserStory() method of the Project
     * Container class
     * can correctly estimate the effort for a user story
     * Expected result: true, indicating that the estimation was successful.
     */

    @Test
    void ensureEstimateEffortUserStorySuccessfully() {
        // Arrange
        UserStoryDto userStoryDto = new UserStoryDto("US001",
                "I want to create a project", "ongoing");
        ProjectContainer projectContainerTest = new ProjectContainer();
        Project projectDouble = mock(Project.class);
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        ProjectTypologyContainer projectTypologyContainerDouble = mock(
                ProjectTypologyContainer.class);
        CustomerContainer costumerContainerDouble = mock(CustomerContainer.class);
        BusinessSectorContainer businessSectorContainerDouble = mock(
                BusinessSectorContainer.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(
                FactoryProductBacklog.class);
        IFactoryProject factoryProjectDouble = mock(FactoryProject.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklogDouble = mock(
                FactorySprintBacklog.class);
        IFactorySprint factorySprintDouble = mock(FactorySprint.class);
        when(factoryProjectDouble.createProject(any(), any(), any(), any(), any(), any(),
                any(), any(), any())).thenReturn(projectDouble);
        when(projectDouble.estimateEffortUserStory(any(), any(), any())).thenReturn(true);
        when(projectDouble.hasProjectCode(any())).thenReturn(true);
        projectContainerTest.registerProject(projectCreationDtoDouble,
                projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble,
                factoryProductBacklogDouble,
                factoryUserStoryDouble, factoryProjectDouble, factoryPeriodDouble,
                factorySprintBacklogDouble, factorySprintDouble);

        // Act
        boolean result = projectContainerTest.estimateEffortUserStory(userStoryDto,
                Effort.FIVE, "AA001", LocalDate.of(2023, 3, 8));

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verifies that the estimateEffortUserStory() method of the Project
     * Container class
     * can't correctly estimate the effort for a user story, because userStoryNumber
     * doesn't exist.
     * Expected result: false, indicating that the estimation was unsuccessful.
     */


    @Test
    void ensureEstimateEffortUserStoryUnsuccessfullyUserStoryNotFound() {
        // Arrange
        UserStoryDto userStoryDto = new UserStoryDto("US001",
                "I want to create a project", "ongoing");
        ProjectContainer projectContainerTest = new ProjectContainer();
        Project projectDouble = mock(Project.class);
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        ProjectTypologyContainer projectTypologyContainerDouble = mock(
                ProjectTypologyContainer.class);
        CustomerContainer costumerContainerDouble = mock(CustomerContainer.class);
        BusinessSectorContainer businessSectorContainerDouble = mock(
                BusinessSectorContainer.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(
                FactoryProductBacklog.class);
        IFactoryProject factoryProjectDouble = mock(FactoryProject.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklogDouble = mock(
                FactorySprintBacklog.class);
        IFactorySprint factorySprintDouble = mock(FactorySprint.class);
        when(factoryProjectDouble.createProject(any(), any(), any(), any(), any(), any(),
                any(), any(), any())).thenReturn(projectDouble);
        when(projectDouble.estimateEffortUserStory(any(), any(), any())).thenReturn(
                false);
        when(projectDouble.hasProjectCode(any())).thenReturn(true);
        projectContainerTest.registerProject(projectCreationDtoDouble,
                projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble,
                factoryProductBacklogDouble,
                factoryUserStoryDouble, factoryProjectDouble, factoryPeriodDouble,
                factorySprintBacklogDouble, factorySprintDouble);


        // Act
        boolean result = projectContainerTest.estimateEffortUserStory(userStoryDto,
                Effort.FIVE, "AA001", LocalDate.of(2023, 3, 8));

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: Verifies that the estimateEffortUserStory() method of the Project
     * Container class
     * can't correctly estimate the effort for a user story, because project isn't
     * registered in the system
     * Expected result: false, indicating that the estimation was unsuccessful.
     */

    @Test
    void ensureEstimateEffortUserStoryUnsuccessfullyProjectCodeAlreadyExists() {
        // Arrange
        UserStoryDto userStoryDto = new UserStoryDto("US001",
                "I want to create a project", "ongoing");
        ProjectContainer projectContainerTest = new ProjectContainer();
        Project projectDouble = mock(Project.class);
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        ProjectTypologyContainer projectTypologyContainerDouble = mock(
                ProjectTypologyContainer.class);
        CustomerContainer costumerContainerDouble = mock(CustomerContainer.class);
        BusinessSectorContainer businessSectorContainerDouble = mock(
                BusinessSectorContainer.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(
                FactoryProductBacklog.class);
        IFactoryProject factoryProjectDouble = mock(FactoryProject.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklogDouble = mock(
                FactorySprintBacklog.class);
        IFactorySprint factorySprintDouble = mock(FactorySprint.class);
        when(factoryProjectDouble.createProject(any(), any(), any(), any(), any(), any(),
                any(), any(), any())).thenReturn(projectDouble);
        when(projectDouble.estimateEffortUserStory(any(), any(), any())).thenReturn(true);
        when(projectDouble.hasProjectCode(any())).thenReturn(false);
        projectContainerTest.registerProject(projectCreationDtoDouble,
                projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble,
                factoryProductBacklogDouble,
                factoryUserStoryDouble, factoryProjectDouble, factoryPeriodDouble,
                factorySprintBacklogDouble, factorySprintDouble);


        // Act
        boolean result = projectContainerTest.estimateEffortUserStory(userStoryDto,
                Effort.FIVE, "", LocalDate.of(2023, 3, 8));

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verifies that the estimateEffortUserStory() method of the Project
     * Container class
     * can't correctly estimate the effort for a user story, because project is null
     * Expected result: false, indicating that the estimation was unsuccessful.
     */
    @Test
    void ensureEstimateEffortUserStoryUnsuccessfullyProjectNull() {
        // Arrange
        UserStoryDto userStoryDto = new UserStoryDto("US001",
                "I want to create a project", "ongoing");
        ProjectContainer projectContainerTest = new ProjectContainer();
        Project projectDouble = mock(Project.class);
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        ProjectTypologyContainer projectTypologyContainerDouble = mock(
                ProjectTypologyContainer.class);
        CustomerContainer costumerContainerDouble = mock(CustomerContainer.class);
        BusinessSectorContainer businessSectorContainerDouble = mock(
                BusinessSectorContainer.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(
                FactoryProductBacklog.class);
        IFactoryProject factoryProjectDouble = mock(FactoryProject.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklogDouble = mock(
                FactorySprintBacklog.class);
        IFactorySprint factorySprintDouble = mock(FactorySprint.class);
        when(factoryProjectDouble.createProject(any(), any(), any(), any(), any(), any(),
                any(), any(), any())).thenReturn(null);
        when(projectDouble.estimateEffortUserStory(any(), any(), any())).thenReturn(true);
        when(projectDouble.hasProjectCode(any())).thenReturn(false);
        projectContainerTest.registerProject(projectCreationDtoDouble,
                projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble,
                factoryProductBacklogDouble,
                factoryUserStoryDouble, factoryProjectDouble, factoryPeriodDouble,
                factorySprintBacklogDouble, factorySprintDouble);


        // Act
        boolean result = projectContainerTest.estimateEffortUserStory(userStoryDto,
                Effort.FIVE, null, LocalDate.of(2023, 3, 8));

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD getProductBacklog
     * Scenario 1: this test ensure that a Product Backlog of a give Project is
     * successfully
     * returned.
     */
    @Test
    void ensureThatProductBacklogIsSuccessfullyReturned() {
        //ARRANGE
        // Create ProjectContainer
        ProjectContainer projectContainer = new ProjectContainer();

        // Create ProjectDto to be used
        ProjectDto projectDto = new ProjectDto("P001", "Project1", "ITV", "Panned",
                "Fixed cost", "Media");

        // Create and Add Project to Projects
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        ProjectTypologyContainer projectTypologyContainerDouble = mock(
                ProjectTypologyContainer.class);
        CustomerContainer customerContainerDouble = mock(CustomerContainer.class);
        BusinessSectorContainer businessSectorContainerDouble = mock(
                BusinessSectorContainer.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(
                FactoryProductBacklog.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProject factoryProjectDouble = mock(FactoryProject.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklogDouble = mock(
                FactorySprintBacklog.class);
        IFactorySprint factorySprintDouble = mock(FactorySprint.class);

        Customer customerDouble = mock(Customer.class);
        when(customerContainerDouble.getCustomer(any(), any())).thenReturn(
                customerDouble);

        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        when(projectTypologyContainerDouble.getProjectTypology(any())).thenReturn(
                projectTypologyDouble);

        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        when(businessSectorContainerDouble.getBusinessSector(any())).thenReturn(
                businessSectorDouble);

        Project projectDouble = mock(Project.class);
        when(factoryProjectDouble.createProject(projectCreationDtoDouble, customerDouble,
                projectTypologyDouble, businessSectorDouble, factoryProductBacklogDouble,
                factoryUserStoryDouble, factoryPeriodDouble, factorySprintBacklogDouble,
                factorySprintDouble))
                .thenReturn(projectDouble);

        projectContainer.registerProject(projectCreationDtoDouble,
                projectTypologyContainerDouble,
                customerContainerDouble, businessSectorContainerDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryProjectDouble,
                factoryPeriodDouble, factorySprintBacklogDouble, factorySprintDouble);

        // Get Project and get Product Backlog
        when(projectDouble.hasProjectCode("P001")).thenReturn(true);

        ProductBacklog expected = mock(ProductBacklog.class);
        when(projectDouble.getProductBacklog()).thenReturn(expected);

        //ACT
        ProductBacklog result = projectContainer.getProductBacklog(projectDto);

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * METHOD getProductBacklog
     * Scenario 2: this test ensures that an exception is thrown if the Project is not
     * successfully returned.
     */
    @Test
    void ensureThatProductBacklogIsNotSuccessfullyReturnedIfProjectDoesNotExist() {
        //ARRANGE
        // Create ProjectContainer
        ProjectContainer projectContainer = new ProjectContainer();

        // Create ProjectDto to be used
        ProjectDto projectDtoTwo = new ProjectDto("P002", "Project1", "ITV", "Panned",
                "Fixed cost", "Media");

        // Create and Add Project to Projects
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        ProjectTypologyContainer projectTypologyContainerDouble = mock(
                ProjectTypologyContainer.class);
        CustomerContainer customerContainerDouble = mock(CustomerContainer.class);
        BusinessSectorContainer businessSectorContainerDouble = mock(
                BusinessSectorContainer.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(
                FactoryProductBacklog.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProject factoryProjectDouble = mock(FactoryProject.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklogDouble = mock(
                FactorySprintBacklog.class);
        IFactorySprint factorySprintDouble = mock(FactorySprint.class);

        Customer customerDouble = mock(Customer.class);
        when(customerContainerDouble.getCustomer(any(), any())).thenReturn(
                customerDouble);

        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        when(projectTypologyContainerDouble.getProjectTypology(any())).thenReturn(
                projectTypologyDouble);

        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        when(businessSectorContainerDouble.getBusinessSector(any())).thenReturn(
                businessSectorDouble);

        Project projectDouble = mock(Project.class);
        when(factoryProjectDouble.createProject(projectCreationDtoDouble, customerDouble,
                projectTypologyDouble, businessSectorDouble, factoryProductBacklogDouble,
                factoryUserStoryDouble, factoryPeriodDouble, factorySprintBacklogDouble,
                factorySprintDouble))
                .thenReturn(projectDouble);

        projectContainer.registerProject(projectCreationDtoDouble,
                projectTypologyContainerDouble,
                customerContainerDouble, businessSectorContainerDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryProjectDouble,
                factoryPeriodDouble, factorySprintBacklogDouble, factorySprintDouble);

        // Get Project and get Product Backlog
        when(projectDouble.hasProjectCode("P002")).thenReturn(false);

        ProductBacklog expected = mock(ProductBacklog.class);
        when(projectDouble.getProductBacklog()).thenReturn(expected);

        Exception exception = assertThrows(Exception.class, () -> {
            projectContainer.getProductBacklog(projectDtoTwo);
        });
        String expectedMessage = "No value present";
        //ACT
        String actualMessage = exception.getMessage();
        //ASSERT
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * METHOD getScrumBoard
     * Scenario 1: This test ensures that the Scrum Board (composed of the sprint
     * backlog) for a given project is successfully returned.
     */

    @Test
    void ensureThatSprintBacklogIsSuccessfullyReturned() {
        //ARRANGE
        // Create ProjectContainer
        ProjectContainer projectContainer = new ProjectContainer();

        // Create ProjectDto to be used
        ProjectDto projectDto = new ProjectDto("P001", "Project1", "ITV", "Planned",
                "Fixed cost", "Media");

        // Create and Add Project to Projects
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        ProjectTypologyContainer projectTypologyContainerDouble = mock(
                ProjectTypologyContainer.class);
        CustomerContainer customerContainerDouble = mock(CustomerContainer.class);
        BusinessSectorContainer businessSectorContainerDouble = mock(
                BusinessSectorContainer.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(
                FactoryProductBacklog.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProject factoryProjectDouble = mock(FactoryProject.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklogDouble = mock(
                FactorySprintBacklog.class);
        IFactorySprint factorySprintDouble = mock(FactorySprint.class);

        Customer customerDouble = mock(Customer.class);
        when(customerContainerDouble.getCustomer(any(), any())).thenReturn(
                customerDouble);

        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        when(projectTypologyContainerDouble.getProjectTypology(any())).thenReturn(
                projectTypologyDouble);

        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        when(businessSectorContainerDouble.getBusinessSector(any())).thenReturn(
                businessSectorDouble);

        Project projectDouble = mock(Project.class);
        when(factoryProjectDouble.createProject(projectCreationDtoDouble, customerDouble,
                projectTypologyDouble, businessSectorDouble, factoryProductBacklogDouble,
                factoryUserStoryDouble, factoryPeriodDouble, factorySprintBacklogDouble,
                factorySprintDouble))
                .thenReturn(projectDouble);

        projectContainer.registerProject(projectCreationDtoDouble,
                projectTypologyContainerDouble,
                customerContainerDouble, businessSectorContainerDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryProjectDouble,
                factoryPeriodDouble, factorySprintBacklogDouble, factorySprintDouble);

        // Get Project and get Sprint Backlog
        when(projectDouble.hasProjectCode("P001")).thenReturn(true);

        SprintBacklog sprintBacklogExpected = mock(SprintBacklog.class);
        when(projectDouble.getSprintBacklogByDate(LocalDate.of(2023, 3, 9),
                factoryUserStoryDouble)).thenReturn(
                Optional.ofNullable(sprintBacklogExpected));
        Optional<SprintBacklog> expected = Optional.of(sprintBacklogExpected);
        //ACT
        Optional<SprintBacklog> result = projectContainer.getScrumBoard(projectDto.code,
                LocalDate.of(2023, 3, 9), factoryUserStoryDouble);

        //ASSERT
        assertEquals(expected, result);

    }
    /**
     * Method: registerProject()
     * <p>
     * Scenario 1: Project is registered successfully with isolation.
     * Expected result: true.
     */

    @Test
    void ensureProjectIsRegisteredWithIsolation() {
        // Arrange
        ProjectContainer projectContainerTest = new ProjectContainer();
        Project projectDouble = mock(Project.class);
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        ProjectTypologyContainer projectTypologyContainerDouble = mock(ProjectTypologyContainer.class);
        CustomerContainer costumerContainerDouble = mock(CustomerContainer.class);
        BusinessSectorContainer businessSectorContainerDouble = mock(BusinessSectorContainer.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(FactoryProductBacklog.class);
        IFactoryProject factoryProjectDouble = mock(FactoryProject.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        IFactorySprint factorySprintDouble = mock(FactorySprint.class);
        ProjectCreationMapper projectCreationMapperDouble = mock(ProjectCreationMapper.class);
        when(projectCreationMapperDouble.getProjectFromDto(projectCreationDtoDouble, projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble, factoryProductBacklogDouble,
                factoryUserStoryDouble, factoryProjectDouble, factoryPeriodDouble, factorySprintBacklogDouble,
                factorySprintDouble)).thenReturn(projectDouble);

        // Act
        boolean result = projectContainerTest.registerProject(projectCreationDtoDouble, projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble, factoryProductBacklogDouble,
                factoryUserStoryDouble, factoryProjectDouble, factoryPeriodDouble, factorySprintBacklogDouble,
                factorySprintDouble);

        // Assert
        assertTrue(result);
    }
    /**
     * Method: createSprint()
     * <p>
     * Scenario 1: Sprint is not created because of project null.
     * Expected result: false.
     */

    @Test
    void ensureSprintIsNotCreatedInAProject_ProjectNull() {
        //Arrange
        SprintCreationDto sprintCreationDto = mock(SprintCreationDto.class);
        ProjectDto projectDto = mock( ProjectDto.class);
        ProjectContainer projectContainerTest = new ProjectContainer();
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        ProjectTypologyContainer projectTypologyContainerDouble = mock(
                ProjectTypologyContainer.class);
        CustomerContainer costumerContainerDouble = mock(CustomerContainer.class);
        BusinessSectorContainer businessSectorContainerDouble = mock(
                BusinessSectorContainer.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(
                FactoryProductBacklog.class);
        IFactoryProject factoryProjectDouble = mock(FactoryProject.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklogDouble = mock(
                FactorySprintBacklog.class);
        IFactorySprint factorySprintDouble = mock(FactorySprint.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        Customer costumerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        when(factoryProjectDouble.createProject(projectCreationDtoDouble, costumerDouble,
                projectTypologyDouble, businessSectorDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryPeriodDouble,
                factorySprintBacklogDouble, factorySprintDouble)).thenReturn(null);
        projectContainerTest.registerProject(projectCreationDtoDouble,
                projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryProjectDouble,
                factoryPeriodDouble, factorySprintBacklogDouble, factorySprintDouble);
        //Act
        boolean result = projectContainerTest.createSprint(sprintCreationDto,projectDto);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 2: Sprint is created in a given Project.
     * return true.
     */
    @Test
    void ensureSprintIsCreatedInAProject() {
        //Arrange
        SprintCreationDto sprintCreationDtoDouble = mock(SprintCreationDto.class);
        ProjectDto projectDto = new ProjectDto("P001", "Project1", "ITV", "Panned",
                "Fixed cost", "Media");
        ProjectContainer projectContainerTest = new ProjectContainer();
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        ProjectTypologyContainer projectTypologyContainerDouble = mock(
                ProjectTypologyContainer.class);
        CustomerContainer costumerContainerDouble = mock(CustomerContainer.class);
        BusinessSectorContainer businessSectorContainerDouble = mock(
                BusinessSectorContainer.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(
                FactoryProductBacklog.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklogDouble = mock(
                FactorySprintBacklog.class);
        IFactorySprint factorySprintDouble = mock(FactorySprint.class);
        Project projectDouble = mock(Project.class);
        IFactoryProject factoryProjectDouble = mock(FactoryProject.class);
        when(factoryProjectDouble.createProject(any(), any(), any(), any(), any(), any(),
                any(), any(), any())).
                thenReturn(projectDouble);
        when(projectDouble.createSprint(sprintCreationDtoDouble)).thenReturn(true);
        when(projectDouble.hasProjectCode("P001")).thenReturn(true);
        projectContainerTest.registerProject(projectCreationDtoDouble,
                projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble,
                factoryProductBacklogDouble,
                factoryUserStoryDouble, factoryProjectDouble, factoryPeriodDouble,
                factorySprintBacklogDouble, factorySprintDouble);
        //Act
        boolean result = projectContainerTest.createSprint(sprintCreationDtoDouble,projectDto);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 3: this test doesn't create a Sprint in a given project
     * due to project not be in the container
     */
    @Test
    void ensureSprintIsNotCreatedInAProject() {
        //Arrange
        SprintCreationDto sprintCreationDtoDouble = mock(SprintCreationDto.class);
        ProjectDto projectDto = new ProjectDto("P001", "Project1", "ITV", "Panned",
                "Fixed cost", "Media");
        ProjectContainer projectContainerTest = new ProjectContainer();
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        ProjectTypologyContainer projectTypologyContainerDouble = mock(
                ProjectTypologyContainer.class);
        CustomerContainer costumerContainerDouble = mock(CustomerContainer.class);
        BusinessSectorContainer businessSectorContainerDouble = mock(
                BusinessSectorContainer.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(
                FactoryProductBacklog.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklogDouble = mock(
                FactorySprintBacklog.class);
        IFactorySprint factorySprintDouble = mock(FactorySprint.class);
        Project projectDouble = mock(Project.class);
        IFactoryProject factoryProjectDouble = mock(FactoryProject.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        Customer costumerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        when(factoryProjectDouble.createProject(projectCreationDtoDouble, costumerDouble,
                projectTypologyDouble, businessSectorDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryPeriodDouble,
                factorySprintBacklogDouble, factorySprintDouble)).thenReturn(
                projectDouble);
        projectContainerTest.registerProject(projectCreationDtoDouble,
                projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryProjectDouble,
                factoryPeriodDouble, factorySprintBacklogDouble, factorySprintDouble);
        when(projectDouble.createSprint(sprintCreationDtoDouble)).thenReturn(true);
        //Act
        boolean result = projectContainerTest.createSprint(sprintCreationDtoDouble,projectDto);
        //Assert
        assertFalse(result);
    }
    /**
     * Scenario 4: this test doesn't create a Sprint in a given project
     * due to Sprint be repeated
     */
    @Test
    void ensureSprintIsNotCreatedInAProject_RepeatedSprint() {
        //Arrange
        SprintCreationDto sprintCreationDtoDouble = mock(SprintCreationDto.class);
        ProjectDto projectDto = new ProjectDto("P001", "Project1", "ITV", "Panned",
                "Fixed cost", "Media");
        ProjectContainer projectContainerTest = new ProjectContainer();
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        ProjectTypologyContainer projectTypologyContainerDouble = mock(
                ProjectTypologyContainer.class);
        CustomerContainer costumerContainerDouble = mock(CustomerContainer.class);
        BusinessSectorContainer businessSectorContainerDouble = mock(
                BusinessSectorContainer.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(
                FactoryProductBacklog.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklogDouble = mock(
                FactorySprintBacklog.class);
        IFactorySprint factorySprintDouble = mock(FactorySprint.class);
        Project projectDouble = mock(Project.class);
        IFactoryProject factoryProjectDouble = mock(FactoryProject.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        Customer costumerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        when(factoryProjectDouble.createProject(projectCreationDtoDouble, costumerDouble,
                projectTypologyDouble, businessSectorDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryPeriodDouble,
                factorySprintBacklogDouble, factorySprintDouble)).thenReturn(
                projectDouble);
        projectContainerTest.registerProject(projectCreationDtoDouble,
                projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryProjectDouble,
                factoryPeriodDouble, factorySprintBacklogDouble, factorySprintDouble);
        when(projectDouble.createSprint(sprintCreationDtoDouble)).thenReturn(false);
        //Act
        boolean result = projectContainerTest.createSprint(sprintCreationDtoDouble,projectDto);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD addUserStoryToSprintBacklog(projectCode, UserStoryNumber, SprintNumber)
     * adds a User Story to Sprint Backlog
     * <p>
     * Scenario 1: verify if a User Story is added to Sprint Backlog if it is not
     * already there. Should return TRUE.
     */
    @Test
    void ensureThatUserStoryIsAddedToSprintBacklog() {
        //ARRANGE
        ProjectContainer projectContainerTest = new ProjectContainer();
        Project projectDouble = mock(Project.class);
        projectContainerTest.addProjectToProjectContainer(projectDouble);

        when(projectDouble.hasProjectCode("P1")).thenReturn(true);

        when(projectDouble.addUserStoryToSprintBacklog("US001", "SP10")).thenReturn(true);

        //ACT
        boolean result = projectContainerTest.addUserStoryToSprintBacklog("P1", "US001",
                "SP10");

        //ASSERT
        assertTrue(result);
    }

    /**
     * Scenario 2: verify if a User Story is not added to the Sprint Backlog of a
     * Project that does not exist.
     * Should return FALSE.
     */
    @Test
    void ensureThatUserStoryIsNotAddedToSprintBacklogBecauseProjectDoesNotExist() {
        //ARRANGE
        ProjectContainer projectContainerTest = new ProjectContainer();

        //ACT
        boolean result = projectContainerTest.addUserStoryToSprintBacklog("P1", "US001",
                "SP10");

        //ASSERT
        assertFalse(result);
    }

    /**
     * Scenario 3: verify if a User Story is not added to the Sprint Backlog of a
     * Project because the User Story or The Sprint does not exist.
     * Should return FALSE.
     */
    @Test
    void ensureThatUserStoryIsNotAddedToSprintBacklogBecauseUserStoryOrSprintDoesNotExist() {
        //ARRANGE
        ProjectContainer projectContainerTest = new ProjectContainer();

        Project projectDouble = mock(Project.class);

        projectContainerTest.addProjectToProjectContainer(projectDouble);

        when(projectDouble.hasProjectCode("P1")).thenReturn(true);

        when(projectDouble.addUserStoryToSprintBacklog("US001", "SP10")).thenReturn(false);

        //ACT
        boolean result = projectContainerTest.addUserStoryToSprintBacklog("P1", "US001",
                "SP10");

        //ASSERT
        assertFalse(result);
    }

    /**
     * METHOD addProjectToProjectContainer(project)
     * adds a Project to Project Container
     * <p>
     * Scenario 1: verify if a Project is added to the list of Projects if it is not
     * already there. Should return TRUE.
     */
    @Test
    void ensureThatSprintIsSuccessfullyAddedToSprintsList() {
        //Arrange
        Project projectDouble = mock(Project.class);
        ProjectContainer projectContainer = new ProjectContainer();

        Project projectDoubleTest = mock(Project.class);

        projectContainer.addProjectToProjectContainer(projectDoubleTest);

        //Act
        boolean result = projectContainer.addProjectToProjectContainer(projectDouble);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: verify if a Project is added to the list os Projects because project
     * list is empty.
     * Should return TRUE.
     */
    @Test
    void ensureProjectIsSuccessfullyAddedToProjectContainerBecauseProjectListIsEmpty() {
        //Arrange
        Project projectDouble = mock(Project.class);
        ProjectContainer projectContainer = new ProjectContainer();

        //Act
        boolean result = projectContainer.addProjectToProjectContainer(projectDouble);

        //Assert
        assertTrue(result);

    }

    /**
     * Scenario 3: verify if a Project is not added to the list os Projects because
     * project is already there. Should return FALSE.
     */
    @Test
    void ensureSprintIsNotAddedToSprintsListBecauseItAlreadyExists() {
        //Arrange
        Project projectDouble = mock(Project.class);
        ProjectContainer projectContainer = new ProjectContainer();

        projectContainer.addProjectToProjectContainer(projectDouble);

        //Act
        boolean result = projectContainer.addProjectToProjectContainer(projectDouble);

        //Assert
        assertFalse(result);
    }

}