package org.switch2022.project.ddd.webcontrollers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.ddd.application.UsService;
import org.switch2022.project.ddd.application.UserStoriesInSprintService;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;
import org.switch2022.project.ddd.dto.UserStoryStatusDto;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        classes = UserStoryWebController.class)
class UserStoryWebControllerTest {
    @MockBean
    UsService usService;
    @InjectMocks
    UserStoryWebController userStoryWebController;

    @MockBean
    UserStoriesInSprintService usInSprintService;

    /**
     * Method: createUs()
     * Scenario 1: Test case to ensure that a user story is created successfully.
     * This test verifies the behavior of the createUs method in the UserStoryWebController class.
     * It validates that the controller properly handles the creation of a user story, verify
     * that the HTTP response status code is 201 (Created) and retrieve the response body and
     * ensure it matches the expected user story ID (usId).
     *
     * @throws Exception if an error occurs during the test execution.
     */
    @Test
    void ensureThatUserStoryIsCreated() {
        //ARRANGE
        UsId usId = mock(UsId.class);
        String acceptanceCriteria = "E-mail must have an @ and a dot";
        UserStoryCreationDto userStoryCreationDto = new UserStoryCreationDto("P001", "US001",
                "As a user, I want to be able to log in to the system.", "User",
                singletonList(acceptanceCriteria), 1);

        when(usService.createUs(userStoryCreationDto)).thenReturn
                (usId);
        //ACT
        ResponseEntity<Object> responseEntity =
                userStoryWebController.createUs(userStoryCreationDto);
        //ASSERT
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Object res = responseEntity.getBody();
        assertEquals(res, usId);
    }

    /**
     * Method: changeUserStoryStatus
     *
     */
    @Test
    void ensureThatUsStatusIsChanged() {
        //ARRANGE
        UserStoryStatusDto dtoDouble = mock(UserStoryStatusDto.class);
        String usId= "p001_us001";

        when(usInSprintService.changeUserStoryStatus(dtoDouble)).thenReturn(true);
        //ACT
        ResponseEntity<Object> responseEntity = userStoryWebController.changeUserStoryStatus(dtoDouble,usId);
        //ASSERT
        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
}
