package org.switch2022.project.ddd.webcontrollers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.ddd.application.CreateSprintService;

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
        String startDate = "23/03/2024";
        String sprintCode = "SP003";

        when(createSprintService.createSprint(projectCode, startDate)).thenReturn
        (sprintCode);

        ResponseEntity<Object> responseEntity =
                sprintWebController.createSprint(projectCode, startDate);

        assertEquals(responseEntity.getStatusCodeValue(),201);
        Object res = responseEntity.getBody();
        assertEquals(res, sprintCode);
    }

    @Test
    void ensureThatSprintIsNotCreated() throws Exception {

        String projectCode = "P001";
        String startDate = "23/03/2024";
        String sprintCode = "SP003";

        when(createSprintService.createSprint(projectCode, startDate)).thenReturn
                (sprintCode);

        ResponseEntity<Object> responseEntity =
                sprintWebController.createSprint(projectCode, startDate);

        assertEquals(responseEntity.getStatusCodeValue(),201);
        Object res = responseEntity.getBody();
        assertEquals(res, sprintCode);
    }
}