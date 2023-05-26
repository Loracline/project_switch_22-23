package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.sprint.ISprintRepository;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.model.sprint.UserStoryInSprint;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.UserStoryDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = UserStoriesInSprintService.class
)

public class UserStoriesInSprintServiceTest {
    @InjectMocks
    UserStoriesInSprintService service;

    @MockBean
    ISprintRepository sprintRepository;

    @MockBean
    @Qualifier("us_jpa")
    IUsRepository userStoryRepository;

    /**
     * METHOD: getScrumBoard()
     * Scenario 1: ensure the view of the Scrum Board of current sprint.
     *
     * @return a list of UserStoryDto objects representing the user stories in the current sprint
     * of a given project.
     */
    @Test
    public void ensureTheViewOfTheScrumBoardOfCurrentSprint() {
        //ARRANGE
        String projectCode = "P123";
        LocalDate date = LocalDate.now();
        Code code = new Code(123);

        UsId usId = mock(UsId.class);
        UserStoryInSprint userStoryInSprint = mock(UserStoryInSprint.class);
        when(userStoryInSprint.getUsId()).thenReturn(usId);

        List<UserStoryInSprint> userStoryInSprints = new ArrayList<>();
        userStoryInSprints.add(userStoryInSprint);

        Sprint sprint = mock(Sprint.class);
        when(sprint.isDateWithinPeriod(date)).thenReturn(true);
        when(sprint.getUserStoriesInSprint()).thenReturn(userStoryInSprints);

        List<Sprint> sprintsOfProject = new ArrayList<>();
        sprintsOfProject.add(sprint);

        when(sprintRepository.findByProjectCode(code)).thenReturn(sprintsOfProject);

        List<UsId> usIds = new ArrayList<>();
        usIds.add(usId);

        UserStory userStory = mock(UserStory.class);
        when(userStory.getUsNumber()).thenReturn("023");
        when(userStory.getUsText()).thenReturn("As an Administrator, I want to create a Project" +
                " in the web UI.");
        when(userStory.getStatus()).thenReturn("Planned");

        List<UserStory> userStories = new ArrayList<>();
        userStories.add(userStory);

        when(userStoryRepository.getListOfUsWithMatchingIds(usIds)).thenReturn(userStories);

        List<UserStoryDto> expectedList = new ArrayList<>();
        expectedList.add(new UserStoryDto("023", "As an Administrator, I want to create a Project" +
                " in the web UI.", "Planned"));
        //ACT
        List<UserStoryDto> result = service.getScrumBoard(projectCode, date);

        //ASSERT
        assertEquals(expectedList, result);
    }

    /**
     * Scenario 2: ensure the view of the Scrum Board of current sprint with no user stories in it.
     *
     * @return an empty list of UserStoryDto objects since there are no user stories in the current
     * sprint of the given project.
     */
    @Test
    public void ensureTheViewOfTheScrumBoardOfCurrentSprintHasNoUserStories() {
        //ARRANGE
        String projectCode = "P123";
        LocalDate date = LocalDate.now();
        Code code = new Code(123);

        List<UserStoryInSprint> userStoryInSprints = new ArrayList<>();

        Sprint sprint = mock(Sprint.class);
        when(sprint.isDateWithinPeriod(date)).thenReturn(true);
        when(sprint.getUserStoriesInSprint()).thenReturn(userStoryInSprints);

        List<Sprint> sprintsOfProject = new ArrayList<>();
        sprintsOfProject.add(sprint);

        when(sprintRepository.findByProjectCode(code)).thenReturn(sprintsOfProject);

        List<UserStoryDto> expectedList = new ArrayList<>();

        //ACT
        List<UserStoryDto> result = service.getScrumBoard(projectCode, date);

        //ASSERT
        assertEquals(expectedList, result);
    }


    /**
     * Scenario 3: ensure no Scrum Board of current sprint is returned because of invalid project
     * code.
     *
     * @return an empty list of UserStoryDto objects since the project code "ABC" is not valid.
     */
    @Test
    public void ensureNoScrumBoardIsReturnedWhenProjectCodeInvalid() {
        //ARRANGE
        String projectCode = "P123";
        LocalDate date = LocalDate.now();
        Code code = new Code(123);

        when(sprintRepository.findByProjectCode(code)).thenReturn(new ArrayList<>());

        List<UserStoryDto> expectedList = new ArrayList<>();

        //ACT
        List<UserStoryDto> result = service.getScrumBoard(projectCode, date);

        //ASSERT
        assertEquals(expectedList, result);
    }

    /**
     * Scenario 4: ensure no Scrum Board of current sprint is returned because there is no current
     * sprint.
     *
     * @return an empty list of UserStoryDto objects since there is no current sprint for the given
     * project.
     */
    @Test
    public void ensureNoScrumBoardIsReturnedWhenThereIsNoCurrentSprint() {
        //ARRANGE
        String projectCode = "P123";
        LocalDate date = LocalDate.now();
        Code code = new Code(123);

        Sprint sprint = mock(Sprint.class);
        when(sprint.isDateWithinPeriod(date)).thenReturn(false);

        List<Sprint> sprintsOfProject = new ArrayList<>();
        sprintsOfProject.add(sprint);

        when(sprintRepository.findByProjectCode(code)).thenReturn(sprintsOfProject);

        List<UserStoryDto> expectedList = new ArrayList<>();

        //ACT
        List<UserStoryDto> result = service.getScrumBoard(projectCode, date);

        //ASSERT
        assertEquals(expectedList, result);
    }
}

