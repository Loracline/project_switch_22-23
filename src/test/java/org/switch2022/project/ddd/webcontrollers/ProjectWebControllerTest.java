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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
     * Scenario 01: Test case to verify that a project is created
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
     * Scenario 02: Test case to verify that a project is not created.
     */
    @Test
    void ensureThatProjectIsNotCreated() {
        // Arrange
        ProjectCreationDto projectCreationDto = new ProjectCreationDto("panic",
                "panic total", "isep", "isep", "isep",
                5);
        when(projectCreationService.createProject(any())).thenThrow(new RuntimeException("Failed to create project"));

        // Act
        ResponseEntity<Object> responseEntity = projectWebController.createProject(projectCreationDto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Failed to create project", responseEntity.getBody());
    }

}