package org.switch2022.project.ddd.domain.model.sprint;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class SprintTest {

    /**
     * METHOD EQUALS
     * <br>
     * Scenario 1: Verify if the same object don't equal itself.
     */
    @Test
    public void testEqualsWhenDifferentSprintNumber() {
        // Arrange
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintNumber sprintNumberOne = new SprintNumber(1);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        SprintId sprintIdOne = new SprintId(projectCode.toString(),
                sprintNumberOne.getSprintNumber());
        Sprint sprintOne = new Sprint(projectCode, sprintIdOne, sprintNumberOne, period);
        Sprint sprintTwo = new Sprint(projectCode, sprintId, sprintNumber, period);
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
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprintOne = new Sprint(projectCode, sprintId, sprintNumber, period);
        Sprint sprintTwo = new Sprint(projectCode, sprintId, sprintNumber, period);
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
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprintOne = new Sprint(projectCode, sprintId, sprintNumber, period);
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
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprintOne = new Sprint(projectCode, sprintId, sprintNumber, period);
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
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprintOne = new Sprint(projectCode, sprintId, sprintNumber, period);
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
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprintOne = new Sprint(projectCode, sprintId, sprintNumber, period);
        Sprint sprintTwo = new Sprint(projectCode, sprintId, sprintNumber, period);

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
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintNumber sprintNumberOne = new SprintNumber(1);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        SprintId sprintIdOne = new SprintId(projectCode.toString(),
                sprintNumberOne.getSprintNumber());
        Sprint sprintOne = new Sprint(projectCode, sprintIdOne, sprintNumberOne, period);
        Sprint sprintTwo = new Sprint(projectCode, sprintId, sprintNumber, period);

        //Act
        int sprintOneHashCode = sprintOne.hashCode();
        int sprintTwoHashCode = sprintTwo.hashCode();

        //Assert
        assertNotEquals(sprintOneHashCode, sprintTwoHashCode);
    }

    /**
     * METHOD sameIdentityAs()
     * <br>
     * Scenario 1: Check if two instances of Sprint are equal if the value of their
     * sprintID are the same.
     */

    @Test
    void ensureThatTwoSprintsAreTheSame() {
        //Arrange
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint reference = new Sprint(projectCode, sprintId, sprintNumber, period);
        Sprint other = new Sprint(projectCode, sprintId, sprintNumber, period);
        boolean expected = true;

        //Act
        boolean result = reference.sameIdentityAs(other);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Check if two instances of Sprint are not equal if the value of their
     * sprintID are different.
     */

    @Test
    void ensureThatTwoSprintsAreNotTheSame() {
        //Arrange
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint reference = new Sprint(projectCode, sprintId, sprintNumber, period);

        SprintNumber sprintNumberOther = new SprintNumber(1);
        SprintId sprintIdOther = new SprintId(projectCode.toString(),
                sprintNumberOther.getSprintNumber());
        Sprint other = new Sprint(projectCode, sprintIdOther, sprintNumber, period);

        boolean expected = false;

        //Act
        boolean result = reference.sameIdentityAs(other);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD hasSprintId(sprintID)
     * Verifies if Sprint has a given Sprint ID.
     * <p>
     * Scenario 1: returns True.
     */

    @Test
    void ensureThatReturnsTrueIfSprintHasSprintID() {
        // Arrange
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.getCode(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        SprintId sprintIdToCompare = new SprintId("p001", "s002");

        // Act
        boolean result = sprint.hasSprintId(sprintIdToCompare);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: returns false.
     */
    @Test
    void ensureThatReturnsFalseIfSprintDoesNotHaveSprintID() {
        // Arrange
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.getCode(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        SprintId sprintIdToCompare = new SprintId("p001", "s001");

        // Act
        boolean result = sprint.hasSprintId(sprintIdToCompare);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hasProjectCode(projectCode)
     * Verifies if Sprint has a given ProjectCode.
     * <p>
     * Scenario 1: returns True.
     */

    @Test
    void ensureThatReturnsTrueIfSprintHasProjectCode() {
        // Arrange
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.getCode(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);

        // Act
        boolean result = sprint.hasProjectCode(projectCode);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: returns false.
     */
    @Test
    void ensureThatReturnsFalseIfSprintDoesNotHaveProjectCode() {
        // Arrange
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.getCode(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        Code projectCodeOne = new Code(3);

        // Act
        boolean result = sprint.hasProjectCode(projectCodeOne);

        // Assert
        assertFalse(result);
    }

    /**
     * Method getSprintNumber() Returns the sprint number of the current object.
     */

    @Test
    void ensureThatSprintNumberIsReturnedSuccessfully() {
        // Arrange
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        int expected = 2;

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
        LocalDate dateToCompare = mock(LocalDate.class);
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        when(period.isDateEqualOrGreaterThanStartDate(dateToCompare)).thenReturn(true);
        when(period.isDateEqualOrLowerThanEndDate(dateToCompare)).thenReturn(true);

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
        LocalDate dateToCompare = mock(LocalDate.class);
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        when(period.isDateEqualOrGreaterThanStartDate(dateToCompare)).thenReturn(false);
        when(period.isDateEqualOrLowerThanEndDate(dateToCompare)).thenReturn(true);


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
        LocalDate dateToCompare = mock(LocalDate.class);
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        when(period.isDateEqualOrGreaterThanStartDate(dateToCompare)).thenReturn(true);
        when(period.isDateEqualOrLowerThanEndDate(dateToCompare)).thenReturn(false);

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
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        LocalDate date = mock(LocalDate.class);
        when(period.isDateEqualOrGreaterThanStartDate(date)).thenReturn(false);


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
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        LocalDate date = mock(LocalDate.class);
        when(period.isDateEqualOrGreaterThanStartDate(date)).thenReturn(true);


        //Act
        boolean result = sprint.isDateBeforePeriod(date);

        //Assert
        assertFalse(result);
    }

    /**
     * METHOD isPeriodNotOverlapping()
     * Scenario 1 : return true if period is not overlapping
     */
    /*
    @Test
    void ensurePeriodIsNotOverlapping() {
        //Arrange
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        LocalDate endDate = mock(LocalDate.class);
        Period period = new Period(startDate, endDate);
        Period periodOne = new Period(startDate, endDate);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        Sprint sprintOne = new Sprint(projectCode, sprintId, sprintNumber, periodOne);
        when(period.isPeriodNotOverlapping(periodOne)).thenReturn(true);


        //Act
        boolean result = sprint.isPeriodNotOverlapping(sprintOne);

        //Assert
        assertTrue(result);
    }*/

    /**
     * Scenario 2: return false if period is overlapping.
     */
    @Test
    void ensurePeriodIsOverlapping() {
        //Arrange
        Code projectCode = new Code(1);
        Period period = mock(Period.class);
        Period period2 = mock(Period.class);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        //Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        Sprint sprint = mock(Sprint.class);
        when(period.contains(period2)).thenReturn(true);

        Sprint sprintOne = new Sprint(projectCode, sprintId, sprintNumber, period);
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
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        UsId usId = new UsId(projectCode.getCode(), "US001");
        //Act
        boolean result = sprint.addUserStory(usId, 1);

        //Assert
        assertTrue(result);
    }


    /**
     * Scenario 2 : return true if userStory is added, because the US is not already there.
     */
    @Test
    void ensureUserStoryIsAdded_becauseUSisNotAlreadyThere() {
        //Arrange
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        UsId usId = new UsId(projectCode.getCode(), "US001");
        UsId usIdDouble = mock(UsId.class);
        sprint.addUserStory(usIdDouble, 1);
        //Act
        boolean result = sprint.addUserStory(usId, 1);

        //Assert
        assertTrue(result);
    }


    /**
     * Scenario 3 : return false because the US is already there.
     */
    @Test
    void ensureUserStoryIsNotAdded_becauseUSisAlreadyThere() {
        //Arrange
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        UsId usId = new UsId(projectCode.getCode(), "US001");
        sprint.addUserStory(usId, 1);
        //Act
        boolean result = sprint.addUserStory(usId, 1);

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
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        UsId usId = new UsId(projectCode.getCode(), "US001");
        sprint.addUserStory(usId, 1);

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
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        UsId usId = new UsId(projectCode.getCode(), "US001");
        UsId usIdDouble = mock(UsId.class);
        sprint.addUserStory(usIdDouble, 1);

        //Act
        boolean result = sprint.hasUserStory(usId);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 3 : return false because the userStory list is empty.
     */
    @Test
    void ensureItReturnsFalseBecauseUserStoryIsEmpty() {
        //Arrange
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        UsId usId = new UsId(projectCode.getCode(), "US001");

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
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        UsId usId = new UsId(projectCode.getCode(), "US001");
        sprint.addUserStory(usId, 1);
        when(period.isDateEqualOrGreaterThanStartDate(date)).thenReturn(false);

        //Act
        boolean result = sprint.estimateEffortUserStory(usId, 1);

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
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        UsId usId = new UsId(projectCode.getCode(), "US001");
        when(period.isDateEqualOrGreaterThanStartDate(date)).thenReturn(true);

        //Act
        boolean result = sprint.estimateEffortUserStory(usId, 1);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 3 : returns false because UserStory does not exist.
     */
    @Test
    void ensureEstimateEffortHasNotChangedBecauseUSDoesNotExist() {
        //Arrange
        LocalDate date = mock(LocalDate.class);
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        UsId usId = new UsId(projectCode.getCode(), "US001");
        UsId usIdDouble = mock(UsId.class);
        sprint.addUserStory(usIdDouble, 2);
        when(date.isAfter(any())).thenReturn(true);

        //Act
        boolean result = sprint.estimateEffortUserStory(usId, 2);

        //Assert
        assertFalse(result);
    }

    /**
     * METHOD getSprintBacklog()
     * Scenario 1 : ensure it returns an empty list.
     */
    @Test
    void ensureItReturnsAnEmptyList() {
        //Arrange
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
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
    void ensureItReturnsAListOfUsIds() {
        //Arrange
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        List<UsId> expected = new ArrayList<>();
        UsId usId = mock(UsId.class);
        UsId usIdDouble = mock(UsId.class);
        UsId usIdTriple = mock(UsId.class);
        expected.add(usId);
        expected.add(usIdDouble);
        expected.add(usIdTriple);
        sprint.addUserStory(usId, 1);
        sprint.addUserStory(usIdDouble, 2);
        sprint.addUserStory(usIdTriple, 3);

        //Act
        List<UsId> result = sprint.getSprintBacklog();

        //Assert
        assertEquals(expected, result);


    }

    /**
     * Method:isPeriodAfterOrEqualThanDate
     * scenario 1: returns true
     */
    @Test
    void ensurePeriodIsAfterOrEqualDate() {
        //Arrange
        LocalDate date = mock(LocalDate.class);
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        when(period.isDateEqualOrLowerThanStartDate(date)).thenReturn(true);
        //Act
        boolean result = sprint.isPeriodAfterOrEqualThanDate(date);
        //Assert
        assertTrue(result);
    }

    /**
     * scenario 2: returns false
     */
    @Test
    void ensurePeriodIsNotAfterOrEqualDate() {
        //Arrange
        LocalDate date = mock(LocalDate.class);
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        Period period = new Period(startDate, 2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        when(period.isDateEqualOrLowerThanStartDate(date)).thenReturn(false);
        //Act
        boolean result = sprint.isPeriodAfterOrEqualThanDate(date);
        //Assert
        assertFalse(result);
    }

    /**
     * Method: isEndDateBeforeOrGreaterThanDate
     * scenario 1: returns true
     */
    @Test
    void ensurePeriodIsBeforeOrEqualDate() {
        //Arrange
        LocalDate date = mock(LocalDate.class);
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        LocalDate endDate = mock(LocalDate.class);
        Period period = new Period(startDate, endDate);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        when(period.isDateEqualOrGreaterThanEndDate(date)).thenReturn(true);
        //Act
        boolean result = sprint.isEndDateBeforeOrGreaterThanDate(date);
        //Assert
        assertTrue(result);
    }

    /**
     * scenario 2: returns true
     */
    @Test
    void ensurePeriodIsNotBeforeOrEqualDate() {
        //Arrange
        LocalDate date = mock(LocalDate.class);
        Code projectCode = new Code(1);
        LocalDate startDate = mock(LocalDate.class);
        LocalDate endDate = mock(LocalDate.class);
        Period period = new Period(startDate, endDate);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);
        when(period.isDateEqualOrGreaterThanEndDate(date)).thenReturn(false);
        //Act
        boolean result = sprint.isEndDateBeforeOrGreaterThanDate(date);
        //Assert
        assertFalse(result);
    }

    /**
     * Method: getUserStoriesInSprint().
     * Scenario 1: This test evaluates if a list of User Story In Sprint is retrieved.
     */
    @Test
    public void ensureUserStoriesInSprintIsRetrieved() {
        //ARRANGE
        List<UserStoryInSprint> expected = new ArrayList<>();

        Code projectCode = mock(Code.class);
        SprintId sprintId = mock(SprintId.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        Period period = mock(Period.class);
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);

        //ACT
        List<UserStoryInSprint> result = sprint.getUserStoriesInSprint();

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Method: getSprintId()
     *
     * Scenario 1: verify that a sprintId is retrieved as a string.
     */
    @Test
    void ensureThatSprintIdIsRetrievedSuccessfully() {
        // Arrange
        String expected = "P001_S001";
        Code projectCode = mock(Code.class);
        SprintId sprintId = mock(SprintId.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        Period period = mock(Period.class);
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);

        when(sprintId.getSprintId()).thenReturn(expected);

        // Act
        String result = sprint.getSprintId();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Method: getProjectCode()
     *
     * Scenario 1: verify that a projectCode is retrieved as a string.
     */
    @Test
    void ensureThatProjectCodeIsRetrievedSuccessfully() {
        // Arrange
        String expected = "P001";
        Code projectCode = mock(Code.class);
        SprintId sprintId = mock(SprintId.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        Period period = mock(Period.class);
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);

        when(projectCode.getCode()).thenReturn(expected);

        // Act
        String result = sprint.getProjectCode();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Method: getStartDate()
     *
     * Scenario 1: verify that a startDate is retrieved as a string.
     */
    @Test
    void ensureThatStartDateIsRetrievedSuccessfully() {
        // Arrange
        String expected = "2023-07-23";
        Code projectCode = mock(Code.class);
        SprintId sprintId = mock(SprintId.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        Period period = mock(Period.class);
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);

        when(period.getStartDate()).thenReturn(expected);

        // Act
        String result = sprint.getStartDate();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Method: getEndDate()
     *
     * Scenario 1: verify that a endDate is retrieved as a string.
     */
    @Test
    void ensureThatEndDateIsRetrievedSuccessfully() {
        // Arrange
        String expected = "2023-08-06";
        Code projectCode = mock(Code.class);
        SprintId sprintId = mock(SprintId.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        Period period = mock(Period.class);
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);

        when(period.getEndDate()).thenReturn(expected);

        // Act
        String result = sprint.getEndDate();

        // Assert
        assertEquals(expected, result);
    }
    /**
     * Method: getFullSprintNumber()
     *
     * Scenario 1: verify that a full sprintNumber is retrieved as a string.
     */
    @Test
    void ensureFullSprintNumberIsRetrievedSuccessfully() {
        // Arrange
        String expected = "S001";
        Code projectCode = mock(Code.class);
        SprintId sprintId = mock(SprintId.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        Period period = mock(Period.class);
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);

        when(sprintNumber.getSprintNumber()).thenReturn(expected);

        // Act
        String result = sprint.getFullSprintNumber();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Method: getUSerStoriesInSprint()
     *
     * Scenario 1: verify that a list of userStoryInSprint is retrieved.
     */
    @Test
    void ensureThatUserStoriesInSprintIsRetrievedSuccessfully() {
        // Arrange
        Code projectCode = mock(Code.class);
        SprintId sprintId = mock(SprintId.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        Period period = mock(Period.class);
        Sprint sprint = new Sprint(projectCode, sprintId, sprintNumber, period);

        UsId usId1 = mock(UsId.class);
        UsId usId2 = mock(UsId.class);
        sprint.addUserStory(usId1, 1);
        sprint.addUserStory(usId2, 2);

        UserStoryInSprint userStoryInSprint1 = new UserStoryInSprint(usId1, Effort.ONE);
        UserStoryInSprint userStoryInSprint2 = new UserStoryInSprint(usId2, Effort.TWO);
        List<UserStoryInSprint> expected = Arrays.asList(userStoryInSprint1, userStoryInSprint2);

        // Act
        List<UserStoryInSprint> result = sprint.getUserStoriesInSprint();

        // Assert
        assertEquals(expected, result);
    }
}