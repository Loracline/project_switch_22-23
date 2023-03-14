package org.switch2022.project.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.dto.ProjectCreationDto;
import org.switch2022.project.dto.ProjectDto;
import org.switch2022.project.dto.UserStoryCreationDto;
import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.factories.*;
import org.switch2022.project.model.*;
import org.switch2022.project.utils.Effort;


import java.time.LocalDate;

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
        projectContainer.registerProject(projectOneDTO, projectTypologyContainer, customerContainer,
                businessSectorContainer, factoryProductBacklog, factoryUserStory, factoryProject, factoryPeriod, factorySprintBacklog, factorySprint);


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
        boolean result = projectContainer.registerProject(projectTwoDTO, projectTypologyContainer, customerContainer,
                businessSectorContainer, factoryProductBacklog, factoryUserStory, factoryProject, factoryPeriod, factorySprintBacklog, factorySprint);

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
        boolean result = projectContainer.registerProject(projectOneDTO, projectTypologyContainer, customerContainer,
                businessSectorContainer, factoryProductBacklog, factoryUserStory, factoryProject, factoryPeriod, factorySprintBacklog, factorySprint);

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
        UserStoryCreationDto userStoryCreationDtoDouble = mock(UserStoryCreationDto.class);
        ProjectDto projectDto = new ProjectDto("P001", "Project1", "ITV", "Panned",
                "Fixed cost", "Media");
        ProjectContainer projectContainerTest = new ProjectContainer();
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        ProjectTypologyContainer projectTypologyContainerDouble = mock(ProjectTypologyContainer.class);
        CustomerContainer costumerContainerDouble = mock(CustomerContainer.class);
        BusinessSectorContainer businessSectorContainerDouble = mock(BusinessSectorContainer.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(FactoryProductBacklog.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        IFactorySprint factorySprintDouble = mock(FactorySprint.class);
        Project projectDouble = mock(Project.class);
        IFactoryProject factoryProjectDouble = mock(FactoryProject.class);
        when(factoryProjectDouble.createProject(any(), any(), any(), any(), any(), any(), any(), any(), any())).
                thenReturn(projectDouble);
        when(projectDouble.createUserStory(userStoryCreationDtoDouble)).thenReturn(true);
        when(projectDouble.hasProjectCode("P001")).thenReturn(true);
        projectContainerTest.registerProject(projectCreationDtoDouble, projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble, factoryProductBacklogDouble,
                factoryUserStoryDouble, factoryProjectDouble, factoryPeriodDouble, factorySprintBacklogDouble, factorySprintDouble);
        //Act
        boolean result = projectContainerTest.createUserStory(projectDto, userStoryCreationDtoDouble);
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
        UserStoryCreationDto userStoryCreationDtoDouble = mock(UserStoryCreationDto.class);
        ProjectDto projectDto = new ProjectDto("P001", "Project1", "ITV", "Panned",
                "Fixed cost", "Media");
        ProjectContainer projectContainerTest = new ProjectContainer();
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        ProjectTypologyContainer projectTypologyContainerDouble = mock(ProjectTypologyContainer.class);
        CustomerContainer costumerContainerDouble = mock(CustomerContainer.class);
        BusinessSectorContainer businessSectorContainerDouble = mock(BusinessSectorContainer.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(FactoryProductBacklog.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        IFactorySprint factorySprintDouble = mock(FactorySprint.class);
        Project projectDouble = mock(Project.class);
        IFactoryProject factoryProjectDouble = mock(FactoryProject.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        Customer costumerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        when(factoryProjectDouble.createProject(projectCreationDtoDouble, costumerDouble, projectTypologyDouble, businessSectorDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryPeriodDouble, factorySprintBacklogDouble, factorySprintDouble)).thenReturn(projectDouble);
        projectContainerTest.registerProject(projectCreationDtoDouble, projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble, factoryProductBacklogDouble, factoryUserStoryDouble, factoryProjectDouble, factoryPeriodDouble, factorySprintBacklogDouble, factorySprintDouble);
        when(projectDouble.createUserStory(userStoryCreationDtoDouble)).thenReturn(true);
        //Act
        boolean result = projectContainerTest.createUserStory(projectDto, userStoryCreationDtoDouble);
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
        UserStoryCreationDto userStoryCreationDtoDouble = mock(UserStoryCreationDto.class);
        ProjectDto projectDto = new ProjectDto("P001", "Project1", "ITV", "Panned",
                "Fixed cost", "Media");
        ProjectContainer projectContainerTest = new ProjectContainer();
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        ProjectTypologyContainer projectTypologyContainerDouble = mock(ProjectTypologyContainer.class);
        CustomerContainer costumerContainerDouble = mock(CustomerContainer.class);
        BusinessSectorContainer businessSectorContainerDouble = mock(BusinessSectorContainer.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(FactoryProductBacklog.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        IFactorySprint factorySprintDouble = mock(FactorySprint.class);
        Project projectDouble = mock(Project.class);
        IFactoryProject factoryProjectDouble = mock(FactoryProject.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        Customer costumerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        when(factoryProjectDouble.createProject(projectCreationDtoDouble, costumerDouble, projectTypologyDouble, businessSectorDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryPeriodDouble, factorySprintBacklogDouble, factorySprintDouble)).thenReturn(projectDouble);
        projectContainerTest.registerProject(projectCreationDtoDouble, projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble, factoryProductBacklogDouble, factoryUserStoryDouble, factoryProjectDouble, factoryPeriodDouble, factorySprintBacklogDouble, factorySprintDouble);
        when(projectDouble.createUserStory(userStoryCreationDtoDouble)).thenReturn(false);
        //Act
        boolean result = projectContainerTest.createUserStory(projectDto, userStoryCreationDtoDouble);
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
        UserStoryCreationDto userStoryCreationDtoDouble = mock(UserStoryCreationDto.class);
        ProjectDto projectDto = new ProjectDto("P001", "Project1", "ITV", "Panned",
                "Fixed cost", "Media");
        ProjectContainer projectContainerTest = new ProjectContainer();
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
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        Customer costumerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        when(factoryProjectDouble.createProject(projectCreationDtoDouble, costumerDouble, projectTypologyDouble, businessSectorDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryPeriodDouble, factorySprintBacklogDouble, factorySprintDouble)).thenReturn(null);
        projectContainerTest.registerProject(projectCreationDtoDouble, projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble, factoryProductBacklogDouble, factoryUserStoryDouble, factoryProjectDouble, factoryPeriodDouble, factorySprintBacklogDouble, factorySprintDouble);
        //Act
        boolean result = projectContainerTest.createUserStory(projectDto, userStoryCreationDtoDouble);
        //Assert
        assertFalse(result);
    }

    /**
     * Method ensureEstimateEffortUserStory (userStoryDto, effort, projectCode)
     * Scenario 1: Verifies that the estimateEffortUserStory() method of the Project Container class
     * can correctly estimate the effort for a user story
     * Expected result: true, indicating that the estimation was successful.
     */

    @Test
    void ensureEstimateEffortUserStorySuccessfully() {
        // Arrange
        UserStoryDto userStoryDto = new UserStoryDto("US001", "I want to create a project", "ongoing");
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
        when(factoryProjectDouble.createProject(any(), any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(projectDouble);
        when(projectDouble.estimateEffortUserStory(any(), any(), any())).thenReturn(true);
        when(projectDouble.hasProjectCode(any())).thenReturn(true);
        projectContainerTest.registerProject(projectCreationDtoDouble, projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble, factoryProductBacklogDouble,
                factoryUserStoryDouble, factoryProjectDouble, factoryPeriodDouble, factorySprintBacklogDouble, factorySprintDouble);

        // Act
        boolean result = projectContainerTest.estimateEffortUserStory(userStoryDto, Effort.FIVE, "AA001", LocalDate.of(2023, 3, 8));

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verifies that the estimateEffortUserStory() method of the Project Container class
     * can't correctly estimate the effort for a user story, because userStoryNumber doesn't exist.
     * Expected result: false, indicating that the estimation was unsuccessful.
     */


    @Test
    void ensureEstimateEffortUserStoryUnsuccessfullyUserStoryNotFound() {
        // Arrange
        UserStoryDto userStoryDto = new UserStoryDto("US001", "I want to create a project", "ongoing");
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
        when(factoryProjectDouble.createProject(any(), any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(projectDouble);
        when(projectDouble.estimateEffortUserStory(any(), any(), any())).thenReturn(false);
        when(projectDouble.hasProjectCode(any())).thenReturn(true);
        projectContainerTest.registerProject(projectCreationDtoDouble, projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble, factoryProductBacklogDouble,
                factoryUserStoryDouble, factoryProjectDouble, factoryPeriodDouble, factorySprintBacklogDouble, factorySprintDouble);


        // Act
        boolean result = projectContainerTest.estimateEffortUserStory(userStoryDto, Effort.FIVE, "AA001", LocalDate.of(2023, 3, 8));

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: Verifies that the estimateEffortUserStory() method of the Project Container class
     * can't correctly estimate the effort for a user story, because project isn't registered in the system
     * Expected result: false, indicating that the estimation was unsuccessful.
     */

    @Test
    void ensureEstimateEffortUserStoryUnsuccessfullyProjectCodeAlreadyExists() {
        // Arrange
        UserStoryDto userStoryDto = new UserStoryDto("US001", "I want to create a project", "ongoing");
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
        when(factoryProjectDouble.createProject(any(), any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(projectDouble);
        when(projectDouble.estimateEffortUserStory(any(), any(), any())).thenReturn(true);
        when(projectDouble.hasProjectCode(any())).thenReturn(false);
        projectContainerTest.registerProject(projectCreationDtoDouble, projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble, factoryProductBacklogDouble,
                factoryUserStoryDouble, factoryProjectDouble, factoryPeriodDouble, factorySprintBacklogDouble, factorySprintDouble);


        // Act
        boolean result = projectContainerTest.estimateEffortUserStory(userStoryDto, Effort.FIVE, "", LocalDate.of(2023, 3, 8));

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verifies that the estimateEffortUserStory() method of the Project Container class
     * can't correctly estimate the effort for a user story, because project is null
     * Expected result: false, indicating that the estimation was unsuccessful.
     */
    @Test
    void ensureEstimateEffortUserStoryUnsuccessfullyProjectNull() {
        // Arrange
        UserStoryDto userStoryDto = new UserStoryDto("US001", "I want to create a project", "ongoing");
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
        when(factoryProjectDouble.createProject(any(), any(), any(), any(), any(), any(),any(),any(),any())).thenReturn(null);
        when(projectDouble.estimateEffortUserStory(any(), any(), any())).thenReturn(true);
        when(projectDouble.hasProjectCode(any())).thenReturn(false);
        projectContainerTest.registerProject(projectCreationDtoDouble, projectTypologyContainerDouble,
                costumerContainerDouble, businessSectorContainerDouble, factoryProductBacklogDouble,
                factoryUserStoryDouble, factoryProjectDouble,factoryPeriodDouble, factorySprintBacklogDouble,factorySprintDouble);


        // Act
        boolean result = projectContainerTest.estimateEffortUserStory(userStoryDto, Effort.FIVE, null, LocalDate.of(2023, 3, 8));

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD getProductBacklog
     * Scenario 1: this test ensure that a Product Backlog of a give Project is successfully
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
        ProjectTypologyContainer projectTypologyContainerDouble = mock(ProjectTypologyContainer.class);
        CustomerContainer customerContainerDouble = mock(CustomerContainer.class);
        BusinessSectorContainer businessSectorContainerDouble = mock(BusinessSectorContainer.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(FactoryProductBacklog.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProject factoryProjectDouble = mock(FactoryProject.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        IFactorySprint factorySprintDouble = mock(FactorySprint.class);

        Customer customerDouble = mock(Customer.class);
        when(customerContainerDouble.getCustomer(any(), any())).thenReturn(customerDouble);

        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        when(projectTypologyContainerDouble.getProjectTypology(any())).thenReturn(projectTypologyDouble);

        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        when(businessSectorContainerDouble.getBusinessSector(any())).thenReturn(businessSectorDouble);

        Project projectDouble = mock(Project.class);
        when(factoryProjectDouble.createProject(projectCreationDtoDouble, customerDouble,
                projectTypologyDouble, businessSectorDouble, factoryProductBacklogDouble,
                factoryUserStoryDouble, factoryPeriodDouble, factorySprintBacklogDouble,
                factorySprintDouble))
                .thenReturn(projectDouble);

        projectContainer.registerProject(projectCreationDtoDouble, projectTypologyContainerDouble,
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

}