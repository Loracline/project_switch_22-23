package org.switch2022.project.ddd.datamodel_jpa.assemblers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.switch2022.project.ddd.datamodel_jpa.SprintJpa;
import org.switch2022.project.ddd.datamodel_jpa.UserStoryInSprintJpa;
import org.switch2022.project.ddd.domain.model.sprint.ISprintFactory;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.model.sprint.UserStoryInSprint;
import org.switch2022.project.ddd.domain.value_object.UsId;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class SprintDomainDataAssemblerTest {

    @InjectMocks
    SprintDomainDataAssembler sprintDomainDataAssembler;
    @MockBean
    ISprintFactory factory;


    /**
     * Method toData.
     */

    @Test
    void ensureThatTheConversionOfAnInstanceOfSprintToAnInstanceOfSprintJpaIsDoneSuccessfully() {
        //ARRANGE
        //Sprint
        Sprint sprintDouble = mock(Sprint.class);
        when(sprintDouble.getSprintId()).thenReturn("P001_S001");
        when(sprintDouble.getFullSprintNumber()).thenReturn("S001");
        when(sprintDouble.getProjectCode()).thenReturn("P001");
        when(sprintDouble.getStartDate()).thenReturn("2023-01-01");
        when(sprintDouble.getEndDate()).thenReturn("2023-02-01");
        when(sprintDouble.getStatus()).thenReturn("open");

        //UserStoriesInSprint
        UserStoryInSprint userStoryDoubleOne = mock(UserStoryInSprint.class);
        UsId usIdOne = mock(UsId.class);
        when(userStoryDoubleOne.getUsId()).thenReturn(usIdOne);
        when(userStoryDoubleOne.getUsId().toString()).thenReturn("US001");
        when(userStoryDoubleOne.getEffort()).thenReturn(5);

        UserStoryInSprint userStoryDoubleTwo = mock(UserStoryInSprint.class);
        UsId usIdTwo = mock(UsId.class);
        when(userStoryDoubleTwo.getUsId()).thenReturn(usIdTwo);
        when(userStoryDoubleTwo.getUsId().toString()).thenReturn("US002");
        when(userStoryDoubleTwo.getEffort()).thenReturn(3);

        List<UserStoryInSprint> userStoriesInSprint = new ArrayList<>();
        userStoriesInSprint.add(userStoryDoubleOne);
        userStoriesInSprint.add(userStoryDoubleTwo);

        when(sprintDouble.getUserStoriesInSprint()).thenReturn(userStoriesInSprint);

        //Expected
        SprintJpa expected = new SprintJpa("P001_S001", "S001", "P001", "2023-01-01", "2023-02-01", "open");
        expected.getUserStoriesInSprint().add(new UserStoryInSprintJpa("US001", 5));
        expected.getUserStoriesInSprint().add(new UserStoryInSprintJpa("US002", 3));

        //ACT
        SprintJpa result = sprintDomainDataAssembler.toData(sprintDouble);

        //ASSERT
        assertEquals(expected, result);
    }


    /**
     * Method toDomain.
     */

    @Test
    void ensureThatTheConversionOfAnInstanceOfSprintJpaToAnInstanceOfSprintDoneSuccessfully() {
        //ARRANGE
        SprintJpa sprintJpaDouble = mock(SprintJpa.class);
        when(sprintJpaDouble.getSprintNumber()).thenReturn("S001");
        when(sprintJpaDouble.getProjectCode()).thenReturn("P001");
        when(sprintJpaDouble.getStartDate()).thenReturn("2023-01-01");
        when(sprintJpaDouble.getEndDate()).thenReturn("2023-02-20");
        when(sprintJpaDouble.getStatus()).thenReturn("PLANNED");

        Sprint expected = mock(Sprint.class);
        when(factory.createSprint(any(), any(), any(), any())).thenReturn(expected);

        //UserStoriesInSprintJpa
        UserStoryInSprintJpa userStoryJpaDoubleOne = mock(UserStoryInSprintJpa.class);
        when(userStoryJpaDoubleOne.getUsId()).thenReturn("P001_US001");

        UserStoryInSprintJpa userStoryJpaDoubleTwo = mock(UserStoryInSprintJpa.class);
        when(userStoryJpaDoubleTwo.getUsId()).thenReturn("P001_US002");


        List<UserStoryInSprintJpa> userStoriesInSprintJpa = new ArrayList<>();
        userStoriesInSprintJpa.add(userStoryJpaDoubleOne);
        userStoriesInSprintJpa.add(userStoryJpaDoubleTwo);

        when(sprintJpaDouble.getUserStoriesInSprint()).thenReturn(userStoriesInSprintJpa);

        //ACT
        Sprint result = sprintDomainDataAssembler.toDomain(sprintJpaDouble);

        //ASSERT
        assertEquals(expected, result);
    }


}