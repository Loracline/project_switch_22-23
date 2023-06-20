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
import org.switch2022.project.ddd.dto.ProjectCodeStringDto;
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
        ProjectCreationDto projectCreationDto = new ProjectCreationDto("panic",
                "panic total", "BS001", "242526272", "TP001");

        when(projectCreationService.createProject(any())).thenReturn(new ProjectCodeStringDto(
                "P001"));

        // Act
        ResponseEntity<Object> responseEntity =
                projectWebController.createProject(projectCreationDto);

        // Assert
        assertEquals(201, responseEntity.getStatusCodeValue());
        ProjectCodeStringDto responseBody = (ProjectCodeStringDto) responseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals("P001", responseBody.getCode());
        assertEquals("http://localhost/projects/P001", responseBody.getLink("self").get().getHref());
    }


    /**
     * Scenario 2: Test case to verify that a project is not created.
     */
    @Test
    void ensureThatProjectIsNotCreated() {
        // Arrange
        ProjectCreationDto projectCreationDto = new ProjectCreationDto("panic",
                "panic total", "BS001", "242526272", "TP001");
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
        expectedUserStories.add(new UserStoryDto("us001",
                "I love chocolate","Planned"));

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

    /**
     * Method getProject(code)
     *
     * Scenario: project is retrieved
     */

    @Test
    void ensureThatProjectIsRetrievedSuccessfully() {
        // Arrange
        ProjectDto projectDto = mock(ProjectDto.class);
        when(projectService.getProjectDto("P001")).thenReturn(projectDto);

        // Act
        ResponseEntity<ProjectDto> responseEntity = projectWebController.getProject("P001");

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ProjectDto realProjectDto = responseEntity.getBody();
        assertEquals(projectDto, realProjectDto);
    }
}

