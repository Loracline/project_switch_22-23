package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.sprint.ISprintRepository;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        classes = org.switch2022.project.ddd.application.AddUserStoryToSprintBacklogServiceTest.class)
class AddUserStoryToSprintBacklogServiceTest {

    @InjectMocks
    AddUserStoryToSprintBacklogService service;
    @MockBean
    private ISprintRepository sprintRepository;
    @MockBean
    private IUsRepository usRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Method: addUserStoryToSprintBacklog
     * scenario 1: Adds a US to an empty sprint backlog
     */
    @Test
    void ensureUserStoryIsAddedToEmptySprint() throws Exception {
        //Arrange
        String usId = "p001_us001";
        String sprintId = "p001_s001";
        UserStory userStoryDouble = mock(UserStory.class);
        List<UserStory> userStoryList = new ArrayList<>();
        userStoryList.add(userStoryDouble);
        Sprint sprintDouble = mock(Sprint.class);
        Optional<Sprint> sprintOptional = Optional.of(sprintDouble);
        when(usRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStoryList);
        when(userStoryDouble.hasStatus(any())).thenReturn(false);
        when(sprintRepository.getSprintById(any())).thenReturn(sprintOptional);
        when(sprintDouble.isPeriodAfterOrEqualThanDate(any())).thenReturn(false);
        when(sprintDouble.addUserStory(any(), any())).thenReturn(true);

        //Act
        boolean result = service.addUserStoryToSprintBacklog(usId, sprintId);
        //Assert
        assertTrue(result);
    }

    /**
     * scenario 2: Adds a US to a non-empty sprint backlog
     */
    @Test
    void ensureUserStoryIsAddedToSprint() throws Exception {
        //Arrange
        String usId = "p001_us001";
        String sprintId = "p001_s001";
        UserStory userStoryDouble = mock(UserStory.class);
        List<UserStory> userStoryList = new ArrayList<>();
        userStoryList.add(userStoryDouble);
        Sprint sprintDouble = mock(Sprint.class);
        Optional<Sprint> sprintOptional = Optional.of(sprintDouble);
        when(usRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStoryList);
        when(userStoryDouble.hasStatus(any())).thenReturn(false);
        when(sprintRepository.getSprintById(any())).thenReturn(sprintOptional);
        when(sprintDouble.isPeriodAfterOrEqualThanDate(any())).thenReturn(false);
        when(sprintDouble.addUserStory(any(), any())).thenReturn(true);
        service.addUserStoryToSprintBacklog(usId, sprintId);

        //Act
        boolean result = service.addUserStoryToSprintBacklog(usId, sprintId);
        //Assert
        assertTrue(result);
    }

    /**
     * scenario 3: returns false because US is already in the sprint backlog.
     */
    @Test
    void ensureUserStoryIsNotAddedToSprintBecauseIsALreadyInTheSprint() throws Exception {
        //Arrange
        String usId = "p001_us001";
        String sprintId = "p001_s001";
        UserStory userStoryDouble = mock(UserStory.class);
        List<UserStory> userStoryList = new ArrayList<>();
        userStoryList.add(userStoryDouble);
        Sprint sprintDouble = mock(Sprint.class);
        Optional<Sprint> sprintOptional = Optional.of(sprintDouble);
        when(usRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStoryList);
        when(userStoryDouble.hasStatus(any())).thenReturn(false);
        when(sprintRepository.getSprintById(any())).thenReturn(sprintOptional);
        when(sprintDouble.isPeriodAfterOrEqualThanDate(any())).thenReturn(false);
        when(sprintDouble.addUserStory(any(), any())).thenReturn(false);

        //Act
        boolean result = service.addUserStoryToSprintBacklog(usId, sprintId);
        //Assert
        assertFalse(result);
    }

    /**
     * scenario 4: Fails to add a finished US to the sprint backlog.
     */
    @Test
    void ensureUserStoryIsNotAddedToSprintBecauseUsIsFinished() {
        //Arrange
        String expected = "The User Story Status is not suitable to be added to the Sprint";
        String usId = "p001_us001";
        String sprintId = "p001_s001";
        UserStory userStoryDouble = mock(UserStory.class);
        List<UserStory> userStoryList = new ArrayList<>();
        userStoryList.add(userStoryDouble);
        Sprint sprintDouble = mock(Sprint.class);
        Optional<Sprint> sprintOptional = Optional.of(sprintDouble);
        when(usRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStoryList);
        when(userStoryDouble.hasStatus(Status.FINISHED)).thenReturn(true);
        when(sprintRepository.getSprintById(any())).thenReturn(sprintOptional);
        when(sprintDouble.isPeriodAfterOrEqualThanDate(any())).thenReturn(false);
        when(sprintDouble.addUserStory(any(), any())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () ->
                service.addUserStoryToSprintBacklog(usId, sprintId));
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 5: Fails to add a blocked US to the sprint backlog.
     */
    @Test
    void ensureUserStoryIsNotAddedToSprintBecauseUsIsBlocked() {
        //Arrange
        String expected = "The User Story Status is not suitable to be added to the Sprint";
        String usId = "p001_us001";
        String sprintId = "p001_s001";
        UserStory userStoryDouble = mock(UserStory.class);
        List<UserStory> userStoryList = new ArrayList<>();
        userStoryList.add(userStoryDouble);
        Sprint sprintDouble = mock(Sprint.class);
        Optional<Sprint> sprintOptional = Optional.of(sprintDouble);
        when(usRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStoryList);
        when(userStoryDouble.hasStatus(Status.BLOCKED)).thenReturn(true);
        when(sprintRepository.getSprintById(any())).thenReturn(sprintOptional);
        when(sprintDouble.isPeriodAfterOrEqualThanDate(any())).thenReturn(false);
        when(sprintDouble.addUserStory(any(), any())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () ->
                service.addUserStoryToSprintBacklog(usId, sprintId));
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 6: Fails to add a US to a sprint with start date greater than the date.
     */
    @Test
    void ensureUserStoryIsNotAddedToSprintBecauseSprintStartDateIsGreaterThenDate() {
        //Arrange
        String expected = "The Sprint is not valid";
        String usId = "p001_us001";
        String sprintId = "p001_s001";
        UserStory userStoryDouble = mock(UserStory.class);
        List<UserStory> userStoryList = new ArrayList<>();
        userStoryList.add(userStoryDouble);
        Sprint sprintDouble = mock(Sprint.class);
        Optional<Sprint> sprintOptional = Optional.of(sprintDouble);
        when(usRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStoryList);
        when(userStoryDouble.hasStatus(any())).thenReturn(false);
        when(sprintRepository.getSprintById(any())).thenReturn(sprintOptional);
        when(sprintDouble.isPeriodAfterOrEqualThanDate(any())).thenReturn(true);
        when(sprintDouble.addUserStory(any(), any())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () ->
                service.addUserStoryToSprintBacklog(usId, sprintId));
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }
    /**
     * scenario 7: Fails to add a US to a sprint because usId is not in the repository
     */
    @Test
    void ensureUserStoryIsNotAddedToSprintBecauseUsNotInTheRepository() {
        //Arrange
        String expected = "There is no User Story";
        String usId = "p001_us001";
        String sprintId = "p001_s001";
        List<UserStory> userStoryList = new ArrayList<>();
        Sprint sprintDouble = mock(Sprint.class);
        Optional<Sprint> sprintOptional = Optional.of(sprintDouble);
        when(usRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStoryList);
        when(sprintRepository.getSprintById(any())).thenReturn(sprintOptional);
        when(sprintDouble.isPeriodAfterOrEqualThanDate(any())).thenReturn(false);
        when(sprintDouble.addUserStory(any(), any())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () ->
                service.addUserStoryToSprintBacklog(usId, sprintId));
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }
    /**
     * scenario 8: Fails to add a US to a sprint because sprint is not in the repository.
     */
    @Test
    void ensureUserStoryIsNotAddedToSprintBecauseSprintIsNotInTheRepository() {
        //Arrange
        String expected = "No sprint with that id";
        String usId = "p001_us001";
        String sprintId = "p001_s001";
        UserStory userStoryDouble = mock(UserStory.class);
        List<UserStory> userStoryList = new ArrayList<>();
        userStoryList.add(userStoryDouble);
        Optional<Sprint> sprintOptional = Optional.empty();
        when(usRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStoryList);
        when(userStoryDouble.hasStatus(any())).thenReturn(false);
        when(sprintRepository.getSprintById(any())).thenReturn(sprintOptional);
        Exception exception = assertThrows(Exception.class, () ->
                service.addUserStoryToSprintBacklog(usId, sprintId));
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }
}