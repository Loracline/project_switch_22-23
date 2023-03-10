package org.switch2022.project.utils;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class PeriodTest {

    /**
     * Test the getStartDate method.
     */
    @Test
    public void testGetStartDate() {
        //Arrange
        LocalDate startDate = LocalDate.of(2023, 3, 8);
        //Act
        Period period = new Period(startDate, 2);
        //Assert
        assertEquals(startDate, period.getStartDate());
    }

    /**
     * Test the getEndDate method.
     */
    @Test
    public void testGetEndDate() {
        //Arrange
        LocalDate startDate = LocalDate.of(2023, 3, 8);
        //Act
        Period period = new Period(startDate, 2);
        LocalDate expectedEndDate = LocalDate.of(2023, 3, 22);
        //Assert
        assertEquals(expectedEndDate, period.getEndDate());
    }

    /**
     * Test the calculateEndDate method.
     */

    @Test
    public void testCalculateEndDate() {
        //Arrange
        LocalDate startDate = LocalDate.of(2023, 3, 1);
        int sprintDuration = 2;
        //Act
        Period period = new Period(startDate, sprintDuration);
        LocalDate expectedEndDate = LocalDate.of(2023, 3, 15);
        LocalDate actualEndDate = period.getEndDate();
        //Assert
        assertEquals(expectedEndDate, actualEndDate);
    }

    /**
     * Verify the class constructor.
     */

    @Test
    void testConstructor() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        int sprintDuration = 2;
        //Act
        Period period = new Period(startDate, sprintDuration);
        //Assert
        assertNotNull(period.getStartDate());
        assertNotNull(period.getEndDate());
    }


    /**
     * Verify getStartDate with the actual date ( Now ).
     */

    @Test
    void testGetStartDateNow() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        int sprintDuration = 2;
        Period period = new Period(startDate, sprintDuration);
        //Assert
        assertEquals(startDate, period.getStartDate());
    }

    /**
     * Verify getEndDate with the actual date ( Now ).
     */

    @Test
    void testGetEndDateNow() {
        // Arrange
        LocalDate startDate = LocalDate.now();
        int sprintDuration = 2;
        Period period = new Period(startDate, sprintDuration);
        // Act
        LocalDate expectedEndDate = startDate.plusWeeks(sprintDuration);
        LocalDate actualEndDate = period.getEndDate();
        // Assert
        assertEquals(expectedEndDate, actualEndDate);
    }

    /**
     * Verify calculateEndDate with the actual date ( Now ).
     */
    @Test
    void testCalculateEndDateNow() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        int sprintDuration = 2;
        Period period = new Period(startDate, sprintDuration);
        //Assert
        assertEquals(startDate.plusWeeks(sprintDuration), period.getEndDate());
    }

   /*
      METHOD equals()
     */

    /**
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    public void ensurePeriodEqualsItself() {
        //Arrange
        LocalDate startDate = LocalDate.of(2023, 3, 1);
        int sprintDuration = 2;
        Period period1 = new Period(startDate, sprintDuration);
        Period period2 = new Period(startDate, sprintDuration);
        //Assert
        assertTrue(period1.equals(period2));
    }

    /**
     * Scenario 2: Scenario 2: Verify if two objects of the same class are different from
     * each other.
     */

    @Test
    void ensurePeriodsAreNotTheSame() {
        // Arrange
        LocalDate startDate = LocalDate.now();
        int sprintDuration = 2;
        Period period = new Period(startDate, sprintDuration);
        String str = "some string";

        // Act
        boolean result = period.equals(str);

        // Assert
        assertFalse(result);
    }

    /*
      METHOD hashCode()
     */

    /**
     * Scenario 1: Two Period objects are the same.
     */
    @Test
    void testHashCodeForEqualPeriods() {
        // Arrange
        LocalDate startDate = LocalDate.of(2023, 3, 1);
        int sprintDuration = 4;
        Period period1 = new Period(startDate, sprintDuration);
        Period period2 = new Period(startDate, sprintDuration);

        // Act
        int hashCode1 = period1.hashCode();
        int hashCode2 = period2.hashCode();

        // Assert
        assertEquals(hashCode1, hashCode2);
    }

    /**
     * Scenario 2: Two Period objects are not the same.
     */
    @Test
    void testHashCodeForUnequalPeriods() {
        // Arrange
        LocalDate startDate1 = LocalDate.of(2023, 3, 1);
        int sprintDuration1 = 4;
        Period period1 = new Period(startDate1, sprintDuration1);

        LocalDate startDate2 = LocalDate.of(2023, 3, 1);
        int sprintDuration2 = 5;
        Period period2 = new Period(startDate2, sprintDuration2);

        // Act
        int hashCode1 = period1.hashCode();
        int hashCode2 = period2.hashCode();

        // Assert
        assertNotEquals(hashCode1, hashCode2);
    }

    /*
      METHOD isPeriodNotOverlapping()
     */

    /**
     * Scenario 1: when period 1 do not overlap period 2.
     */

    @Test
    public void testIsPeriodNotOverlappingWhenPeriod1DoesNotOverlapPeriod2() {
        // Arrange
        LocalDate startDate1 = LocalDate.of(2022, 1, 1);
        Period period1 = new Period(startDate1, 3);
        Period period2 = new Period(startDate1.plusMonths(6), 3);
        boolean expected = true;
        //Act
        boolean result = period1.isPeriodNotOverlapping(period2);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: when period 1 do not overlap period 2.
     */
    @Test
    public void testIsPeriodNotOverlappingWhenPeriod2DoesNotOverlapPeriod1() {
        // Arrange
        LocalDate startDate1 = LocalDate.of(2022, 1, 1);
        Period period1 = new Period(startDate1, 3);
        Period period2 = new Period(startDate1.plusMonths(6), 3);
        boolean expected = true;
        //Act
        boolean result = period2.isPeriodNotOverlapping(period1);
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: when one period is before the other.
     */

    @Test
    public void testIsPeriodNotOverlappingWhenOnePeriodIsBeforeTheOther() {
        // Arrange
        LocalDate startDate1 = LocalDate.of(2022, 1, 1);
        Period period1 = new Period(startDate1, 5);
        LocalDate startDate2 = LocalDate.of(2022, 7, 1);
        Period period2 = new Period(startDate2, 5);
        boolean expected = true;

        // Act
        boolean result = period1.isPeriodNotOverlapping(period2);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: when periods overlap.
     */

    @Test
    public void testIsPeriodNotOverlappingWhenPeriodsOverlap() {
        // Arrange
        LocalDate startDate1 = LocalDate.of(2022, 1, 1);
        Period period1 = new Period(startDate1, 5);
        LocalDate startDate2 = LocalDate.of(2022, 2, 1);
        Period period2 = new Period(startDate2, 5);
        boolean expected = false;

        // Assert
        boolean result = period1.isPeriodNotOverlapping(period2);
        //Act
        assertEquals(expected, result);
    }

    /**
     * Scenario 5: when periods are adjacent.
     */

    @Test
    public void testIsPeriodNotOverlappingWhenPeriodsAreAdjacent() {
        // Arrange
        LocalDate startDate1 = LocalDate.of(2022, 1, 1);
        Period period1 = new Period(startDate1, 3);
        LocalDate startDate2 = startDate1.plusWeeks(5);
        Period period2 = new Period(startDate2, 3);
        boolean expected = true;
        //Act
        boolean result = period1.isPeriodNotOverlapping(period2);
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 6: when periods are the same.
     */

    @Test
    public void testIsPeriodNotOverlappingWhenPeriodsAreTheSame() {
        // Arrange
        LocalDate startDate1 = LocalDate.of(2022, 1, 1);
        Period period1 = new Period(startDate1, 3);
        Period period2 = new Period(startDate1, 3);
        boolean expected = false;
        //Act
        boolean result = period1.isPeriodNotOverlapping(period2);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 7: not overlapping when given period ens before this period starts.
     */
    @Test
    public void testIsPeriodNotOverlappingWhenGivenPeriodEndsBeforeThisPeriodStarts() {
        //Arrange
        LocalDate startDate1 = LocalDate.of(2022, 1, 1);
        Period period1 = new Period(startDate1, 3);
        Period period2 = new Period(startDate1.plusWeeks(4), 3);
        boolean expected = true;

        // Act
        boolean result = period1.isPeriodNotOverlapping(period2);
        //Assert
        assertEquals(expected, result);
    }

    /*
      METHOD isStartDateBeforeNow()
     */

    /**
     * Scenario 1: when start date is in the past.
     */
    @Test
    public void testIsStartDateBeforeNowWhenStartDateIsInPast() {
        // Arrange
        LocalDate pastDate = LocalDate.now().minusDays(7);
        Period pastPeriod = new Period(pastDate, 2);
        boolean expect = true;
        // Act
        boolean result = pastPeriod.isStartDateBeforeNow();
        // Assert
        assertEquals(expect, result);
    }

    /**
     * Scenario 2: when start date is in the future.
     */
    @Test
    public void testIsStartDateBeforeNowWhenStartDateIsInFuture() {
        // Arrange
        LocalDate futureDate = LocalDate.now().plusDays(7);
        Period futurePeriod = new Period(futureDate, 2);
        boolean expected = false;

        // Act
        boolean result = futurePeriod.isStartDateBeforeNow();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method isDateWithinPeriod(date).
     * checks if date is equal or greater than start date and equal or lower than end date.
     * <p>
     * Scenario 1: Date is within period.
     */
    @Test
    public void ensureDateIsWithinPeriod() {
        //Arrange
        LocalDate startDate1 = LocalDate.of(2022, 1, 1);
        Period period1 = new Period(startDate1, 3);
        LocalDate dateToCompare = LocalDate.of(2022, 1, 15);

        //Act
        boolean result = period1.isDateWithinPeriod(dateToCompare);

        //Assert
        assertTrue(result);

    }

    /**
     * Scenario 2: Start date and  date to compare are equal.
     */
    @Test
    public void ensureDateIsWithinPeriodBecauseEqualsStartDate() {
        //Arrange
        LocalDate startDate1 = LocalDate.of(2022, 1, 1);
        Period period1 = new Period(startDate1, 3);
        LocalDate dateToCompare = LocalDate.of(2022, 1, 1);

        //Act
        boolean result = period1.isDateWithinPeriod(dateToCompare);

        //Assert
        assertTrue(result);

    }

    /**
     * Scenario 3: End date and date to compare are equal.
     */
    @Test
    public void ensureDateIsWithinPeriodBecauseEqualsEndDate() {
        //Arrange
        LocalDate startDate1 = LocalDate.of(2022, 1, 1);
        Period period1 = new Period(startDate1, 3);
        LocalDate dateToCompare = LocalDate.of(2022, 1, 22);

        //Act
        boolean result = period1.isDateWithinPeriod(dateToCompare);

        //Assert
        assertTrue(result);

    }

    /**
     * Scenario 4: Start date is greater than the date to compare.
     */
    @Test
    public void ensureDateIsNotWithinPeriodBecauseIsLowerThenStartDate() {
        //Arrange
        LocalDate startDate1 = LocalDate.of(2022, 1, 1);
        Period period1 = new Period(startDate1, 3);
        LocalDate dateToCompare = LocalDate.of(2021, 12, 31);

        //Act
        boolean result = period1.isDateWithinPeriod(dateToCompare);

        //Assert
        assertFalse(result);

    }

    /**
     * Scenario 5: End date is lower than the date to compare.
     */
    @Test
    public void ensureDateIsNotWithinPeriodBecauseIsGreaterThenEndDate() {
        //Arrange
        LocalDate startDate1 = LocalDate.of(2022, 1, 1);
        Period period1 = new Period(startDate1, 3);
        LocalDate dateToCompare = LocalDate.of(2022, 1, 23);

        //Act
        boolean result = period1.isDateWithinPeriod(dateToCompare);

        //Assert
        assertFalse(result);

    }
}