package org.switch2022.project.model;

import org.junit.jupiter.api.Test;
import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.factories.FactoryPeriod;
import org.switch2022.project.factories.FactorySprintBacklog;
import org.switch2022.project.factories.IFactoryPeriod;
import org.switch2022.project.factories.IFactorySprintBacklog;
import org.switch2022.project.utils.Period;
import org.switch2022.project.utils.Effort;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.switch2022.project.model.UserStory.createUserStory;


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

    /**
     * Scenario 5: Verify the same object returns true when comparing the same object.
     */
    @Test
    public void testEqualsShouldReturnTrueWhenComparingTheSameObject() {
        // Arrange
        Sprint sprint = Sprint.createSprint(LocalDate.now(), 14, "1", mock(IFactoryPeriod.class), mock(IFactorySprintBacklog.class));
        // Act
        boolean result = sprint.equals(sprint);
        // Assert
        assertTrue(result);
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

    /**
     * METHOD estimateEffortUserStory(userStoryDto, effort)
     * <p>
     * Scenario 1: sets the effort of a UserStory.
     */

    @Test
    void ensureEffortIsSetForUserStory() {
        //Arrange
        LocalDate date = LocalDate.of(2021, 9, 13);

        IFactoryPeriod factoryPeriod = new FactoryPeriod();
        IFactorySprintBacklog factorySprintBacklog = new FactorySprintBacklog();
        Sprint sprint = Sprint.createSprint(date, 2, "S55", factoryPeriod, factorySprintBacklog);

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
     * Scenario 2: does not set the effort of a userStory because userStoryNumber does not exist.
     */
    @Test
    void ensureEffortIsNotSetForUserStory() {
        //Arrange
        LocalDate date = LocalDate.of(2021, 9, 13);
        IFactoryPeriod factoryPeriod = new FactoryPeriod();
        IFactorySprintBacklog factorySprintBacklog = new FactorySprintBacklog();
        Sprint sprint = Sprint.createSprint(date, 2, "S55", factoryPeriod, factorySprintBacklog);


        UserStory userStory = createUserStory("US001", "Manager",
                "I want to create a profile");
        sprint.addUserStoryToSprintBacklog(userStory);

        UserStoryDto userStoryDto = new UserStoryDto("US002", "Manager",
                "I want to create a profile");
        Effort effort = Effort.THREE;
        //Act
        boolean result = sprint.estimateEffortUserStory(userStoryDto, effort);

        //Assert
        assertFalse(result);
    }

    /**
     * METHOD hasUserStory(userStoryNumber).
     * Verifies if the Sprint has a given userStoryNumber, making use of hasUserStory from SprintBacklog
     * Scenario 1: returns True
     */
    @Test
    void ensureThatUserStoryIsNotPresentInTheSprintBacklog() {
        //Arrange
        IFactoryPeriod IFactoryPeriodDouble = mock(IFactoryPeriod.class);
        SprintBacklog sprintBacklogDouble = mock(SprintBacklog.class);
        IFactorySprintBacklog factorySprintBacklogDouble =
                mock(FactorySprintBacklog.class);
        when(factorySprintBacklogDouble.createSprintBacklog()).thenReturn(sprintBacklogDouble);
        Sprint sprint = Sprint.createSprint(LocalDate.of(2023, 3, 9),
                3, "S035", IFactoryPeriodDouble, factorySprintBacklogDouble);
        when(sprintBacklogDouble.hasUserStory("US001")).thenReturn(true);

        //Act
        boolean result = sprint.hasUserStory("US001");

        //Assert
        assertTrue(result);
    }
    /**
     * Scenario 2: returns False
     */
    @Test
    void ensureThatUserStoryIsPresentInTheSprintBacklog() {
        //Arrange
        IFactoryPeriod IFactoryPeriodDouble = mock(IFactoryPeriod.class);
        SprintBacklog sprintBacklogDouble = mock(SprintBacklog.class);
        IFactorySprintBacklog factorySprintBacklogDouble =
                mock(FactorySprintBacklog.class);
        when(factorySprintBacklogDouble.createSprintBacklog()).thenReturn(sprintBacklogDouble);
        Sprint sprint = Sprint.createSprint(LocalDate.of(2023, 3, 9),
                3, "S035", IFactoryPeriodDouble, factorySprintBacklogDouble);
        when(sprintBacklogDouble.hasUserStory("US001")).thenReturn(false);

        //Act
        boolean result = sprint.hasUserStory("US001");

        //Assert
        assertFalse(result);
    }

    /**
     * Method isDateWithinPeriod(date).
     * checks if date is equal or greater than start date and equal or lower than end date.
     * * Scenario 1: Date is within period.
     */
    @Test
    public void ensureDateIsWithinPeriod() {
        //Arrange
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        Period periodDouble = mock(Period.class);
        when(factoryPeriodDouble.createPeriod(LocalDate.of(2022, 1, 1), 3)).thenReturn(periodDouble);
        when(periodDouble.isDateEqualOrGreaterThanStartDate(LocalDate.of(2022, 1, 15))).thenReturn(true);
        when(periodDouble.isDateEqualOrLowerThanEndDate(LocalDate.of(2022, 1, 15))).thenReturn(true);
        LocalDate dateToCompare = LocalDate.of(2022, 1, 15);
        Sprint sprintToTest = Sprint.createSprint(LocalDate.of(2022, 1, 1), 3, "SP001", factoryPeriodDouble, factorySprintBacklogDouble);

        //Act
        boolean result = sprintToTest.isDateWithinPeriod(dateToCompare);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Start date and  date to compare are equal.
     */
    @Test
    public void ensureDateIsWithinPeriodBecauseEqualsStartDate() {
        //Arrange
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        Period periodDouble = mock(Period.class);
        when(factoryPeriodDouble.createPeriod(LocalDate.of(2022, 1, 1), 3)).thenReturn(periodDouble);
        when(periodDouble.isDateEqualOrGreaterThanStartDate(LocalDate.of(2022, 1, 1))).thenReturn(true);
        when(periodDouble.isDateEqualOrLowerThanEndDate(LocalDate.of(2022, 1, 1))).thenReturn(true);
        LocalDate dateToCompare = LocalDate.of(2022, 1, 1);
        Sprint sprintToTest = Sprint.createSprint(LocalDate.of(2022, 1, 1), 3, "SP001", factoryPeriodDouble, factorySprintBacklogDouble);

        //Act
        boolean result = sprintToTest.isDateWithinPeriod(dateToCompare);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 3: End date and date to compare are equal.
     */
    @Test
    public void ensureDateIsWithinPeriodBecauseEqualsEndDate() {
        //Arrange
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        Period periodDouble = mock(Period.class);
        when(factoryPeriodDouble.createPeriod(LocalDate.of(2022, 1, 1), 3)).thenReturn(periodDouble);
        when(periodDouble.isDateEqualOrGreaterThanStartDate(LocalDate.of(2022, 1, 22))).thenReturn(true);
        when(periodDouble.isDateEqualOrLowerThanEndDate(LocalDate.of(2022, 1, 22))).thenReturn(true);
        LocalDate dateToCompare = LocalDate.of(2022, 1, 22);
        Sprint sprintToTest = Sprint.createSprint(LocalDate.of(2022, 1, 1), 3, "SP001", factoryPeriodDouble, factorySprintBacklogDouble);

        //Act
        boolean result = sprintToTest.isDateWithinPeriod(dateToCompare);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 4: Start date is greater than the date to compare.
     */
    @Test
    public void ensureDateIsNotWithinPeriodBecauseIsLowerThenStartDate() {
        //Arrange
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        Period periodDouble = mock(Period.class);
        when(factoryPeriodDouble.createPeriod(LocalDate.of(2022, 1, 1), 3)).thenReturn(periodDouble);
        when(periodDouble.isDateEqualOrGreaterThanStartDate(LocalDate.of(2021, 12, 31))).thenReturn(false);
        when(periodDouble.isDateEqualOrLowerThanEndDate(LocalDate.of(2021, 12, 31))).thenReturn(true);
        LocalDate dateToCompare = LocalDate.of(2021, 12, 31);
        Sprint sprintToTest = Sprint.createSprint(LocalDate.of(2022, 1, 1), 3, "SP001", factoryPeriodDouble, factorySprintBacklogDouble);

        //Act
        boolean result = sprintToTest.isDateWithinPeriod(dateToCompare);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: End date is lower than the date to compare.
     */
    @Test
    public void ensureDateIsNotWithinPeriodBecauseIsGreaterThenEndDate() {
        //Arrange
        IFactorySprintBacklog factorySprintBacklogDouble = mock(FactorySprintBacklog.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        Period periodDouble = mock(Period.class);
        when(factoryPeriodDouble.createPeriod(LocalDate.of(2022, 1, 1), 3)).thenReturn(periodDouble);
        when(periodDouble.isDateEqualOrGreaterThanStartDate(LocalDate.of(2022, 1, 23))).thenReturn(true);
        when(periodDouble.isDateEqualOrLowerThanEndDate(LocalDate.of(2022, 1, 23))).thenReturn(false);
        LocalDate dateToCompare = LocalDate.of(2022, 1, 23);
        Sprint sprintToTest = Sprint.createSprint(LocalDate.of(2022, 1, 1), 3, "SP001", factoryPeriodDouble, factorySprintBacklogDouble);

        //Act
        boolean result = sprintToTest.isDateWithinPeriod(dateToCompare);

        //Assert
        assertFalse(result);
    }
}