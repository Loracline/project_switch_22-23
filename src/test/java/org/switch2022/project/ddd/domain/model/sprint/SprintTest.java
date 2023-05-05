package org.switch2022.project.ddd.domain.model.sprint;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.Effort;
import org.switch2022.project.ddd.domain.value_object.Period;
import org.switch2022.project.ddd.domain.value_object.UsId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class SprintTest {

    /**
     * Constructor tests.
     * Scenario 1: Sprint is not created when the Period is null. Should
     * throw an exception.
     */

    @Test
    public void ensureSprintIsNotCreateBecausePeriodIsNull() {
        //Arrange
        String expected = "Period cannot be null";
        int sprintNumber = 2;
        String projectCode = "P1";

        // Act
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Sprint(projectCode, sprintNumber, null));

        //Assert
        assertEquals(expected, result.getMessage());

    }
    /**
     * Scenario 2: Sprint is not created when the Sprint NUmber is negative. Should
     * throw an exception.
     */

    @Test
    public void ensureSprintIsNotCreateBecauseSprintNumberISNegative() {
        //Arrange
        String projectCode = "P1";
        Period period = mock(Period.class);
        String expected = "The Sprint Number must not be negative";

        // Act
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Sprint(projectCode,-2, period));

        //Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * METHOD EQUALS
     * <br>
     * Scenario 1: Verify if the same object don't equal itself.
     */
    @Test
    public void testEqualsWhenDifferentSprintNumber() {
        // Arrange
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprintOne = new Sprint(projectCode,3, period);
        Sprint sprintTwo = new Sprint(projectCode,2, period);
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
        String projectCode = "P1";
        Period period = mock(Period.class);
        Sprint sprintOne = new Sprint(projectCode,3, period);
        Sprint sprintTwo = new Sprint(projectCode,3, period);
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
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprintOne = new Sprint(projectCode,3, period);
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
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprintOne = new Sprint(projectCode,3, period);
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
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprintOne = new Sprint(projectCode,3, period);
        Sprint sprintTwo = sprintOne;
        // Act
        boolean result = sprintTwo.equals(sprintOne);
        // Assert
        assertTrue(result);
    }

    /**
     * METHOD HASHCODE
     * <br>
     * Scenario 1: Two Sprint objects are the same.
     */
    @Test
    public void ensureTwoSprintHashcodeAreTheSame() {
        //Arrange
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprintOne = new Sprint(projectCode,3, period);
        Sprint sprintTwo = new Sprint(projectCode,3, period);

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
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprintOne = new Sprint(projectCode,3, period);
        Sprint sprintTwo = new Sprint(projectCode,2, period);

        //Act
        int sprintOneHashCode = sprintOne.hashCode();
        int sprintThreeHashCode = sprintTwo.hashCode();

        //Assert
        assertNotEquals(sprintOneHashCode, sprintThreeHashCode);
    }

    /**
     * METHOD sameIdentityAs()
     * <br>
     * Scenario 1: Check if two instances of Sprint are equal if the value of their
     * sprintNumber are the same.
     */

    @Test
    void ensureThatTwoTypologiesAreTheSame() {
        //Arrange
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint reference = new Sprint(projectCode,8, period);
        Sprint other = new Sprint(projectCode,8, period);
        boolean expected = true;

        //Act
        boolean result = reference.sameIdentityAs(other);

        //Assert
        assertEquals(expected, result);
    }

    /*
    @Test
    void ensureThatUserStoryIsAddedSuccessfully() {
        //boolean result = sprint.addUserStory(userStoryInSprint);
    }*/

    /**
     * METHOD hasSprintNumber(sprintNumber)
     * Verifies if Sprint has a given Sprint Number.
     * <p>
     * Scenario 1: returns True.
     */

    @Test
    void ensureThatReturnsTrueIfSprintHasSprintNumber() {
        // Arrange
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprint= new Sprint(projectCode,8, period);
        String sprintNumberToCompare = "s008";

        // Act
        boolean result = sprint.hasSprintNumber(sprintNumberToCompare);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: returns false.
     */
    @Test
    void ensureThatReturnsFalseIfSprintDoesNotHaveSprintNumber() {
        // Arrange
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprint= new Sprint(projectCode,8, period);
        String sprintNumberToCompare = "s002";

        // Act
        boolean result = sprint.hasSprintNumber(sprintNumberToCompare);

        // Assert
        assertFalse(result);
    }

    /**
     * Method getSprintNumber() Returns the sprint number of the current object.
     */

    @Test
    void ensureThatSprintNumberIsReturnedSuccessfully() {
        // Arrange
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprint= new Sprint(projectCode,8, period);
        int expected = 8;

        // Act
        int result = sprint.getSprintNumber();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Method isDateWithinPeriod(date).
     * checks if date is equal or greater than start date and equal or lower than end
     * date.
     * * Scenario 1: Date is within period.
     */
    @Test
    public void ensureDateIsWithinPeriod() {
        //Arrange
        LocalDate dateToCompare = LocalDate.of(2022, 1, 15);
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprint= new Sprint(projectCode,8, period);
        when(period.isDateEqualOrGreaterThanStartDate(any())).thenReturn(true);
        when(period.isDateEqualOrLowerThanEndDate(any())).thenReturn(true);

        //Act
        boolean result = sprint.isDateWithinPeriod(dateToCompare);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Start date is greater than the date to compare.
     */
    @Test
    public void ensureDateIsNotWithinPeriodBecauseIsLowerThenStartDate() {
        //Arrange
        LocalDate dateToCompare = LocalDate.of(2022, 1, 15);
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprint= new Sprint(projectCode,8, period);
        when(period.isDateEqualOrGreaterThanStartDate(any())).thenReturn(false);
        when(period.isDateEqualOrLowerThanEndDate(any())).thenReturn(true);


        //Act
        boolean result = sprint.isDateWithinPeriod(dateToCompare);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: End date is lower than the date to compare.
     */
    @Test
    public void ensureDateIsNotWithinPeriodBecauseIsGreaterThenEndDate() {
        //Arrange
        LocalDate dateToCompare = LocalDate.of(2022, 1, 15);
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprint= new Sprint(projectCode,8, period);
        when(period.isDateEqualOrGreaterThanStartDate(any())).thenReturn(true);
        when(period.isDateEqualOrLowerThanEndDate(any())).thenReturn(false);

        //Act
        boolean result = sprint.isDateWithinPeriod(dateToCompare);

        //Assert
        assertFalse(result);
    }

    /**
     * METHOD isDateBeforePeriod(date) checks if date is before the sprint period.
     * <p>
     * Scenario 1: the given date is before the User Story period. Should return TRUE.
     */
    @Test
    void ensureThatDateIsBeforeSprintPeriod() {
        //Arrange
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprint= new Sprint(projectCode,8, period);
        when(period.isDateEqualOrGreaterThanStartDate(any())).thenReturn(false);
        LocalDate date = LocalDate.of(2021, 12, 31);

        //Act
        boolean result = sprint.isDateBeforePeriod(date);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: the given date is not before the User Story period. Should return
     * FALSE.
     */
    @Test
    void ensureThatDateIsNotBeforeSprintPeriod() {
        //Arrange
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprint= new Sprint(projectCode,8, period);
        when(period.isDateEqualOrGreaterThanStartDate(any())).thenReturn(true);
        LocalDate date = LocalDate.of(2023, 12, 31);

        //Act
        boolean result = sprint.isDateBeforePeriod(date);

        //Assert
        assertFalse(result);
    }

    /**
     * METHOD isPeriodNotOverlapping()
     * Scenario 1 : return true if period is not overlapping
     */
    @Test
    void ensurePeriodIsNotOverlapping() {
        //Arrange
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprint= new Sprint(projectCode,8, period);
        when(period.isPeriodNotOverlapping(any())).thenReturn(true);

        Sprint sprintOne = new Sprint(projectCode, 2, period);
        //Act
        boolean result = sprint.isPeriodNotOverlapping(sprintOne);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: return false if period is overlapping.
     */
    @Test
    void ensurePeriodIsOverlapping() {
        //Arrange
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprint = new Sprint(projectCode, 8, period);
        when(period.isPeriodNotOverlapping(any())).thenReturn(false);

        Sprint sprintOne = new Sprint(projectCode, 2, period);
        //Act
        boolean result = sprint.isPeriodNotOverlapping(sprintOne);

        //Assert
        assertFalse(result);
    }

    /**
     * METHOD addUserStory()
     * Scenario 1 : return true if userStory is added, because the list is empty.
     */

    @Test
    void ensureUserStoryIsAdded_becauseTheListIsEmpty() {
        //Arrange
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprint = new Sprint(projectCode, 1, period);
        UsId usId = mock(UsId.class);
        Effort effort = mock(Effort.class);
        //Act
        boolean result = sprint.addUserStory(usId, effort);

        //Assert
        assertTrue(result);
    }


    /**
     * Scenario 2 : return true if userStory is added, because the US is not already there.
     */
    @Test
    void ensureUserStoryIsAdded_becauseUSisNotAlreadyThere() {
        //Arrange
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprint = new Sprint(projectCode, 1, period);
        UsId usId = mock(UsId.class);
        Effort effort = mock(Effort.class);
        UsId usIdDouble = mock(UsId.class);
        sprint.addUserStory(usIdDouble, effort);
        //Act
        boolean result = sprint.addUserStory(usId, effort);

        //Assert
        assertTrue(result);
    }


    /**
     * Scenario 3 : return false because the US is already there.
     */
    @Test
    void ensureUserStoryIsNotAdded_becauseUSisAlreadyThere() {
        //Arrange
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprint = new Sprint(projectCode, 1, period);
        UsId usId = mock(UsId.class);
        Effort effort = mock(Effort.class);
        sprint.addUserStory(usId, effort);
        //Act
        boolean result = sprint.addUserStory(usId, effort);

        //Assert
        assertFalse(result);
    }

    /**
     * METHOD hasUserStory()
     * Scenario 1 : return true if it has the userStory.
     */

    @Test
    void ensureItReturnsTrueIfItHasUserStory() {
        //Arrange
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprint = new Sprint(projectCode, 1, period);
        UsId usId = mock(UsId.class);
        Effort effort = mock(Effort.class);
        sprint.addUserStory(usId, effort);

        //Act
        boolean result = sprint.hasUserStory(usId);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2 : return false because the userStory is not in list.
     */

    @Test
    void ensureItReturnsFalseBecauseUserStoryIsNotInTheList() {
        //Arrange
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprint = new Sprint(projectCode, 1, period);
        UsId usId = mock(UsId.class);
        Effort effort = mock(Effort.class);
        UsId usIdDouble = mock(UsId.class);
        sprint.addUserStory(usIdDouble, effort);

        //Act
        boolean result = sprint.hasUserStory(usId);

        //Assert
        assertFalse(result);
    }

    /**
     * METHOD estimateEffortUserStory()
     * Scenario 1 : return true if Effort changed.
     */

    @Test
    void ensureEstimateEffortHasChanged() {
        //Arrange
        LocalDate date = mock(LocalDate.class);
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprint = new Sprint(projectCode, 1, period);
        UsId usId = mock(UsId.class);
        Effort effort = Effort.TWO;
        sprint.addUserStory(usId, effort);
        when(date.isAfter(any())).thenReturn(true);
        //when(effort.sameValueAs(effort)).thenReturn(true);

        //Act
        boolean result = sprint.estimateEffortUserStory(usId,effort,date);

        //Assert
        assertTrue(result);
    }

    /**
    * Scenario 2 : return false because date is after start date.
    */

    @Test
    void ensureEstimateEffortIsNotChangedBecauseDateIsAfterStartDate() {
        //Arrange
        LocalDate date = mock(LocalDate.class);
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprint = new Sprint(projectCode, 1, period);
        UsId usId = mock(UsId.class);
        Effort effort = Effort.TWO;
        when(date.isAfter(any())).thenReturn(false);

        //Act
        boolean result = sprint.estimateEffortUserStory(usId,effort,date);

        //Assert
        assertFalse(result);
    }

    /**
     * METHOD getSprintBacklog()
     * Scenario 1 : ensure it returns an empty list.
     */

    @Test
    void ensureItReturnsAnEmptyList(){
        //Arrange
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprint = new Sprint(projectCode, 1, period);
        List<UsId> expected = new ArrayList<>();

        //Act
        List<UsId> result = sprint.getSprintBacklog();

        //Assert
        assertEquals(result, expected);

    }

    /**
     * Scenario 2: ensure it returns a list of UsId's.
     */
    @Test
    void ensureItReturnsAListOfUsIds(){
        //Arrange
        Period period = mock(Period.class);
        String projectCode = "P1";
        Sprint sprint = new Sprint(projectCode, 1, period);
        Effort effort = mock(Effort.class);
        List<UsId> expected = new ArrayList<>();
        UsId usId = mock(UsId.class);
        UsId usIdDouble = mock(UsId.class);
        UsId usIdTriple = mock(UsId.class);
        expected.add(usId);
        expected.add(usIdDouble);
        expected.add(usIdTriple);
        sprint.addUserStory(usId,effort);
        sprint.addUserStory(usIdDouble,effort);
        sprint.addUserStory(usIdTriple,effort);

        //Act
        List<UsId> result = sprint.getSprintBacklog();

        //Assert
        assertEquals(expected,result);

    }





}