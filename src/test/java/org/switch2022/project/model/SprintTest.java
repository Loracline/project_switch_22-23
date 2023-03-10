package org.switch2022.project.model;

import org.junit.jupiter.api.Test;
import org.switch2022.project.factories.IFactoryPeriod;
import org.switch2022.project.factories.FactoryPeriod;
import org.switch2022.project.factories.FactorySprintBacklog;
import org.switch2022.project.factories.IFactorySprintBacklog;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class SprintTest {

    private final IFactoryPeriod IFactoryPeriod = new FactoryPeriod();

    /*
     *METHOD EQUALS
     */

    /**
     * Scenario 1: Verify if the same object don't equal itself.
     */
    @Test
    public void testEqualsWhenDifferentSprintNumber() {
        // Arrange
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        Sprint sprintOne = Sprint.createSprint(LocalDate.now(), 3, "S001", IFactoryPeriod
                , factorySprintBacklogDouble);
        Sprint sprintTwo = Sprint.createSprint(LocalDate.now(), 3, "S002", IFactoryPeriod
                , factorySprintBacklogDouble);
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
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        Sprint sprintOne = Sprint.createSprint(LocalDate.now(), 3, "SP001",
                IFactoryPeriod, factorySprintBacklogDouble);
        Sprint sprintTwo = Sprint.createSprint(LocalDate.now(), 3, "SP001",
                IFactoryPeriod, factorySprintBacklogDouble);
        boolean expected = true;
        // Act
        boolean result = sprintOne.equals(sprintTwo);
        // Assert
        assertEquals(result, expected);
    }

    /**
     * Scenario 3: Verify if the objects are different.
     */
    @Test
    public void testEqualsWhenComparedToDifferentObject() {
        // Arrange
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        Sprint sprintOne = Sprint.createSprint(LocalDate.now(), 4, "SP001",
                IFactoryPeriod, factorySprintBacklogDouble);
        Object object = new Object();
        boolean expected = false;
        // Act
        boolean result = sprintOne.equals(object);
        // Assert
        assertEquals(result, expected);
    }

    /**
     * Scenario 4: Verify if a Sprint and a null object are not the same.
     */
    @Test
    public void testSprintDoesNotEqualNull() {
        //Arrange
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        Sprint sprintOne = Sprint.createSprint(LocalDate.now(), 4, "SP001",
                IFactoryPeriod, factorySprintBacklogDouble);
        Sprint other = null;
        boolean expected = false;
        //Act
        boolean result = sprintOne.equals(other);
        //Assert
        assertEquals(expected, result);
    }

    /*
     *METHOD HASHCODE
     */

    /**
     * Scenario 1: Two Sprint objects are the same.
     */
    @Test
    public void ensureTwoSprintHashcodeAreTheSame() {
        //Arrange
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        Sprint sprintOne = Sprint.createSprint(LocalDate.now(), 3, "SP001",
                IFactoryPeriod, factorySprintBacklogDouble);
        Sprint sprintTwo = Sprint.createSprint(LocalDate.now(), 3, "SP001",
                IFactoryPeriod, factorySprintBacklogDouble);
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
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        Sprint sprintOne = Sprint.createSprint(LocalDate.now(), 3, "SP001",
                IFactoryPeriod, factorySprintBacklogDouble);
        Sprint sprintThree = Sprint.createSprint(LocalDate.now(), 4, "SP003",
                IFactoryPeriod, factorySprintBacklogDouble);
        //Act
        int sprintOneHashCode = sprintOne.hashCode();
        int sprintThreeHashCode = sprintThree.hashCode();
        //Assert
        assertNotEquals(sprintOneHashCode, sprintThreeHashCode);
    }

    /**
     * METHOD addUserStoryToSprintBacklog(userStory)
     * adds a User Story to the Product Backlog
     * <p>
     * Scenario 1: verify if a User Story is added to Product Backlog. Should return True.
     */
    @Test
    void ensureThatUserStoryIsAddedToSprintBacklog() {
        //Arrange
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        IFactoryPeriod IFactoryPeriodDouble = mock(IFactoryPeriod.class);

        SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        when(factorySprintBacklogDouble.createSprintBacklog()).thenReturn(sprintBacklog);

        Sprint sprint = Sprint.createSprint(LocalDate.of(2023, 3, 9),
                3, "S035", IFactoryPeriodDouble, factorySprintBacklogDouble);

        UserStory userStoryDouble = mock(UserStory.class);
        when(sprintBacklog.addUserStory(userStoryDouble)).thenReturn(true);

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
        IFactoryPeriod IFactoryPeriodDouble = mock(IFactoryPeriod.class);
        SprintBacklog sprintBacklogDouble = mock(SprintBacklog.class);
        IFactorySprintBacklog factorySprintBacklogDouble =
                mock(FactorySprintBacklog.class);
        when(factorySprintBacklogDouble.createSprintBacklog()).thenReturn(sprintBacklogDouble);
        Sprint sprint = Sprint.createSprint(LocalDate.of(2023, 3, 9),
                3, "S035", IFactoryPeriodDouble, factorySprintBacklogDouble);
        sprint.addUserStoryToSprintBacklog(userStoryDouble);
        when(sprintBacklogDouble.addUserStory(userStoryDouble)).thenReturn(false);

        //Act
        boolean result = sprint.addUserStoryToSprintBacklog(userStoryDouble);

        //Assert
        assertFalse(result);
    }

    /**
     * METHOD hasSprintNumber(sprintNumber)
     * Verifies if Sprint has a given Sprint Number.
     * <p>
     * Scenario 1: returns True
     */
    @Test
    void ensureThatReturnsTrueIfSprintHasSprintNumber() {
        //ARRANGE
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        IFactoryPeriod factoryPeriodDouble = mock(IFactoryPeriod.class);

        Sprint sprint = Sprint.createSprint(LocalDate.of(2023, 3, 9),
                3, "S1", factoryPeriodDouble, factorySprintBacklogDouble);

        //ACT
        boolean result = sprint.hasSprintNumber("S1");

        //ASSERT
        assertTrue(result);
    }

    /**
     * Scenario 2: returns False
     */
    @Test
    void ensureThatReturnsFalseIfSprintDoesNotHaveSprintNumber() {
        //ARRANGE
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        IFactoryPeriod factoryPeriodDouble = mock(IFactoryPeriod.class);

        Sprint sprint = Sprint.createSprint(LocalDate.of(2023, 3, 9),
                3, "S2", factoryPeriodDouble, factorySprintBacklogDouble);

        //ACT
        boolean result = sprint.hasSprintNumber("S1");

        //ASSERT
        assertFalse(result);
    }
}