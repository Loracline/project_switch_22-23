package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.project.IFactoryProductBacklog;
import org.switch2022.project.ddd.domain.model.project.IFactoryProject;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.ProjectCreationDto;

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
    IFactoryProject factoryProjectDouble;
    IProjectRepository projectRepositoryDouble;
    IFactoryProductBacklog factoryProductBacklogDouble;
    ProjectService projectService;

    @BeforeEach
    void setUp() {
        factoryProjectDouble = mock(IFactoryProject.class);
        projectRepositoryDouble = mock(IProjectRepository.class);
        factoryProductBacklogDouble = mock(IFactoryProductBacklog.class);
        projectService = new ProjectService(factoryProjectDouble, projectRepositoryDouble,
                factoryProductBacklogDouble);
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
        boolean result = projectService.addToProductBacklog(usIdDouble, projectCode, priority);
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
                projectService.addToProductBacklog(usIdDouble, projectCode, priority));
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
                projectService.addToProductBacklog(usIdDouble, projectCode, priority));
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
}