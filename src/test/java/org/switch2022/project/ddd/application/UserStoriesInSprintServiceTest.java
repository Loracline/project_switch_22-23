package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.sprint.ISprintFactory;
import org.switch2022.project.ddd.domain.model.sprint.ISprintRepository;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.model.sprint.SprintFactory;
import org.switch2022.project.ddd.domain.model.user_story.FactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.IFactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.ProjectCodeValueObjectDto;
import org.switch2022.project.ddd.dto.UserStoryDto;
import org.switch2022.project.ddd.dto.UserStoryStatusDto;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = UserStoriesInSprintService.class
)

public class UserStoriesInSprintServiceTest {
    @InjectMocks
    UserStoriesInSprintService service;

    @Qualifier("sprint_jpa")
    @MockBean
    ISprintRepository sprintRepository;

    @MockBean
    @Qualifier("us_jpa")
    IUsRepository userStoryRepository;

    /**
     * METHOD: getSprintBacklog()
     * Scenario 1: ensure the sprint backlog of the chosen sprint is returned.
     *
     * @return a list of UserStoryDto objects representing the user stories in the chosen sprint.
     */
    @Test
    public void ensureTheSprintBacklogIsRetrieved() {
        //ARRANGE
        IFactoryUserStory factoryUserStory = new FactoryUserStory();
        List<UserStory> userStories = new ArrayList<>();
        List<AcceptanceCriteria> acceptanceCriteria = new ArrayList<>();
        userStories.add(factoryUserStory.createUserStory(new UsNumber("023"), new UsText("As an Administrator, I want to create a " +
                "Project" + " in the web UI."), new Actor("manager"), acceptanceCriteria, new Code(123)));

        String sprintId = "p123_s001";
        ISprintFactory sprintFactory = new SprintFactory();
        Sprint sprint = sprintFactory.createSprint(new Code(123), new SprintId("P123",
                "S001"), new SprintNumber(1), new Period(LocalDate.now(), 2));

        when(sprintRepository.findById(any())).thenReturn(Optional.ofNullable(sprint));
        when(userStoryRepository.getListOfUsWithMatchingIds(anyList())).thenReturn(userStories);

        List<UserStoryDto> expected = new ArrayList<>();
        expected.add(new UserStoryDto("us023", "As an Administrator, I want to create a " +
                "Project" + " in the web UI.", "Planned"));

        //ACT
        List<UserStoryDto> result = service.getSprintBacklog(sprintId);

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: ensure the sprint backlog of the chosen sprint is returned.
     *
     * @return a list of UserStoryDto objects representing the user stories in the chosen sprint.
     */
    @Test
    public void ensureTheSprintBacklogIsRetrieved_Two() {
        //ARRANGE
        IFactoryUserStory factoryUserStory = new FactoryUserStory();
        List<UserStory> userStories = new ArrayList<>();
        List<AcceptanceCriteria> acceptanceCriteria = new ArrayList<>();
        userStories.add(factoryUserStory.createUserStory(new UsNumber("023"), new UsText("As an Administrator, I want to create a " +
                "Project" + " in the web UI."), new Actor("manager"), acceptanceCriteria, new Code(123)));
        userStories.add(factoryUserStory.createUserStory(new UsNumber("024"), new UsText("As an Administrator, I want to create a " +
                "Project" + " in the web UI."), new Actor("manager"), acceptanceCriteria, new Code(123)));
        userStories.add(factoryUserStory.createUserStory(new UsNumber("025"), new UsText("As an Administrator, I want to create a " +
                "Project" + " in the web UI."), new Actor("manager"), acceptanceCriteria, new Code(123)));

        String sprintId = "p123_s001";
        ISprintFactory sprintFactory = new SprintFactory();
        Sprint sprint = sprintFactory.createSprint(new Code(123), new SprintId("P123",
                "S001"), new SprintNumber(1), new Period(LocalDate.now(), 2));

        when(sprintRepository.findById(any())).thenReturn(Optional.ofNullable(sprint));
        when(userStoryRepository.getListOfUsWithMatchingIds(anyList())).thenReturn(userStories);

        List<UserStoryDto> expected = new ArrayList<>();
        expected.add(new UserStoryDto("us023", "As an Administrator, I want to create a " +
                "Project" + " in the web UI.", "Planned"));
        expected.add(new UserStoryDto("us024", "As an Administrator, I want to create a " +
                "Project" + " in the web UI.", "Planned"));
        expected.add(new UserStoryDto("us025", "As an Administrator, I want to create a " +
                "Project" + " in the web UI.", "Planned"));
        //ACT
        List<UserStoryDto> result = service.getSprintBacklog(sprintId);

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: ensure the sprint backlog of the chosen sprint is returned as an empty list.
     *
     * @return an empty list of UserStoryDto objects since there are no user stories in the chosen
     * sprint of the given project.
     */
    @Test
    public void ensureTheSprintBacklogIsRetrieved_emptyList() {
        //ARRANGE
        List<UserStory> userStories = new ArrayList<>();

        String sprintId = "p123_s001";
        ISprintFactory sprintFactory = new SprintFactory();
        Sprint sprint = sprintFactory.createSprint(new Code(123), new SprintId("P123",
                "S001"), new SprintNumber(1), new Period(LocalDate.now(), 2));

        when(sprintRepository.findById(any())).thenReturn(Optional.ofNullable(sprint));
        when(userStoryRepository.getListOfUsWithMatchingIds(anyList())).thenReturn(userStories);

        List<UserStoryDto> expected = new ArrayList<>();

        //ACT
        List<UserStoryDto> result = service.getSprintBacklog(sprintId);

        //ASSERT
        assertEquals(expected, result);
    }


    /**
     * Scenario 4: ensure the sprint backlog of chosen sprint is not returned because the sprint Id does
     * not exist.
     *
     * @return an exception stating that the sprint ID does not exist.
     */
    @Test
    public void ensureSprintBacklogIsNotReturned_sprintIdDoesNotExist() {
        //ARRANGE
        Optional<Sprint> sprint = Optional.empty();

        when(sprintRepository.findById(any())).thenReturn(sprint);

        NotFoundInRepoException exception = assertThrows(NotFoundInRepoException.class, () ->
                service.getSprintBacklog("p123_s001"));

        String expected = "No sprint with that id";

        //ACT
        String result = exception.getMessage();

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Method: changeUserStoryStatus
     * scenario 1: returns true
     */
    @Test
    void ensureTheStatusOfAUserStoryIsChangedSuccessfully() {
        //Arrange
        UserStoryStatusDto userStoryStatusDto =
                new UserStoryStatusDto("p001_us001", "p001_s_001", "RUNNING");
        UserStory userStoryDouble = mock(UserStory.class);
        Optional<UserStory> userStoryOptional = Optional.of(userStoryDouble);
        when(userStoryRepository.findByUsId(any())).thenReturn(userStoryOptional);
        when(sprintRepository.hasStatus(any(), any())).thenReturn(true);
        when(sprintRepository.hasUsId(any(), any())).thenReturn(true);
        when(userStoryRepository.save(userStoryDouble)).thenReturn(true);

        //Act
        boolean result = service.changeUserStoryStatus(userStoryStatusDto);
        //Assert
        assertTrue(result);
    }

    /**
     * scenario 2: returns an exception because the sprint is not open
     */
    @Test
    void ensureTheStatusOfAUserStoryIsChangedUnsuccessfully() {
        //Arrange
        UserStoryStatusDto userStoryStatusDto =
                new UserStoryStatusDto("p001_us001", "p001_s_001", "RUNNING");
        UserStory userStoryDouble = mock(UserStory.class);
        Optional<UserStory> userStoryOptional = Optional.of(userStoryDouble);
        when(userStoryRepository.findByUsId(any())).thenReturn(userStoryOptional);
        when(sprintRepository.hasStatus(any(), any())).thenReturn(false);
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                service.changeUserStoryStatus(userStoryStatusDto));

        String expected = "The Sprint is not open";

        //ACT
        String result = exception.getMessage();

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * scenario 3: returns an exception because there is no userStory with that Id
     */

    @Test
    void ensureTheStatusOfAUserStoryIsChangedUnsuccessfully_NoUserStory() {
        //Arrange
        UserStoryStatusDto userStoryStatusDto =
                new UserStoryStatusDto("p001_us001", "p001_s_001", "RUNNING");

        Optional<UserStory> userStoryOptional = Optional.empty();
        when(userStoryRepository.findByUsId(any())).thenReturn(userStoryOptional);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                service.changeUserStoryStatus(userStoryStatusDto));

        String expected = "No user story with that id";

        //ACT
        String result = exception.getMessage();

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * scenario 4: returns an exception because the userStory and the sprint don't belong to the same project
     */
    @Test
    void ensureTheStatusOfAUserStoryIsChangedUnsuccessfully_NotSameProjectCode() {
        //Arrange
        UserStoryStatusDto userStoryStatusDto =
                new UserStoryStatusDto("p001_us001", "p001_s_001", "RUNNING");
        UserStory userStoryDouble = mock(UserStory.class);
        Optional<UserStory> userStoryOptional = Optional.of(userStoryDouble);
        when(userStoryRepository.findByUsId(any())).thenReturn(userStoryOptional);
        when(sprintRepository.hasUsId(any(), any())).thenReturn(false);
        when(sprintRepository.hasStatus(any(), any())).thenReturn(true);
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                service.changeUserStoryStatus(userStoryStatusDto));

        String expected = "The User Story doesn't belong to the sprint";

        //ACT
        String result = exception.getMessage();

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Method: getScrumBoard
     * scenario 1: returns a list of dtos
     */
    @Test
    public void ensureTheScrumBoardIsRetrieved() {
        //ARRANGE
        IFactoryUserStory factoryUserStory = new FactoryUserStory();
        List<UserStory> userStories = new ArrayList<>();
        List<AcceptanceCriteria> acceptanceCriteria = new ArrayList<>();
        userStories.add(factoryUserStory.createUserStory(new UsNumber("023"), new UsText("As an Administrator, I want to create a " +
                "Project" + " in the web UI."), new Actor("manager"), acceptanceCriteria, new Code(123)));

        ISprintFactory sprintFactory = new SprintFactory();
        Sprint sprint = sprintFactory.createSprint(new Code(123), new SprintId("P123",
                "S001"), new SprintNumber(1), new Period(LocalDate.now(), 2));

        when(sprintRepository.findById(any())).thenReturn(Optional.ofNullable(sprint));
        when(userStoryRepository.getListOfUsWithMatchingIds(anyList())).thenReturn(userStories);

        List<UserStoryDto> expected = new ArrayList<>();
        expected.add(new UserStoryDto("us023", "As an Administrator, I want to create a " +
                "Project" + " in the web UI.", "Planned"));

        Code projectCode = mock(Code.class);
        ProjectCodeValueObjectDto dto = mock(ProjectCodeValueObjectDto.class);
        when(dto.getCode()).thenReturn(projectCode);
        when(sprintRepository.findByProjectCodeAndStatus
                (projectCode, SprintStatus.OPEN)).thenReturn(Optional.ofNullable(sprint));
        //ACT
        List<UserStoryDto> result = service.getScrumBoard(dto);

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * scenario 2: returns an empty list
     */
    @Test
    public void ensureTheScrumBoardIsNotRetrieved() {
        //ARRANGE
        List<UserStoryDto> expected = new ArrayList<>();
        Code projectCode = mock(Code.class);
        ProjectCodeValueObjectDto dto = mock(ProjectCodeValueObjectDto.class);
        when(dto.getCode()).thenReturn(projectCode);
        when(sprintRepository.findByProjectCodeAndStatus
                (projectCode, SprintStatus.OPEN)).thenReturn(Optional.empty());
        //ACT
        List<UserStoryDto> result = service.getScrumBoard(dto);

        //ASSERT
        assertEquals(expected, result);
    }
}