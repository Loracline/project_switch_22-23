package org.switch2022.project.ddd.webcontrollers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.ddd.application.CreateSprintService;
import org.switch2022.project.ddd.dto.SprintCreationDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        classes = SprintWebController.class)
class SprintWebControllerTest {
    @MockBean
    CreateSprintService createSprintService;
    @InjectMocks
    SprintWebController sprintWebController;

    @Test
    void ensureThatSprintIsCreated() throws Exception {

        String projectCode = "P001";
        String startDate = "2024-04-23";
        String sprintCode = "SP003";
        SprintCreationDto sprintCreationDto = new SprintCreationDto(projectCode, startDate);

        when(createSprintService.createSprint(projectCode, startDate)).thenReturn
        (sprintCode);

        ResponseEntity<Object> responseEntity =
                sprintWebController.createSprint(sprintCreationDto);

        assertEquals(responseEntity.getStatusCodeValue(),201);
        Object res = responseEntity.getBody();
        assertEquals(sprintCode, res);
    }

    @Test
    void ensureThatSprintIsNotCreated() throws Exception {

        String projectCode = "P001";
        String startDate = "2024-04-23";
        SprintCreationDto sprintCreationDto = new SprintCreationDto(projectCode, startDate);

        when(createSprintService.createSprint(projectCode, startDate)).thenThrow(new Exception("Failed to create sprint"));

        ResponseEntity<Object> responseEntity =
                sprintWebController.createSprint(sprintCreationDto);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Failed to create sprint", responseEntity.getBody());
    }
}