package org.switch2022.project.ddd.webcontrollers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.ddd.application.ProjectCreationService;
import org.switch2022.project.ddd.application.ProjectListService;
import org.switch2022.project.ddd.application.ProjectService;
import org.switch2022.project.ddd.dto.ProjectCreationDto;
import org.switch2022.project.ddd.dto.ProjectDto;
import org.switch2022.project.ddd.dto.UserStoryDto;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        classes = ProjectWebController.class)
class ProjectWebControllerTest {

    @InjectMocks
    ProjectWebController projectWebController;

    @MockBean
    ProjectCreationService projectCreationService;

    @MockBean
    ProjectService projectService;

    @MockBean
    ProjectListService projectListService;


    /**
     * Method: createProject()
     * Scenario 1: Test case to verify that a project is created
     * successfully.
     */
    @Test
    void ensureThatProjectIsCreated() {
        // Arrange
        String projectCode = "P001";
        ProjectCreationDto projectCreationDto = new ProjectCreationDto("panic",
                "panic total", "isep", "isep", "isep",
                5);

        when(projectCreationService.createProject(any())).thenReturn("P001");

        //Act
        ResponseEntity<Object> responseEntity =
                projectWebController.createProject(projectCreationDto);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);
        Object res = responseEntity.getBody();
        assertEquals(res, projectCode);
        assertEquals("P001", projectCreationService.createProject(projectCreationDto));
    }


    /**
     * Scenario 2: Test case to verify that a project is not created.
     */
    @Test
    void ensureThatProjectIsNotCreated() {
        // Arrange
        ProjectCreationDto projectCreationDto = new ProjectCreationDto("panic",
                "panic total", "isep", "isep", "isep",
                5);
        when(projectCreationService.createProject(any())).thenThrow(new RuntimeException("Failed to create project"));

        // Act
        ResponseEntity<Object> responseEntity =
                projectWebController.createProject(projectCreationDto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Failed to create project", responseEntity.getBody());
    }

    /**
     * Method: listAllProjects()
     * Scenario 1: returns a list of ProjectDto successfully.
     */

    @Test
    void ensureThatAListOfProjectsDtoIsReturned() {
        // Arrange
        List<ProjectDto> expected = new ArrayList<>();
        ProjectDto projectDtoDouble = mock(ProjectDto.class);
        expected.add(projectDtoDouble);
        when(projectListService.requestAllProjects()).thenReturn(expected);

        // Act
        ResponseEntity<List<ProjectDto>> responseEntity =
                projectWebController.listAllProjects();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<ProjectDto> actual = responseEntity.getBody();
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    /**
     * Scenario 2: returns an empty list when there are no projects.
     */
    @Test
    void ensureThatAnEmptyListIsReturnedWhenThereAreNoProjects() {
        // Arrange
        when(projectListService.requestAllProjects()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<ProjectDto>> responseEntity =
                projectWebController.listAllProjects();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<ProjectDto> actual = responseEntity.getBody();
        assertNotNull(actual);
        assertTrue(actual.isEmpty());
    }

    /**
     * Method: getProductBacklog()
     * Scenario 1: retrieves a list of user stories for a specific project
     */
    @Test
    void ensureProductBacklogIsRetrieved() {
        // Arrange
        String projectCode = "P001";
        List<UserStoryDto> expectedUserStories = new ArrayList<>();

        when(projectService.getProductBacklog(projectCode)).thenReturn(expectedUserStories);

        // Act
        ResponseEntity<List<UserStoryDto>> responseEntity =
                projectWebController.getProductBacklog(projectCode);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<UserStoryDto> actualUserStories = responseEntity.getBody();
        assertNotNull(actualUserStories);
        assertEquals(expectedUserStories, actualUserStories);
    }

    /**
     * Scenario 2: returns an empty list if no user stories are associated with the project code.
     */
    @Test
    void ensureThatEmptyListIsReturnedForValidProjectWithNoUserStories() {
        // Arrange
        String projectCode = "P002";
        List<UserStoryDto> expectedUserStories = new ArrayList<>();

        when(projectService.getProductBacklog(projectCode)).thenReturn(expectedUserStories);

        // Act
        ResponseEntity<List<UserStoryDto>> responseEntity =
                projectWebController.getProductBacklog(projectCode);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<UserStoryDto> actualUserStories = responseEntity.getBody();
        assertNotNull(actualUserStories);
        assertTrue(actualUserStories.isEmpty());
    }
    
}

