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
import org.switch2022.project.ddd.domain.value_object.Status;
import org.switch2022.project.ddd.dto.UserStoryInSprintDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        classes = org.switch2022.project.ddd.application.AddUserStoryToSprintBacklogService.class)
class AddUserStoryToSprintBacklogServiceTest {

    @InjectMocks
    AddUserStoryToSprintBacklogService service;
    @Qualifier("sprint_jpa")
    @MockBean
    private ISprintRepository sprintRepository;
    @Qualifier("us_jpa")
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
  /*  @Test
    void ensureUserStoryIsAddedToEmptySprint() {
        //Arrange
        String usId = "p001_us001";
        String sprintId = "p001_s001";
        UserStoryInSprintDto dto = new UserStoryInSprintDto(usId, sprintId);
        UserStory userStoryDouble = mock(UserStory.class);
        List<UserStory> userStoryList = new ArrayList<>();
        userStoryList.add(userStoryDouble);
        Sprint sprintDouble = mock(Sprint.class);
        Optional<Sprint> sprintOptional = Optional.of(sprintDouble);
        when(usRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStoryList);
        when(userStoryDouble.hasStatus(any())).thenReturn(true);
        when(userStoryDouble.getProjectCode()).thenReturn("p001");
        when(sprintDouble.hasProjectCode(any())).thenReturn(true);
        when(sprintRepository.findById(any())).thenReturn(sprintOptional);
        when(sprintDouble.isOpen()).thenReturn(true);
        when(sprintDouble.addUserStory(any())).thenReturn(true);

        //Act
        boolean result = service.addUserStoryToSprint(dto);
        //Assert
        assertTrue(result);
    }*/

    /**
     * scenario 2: Adds a US to a non-empty sprint backlog
     */
  /*  @Test
    void ensureUserStoryIsAddedToSprint() {
        //Arrange
        String usId = "p001_us001";
        String sprintId = "p001_s001";
        UserStoryInSprintDto dto = new UserStoryInSprintDto(usId, sprintId);
        UserStory userStoryDouble = mock(UserStory.class);
        List<UserStory> userStoryList = new ArrayList<>();
        userStoryList.add(userStoryDouble);
        Sprint sprintDouble = mock(Sprint.class);
        Optional<Sprint> sprintOptional = Optional.of(sprintDouble);
        when(usRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStoryList);
        when(userStoryDouble.hasStatus(any())).thenReturn(true);
        when(userStoryDouble.getProjectCode()).thenReturn("p001");
        when(sprintDouble.hasProjectCode(any())).thenReturn(true);
        when(sprintRepository.findById(any())).thenReturn(sprintOptional);
        when(sprintDouble.isOpen()).thenReturn(true);
        when(sprintDouble.addUserStory(any())).thenReturn(true);
        service.addUserStoryToSprint(dto);

        //Act
        boolean result = service.addUserStoryToSprint(dto);
        //Assert
        assertTrue(result);
    }*/

    /**
     * scenario 3: returns false because US is already in the sprint backlog.
     */
   /* @Test
    void ensureUserStoryIsNotAddedToSprintBecauseIsALreadyInTheSprint() {
        //Arrange
        String usId = "p001_us001";
        String sprintId = "p001_s001";
        UserStoryInSprintDto dto = new UserStoryInSprintDto(usId, sprintId);
        UserStory userStoryDouble = mock(UserStory.class);
        List<UserStory> userStoryList = new ArrayList<>();
        userStoryList.add(userStoryDouble);
        Sprint sprintDouble = mock(Sprint.class);
        Optional<Sprint> sprintOptional = Optional.of(sprintDouble);
        when(usRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStoryList);
        when(userStoryDouble.hasStatus(any())).thenReturn(true);
        when(userStoryDouble.getProjectCode()).thenReturn("p001");
        when(sprintDouble.hasProjectCode(any())).thenReturn(true);
        when(sprintRepository.findById(any())).thenReturn(sprintOptional);
        when(sprintDouble.isOpen()).thenReturn(true);
        when(sprintDouble.addUserStory(any())).thenReturn(false);

        //Act
        boolean result = service.addUserStoryToSprint(dto);
        //Assert
        assertFalse(result);
    }*/

    /**
     * scenario 4: Fails to add a finished US to the sprint backlog.
     */
    @Test
    void ensureUserStoryIsNotAddedToSprintBecauseUsIsFinished() {
        //Arrange
        String expected = "Only User Stories with 'PLANNED' status can be added to the Sprint";
        String usId = "p001_us001";
        String sprintId = "p001_s001";
        UserStoryInSprintDto dto = new UserStoryInSprintDto(usId, sprintId);
        UserStory userStoryDouble = mock(UserStory.class);
        List<UserStory> userStoryList = new ArrayList<>();
        userStoryList.add(userStoryDouble);
        Sprint sprintDouble = mock(Sprint.class);
        Optional<Sprint> sprintOptional = Optional.of(sprintDouble);
        when(usRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStoryList);
        when(userStoryDouble.hasStatus(Status.FINISHED)).thenReturn(true);
        when(userStoryDouble.getProjectCode()).thenReturn("p001");
        when(sprintDouble.hasProjectCode(any())).thenReturn(true);
        when(sprintRepository.findById(any())).thenReturn(sprintOptional);
        when(sprintDouble.isOpen()).thenReturn(true);
        when(sprintDouble.addUserStory(any())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () ->
                service.addUserStoryToSprint(dto));
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
        String expected = "Cannot add user stories to a sprint that is not in the 'OPEN' state";
        String usId = "p001_us001";
        String sprintId = "p001_s001";
        UserStoryInSprintDto dto = new UserStoryInSprintDto(usId, sprintId);
        UserStory userStoryDouble = mock(UserStory.class);
        List<UserStory> userStoryList = new ArrayList<>();
        userStoryList.add(userStoryDouble);
        Sprint sprintDouble = mock(Sprint.class);
        Optional<Sprint> sprintOptional = Optional.of(sprintDouble);
        when(usRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStoryList);
        when(userStoryDouble.hasStatus(Status.BLOCKED)).thenReturn(true);
        when(sprintRepository.findById(any())).thenReturn(sprintOptional);
        when(sprintDouble.isPeriodAfterOrEqualThanDate(any())).thenReturn(false);
        when(sprintDouble.addUserStory(any())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () ->
                service.addUserStoryToSprint(dto));
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 6: Fails to add a US to a sprint because usId is not in the repository
     */
    @Test
    void ensureUserStoryIsNotAddedToSprintBecauseUsNotInTheRepository() {
        //Arrange
        String expected = "There is no User Story";
        String usId = "p001_us001";
        String sprintId = "p001_s001";
        UserStoryInSprintDto dto = new UserStoryInSprintDto(usId, sprintId);
        List<UserStory> userStoryList = new ArrayList<>();
        Sprint sprintDouble = mock(Sprint.class);
        Optional<Sprint> sprintOptional = Optional.of(sprintDouble);
        when(usRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStoryList);
        when(sprintRepository.findById(any())).thenReturn(sprintOptional);
        when(sprintDouble.isPeriodAfterOrEqualThanDate(any())).thenReturn(false);
        when(sprintDouble.addUserStory(any())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () ->
                service.addUserStoryToSprint(dto));
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 7: Fails to add a US to a sprint because sprint is not in the repository.
     */
    @Test
    void ensureUserStoryIsNotAddedToSprintBecauseSprintIsNotInTheRepository() {
        //Arrange
        String expected = "No sprint with that id";
        String usId = "p001_us001";
        String sprintId = "p001_s001";
        UserStoryInSprintDto dto = new UserStoryInSprintDto(usId, sprintId);
        UserStory userStoryDouble = mock(UserStory.class);
        List<UserStory> userStoryList = new ArrayList<>();
        userStoryList.add(userStoryDouble);
        Optional<Sprint> sprintOptional = Optional.empty();
        when(usRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStoryList);
        when(userStoryDouble.hasStatus(any())).thenReturn(false);
        when(sprintRepository.findById(any())).thenReturn(sprintOptional);
        Exception exception = assertThrows(Exception.class, () ->
                service.addUserStoryToSprint(dto));
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }
}