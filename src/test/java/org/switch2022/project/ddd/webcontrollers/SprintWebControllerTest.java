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
import org.switch2022.project.ddd.application.SprintStatusChangeService;
import org.switch2022.project.ddd.application.UserStoriesInSprintService;
import org.switch2022.project.ddd.dto.AllocationDto;
import org.switch2022.project.ddd.dto.SprintCreationDto;
import org.switch2022.project.ddd.dto.SprintStatusDto;
import org.switch2022.project.ddd.dto.UserStoryDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = SprintWebControllerTest.class)
class SprintWebControllerTest {
    @MockBean
    CreateSprintService createSprintService;
    @MockBean
    UserStoriesInSprintService userStoriesInSprintService;
    @MockBean
    SprintStatusChangeService sprintStatusChangeService;
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

        assertEquals(responseEntity.getStatusCodeValue(), 201);
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


    /**
     * Method: getSprintBacklog()
     * Scenario 1: retrieves a list of user stories for a specific sprint.
     *
     * @returns a list of user stories.
     */
    @Test
    void ensureSprintBacklogIsRetrieved() {
        // Arrange
        String sprintId = "P001_S001";
        List<UserStoryDto> expectedUserStories = new ArrayList<>();
        expectedUserStories.add(new UserStoryDto("us001",
                "I love chocolate", "Planned"));

        when(userStoriesInSprintService.getSprintBacklog(any())).thenReturn(expectedUserStories);

        // Act
        ResponseEntity<List<UserStoryDto>> responseEntity =
                sprintWebController.getSprintBacklog(sprintId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<UserStoryDto> actualUserStories = responseEntity.getBody();
        assertNotNull(actualUserStories);
        assertEquals(expectedUserStories, actualUserStories);
    }

    /**
     * Scenario 2: returns an empty list if no user stories are associated with the sprint.
     *
     * @returns an empty list.
     */
    @Test
    void ensureThatEmptyListIsReturnedForValidProjectWithNoUserStories() {
        // Arrange
        String sprintId = "P001_S001";
        List<UserStoryDto> expectedUserStories = new ArrayList<>();

        when(userStoriesInSprintService.getSprintBacklog(sprintId)).thenReturn(expectedUserStories);

        // Act
        ResponseEntity<List<UserStoryDto>> responseEntity =
                sprintWebController.getSprintBacklog(sprintId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<UserStoryDto> actualUserStories = responseEntity.getBody();
        assertNotNull(actualUserStories);
        assertTrue(actualUserStories.isEmpty());
    }

    /**
     * Method: changeSprintStatus()
     * Scenario 1: changes the state of a specific sprint.
     */

    @Test
    void ensureThatStateOfSprintIsChanged() {
        //ARRANGE
        SprintStatusDto dtoDouble = mock(SprintStatusDto.class);

        when(sprintStatusChangeService.changeSprintStatus(dtoDouble)).thenReturn(true);
        //ACT
        ResponseEntity<Object> responseEntity = sprintWebController.changeSprintStatus(dtoDouble);
        //ASSERT
        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
}