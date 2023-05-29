package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.application.ProjectCreationService;
import org.switch2022.project.ddd.dto.ProjectCreationDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CreateProjectController.class
)
class CreateProjectControllerTest {

    @InjectMocks
    CreateProjectController createProjectController;

    @MockBean
    ProjectCreationService createProjectService;

    /**
     * Method: createProject()
     * Scenario 01: The project is created successfully.
     * Expected return: project ID.
     */

    @Test
    void ensureProjectIsCreatedSuccessfully() {
        //Arrange
        ProjectCreationDto projectCreationDto = new ProjectCreationDto("panic",
                "panic total", "BS001", "242526272", "TP001");

        when(createProjectService.createProject(any())).thenReturn("P001");

        //Act
        String expected = "P001";
        String result = createProjectController.createProject(projectCreationDto);

        //Assert
        assertEquals(expected, result);
    }
}