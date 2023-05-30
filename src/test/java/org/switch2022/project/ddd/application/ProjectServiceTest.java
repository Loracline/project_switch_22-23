package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.customer.ICustomerRepository;
import org.switch2022.project.ddd.domain.model.project.IFactoryProject;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.ProjectDto;
import org.switch2022.project.ddd.dto.UserStoryDto;
import org.switch2022.project.ddd.dto.mapper.ProjectMapper;
import org.switch2022.project.ddd.dto.mapper.UserStoryMapper;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;
import org.switch2022.project.ddd.exceptions.ProjectNotFoundException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ProjectService.class
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
    @Qualifier("project_jpa")
    IProjectRepository projectRepository;
    @MockBean
    IUsRepository usRepository;
    @MockBean
    ProjectMapper projectMapper;
    @MockBean
    @Qualifier("customer_jpa")
    ICustomerRepository customerRepository;
    @MockBean
    UserStoryMapper userStoryMapper;


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
     * Method: getProjectByCode
     * scenario 1: returns an optional
     */
    @Test
    void ensureProjectIsRetrieved() {
        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);
        when(projectRepository.findByCode(any())).thenReturn(optionalProject);
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
    void ensureProductBacklogIsRetrievedSuccessfully() {
        //Arrange
        UsId usIdDouble = mock(UsId.class);
        UsId usIdDoubleTwo = mock(UsId.class);
        List<UsId> usIds = Arrays.asList(usIdDoubleTwo, usIdDouble);

        UserStory userStoryOne = mock(UserStory.class);
        List<UserStory> userStories = Arrays.asList(userStoryOne);

        UserStoryDto userStoryDto = mock(UserStoryDto.class);
        List<UserStoryDto> expected = Arrays.asList(userStoryDto);
        List<UserStoryDto> userStoryDtos = Arrays.asList(userStoryDto);

        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);

        when(projectRepository.findByCode(any())).thenReturn(optionalProject);
        when(projectDouble.getProductBacklog()).thenReturn(usIds);
        when(usRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStories);
        when(userStoryOne.hasStatus(any())).thenReturn(true);
        when(userStoryMapper.userStoryToDtoList(userStories)).thenReturn(userStoryDtos);

        //Act
        List<UserStoryDto> result = projectService.getProductBacklog("P001");
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 2: returns an empty list
     */
    @Test
    void ensureProductBacklogIsRetrievedSuccessfully_EmptyList() {
        //Arrange
        List<UsId> usIds = Arrays.asList(mock(UsId.class));
        List<UserStory> expected = new ArrayList<>();
        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);
        when(projectRepository.findByCode(any())).thenReturn(optionalProject);
        when(projectDouble.getProductBacklog()).thenReturn(usIds);
        when(userStoryMapper.userStoryToDtoList(expected)).thenReturn(Collections.emptyList());

        //Act
        List<UserStoryDto> result = projectService.getProductBacklog("P001");

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
        when(projectRepository.findByCode(any())).thenReturn(optionalProject);
        ProjectNotFoundException exception = assertThrows(ProjectNotFoundException.class, () ->
                projectService.getProductBacklog("P001"));
        String expected = "No project with that code";
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: requestAllPlannedUserStories(List<UsId> usId).
     * Requests a list of User Stories with matching usIds and "planned" status.
     * <br>
     * Scenario 01: returns a list of User Stories that own the corresponding UsIds and
     * "planned" status.
     */

    @Test
    void ensureThatAllUserStoriesWithPlannedStatusAreReturned() {
        //Arrange
        List<UsId> usIds = new ArrayList<>();
        UserStory userStory = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);

        List<UserStory> expected = new ArrayList<>();
        expected.add(userStory);
        expected.add(userStoryTwo);

        when(usRepository.getListOfUsWithMatchingIds(usIds)).thenReturn(expected);
        when(userStory.hasStatus(any())).thenReturn(true);
        when(userStoryTwo.hasStatus(any())).thenReturn(true);

        //Act
        List<UserStory> result = projectService.requestAllPlannedUserStories(usIds);

        //Assert
        assertEquals(expected, result);

    }

    /**
     * Scenario 02: returns an empty list when there are no UserStories.
     */

    @Test
    void ensureThatReturnsAnEmptyListIfThereAreNoUserStories() {
        //Arrange
        List<UsId> usIds = new ArrayList<>();
        List<UserStory> expected = new ArrayList<>();
        when(usRepository.getListOfUsWithMatchingIds(usIds)).thenReturn(expected);

        //Act
        List<UserStory> result = projectService.requestAllPlannedUserStories(usIds);

        //Assert
        assertTrue(result.isEmpty());

    }

    /**
     * Scenario 03: check if returns an empty list when there are no UserStories with planned status.
     */
    @Test
    void ensureThatReturnsAnEmptyListBecauseThereAreNoUserStoriesWithPlannedStatus() {
        //Arrange
        List<UsId> usIds = new ArrayList<>();
        UserStory userStory = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);

        List<UserStory> expected = new ArrayList<>();

        when(usRepository.getListOfUsWithMatchingIds(usIds)).thenReturn(expected);
        when(userStory.hasStatus(any())).thenReturn(false);
        when(userStoryTwo.hasStatus(any())).thenReturn(false);

        //Act
        List<UserStory> result = projectService.requestAllPlannedUserStories(usIds);

        //Assert
        assertEquals(expected, result);

    }

    /**
     * Method getProjectDto(code)
     *
     * Scenario 1: Project dto is retrieved.
     */
    @Test
    void ensureThatProjectDtoISRetrievedSuccessfully() {
        // Arrange
        Project project = mock(Project.class);
        ProjectDto projectDto = mock(ProjectDto.class);
        Optional<Project> projectOptional = Optional.ofNullable(project);
        when(projectRepository.findByCode(any())).thenReturn(projectOptional);
        when(customerRepository.findCustomerNameByTaxId(any())).thenReturn("Isep");
        when(projectMapper.projectToDto(project, "Isep")).thenReturn(projectDto);

        // Act
        ProjectDto result = projectService.getProjectDto("P001");

        // Assert
        assertEquals(projectDto, result);
    }

    /**
     * Scenario 2: an exception is thrown because the project doesn't exist.
     */
    @Test
    void ensureThatAnExceptionIsThrownBecauseProjectDoesNotEXist() {
        // Arrange
        String message = "This project doesn't exist";
        Optional<Project> projectOptional = Optional.ofNullable(null);
        when(projectRepository.findByCode(any())).thenReturn(projectOptional);

        // Act
        NotFoundInRepoException exception = assertThrows(NotFoundInRepoException.class, () ->
                projectService.getProjectDto("P001"));

        // Assert
        assertEquals(message, exception.getMessage());
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