package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.project.IFactoryProject;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.BusinessSectorId;
import org.switch2022.project.ddd.domain.value_object.CustomerId;
import org.switch2022.project.ddd.domain.value_object.ProjectTypologyId;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.ProjectCreationDto;
import org.switch2022.project.ddd.exceptions.ProjectNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ProjectServiceTest.class
)
class ProjectServiceTest {
    /**
     * BeforeEach execute common code before running the
     * tests below.
     */
    @InjectMocks
    ProjectService projectService;
    @MockBean
    IFactoryProject factoryProject;
    @MockBean
    IProjectRepository projectRepository;
    @MockBean
    IUsRepository usRepository;

    /*
    IFactoryProject factoryProjectDouble;
    IProjectRepository projectRepositoryDouble;
    IFactoryProductBacklog factoryProductBacklogDouble;
    Project projectOne;
    ProjectCreationDto projectCreationDto;
    BusinessSectorId businessSectorIdDouble;
    CustomerId customerIdDouble;
    ProjectTypologyId projectTypologyIdDouble;
    Code code;
    ProductBacklog productBacklogDouble;*/

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);/*
        factoryProjectDouble = mock(IFactoryProject.class);
        projectRepositoryDouble = mock(IProjectRepository.class);
        factoryProductBacklogDouble = mock(IFactoryProductBacklog.class);
        //projectService = new ProjectService(factoryProjectDouble,
                //projectRepositoryDouble,
                //factoryProductBacklogDouble);

        factoryProject = new FactoryProject();
        projectRepository = new ProjectRepository();
        //projectServiceTwo = new ProjectService(factoryProject, projectRepository,
                //factoryProductBacklogDouble);

        code = new Code(1);
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

        projectRepository.addProjectToProjectRepository(projectOne);*/
    }

    /**
     * Method: createProject(projectCreationDto, customerId,businessSectorId,
     * projectTypologyId)
     *
     * scenario1: the project is created successfully. This always happens as all ids
     * are uniques and generated automatically.
     */
    @Test
    void ensureProjectIsCreated() throws Exception {
        //Arrange
        ProjectCreationDto projectCreationDtoDouble = mock(ProjectCreationDto.class);
        CustomerId customerIdDouble = mock(CustomerId.class);
        BusinessSectorId businessSectorIdDouble = mock(BusinessSectorId.class);
        ProjectTypologyId projectTypologyIdDouble = mock(ProjectTypologyId.class);
        String expected = "p002";
        when(projectRepository.getProjectNumber()).thenReturn(1);
        when(projectRepository.addProjectToProjectRepository(any())).thenReturn(true);

        //Act
        String result = projectService.createProject(projectCreationDtoDouble, customerIdDouble,
                businessSectorIdDouble, projectTypologyIdDouble);
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
        when(projectRepository.addProjectToProjectRepository(any())).thenReturn(true);
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
        when(projectRepository.addProjectToProjectRepository(any())).thenReturn(false);
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
        when(projectRepository.getProjectNumber()).thenReturn(0);
        int expected = 1;
        //Act
        int result = projectService.calculateNextProjectNumber();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario2: the method returns number 2, when the Repository has one project
     */
    @Test
    void ensureProjectCodeIsCreatedWithRepositoryWithOneProject() {
        //Arrange
        when(projectRepository.getProjectNumber()).thenReturn(1);
        int expected = 2;
        //Act
        int result = projectService.calculateNextProjectNumber();
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
        when(projectRepository.getProjectByCode(any())).thenReturn(optionalProject);
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
    void ensureProductBacklogIsRetrievedSuccessfully()  {
        //Arrange
        UsId usIdDouble = mock(UsId.class);
        UsId usIdDoubleTwo = mock(UsId.class);
        List<UsId> expected = new ArrayList<>();
        expected.add(usIdDoubleTwo);
        expected.add(usIdDouble);
        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);
        when(projectRepository.getProjectByCode(any())).thenReturn(optionalProject);
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
    void ensureProductBacklogIsRetrievedSuccessfully_EmptyList() {
        //Arrange
        List<UsId> expected = new ArrayList<>();
        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);
        when(projectRepository.getProjectByCode(any())).thenReturn(optionalProject);
        when(projectDouble.getProductBacklog()).thenReturn(expected);
        //Act
        List<UsId> result = projectService.getProductBacklog("P001");
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 3: productBacklog is not retrieved because code doesn't match any project
     */
    @Test
    void ensureThatProductBacklogIsNotRetrievedBecauseCodeDoesNotMatchAnyProject() {
        //Arrange
        Optional<Project> optionalProject = Optional.empty();
        when(projectRepository.getProjectByCode(any())).thenReturn(optionalProject);
        ProjectNotFoundException exception = assertThrows(ProjectNotFoundException.class, () ->
                projectService.getProductBacklog("P001"));
        String expected = "No project with that code";
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: requestAllUserStory(List<UsId> usId).
     * Requests and return a list of User Stories.
     * <br>
     * Scenario 01: returns a list of User Stories that own the corresponding UsIds.
     */

    @Test
    void ensureThatAllUserStoriesAreReturned() throws Exception{
        //Arrange
        List<UsId> usIds = new ArrayList<>();
        UserStory userStory = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);

        List<UserStory> expected = new ArrayList<>();
        expected.add(userStory);
        expected.add(userStoryTwo);

        when(usRepository.getListOfUsWithMatchingIds(usIds)).thenReturn(expected);

        //Act
        List<UserStory> result = projectService.requestAllUserStories(usIds);

        //Assert
        assertEquals(expected, result);

    }

    /**
     * Scenario 02: returns an empty list when there are no UserStories.
     */

    @Test
    void ensureThatReturnsAnEmptyListIfThereAreNoUserStories()  {
        //Arrange
        List<UsId> usIds = new ArrayList<>();
        List<UserStory> expected = new ArrayList<>();
        when(usRepository.getListOfUsWithMatchingIds(usIds)).thenReturn(expected);

        //Act
        List<UserStory> result = projectService.requestAllUserStories(usIds);

        //Assert
        assertTrue(result.isEmpty());

    }

    // Integration testes: ProjectService + Project + ProjectRepository
/*
    *//**
     * METHOD createProject(projectCreationDto, customerId,businessSectorId,
     * projectTypologyId) creates a new Project and adds it to the repository, and
     * creates a ProjectCode using the projectRepository.
     *
     * Scenario 1: project is created successfully. This always happens as all ids are
     * uniques and generated automatically.Should return a String with the code from
     * the project just created.
     *//*
    @Test
    void ensureThatProjectIsCreatedSuccessfully() throws Exception {
        //Arrange
        String expected = "p002";
        //Act
        ProjectCreationDto projectCreationDto = new ProjectCreationDto("P001", "",
                "", "", "", 2);
        CustomerId customerIdDouble = new CustomerId(1);
        BusinessSectorId businessSectorIdDouble = new BusinessSectorId(2);
        ProjectTypologyId projectTypologyIdDouble = new ProjectTypologyId(2);
        String result = projectService.createProject(projectCreationDto,
                customerIdDouble, businessSectorIdDouble, projectTypologyIdDouble);

        //Assert
        assertEquals(expected, result);
    }*/

    /**
     * METHOD addUserStory(priority, usId) adds a userStoryId to the productBacklog of a project.
     * <p>
     * Scenario 1: Adds a user Story because all arguments are valid.
     *
     * @throws Exception if User Story is already added or project doesn't exist.
     *                   Should return TRUE.
      *//*
    @Test
    void ensureUserStoryIsAddedToProductBacklog() throws Exception {
        //Arrange
        UsId usId = mock(UsId.class);
        String projectCode = "p001";
        int priority = 0;
        when(productBacklogDouble.addUserStory(priority, usId)).thenReturn(true);

        //Act
        boolean result = projectService.addUsToProductBacklog(usId, projectCode,
                priority);

        //Assert
        assertTrue(result);
    }*/

    /**
     * Scenario 2: Doesn't add User Story because it is already there. Should throw an
     * exception.
     *//*
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

    *//**
     * Scenario 3: Doesn't add User Story because project doesn't exist. Should throw an
     * exception.
     *//*
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

    *//**
     * METHOD getProductBacklog(String code) returns a list of UsID from the ProductBacklog of a Project.
     * <p>
     * Scenario 1: Returns a list of UsID.
     *
     * @throws Exception if there is no project in the product backlog with corresponding to the specified code.
     *                   Should return a list of UsId.
     *//*
    @Test
    void ensureProductBacklogIsReturned() throws Exception {
        //Arrange
        String projectCode = "p001";
        UsId usId = mock(UsId.class);
        List<UsId> expected = new ArrayList<>();
        expected.add(usId);
        when(productBacklogDouble.getUserStories()).thenReturn(expected);

        //Act
        List<UsId> result = projectServiceTwo.getProductBacklog(projectCode);

        //Assert
        assertEquals(expected, result);
    }

    *//**
     * Scenario 2: Returns an empty list of UsID .
     *
     * @throws Exception if there is no project in the product backlog with corresponding to the specified code.
     * Should return an empty list of UsId.
     *//*
    @Test
    void ensureProductBacklogIsReturnedWithAnEmptyList() throws Exception {
        //Arrange
        String projectCode = "p001";
        List<UsId> expected = new ArrayList<>();
        when(productBacklogDouble.getUserStories()).thenReturn(expected);

        //Act
        List<UsId> result = projectServiceTwo.getProductBacklog(projectCode);

        //Assert
        assertEquals(expected, result);
    }

    *//**
     * Scenario 3: Does not return a productBacklog because code doesn't match any project.
     *
     * Should throw an exception.
     *//*
    @Test
    void ensureThatAnExceptionIsThrownIfProjectCodeDoesNotExist() {
        //Arrange
        String projectCode = "p002";

        //Act and Assert
        assertThrows(Exception.class, () -> projectServiceTwo.getProductBacklog(projectCode)) ;
    }*/
}