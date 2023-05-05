package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.application.ProjectService;
import org.switch2022.project.ddd.dto.ProjectDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ProjectListController.class
)
class ProjectListControllerTest {

    @InjectMocks
    ProjectListController controller;

    @MockBean
    ProjectService projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Method: listAllProjects
     * Scenario 1: returns a list of ProjectDto
     */

    @Test
    void ensureThatAListOfProjectsDtoIsReturned() {
        //Arrange
        List<ProjectDto> expected = new ArrayList<>();
        ProjectDto projectDtoDouble = mock(ProjectDto.class);
        expected.add(projectDtoDouble);
        when(projectService.requestAllProjects()).thenReturn(expected);

        //Act
        List<ProjectDto> result = controller.ListAllProjects();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 2: returns an empty list
     */
    @Test
    void ensureThatAListOfProjectsDtoIsReturnedEmpty() {
        //Arrange
        List<ProjectDto> expected = new ArrayList<>();
        when(projectService.requestAllProjects()).thenReturn(expected);

        //Act
        List<ProjectDto> result = controller.ListAllProjects();
        //Assert
        assertEquals(expected, result);
    }
}