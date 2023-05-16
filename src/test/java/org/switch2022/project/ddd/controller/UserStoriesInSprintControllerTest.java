package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.application.UserStoriesInSprintService;
import org.switch2022.project.ddd.dto.UserStoryDto;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest(
        classes = UserStoriesInSprintController.class)
class UserStoriesInSprintControllerTest {

    @InjectMocks
    UserStoriesInSprintController controller;

    @MockBean
    UserStoriesInSprintService userStoriesInSprintService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Method: getScrumBoard
     * Scenario 1: returns a list of UserStoryDto objects
     */
    @Test
    void ensureThatAListOfUserStoryDtoIsReturned() {
        // Arrange
        String projectCode = "ABC";
        LocalDate date = LocalDate.now();
        List<UserStoryDto> expected = new ArrayList<>();
        UserStoryDto userStoryDtoDouble = mock(UserStoryDto.class);
        expected.add(userStoryDtoDouble);
        when(userStoriesInSprintService.getScrumBoard(projectCode, date)).thenReturn(expected);

        // Act
        List<UserStoryDto> result = controller.getScrumBoard(projectCode, date);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verifies that an empty list of UserStoryDto is returned.
     */
    @Test
    void ensureThatAListOfUserStoryDtoIsReturnedEmpty() {
        // Arrange
        String projectCode = "ABC";
        LocalDate date = LocalDate.now();
        List<UserStoryDto> expected = new ArrayList<>();
        when(userStoriesInSprintService.getScrumBoard(projectCode, date)).thenReturn(expected);

        // Act
        List<UserStoryDto> result = controller.getScrumBoard(projectCode, date);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Verifies that an InvalidInputException is thrown when the projectCode is null.
     */
    @Test
    void ensureThatInvalidInputExceptionIsThrownForNullProjectCode() {
        // Arrange
        String projectCode = null;
        LocalDate date = LocalDate.now();

        // Act and Assert
        assertThrows(InvalidInputException.class, () ->
                controller.getScrumBoard(projectCode, date));
    }

    /**
     * Scenario 4: Verifies that an InvalidInputException is thrown when the date is null.
     */
    @Test
    void ensureThatInvalidInputExceptionIsThrownForNullDate() {
        // Arrange
        String projectCode = "ABC";
        LocalDate date = null;

        // Act and Assert
        assertThrows(InvalidInputException.class, () ->
                controller.getScrumBoard(projectCode, date));
    }

    /**
     * Scenario 5: Verifies that an InvalidInputException is thrown when the projectCode is empty.
     */
    @Test
    void ensureThatInvalidInputExceptionIsThrownForEmptyProjectCode() {
        // Arrange
        String projectCode = "";
        LocalDate date = LocalDate.now();

        // Act and Assert
        assertThrows(InvalidInputException.class, () ->
                controller.getScrumBoard(projectCode, date));
    }
}

