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
import org.switch2022.project.ddd.dto.UserStoryDto;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
                "Project" + " in the web UI."), new Actor("manager"),acceptanceCriteria, new Code(123)));

        String sprintId = "p123_s001";
        ISprintFactory sprintFactory = new SprintFactory();
        Sprint sprint = sprintFactory.createSprint(new Code(123),new SprintId("P123",
                "S001"),new SprintNumber(1),new Period(LocalDate.now(),2));

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
                "Project" + " in the web UI."), new Actor("manager"),acceptanceCriteria, new Code(123)));
        userStories.add(factoryUserStory.createUserStory(new UsNumber("024"), new UsText("As an Administrator, I want to create a " +
                "Project" + " in the web UI."), new Actor("manager"),acceptanceCriteria, new Code(123)));
        userStories.add(factoryUserStory.createUserStory(new UsNumber("025"), new UsText("As an Administrator, I want to create a " +
                "Project" + " in the web UI."), new Actor("manager"),acceptanceCriteria, new Code(123)));

        String sprintId = "p123_s001";
        ISprintFactory sprintFactory = new SprintFactory();
        Sprint sprint = sprintFactory.createSprint(new Code(123),new SprintId("P123",
                "S001"),new SprintNumber(1),new Period(LocalDate.now(),2));

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
        Sprint sprint = sprintFactory.createSprint(new Code(123),new SprintId("P123",
                "S001"),new SprintNumber(1),new Period(LocalDate.now(),2));

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
}

