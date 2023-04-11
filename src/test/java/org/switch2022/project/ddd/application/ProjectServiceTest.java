package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.project.*;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.ProjectCreationDto;
import org.switch2022.project.ddd.infrastructure.ProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectServiceTest {
    /**
     * BeforeEach execute common code before running the
     * tests below.
     */
    IFactoryProject factoryProjectDouble, factoryProject;
    IProjectRepository projectRepositoryDouble, projectRepository;
    IFactoryProductBacklog factoryProductBacklogDouble;
    ProjectService projectService, projectServiceTwo;
    Project projectOne;
    ProjectCreationDto projectCreationDto;
    BusinessSectorId businessSectorIdDouble;
    CustomerId customerIdDouble;
    ProjectTypologyId projectTypologyIdDouble;
    Code code;
    ProductBacklog productBacklogDouble;

    @BeforeEach
    void setUp() {
        factoryProjectDouble = mock(IFactoryProject.class);
        projectRepositoryDouble = mock(IProjectRepository.class);
        factoryProductBacklogDouble = mock(IFactoryProductBacklog.class);
        projectService = new ProjectService(factoryProjectDouble, projectRepositoryDouble,
                factoryProductBacklogDouble);

        factoryProject = new FactoryProject();
        projectRepository = new ProjectRepository();
        projectServiceTwo = new ProjectService(factoryProject, projectRepository,
                factoryProductBacklogDouble);

        code = new Code("P2");
        projectCreationDto = new ProjectCreationDto("Happy Project", "An amazing " +
                "project", "Happy Name", "Happy Customer", "Happy Typology",
                2);
        businessSectorIdDouble = mock(BusinessSectorId.class);
        customerIdDouble = mock(CustomerId.class);
        projectTypologyIdDouble = mock(ProjectTypologyId.class);
        productBacklogDouble = mock(ProductBacklog.class);
        when(factoryProductBacklogDouble.createProductBacklog(any())).thenReturn(productBacklogDouble);
        projectOne = factoryProject.createProject(code, projectCreationDto,
                businessSectorIdDouble, customerIdDouble, projectTypologyIdDouble,
                factoryProductBacklogDouble);

        projectRepository.addProjectToProjectRepository(projectOne);
    }

    /**
     * Constructor
     * scenario 1: the factory project is null
     */
    @Test
    void ensureProjectServiceIsNotCreated_FactoryProjectNull() {
        //Arrange
        IFactoryProject factoryProjectNull = null;
        ProjectRepository projectRepositoryDouble = mock(ProjectRepository.class);
        IFactoryProductBacklog factoryProductBacklogDouble = mock(FactoryProductBacklog.class);

        Exception exception = assertThrows(Exception.class, () ->
                new ProjectService(factoryProjectNull, projectRepositoryDouble,
                        factoryProductBacklogDouble));
        String expected = "Factory Project can't be null";
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Constructor
     * scenario 2: the ProjectRepository is null
     */
    @Test
    void ensureProjectServiceIsNotCreated_ProjectRepositoryNull() {
        //Arrange
        IFactoryProject factoryProjectNull = mock(FactoryProject.class);
        ProjectRepository projectRepositoryDouble = null;
        IFactoryProductBacklog factoryProductBacklogDouble = mock(FactoryProductBacklog.class);

        Exception exception = assertThrows(Exception.class, () ->
                new ProjectService(factoryProjectNull, projectRepositoryDouble,
                        factoryProductBacklogDouble));
        String expected = "Project Repository can't be null";
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Constructor
     * scenario 3: the factory Product Backlog is null
     */
    @Test
    void ensureProjectServiceIsNotCreated_FactoryProductBacklogNull() {
        //Arrange
        IFactoryProject factoryProjectNull = mock(FactoryProject.class);
        ProjectRepository projectRepositoryDouble = mock(ProjectRepository.class);
        IFactoryProductBacklog factoryProductBacklogDouble = null;

        Exception exception = assertThrows(Exception.class, () ->
                new ProjectService(factoryProjectNull, projectRepositoryDouble,
                        factoryProductBacklogDouble));
        String expected = "Factory ProductBacklog can't be null";
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }


    /**
     * Method: createProject
     * scenario1: the project is created successfully
     */
    @Test
    void ensureProjectIsCreated() throws Exception {
        //Arrange
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        CustomerId customerIdDouble = mock(CustomerId.class);
        BusinessSectorId businessSectorIdDouble = mock(BusinessSectorId.class);
        ProjectTypologyId projectTypologyIdDouble = mock(ProjectTypologyId.class);
        String expected = "P2";
        when(projectRepositoryDouble.getProjectNumber()).thenReturn(1);
        when(projectRepositoryDouble.addProjectToProjectRepository(any())).thenReturn(true);

        //Act
        String result = projectService.createProject(projectCreationDtoDouble, customerIdDouble,
                businessSectorIdDouble, projectTypologyIdDouble);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: the project isn't created successfully and throws exception;
     */

    @Test
    void ensureProjectIsNotCreated() {
        //Arrange
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        CustomerId customerIdDouble = mock(CustomerId.class);
        BusinessSectorId businessSectorIdDouble = mock(BusinessSectorId.class);
        ProjectTypologyId projectTypologyIdDouble = mock(ProjectTypologyId.class);
        when(projectRepositoryDouble.getProjectNumber()).thenReturn(1);
        when(projectRepositoryDouble.addProjectToProjectRepository(any())).thenReturn(false);

        Exception exception = assertThrows(Exception.class, () ->
                projectService.createProject(projectCreationDtoDouble, customerIdDouble,
                        businessSectorIdDouble, projectTypologyIdDouble));
        String expected = "Project not created";
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: addProject
     * scenario1: the project is added successfully
     */
    @Test
    void ensureProjectIsAdded() {
        //Arrange
        Project projectDouble = mock(Project.class);
        when(projectRepositoryDouble.addProjectToProjectRepository(any())).thenReturn(true);
        boolean expected = true;
        //Act
        boolean result = projectService.addProject(projectDouble);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario2: the project is not added
     */
    @Test
    void ensureProjectIsNotAdded() {
        //Arrange
        Project projectDouble = mock(Project.class);
        when(projectRepositoryDouble.addProjectToProjectRepository(any())).thenReturn(false);
        boolean expected = false;
        //Act
        boolean result = projectService.addProject(projectDouble);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method createCode
     * scenario1: the method returns number 1 when Repository is empty
     */
    @Test
    void ensureProjectCodeIsCreatedWithEmptyRepository() {
        //Arrange
        when(projectRepositoryDouble.getProjectNumber()).thenReturn(0);
        int expected = 1;
        //Act
        int result = projectService.createCode();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario2: the method returns number 2, when the Repository has one project
     */
    @Test
    void ensureProjectCodeIsCreatedWithRepositoryWithOneProject() {
        //Arrange
        when(projectRepositoryDouble.getProjectNumber()).thenReturn(1);
        int expected = 2;
        //Act
        int result = projectService.createCode();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: addToProductBacklog
     * scenario 1: it adds an usId to the ProductBacklog
     */
    @Test
    void ensureUsIdIsAddedSuccessfullyToProductBacklog() throws Exception {
        //Arrange
        boolean expected = true;
        UsId usIdDouble = mock(UsId.class);
        String projectCode = "P001";
        int priority = 1;
        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);
        when(projectRepositoryDouble.getProjectByCode(any())).thenReturn(optionalProject);
        when(projectDouble.addUserStory(priority, usIdDouble)).thenReturn(true);

        //Act
        boolean result = projectService.addUsToProductBacklog(usIdDouble, projectCode, priority);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: doesn't add an usID to ProductBacklog, because the id is already in the product backlog
     */
    @Test
    void ensureUsIdIsNotAddedSuccessfullyToProductBacklog() {
        //Arrange
        UsId usIdDouble = mock(UsId.class);
        String projectCode = "P001";
        int priority = 1;
        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);
        when(projectRepositoryDouble.getProjectByCode(any())).thenReturn(optionalProject);
        when(projectDouble.addUserStory(priority, usIdDouble)).thenReturn(false);
        Exception exception = assertThrows(Exception.class, () ->
                projectService.addUsToProductBacklog(usIdDouble, projectCode, priority));
        String expected = "The User Story is already in the Product Backlog";
        //Act
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: doesn't add an usID to ProductBacklog, because the projectCode doesn't match any project
     */
    @Test
    void ensureUsIdIsNotAddedSuccessfullyToProductBacklog_NoProject() {
        //Arrange
        UsId usIdDouble = mock(UsId.class);
        String projectCode = "P001";
        int priority = 1;
        Optional<Project> optionalProject = Optional.empty();
        when(projectRepositoryDouble.getProjectByCode(any())).thenReturn(optionalProject);
        Exception exception = assertThrows(Exception.class, () ->
                projectService.addUsToProductBacklog(usIdDouble, projectCode, priority));
        String expected = "No project with that code";
        //Act
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: getProjectByCode
     * scenario 1: returns an optional
     */
    @Test
    void ensureProjectIsRetrieved() {
        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);
        when(projectRepositoryDouble.getProjectByCode(any())).thenReturn(optionalProject);
        //Act
        Optional<Project> result = projectService.getProjectByCode("P001");
        //Assert
        assertEquals(optionalProject, result);
    }

    /**
     * Method: getProductBacklog
     * scenario 1: returns a list of usId
     */
    @Test
    void ensureProductBacklogIsRetrievedSuccessfully() throws Exception {
        //Arrange
        UsId usIdDouble = mock(UsId.class);
        UsId usIdDoubleTwo = mock(UsId.class);
        List<UsId> expected = new ArrayList<>();
        expected.add(usIdDoubleTwo);
        expected.add(usIdDouble);
        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);
        when(projectRepositoryDouble.getProjectByCode(any())).thenReturn(optionalProject);
        when(projectDouble.getProductBacklog()).thenReturn(expected);
        //Act
        List<UsId> result = projectService.getProductBacklog("P001");
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 2: returns an empty list
     */
    @Test
    void ensureProductBacklogIsRetrievedSuccessfully_EmptyList() throws Exception {
        //Arrange
        List<UsId> expected = new ArrayList<>();
        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);
        when(projectRepositoryDouble.getProjectByCode(any())).thenReturn(optionalProject);
        when(projectDouble.getProductBacklog()).thenReturn(expected);
        //Act
        List<UsId> result = projectService.getProductBacklog("P001");
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 3: unable to get a productBacklog because code doesn't match any project
     */
    @Test
    void ensureProductBacklogIsRetrievedUnsuccessfully() {
        //Arrange
        Optional<Project> optionalProject = Optional.empty();
        when(projectRepositoryDouble.getProjectByCode(any())).thenReturn(optionalProject);
        Exception exception = assertThrows(Exception.class, () ->
                projectService.getProductBacklog("P001"));
        String expected = "No project with that code";
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    // Integration testes: ProjectService + Project + ProjectRepository

    /**
     * METHOD addUserStory(priority, usId) adds a userStoryId to the productBacklog of a project.
     * <p>
     * Scenario 1: Adds a user Story because all arguments are valid.
     *
     * @throws Exception if User Story is already added or project doesn't exist.
     *                   Should return TRUE.
     */
    @Test
    void ensureUserStoryIsAddedToProductBacklog() throws Exception {
        //Arrange
        UsId usId = mock(UsId.class);
        String projectCode = "P2";
        int priority = 0;
        when(productBacklogDouble.addUserStory(priority, usId)).thenReturn(true);

        //Act
        boolean result = projectServiceTwo.addUsToProductBacklog(usId, projectCode,
                priority);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Doesn't add User Story because it is already there. Should throw an
     * exception.
     */
    @Test
    void ensureUserStoryIsNotAddedToProductBacklogBecauseItISAlreadyThere() {
        //Arrange
        UsId usId = mock(UsId.class);
        String projectCode = "P2";
        int priority = 0;
        when(productBacklogDouble.addUserStory(priority, usId)).thenReturn(false);

        //Act and Assert
        assertThrows(Exception.class,
                () -> projectServiceTwo.addUsToProductBacklog(usId, projectCode,
                        priority));
    }

    /**
     * Scenario 3: Doesn't add User Story because project doesn't exist. Should throw an
     * exception.
     */
    @Test
    void ensureThatUserStoryIsNotAddedToProductBacklogBecauseProjectDoesNotExist() {
        //Arrange
        UsId usId = mock(UsId.class);
        String projectCode = "P1";
        int priority = 0;

        //Act and Assert
        assertThrows(Exception.class,
                () -> projectServiceTwo.addUsToProductBacklog(usId, projectCode,
                        priority));
    }

    /**
     * METHOD getProductBacklog(String code) returns a list of UsID from the ProductBacklog of a Project.
     * <p>
     * Scenario 1: Returns a list of UsID.
     *
     * @throws Exception if there is no project in the product backlog with corresponding to the specified code.
     *                   Should return a list of UsId.
     */
    @Test
    void ensureProductBacklogIsReturned() throws Exception {
        //Arrange
        String projectCode = "P2";
        UsId usId = mock(UsId.class);
        List<UsId> expected = new ArrayList<>();
        expected.add(usId);
        when(productBacklogDouble.getUserStories()).thenReturn(expected);

        //Act
        List<UsId> result = projectServiceTwo.getProductBacklog(projectCode);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Returns an empty list of UsID .
     *
     * @throws Exception if there is no project in the product backlog with corresponding to the specified code.
     * Should return an empty list of UsId.
     */
    @Test
    void ensureProductBacklogIsReturnedWithAnEmptyList() throws Exception {
        //Arrange
        String projectCode = "P2";
        List<UsId> expected = new ArrayList<>();
        when(productBacklogDouble.getUserStories()).thenReturn(expected);

        //Act
        List<UsId> result = projectServiceTwo.getProductBacklog(projectCode);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Does not return a productBacklog because code doesn't match any project.
     *
     * Should throw an exception.
     */
    @Test
    void ensureThatAnExceptionIsThrownIfProjectCodeDoesNotExist() {
        //Arrange
        String projectCode = "P3";

        //Act and Assert
        assertThrows(Exception.class, () -> projectServiceTwo.getProductBacklog(projectCode)) ;
    }

}