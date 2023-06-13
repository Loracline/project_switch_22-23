package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.sprint.ISprintRepository;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.dto.SprintStatusDto;
import org.switch2022.project.ddd.exceptions.InvalidInputException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SprintStatusChangeService.class
)
class SprintStatusChangeServiceTest {
    /**
     * BeforeEach execute common code before running the
     * tests below.
     */
    @InjectMocks
    SprintStatusChangeService service;

    @MockBean
    @Qualifier("sprint_jpa")
    ISprintRepository sprintRepository;

    @MockBean
    @Qualifier("us_jpa")
    IUsRepository userStoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Method: closeSprint(sprint)
     *
     * Scenario 1: does not close the sprint because it is already closed.
     */
    @Test
    void ensureThatSprintIsNotClosed() {
        //Arrange
        Sprint sprint = mock(Sprint.class);
        when(sprint.isOpen()).thenReturn(false);
        String message = "The sprint is already closed";

        //Act
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> service.closeSprint(sprint));

        //Assert
        assertEquals(message, exception.getMessage());
    }

    /**
     * Scenario 2: sprint is closed and no user story is changed.
     */
    @Test
    void ensureThatSprintIsClosedAndNoUserStoriesAreChanged() {
        //Arrange
        Sprint sprint = mock(Sprint.class);
        when(sprint.isOpen()).thenReturn(true);
        List<UserStory> userStories = new ArrayList<>();
        when(userStoryRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStories);
        when(sprint.changeStatus("CLOSED")).thenReturn(true);

        //Act
        boolean result = service.closeSprint(sprint);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 3: sprint is closed and user stories are changed.
     */
    @Test
    void ensureThatSprintIsClosedAndUserStoriesAreChanged() {
        //Arrange
        Sprint sprint = mock(Sprint.class);
        when(sprint.isOpen()).thenReturn(true);

        UserStory userStory = mock(UserStory.class);
        List<UserStory> userStories = new ArrayList<>();
        userStories.add(userStory);
        when(userStoryRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStories);
        when(userStory.hasStatus(any())).thenReturn(true);
        when(userStoryRepository.save(userStory)).thenReturn(true);

        when(sprint.changeStatus("CLOSED")).thenReturn(true);

        //Act
        boolean result = service.closeSprint(sprint);

        //Assert
        assertTrue(result);
    }

    /**
     * Method: changeSprintStatus(sprintStatusDto).
     *
     * Scenario 1: sprint status doesn't change because the sprint doesn't exist.
     */
    @Test
    void ensureThatTheSprintStatusDoesNotChangeBecauseTheSprintDoesNotExist() {
        //Arrange
        SprintStatusDto sprintStatusDto = mock(SprintStatusDto.class);
        when(sprintStatusDto.getSprintId()).thenReturn("P001_US001");

        String message = "This sprint doesn't exist";

        //Act
        NotFoundInRepoException exception = assertThrows(NotFoundInRepoException.class,
                () -> service.changeSprintStatus(sprintStatusDto));

        //Assert
        assertEquals(message, exception.getMessage());
    }

    /**
     * Scenario 2: sprint is closed.
     */
    @Test
    void ensureThatSprintIsClosed() {
        //Arrange
        //Change status
        SprintStatusDto sprintStatusDto = mock(SprintStatusDto.class);
        when(sprintStatusDto.getSprintId()).thenReturn("P001_US001");

        Sprint sprint = mock(Sprint.class);
        Optional<Sprint> sprintOptional = Optional.of(sprint);
        when(sprintRepository.findById(any())).thenReturn(sprintOptional);
        when(sprintStatusDto.getSprintStatus()).thenReturn("close");

        //Close Sprint
        when(sprint.isOpen()).thenReturn(true);
        List<UserStory> userStories = new ArrayList<>();
        UserStory userStory = mock(UserStory.class);
        userStories.add(userStory);
        when(userStoryRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStories);
        when(userStory.hasStatus(any())).thenReturn(true);
        when(userStoryRepository.save(userStory)).thenReturn(true);
        when(sprint.changeStatus("CLOSED")).thenReturn(true);

        //Act
        boolean result = service.changeSprintStatus(sprintStatusDto);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 3: sprint status doesn't change because the given status is not valid.
     */
    @Test
    void ensureThatSprintDoesNotChangeBecauseTheStatusIsNotValid() {
        ///Arrange
        SprintStatusDto sprintStatusDto = mock(SprintStatusDto.class);
        when(sprintStatusDto.getSprintId()).thenReturn("P001_US001");

        Sprint sprint = mock(Sprint.class);
        Optional<Sprint> sprintOptional = Optional.of(sprint);
        when(sprintRepository.findById(any())).thenReturn(sprintOptional);
        when(sprintStatusDto.getSprintStatus()).thenReturn("planned");

        String message = "Sprints can't have the given status";

        //Act
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> service.changeSprintStatus(sprintStatusDto));

        //Assert
        assertEquals(message, exception.getMessage());
    }
}