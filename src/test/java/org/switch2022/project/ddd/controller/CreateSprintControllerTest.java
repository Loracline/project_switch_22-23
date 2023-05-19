package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.application.CreateSprintService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        classes = CreateSprintController.class)
class CreateSprintControllerTest {
    @InjectMocks
    CreateSprintController controller;

    @MockBean
    CreateSprintService service;

    /**
     * Method: createSprint
     * return:sprintId
     */

    @Test
    void ensureSprintIsCreated() throws Exception {
        //Arrange
        String expected = "p001_s001";
        String projectCode = "p001";
        String startDate = "2020-04-12";
        when(service.createSprint(projectCode,startDate)).thenReturn(expected);
        //Act
        String result = controller.createSprint(projectCode,startDate);
        //Assert
        assertEquals(expected, result);
    }
}