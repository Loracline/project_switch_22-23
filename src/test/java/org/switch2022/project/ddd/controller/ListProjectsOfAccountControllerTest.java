package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.application.ProjectListAllocationService;
import org.switch2022.project.ddd.dto.ProjectDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        classes = ListProjectsOfAccountController.class
)
class ListProjectsOfAccountControllerTest {

    @InjectMocks
    ListProjectsOfAccountController controller;

    @MockBean
    ProjectListAllocationService service;


    @Test
    void ensureListOfProjectDtosIsRetrievedSuccessfully() {
        // Arrange
        String email = "example@isep.ipp.pt";

        ProjectDto projectDtoOne = mock(ProjectDto.class);
        ProjectDto projectDtoTwo = mock(ProjectDto.class);
        List<ProjectDto> expected = Arrays.asList(projectDtoOne, projectDtoTwo);

        when(service.listProjectsByAccount(email)).thenReturn(expected);

        // Act
        List<ProjectDto> result = controller.listProjectsByAccount(email);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatAnEmptyListOfProjectDtosIsRetrievedWhenThereAreNoProjectsCurrentlyOngoing() {
        // Arrange
        String email = "example@isep.ipp.pt";
        List<ProjectDto> expected = new ArrayList<>();

        // Act
        List<ProjectDto> result = controller.listProjectsByAccount(email);

        // Assert
        assertEquals(expected, result);
    }

}