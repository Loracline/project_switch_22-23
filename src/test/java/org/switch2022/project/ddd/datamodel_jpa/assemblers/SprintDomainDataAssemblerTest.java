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
@SpringBootTest(classes = SprintDomainDataAssemblerTest.class)
public class SprintDomainDataAssemblerTest {

    @InjectMocks
    SprintDomainDataAssembler sprintDomainDataAssembler;
    @MockBean
    ISprintFactory factory;


    /**
     * Method toData.
     */

    @Test
    void testToData() {
        // Arrange
        // Create the expected SprintJpa object
        UserStoryInSprint userStoryDouble1 = mock(UserStoryInSprint.class);
        UserStoryInSprint userStoryDouble2 = mock(UserStoryInSprint.class);
        List<UserStoryInSprint> userStoriesDouble = new ArrayList<>();
        userStoriesDouble.add(userStoryDouble1);
        userStoriesDouble.add(userStoryDouble2);
        SprintJpa expected = new SprintJpa("P001_S001", "S001", "P001",
                "2023-01-01", "2023-02-01",null);
        Sprint sprintDouble = mock(Sprint.class);
        when(sprintDouble.getSprintId()).thenReturn("P001_S001");
        when(sprintDouble.getFullSprintNumber()).thenReturn("S001");
        when(sprintDouble.getProjectCode()).thenReturn("P001");
        when(sprintDouble.getStartDate()).thenReturn("2023-01-01");
        when(sprintDouble.getEndDate()).thenReturn("2023-02-01");
        UserStoryInSprintJpa userStoryJpa1 = new UserStoryInSprintJpa("P001_US001", 3, expected);
        userStoryJpa1.setUsId("P001_US001");
        userStoryJpa1.setEffort(3);
        userStoryJpa1.setSprint(expected);

        UserStoryInSprintJpa userStoryJpa2 = new UserStoryInSprintJpa("P001_US002", 5, expected);
        userStoryJpa2.setUsId("P001_US002");
        userStoryJpa2.setEffort(5);
        userStoryJpa2.setSprint(expected);

        List<UserStoryInSprintJpa> userStoriesInSprintJpa = new ArrayList<>();
        userStoriesInSprintJpa.add(userStoryJpa1);
        userStoriesInSprintJpa.add(userStoryJpa2);

        // Act
        SprintJpa result = sprintDomainDataAssembler.toData(sprintDouble);
        // Assert
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