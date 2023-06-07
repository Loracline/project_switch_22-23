package org.switch2022.project.ddd.datamodel_jpa.assemblers;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.switch2022.project.ddd.domain.model.sprint.ISprintFactory;



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
    /*
    @Test
    void testToData() {
        // Arrange

        // UserStoryInSprint userStoryDouble1 = mock(UserStoryInSprint.class);

        // UserStoryInSprint userStoryDouble2 = mock(UserStoryInSprint.class);

        // List<UserStoryInSprint> userStoriesDouble = new ArrayList<>();
        // userStoriesDouble.add(userStoryDouble1);
        // userStoriesDouble.add(userStoryDouble2);

        // Create the expected SprintJpa object
        SprintJpa expected = new SprintJpa("P001_S001", "S001", "P001", "2023-01-01", "2023-02-01");
        Sprint sprintDouble = mock(Sprint.class);
        when(sprintDouble.getSprintId()).thenReturn("P001_S001");
        when(sprintDouble.getFullSprintNumber()).thenReturn("S001");
        when(sprintDouble.getProjectCode()).thenReturn("P001");
        when(sprintDouble.getStartDate()).thenReturn("2023-01-01");
        when(sprintDouble.getEndDate()).thenReturn("2023-02-01");

       // UserStoryInSprintJpa userStoryJpa1 = new UserStoryInSprintJpa("P001_US001", 3, expected);
       // userStoryJpa1.setUsId("P001_US001");
       //  userStoryJpa1.setEffort(3);
       // userStoryJpa1.setSprint(expected);

       // UserStoryInSprintJpa userStoryJpa2 = new UserStoryInSprintJpa("P001_US002", 5, expected);
       // userStoryJpa2.setUsId("P001_US002");
       // userStoryJpa2.setEffort(5);
       // userStoryJpa2.setSprint(expected);

        //  List<UserStoryInSprintJpa> userStoriesInSprintJpa = new ArrayList<>();
        //  userStoriesInSprintJpa.add(userStoryJpa1);
        //  userStoriesInSprintJpa.add(userStoryJpa2);

        // Act
        SprintJpa result = sprintDomainDataAssembler.toData(sprintDouble);

        // Assert
        assertEquals(expected, result);
    }

*/
    /**
     * Method toDomain.
     */
/*
    @Test
    void ensureToDomain() {
        // Arrange
        Sprint expected = mock(Sprint.class);

        // Set the expected property values
        when(expected.getSprintId()).thenReturn("P001_S001");
        when(expected.getSprintNumber()).thenReturn(1);
        when(expected.getProjectCode()).thenReturn("P001");
        when(expected.getStartDate()).thenReturn("2023-01-01");
        when(expected.getEndDate()).thenReturn("2023-02-20");


        SprintJpa sprintJpaDouble = mock(SprintJpa.class);

        // Set the SprintJpa property values
        when(sprintJpaDouble.getSprintNumber()).thenReturn("S001");
        when(sprintJpaDouble.getProjectCode()).thenReturn("P001");
        when(sprintJpaDouble.getStartDate()).thenReturn("2023-01-01");
        when(sprintJpaDouble.getEndDate()).thenReturn("2023-02-20");

        // Act
        Sprint result = sprintDomainDataAssembler.toDomain(sprintJpaDouble);

        // Assert
        assertEquals(expected.getSprintId().toLowerCase(), result.getSprintId());
        assertEquals(expected.getSprintNumber(), result.getSprintNumber());
        assertEquals(expected.getProjectCode().toLowerCase(), result.getProjectCode());
        assertEquals(expected.getStartDate(), result.getStartDate());
        assertEquals(expected.getEndDate(), result.getEndDate());
    }

 */
}