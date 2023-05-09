package org.switch2022.project.ddd.domain.model.sprint;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.*;

import java.time.LocalDate;

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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintNumber sprintNumberOne = new SprintNumber(1);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        SprintId sprintIdOne = new SprintId(projectCode.toString(), sprintNumberOne.getSprintNumber());
        Sprint sprintOne = new Sprint(projectCode,sprintIdOne, sprintNumberOne,period);
        Sprint sprintTwo = new Sprint(projectCode,sprintId,sprintNumber, period);
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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprintOne = new Sprint(projectCode,sprintId,sprintNumber, period);
        Sprint sprintTwo = new Sprint(projectCode,sprintId,sprintNumber, period);
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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprintOne = new Sprint(projectCode,sprintId,sprintNumber, period);
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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprintOne = new Sprint(projectCode,sprintId,sprintNumber, period);
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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprintOne = new Sprint(projectCode,sprintId,sprintNumber, period);
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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprintOne = new Sprint(projectCode,sprintId,sprintNumber, period);
        Sprint sprintTwo = new Sprint(projectCode,sprintId,sprintNumber, period);

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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintNumber sprintNumberOne = new SprintNumber(1);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        SprintId sprintIdOne = new SprintId(projectCode.toString(), sprintNumberOne.getSprintNumber());
        Sprint sprintOne = new Sprint(projectCode,sprintIdOne, sprintNumberOne,period);
        Sprint sprintTwo = new Sprint(projectCode,sprintId,sprintNumber, period);

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
     * sprintNumber are the same.
     */

    @Test
    void ensureThatTwoSprintsAreTheSame() {
        //Arrange
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint reference = new Sprint(projectCode,sprintId,sprintNumber, period);
        Sprint other = new Sprint(projectCode,sprintId,sprintNumber, period);
        boolean expected = true;

        //Act
        boolean result = reference.sameIdentityAs(other);

        //Assert
        assertEquals(expected, result);
    }


    /**
     * METHOD hasSprintNumber(sprintNumber)
     * Verifies if Sprint has a given Sprint Number.
     * <p>
     * Scenario 1: returns True.
     */

    @Test
    void ensureThatReturnsTrueIfSprintHasSprintNumber() {
        // Arrange
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint= new Sprint(projectCode,sprintId,sprintNumber, period);
        String sprintNumberToCompare = "s002";

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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint= new Sprint(projectCode,sprintId,sprintNumber, period);
        String sprintNumberToCompare = "s008";

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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint= new Sprint(projectCode,sprintId,sprintNumber, period);
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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint= new Sprint(projectCode,sprintId,sprintNumber, period);
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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint= new Sprint(projectCode,sprintId,sprintNumber, period);
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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint= new Sprint(projectCode,sprintId,sprintNumber, period);
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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint= new Sprint(projectCode,sprintId,sprintNumber, period);
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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint= new Sprint(projectCode,sprintId,sprintNumber, period);
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
    @Test
    void ensurePeriodIsNotOverlapping() {
        //Arrange
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        LocalDate endDate = mock(LocalDate.class);
        Period period = new Period(startDate,endDate);
        Period periodOne = new Period(startDate,endDate);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint= new Sprint(projectCode,sprintId,sprintNumber, period);
        Sprint sprintOne = new Sprint(projectCode,sprintId,sprintNumber, periodOne);
        when(period.isPeriodNotOverlapping(periodOne)).thenReturn(true);


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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        LocalDate endDate = mock(LocalDate.class);
        Period period = new Period(startDate,endDate);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint= new Sprint(projectCode,sprintId,sprintNumber, period);
        when(period.isPeriodNotOverlapping(period)).thenReturn(false);

        Sprint sprintOne = new Sprint(projectCode,sprintId,sprintNumber, period);
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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint= new Sprint(projectCode,sprintId,sprintNumber, period);
        UsId usId = new UsId(projectCode.getCode(), "US001");
        Effort effort = Effort.ONE;
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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint= new Sprint(projectCode,sprintId,sprintNumber, period);
        UsId usId = new UsId(projectCode.getCode(), "US001");
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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint= new Sprint(projectCode,sprintId,sprintNumber, period);
        UsId usId = new UsId(projectCode.getCode(), "US001");
        Effort effort = Effort.ONE;
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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint= new Sprint(projectCode,sprintId,sprintNumber, period);
        UsId usId = new UsId(projectCode.getCode(), "US001");
        Effort effort = Effort.ONE;
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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint= new Sprint(projectCode,sprintId,sprintNumber, period);
        UsId usId = new UsId(projectCode.getCode(), "US001");
        Effort effort = Effort.ONE;
        UsId usIdDouble = mock(UsId.class);
        sprint.addUserStory(usIdDouble, effort);

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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint= new Sprint(projectCode,sprintId,sprintNumber, period);
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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint= new Sprint(projectCode,sprintId,sprintNumber, period);
        UsId usId = new UsId(projectCode.getCode(), "US001");
        Effort effort = Effort.ONE;
        sprint.addUserStory(usId, effort);
        when(period.isDateEqualOrGreaterThanStartDate(date)).thenReturn(false);

        //Act
        boolean result = sprint.estimateEffortUserStory(usId, effort, date);

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
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint= new Sprint(projectCode,sprintId,sprintNumber, period);
        UsId usId = new UsId(projectCode.getCode(), "US001");
        Effort effort = Effort.ONE;
        when(period.isDateEqualOrGreaterThanStartDate(date)).thenReturn(true);

        //Act
        boolean result = sprint.estimateEffortUserStory(usId, effort, date);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 3 : returns false because date is equal to start date.
     */
    @Test
    void ensureEstimateEffortHasNotChanged() {
        //Arrange
        LocalDate date = mock(LocalDate.class);
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint= new Sprint(projectCode,sprintId,sprintNumber, period);
        UsId usId = new UsId(projectCode.getCode(), "US001");
        Effort effort = Effort.ONE;
        sprint.addUserStory(usId, effort);
        when(period.isDateEqualOrGreaterThanStartDate(date)).thenReturn(true);

        //Act
        boolean result = sprint.estimateEffortUserStory(usId, effort, date);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 4 : returns false because UserStory does not exist.
     */
    @Test
    void ensureEstimateEffortHasNotChangedBecauseUSDoesNotExist() {
        //Arrange
        LocalDate date = mock(LocalDate.class);
        Code projectCode =  new Code (1);
        LocalDate startDate= mock(LocalDate.class);
        Period period = new Period(startDate,2);
        SprintNumber sprintNumber = new SprintNumber(2);
        SprintId sprintId = new SprintId(projectCode.toString(), sprintNumber.getSprintNumber());
        Sprint sprint= new Sprint(projectCode,sprintId,sprintNumber, period);
        UsId usId = new UsId(projectCode.getCode(), "US001");
        UsId usIdDouble = mock(UsId.class);
        Effort effort = Effort.TWO;
        sprint.addUserStory(usIdDouble, effort);
        when(date.isAfter(any())).thenReturn(true);

        //Act
        boolean result = sprint.estimateEffortUserStory(usId, effort, date);

        //Assert
        assertFalse(result);
    }

}