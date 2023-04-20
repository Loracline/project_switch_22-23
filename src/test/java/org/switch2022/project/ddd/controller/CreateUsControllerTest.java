package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.application.ProjectService;
import org.switch2022.project.ddd.application.UsService;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.project.*;
import org.switch2022.project.ddd.domain.model.user_story.FactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.IFactoryUserStory;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.ProjectCreationDto;
import org.switch2022.project.ddd.dto.ProjectDto;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;
import org.switch2022.project.ddd.dto.mapper.UserStoryMapper;
import org.switch2022.project.ddd.infrastructure.ProjectRepository;
import org.switch2022.project.ddd.infrastructure.UsRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*
class CreateUsControllerTest {
    */
/**
     * BeforeEach and AfterEach executes common code before/after running
     * the tests below.
     *//*


    CreateUsController mockCreateUsController;
    UsService mockUsService;
    ProjectService mockProjectService;
    ProjectDto projectDto;
    UserStoryCreationDto userStoryCreationDto;
    UserStoryCreationDto userStoryCreationDtoOne;
    ProjectDto projectDtoOne;

    UsService usService;
    IUsRepository usRepository;
    IFactoryUserStory factoryUserStory;
    ProjectService projectService;
    IProjectRepository projectRepository;
    IFactoryProject factoryProject;
    CreateUsController createUsController;

    @BeforeEach
    void setUp() {
        //Services implemented
        mockUsService = mock(UsService.class);
        mockProjectService = mock(ProjectService.class);

        //Dto
        projectDto = mock(ProjectDto.class);
        userStoryCreationDto = mock(UserStoryCreationDto.class);
        userStoryCreationDtoOne = new UserStoryCreationDto("US001",
                "I want to create a project", "Manager", 0);
        projectDtoOne = new ProjectDto("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");


        //Controller
        mockCreateUsController = new CreateUsController(mockProjectService, mockUsService);


        // Non-mocked objects
        usRepository = new UsRepository();
        factoryUserStory = new FactoryUserStory();
        UserStoryMapper userStoryMapper = new UserStoryMapper();
        usService = new UsService(usRepository, factoryUserStory, userStoryMapper);

        factoryProject = new FactoryProject();
        projectRepository = new ProjectRepository();
        IFactoryProductBacklog factoryProductBacklog = new FactoryProductBacklog();
        projectService = new ProjectService(factoryProject, projectRepository,
                factoryProductBacklog);

        createUsController = new CreateUsController(projectService, usService);
    }

    @AfterEach
    void tearDown() {

        mockCreateUsController = null;
        mockUsService = null;
        mockProjectService = null;
        projectDto = null;
        userStoryCreationDto = null;
        projectDtoOne = null;

    }

    */
/**
     * Method: createUs()
     * Scenario 1: Tests the behavior of the createUs method in the  CreateUsController class
     * when a null ProjectDto object is passed as input.
     * The method should throw an IllegalArgumentExceptionwith a message indicating that the
     * input parameters cannot be null.
     * The test first arranges the input by initializing the expected error message. Then it acts
     * by invoking the method with a null ProjectDto object and verifying that an
     * IllegalArgumentException with the expected message is thrown. Finally, it asserts that the
     * exception message matches the expected message.
     *
     * @throws IllegalArgumentException if the input parameters are null.
     *//*


    @Test
    void ensureCreateUsCopyThrowsExceptionWithNullProjectDto() {
        // Arrange
        String expectedMessage = "Input parameters cannot be null.";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                mockCreateUsController.createUs(null, userStoryCreationDto));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());

    }

    */
/**
     * Scenario 2: Tests the behavior of the createUs method in the CreateUsController class when
     * a null
     * UserStoryCreationDto object is passed as input. The method should throw an
     * IllegalArgumentException with
     * a message indicating that the input parameters cannot be null.
     *
     * @throws IllegalArgumentException if the input parameters are null.
     *//*

    @Test
    void ensureCreateUsThrowsExceptionWithNullUserStoryCreationDto() {
        // Arrange
        UsService usService = mock(UsService.class);
        CreateUsController createUsController = new CreateUsController(mockProjectService,
                usService);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                createUsController.createUs(projectDto, null));

        // Assert
        assertEquals("Input parameters cannot be null.", exception.getMessage());
    }

    */
/**
     * Scenario 3: This test verifies the behavior of the createUs method in the
     * CreateUsController class when a valid UserStoryCreationDto object is passed as input.
     * The method should successfully create a User Story and return true. The test arranges the
     * input by creating mock objects for the UsService and ProjectService, and setting up the appropriate
     * mock method behavior to return a UsId and true when called.
     * Then it acts by invoking the method and verifying that it returns true. Finally, it asserts that the
     * result is true.
     *//*

    @Test
    void ensureUserStoryIsCreatedSuccessfullyWithIsolation() throws Exception {
        // Arrange

        UsId usId = mock(UsId.class);
        when(mockUsService.createUs(userStoryCreationDto, projectDto.getProjectCode())).thenReturn(usId);
        when(mockProjectService.addUsToProductBacklog(usId, projectDto.getProjectCode(), 0)).thenReturn(true);

        // Act
        boolean result = mockCreateUsController.createUs(projectDto, userStoryCreationDto);

        // Assert
        assertTrue(result);
    }

    */
/**
     * Scenario 4: This test verifies if a user story is successfully created when the product
     * backlog is empty.
     *
     * @throws Exception if an error occurs during the test.
     *//*

    @Test
    void ensureUserStoryIsCreatedSuccessfullyEmptyProductBacklog() throws Exception {
        //Act
        boolean result = mockCreateUsController.createUs(projectDtoOne, userStoryCreationDtoOne);
        //Assert
        assertTrue(result);
    }

    */
/**
     * Scenario 5: This test verifies that an exception is thrown and caught when adding a new
     * user story to the product backlog fails during the process of creating a new user story. In this
     * scenario, the createUs() method should also throw an exception.
     *//*

    @Test
    void ensureCreateUsThrowsExceptionWhenAddingToProductBacklogFails() throws Exception {
        // Arrange
        UsId usId = mock(UsId.class);
        when(mockUsService.createUs(userStoryCreationDto, projectDto.getProjectCode())).thenReturn(usId);
        when(mockProjectService.addUsToProductBacklog(usId, projectDto.getProjectCode(), 0)).thenThrow(new Exception());

        // Act & Assert
        assertThrows(Exception.class, () -> mockCreateUsController.createUs(projectDto,
                userStoryCreationDto));
    }

    */
/**
     * Scenario 6: This test checks if a user story is deleted when adding it to the product
     * backlog fails.
     * It verifies that the createUs() method throws an exception and deletes the user story when
     * the
     * ProjectService#addToProductBacklog() method is called with the user story ID, project
     * code, and
     * position 0 in the backlog, and an exception is thrown with the message "Adding to product
     * backlog failed".
     *//*

    @Test
    void ensureUsIsDeletedWhenAddingToProductBacklogFails() throws Exception {
        // Arrange
        UsId usId = mock(UsId.class);
        when(mockUsService.createUs(userStoryCreationDto, projectDto.getProjectCode())).thenReturn(usId);
        when(mockProjectService.addUsToProductBacklog(usId, projectDto.getProjectCode(), 0))
                .thenThrow(new Exception("Adding to product backlog failed"));

        // Act and Assert
        assertThrows(Exception.class, () -> mockCreateUsController.createUs(projectDto,
                userStoryCreationDto));
        verify(mockUsService, times(1)).deleteUs(usId);
    }

    // Acceptance Criteria Tests

    */
/**
     * Method: createUs()
     * Scenario 1: Fails to create a US due to insufficient/invalid information, like nonexistent
     * project.
     * It should return true.
     *//*

    @Test
    void ensureUserStoryIsNotCreatedDueToInvalidInformation() throws Exception {
        //ARRANGE
        ProjectDto projectDtoOne = new ProjectDto("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");

        //ACT
        boolean result = mockCreateUsController.createUs(projectDtoOne, userStoryCreationDto);

        //ASSERT
        assertTrue(result);
    }

    */
/**
     * Scenario 2: Fails to create a US because it has the same number of another in a non-empty
     * backlog.
     * It should throw an exception with the message "The User Story is already in the Product
     * Backlog".
     *//*

    @Test
    void ensureUserStoryIsNotCreated_UserStoryRepeated() throws Exception {
        //ARRANGE
        ProjectCreationDto projectCreationDto = new ProjectCreationDto("projectName",
                "projectDescription", "businessSectorName",
                "customerName", "typologyName", 2);

        Project project = factoryProject.createProject(new Code(1), projectCreationDto,
                new BusinessSectorId(1), new CustomerId(1),
                new ProjectTypologyId(1), new FactoryProductBacklog());

        projectRepository.addProjectToProjectRepository(project);

        UsId usId = new UsId("P001", "US001");
        projectService.addUsToProductBacklog(usId, "P001", 0);

        ProjectDto projectDto = new ProjectDto("P001", null, null, null,
                null, null);

        String expectedMessage = "The User Story is already in the Product Backlog";

        //ACT
        Exception exception = assertThrows(Exception.class, () ->
                createUsController.createUs(projectDto, userStoryCreationDtoOne));

        //ASSERT
        assertEquals(expectedMessage, exception.getMessage());
    }

    */
/**
     * Scenario 3: Create US and add it to an empty backlog.
     * It should return true.
     *//*

    @Test
    void ensureUserStoryIsCreatedAndAddedToEmptyBacklog() throws Exception {
        //ARRANGE
        ProjectCreationDto projectCreationDto = new ProjectCreationDto("projectName",
                "projectDescription", "businessSectorName",
                "customerName", "typologyName", 2);

        Project project = factoryProject.createProject(new Code(1), projectCreationDto,
                new BusinessSectorId(1), new CustomerId(1),
                new ProjectTypologyId(1), new FactoryProductBacklog());

        projectRepository.addProjectToProjectRepository(project);

        ProjectDto projectDto = new ProjectDto("P001", null, null, null,
                null, null);

        //ACT
        boolean result = createUsController.createUs(projectDto, userStoryCreationDtoOne);

        //ASSERT
        assertTrue(result);
    }
}

*/
