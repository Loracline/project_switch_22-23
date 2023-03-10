package org.switch2022.project.model;

import org.junit.jupiter.api.Test;
import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.factories.FactoryPeriod;
import org.switch2022.project.factories.IFactoryPeriod;
import org.switch2022.project.utils.Effort;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.switch2022.project.model.UserStory.createUserStory;


class SprintTest {

    private final FactoryPeriod factoryPeriod = new IFactoryPeriod();
    /*
     *METHOD EQUALS
     */

    /**
     * Scenario 1: Verify if the same object don't equal itself.
     */
    @Test
    public void testEqualsWhenDifferentSprintNumber() {
        // Arrange
        Sprint sprintOne = Sprint.createSprint(LocalDate.now(),3,"S001", factoryPeriod);
        Sprint sprintTwo = Sprint.createSprint(LocalDate.now(),3,"S002",factoryPeriod);
        boolean expected = false;
        // Act
        boolean result = sprintOne.equals(sprintTwo);
        // Assert
        assertEquals(expected, result);
    }
    /**
     * Scenario 2: Verify if the same object equals itself.
     */
    @Test
    public void testEqualsWhenSameSprintNumber() {
        // Arrange
        Sprint sprintOne = Sprint.createSprint(LocalDate.now(), 3, "SP001", factoryPeriod);
        Sprint sprintTwo = Sprint.createSprint(LocalDate.now(), 3, "SP001", factoryPeriod);
        boolean expected = true;
        // Act
        boolean result = sprintOne.equals(sprintTwo);
        // Assert
        assertEquals(result,expected);
    }
    /**
     * Scenario 3: Verify if the objects are different.
     */
    @Test
    public void testEqualsWhenComparedToDifferentObject() {
        // Arrange
        Sprint sprintOne = Sprint.createSprint(LocalDate.now(), 4, "SP001", factoryPeriod);
        Object object = new Object();
        boolean expected = false;
        // Act
        boolean result = sprintOne.equals(object);
        // Assert
        assertEquals(result,expected);
    }

    /**
     * Scenario 4: Verify if a Sprint and a null object are not the same.
     */
    @Test
    public void testSprintDoesNotEqualNull() {
        //Arrange
        Sprint sprintOne = Sprint.createSprint(LocalDate.now(), 4, "SP001", factoryPeriod);
        Sprint other = null;
        boolean expected = false;
        //Act
        boolean result = sprintOne.equals(other);
        //Assert
        assertEquals(expected,result);
    }

    /*
     *METHOD HASHCODE
     */
    /**
     *Scenario 1: Two Sprint objects are the same.
     */
    @Test
    public void ensureTwoSprintHashcodeAreTheSame() {
        //Arrange
        Sprint sprintOne = Sprint.createSprint(LocalDate.now(), 3, "SP001", factoryPeriod);
        Sprint sprintTwo = Sprint.createSprint(LocalDate.now(), 3, "SP001", factoryPeriod);
        //Act
        int sprintOneHashCode = sprintOne.hashCode();
        int sprintTwoHashCode = sprintTwo.hashCode();
        //Assert
        assertEquals(sprintOneHashCode, sprintTwoHashCode);
    }

    /**
     * Scenario 2: Two Sprint objects are not the same.
     */
    @Test
    public void ensureTwoSprintHashcodeAreNotTheSame() {
        //Arrange
        Sprint sprintOne = Sprint.createSprint(LocalDate.now(), 3, "SP001", factoryPeriod);
        Sprint sprintThree = Sprint.createSprint(LocalDate.now(), 4, "SP003", factoryPeriod);
        //Act
        int sprintOneHashCode = sprintOne.hashCode();
        int sprintThreeHashCode = sprintThree.hashCode();
        //Assert
        assertNotEquals(sprintOneHashCode, sprintThreeHashCode);
    }

    /**
     * METHOD estimateEffortUserStory(userStoryDto, effort)
     * <p>
     * Scenario 1: sets the effort of a UserStory.
     */

    @Test
    void ensureEffortIsIsSetForUserStory() {
        //Arrange
        LocalDate date = LocalDate.of(2021, 9, 13);
        FactoryPeriod factoryPeriod = new IFactoryPeriod();
        Sprint sprint = Sprint.createSprint(date, 2, "S55", factoryPeriod);

        UserStory userStory = createUserStory("US001", "Manager",
                "I want to create a profile");
        sprint.addUserStoryToSprintBacklog(userStory);

        UserStoryDto userStoryDto = new UserStoryDto("US001", "Manager",
                "I want to create a profile");
        Effort effort = Effort.THREE;

        //Act
        boolean result = sprint.estimateEffortUserStory(userStoryDto, effort);

        //Assert
        assertTrue(result);
    }

    /**
     +     * METHOD addUserStoryToSprintBacklog(userStory)
     +     * adds a User Story to the Product Backlog
     +     * <p>
     +     * Scenario 1: verify if a User Story is added to Product Backlog. Should return True.
     +     */
    @Test
    void ensureThatUserStoryIsAddedToSprintBacklog() {
        //Arrange
        UserStory userStoryDouble = mock(UserStory.class);
        FactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        Sprint sprint = Sprint.createSprint(LocalDate.of(2023, 3, 9),
                3, "S035", factoryPeriodDouble);

        //Act
        boolean result = sprint.addUserStoryToSprintBacklog(userStoryDouble);

        //Assert
        assertTrue(result);
    }

    /**
    * Scenario 2: verify if a User Story is not added to Product Backlog. Should
    * return false.
    */

    @Test
    void ensureThatUserStoryIsNotAddedToSprintBacklog() {
        //Arrange
        UserStory userStoryDouble = mock(UserStory.class);
        FactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        SprintBacklog sprintBacklogDouble = mock(SprintBacklog.class);
        Sprint sprint = Sprint.createSprint(LocalDate.of(2023, 3, 9),
               3, "S035", factoryPeriodDouble);
        sprint.addUserStoryToSprintBacklog(userStoryDouble);
        when(sprintBacklogDouble.addUserStory(userStoryDouble)).thenReturn(false);

        //Act
        boolean result = sprint.addUserStoryToSprintBacklog(userStoryDouble);

        //Assert
        assertFalse(result);
        }
}