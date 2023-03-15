package org.switch2022.project.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
        //Act
        LocalDate result = period.getStartDate();

        //Assert
        assertEquals(startDate, result);
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
        //Act
        LocalDate result = period.getEndDate();

        //Assert
        assertEquals(startDate.plusWeeks(sprintDuration), result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    public void ensurePeriodEqualsItself() {
        //Arrange
        LocalDate startDate = LocalDate.of(2023, 3, 1);
        int sprintDuration = 2;
        Period period1 = new Period(startDate, sprintDuration);
        Period period2 = new Period(startDate, sprintDuration);
        //Act
        boolean result = (period1.equals(period2));
        //Assert
        assertTrue(result);
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
        Period periodOne = new Period(startDate.plusDays(7),3);

        // Act
        boolean result = period.equals(periodOne);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: Scenario 3: Verify if a Period and a different type of object are not
     * the same.
     */
    @Test
    void ensurePeriodDoesNotEqualOtherTypeOfObject() {
        //Arrange
        LocalDate startDate = LocalDate.of(2023, 3, 1);
        int sprintDuration = 2;
        Period period = new Period(startDate, sprintDuration);
        String other = "12,3,2";
        boolean expected = false;
        //Act
        boolean result = period.equals(other);
        //Assert
        assertEquals(result, expected);
    }

    /**
     * Scenario 4: Verify if a Period and a null object are not the same.
     */
    @Test
    void ensurePeriodDoesNotEqualNull() {
        //Arrange
        LocalDate startDate = LocalDate.of(2023, 3, 1);
        int sprintDuration = 2;
        Period period = new Period(startDate, sprintDuration);
        Period other = null;
        boolean expected = false;
        //Act
        boolean result = period.equals(other);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 5: Verify if a Period is equal to it self.
     */
    @Test
    void ensurePeriodEqualsItSelf() {
        //Arrange
        LocalDate startDate = LocalDate.of(2023, 3, 1);
        int sprintDuration = 2;
        Period period = new Period(startDate, sprintDuration);
        Period other = period;
        boolean expected = true;
        //Act
        boolean result = period.equals(other);
        //Assert
        assertEquals(expected,result);
    }

    /**
     * METHOD hashCode()
     * <br>
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

    /**
     * METHOD isPeriodNotOverlapping()
     * <br>
     * Scenario 1: when period 1 do not overlap period 2.
     */
    @Test
    public void ensurePeriodIsNotOverlappingWhenPeriod1DoesNotOverlapPeriod2() {
        // Arrange
        LocalDate startDate1 = LocalDate.of(2022, 1, 1);
        Period period1 = new Period(startDate1, 3);
        LocalDate startDate2 = LocalDate.of(2022, 3, 19);
        Period period2 = new Period(startDate2, 3);
        boolean expected = true;

        //Act
        boolean result = period1.isPeriodNotOverlapping(period2);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: when period 2 do not overlap period 1.
     */
    @Test
    public void ensurePeriodIsNotOverlappingWhenPeriod2DoesNotOverlapPeriod1() {
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
    public void ensurePeriodIsNotOverlappingWhenPeriodsOverlap() {
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
     * Scenario 5: when startDate of Period1 is the startDate of Period2.
     * return false.
     */
    @Test
    public void ensurePeriodIsNotOverlappingWhenPeriodsAreAdjacent() {
        // Arrange
        LocalDate startDate1 = LocalDate.of(2022, 1, 1);
        Period period1 = new Period(startDate1, 3);
        LocalDate startDate2 = startDate1.plusWeeks(3);
        Period period2 = new Period(startDate2, 3);
        boolean expected = false;

        //Act
        boolean result = period1.isPeriodNotOverlapping(period2);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 6: when periods are the same.
     */
    @Test
    public void ensurePeriodIsNotOverlappingWhenPeriodsAreTheSame() {
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
     * Scenario 7: not overlapping when given period ends before this period starts.
     */
    @Test
    public void ensurePeriodIsNotOverlappingWhenGivenPeriodEndsBeforeThisPeriodStarts() {
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

    /**
     * Scenario 8: verify Period2 is inside Period1 duration.
     * should return false.
     */
    @Test
    public void ensurePeriodIsNotOverlappingWhenPeriodTwoIsInsidePeriodOne() {
        //Arrange
        LocalDate startDate1 = LocalDate.of(2022, 1, 1);
        Period period1 = new Period(startDate1, 3);
        Period period2 = new Period(startDate1.plusWeeks(1), 1);
        boolean expected = false;

        // Act
        boolean result = period1.isPeriodNotOverlapping(period2);

        //Assert
        assertEquals(expected, result);
    }
    /**
    *Scenario 9: verify endDate of Period2 is before startDate of Period1.
    * should return true.
    */
    @Test
    void ensureEndDateOfPeriodTwoIsBeforeStartDateOfPeriodOne() {
        //Arrange
        LocalDate startDateOne = LocalDate.of(2022, 1, 1);
        Period period1 = new Period(startDateOne, 3);
        LocalDate startDateTwo = LocalDate.of(2022,1,23);
        Period period2 = new Period(startDateTwo, 1);
        boolean expected = true;

        // Act
        boolean result = period1.isPeriodNotOverlapping(period2);

        //Assert
        assertEquals(expected, result);
    }

    /**
     *Scenario 10: verify endDate of Period2 is after startDate of Period1.
     * should return true.
     */
    @Test
    void ensureEndDateOfPeriodTwoIsAfterStartDateOfPeriodOne() {
        //Arrange
        LocalDate startDateOne = LocalDate.of(2022, 1, 1);
        Period period1 = new Period(startDateOne, 3);
        LocalDate startDateTwo = LocalDate.of(2022,1,21);
        Period period2 = new Period(startDateTwo, 1);
        boolean expected = false;

        // Act
        boolean result = period1.isPeriodNotOverlapping(period2);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD isStartDateBeforeNow()
     * <br>
     * Scenario 1: when start date is in the past.
     */
    @Test
    public void ensureIsStartDateBeforeNowWhenStartDateIsInPast() {
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
    public void ensureIsStartDateBeforeNowWhenStartDateIsInFuture() {
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
     * Scenario 3: start date is today.
     */
    @Test
    public void ensureStartDateBeforeNowIsUnsuccessful_today() {
        // Arrange
        LocalDate futureDate = LocalDate.now();
        Period futurePeriod = new Period(futureDate, 2);
        boolean expected = false;

        // Act
        boolean result = futurePeriod.isStartDateBeforeNow();

        //Assert
        assertEquals(expected, result);
    }


    /**
     * Method isDateEqualOrGreaterThanStartDate(date).
     * checks if date is equal or greater than start date.
     * Scenario 1: Date is equal start date.
     */
    @Test
    public void testIfDateToCompareIsEqualStartDate() {
        //Arrange
        LocalDate dateToCompare = LocalDate.of(2022, 2, 1);
        Period periodToTest = new Period(LocalDate.of(2022, 2, 1), 3);

        //Act
        boolean result = periodToTest.isDateEqualOrGreaterThanStartDate(dateToCompare);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Date is greater than start date.
     */
    @Test
    public void testIfDateToCompareIsGreaterStartDate() {
        //Arrange
        LocalDate dateToCompare = LocalDate.of(2022, 2, 22);
        Period periodToTest = new Period(LocalDate.of(2022, 2, 1), 3);

        //Act
        boolean result = periodToTest.isDateEqualOrGreaterThanStartDate(dateToCompare);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 3: Date is lower than start date.
     */
    @Test
    public void testIfDateToCompareIsLowerStartDate() {
        //Arrange
        LocalDate dateToCompare = LocalDate.of(2022, 1, 20);
        Period periodToTest = new Period(LocalDate.of(2022, 2, 1), 3);

        //Act
        boolean result = periodToTest.isDateEqualOrGreaterThanStartDate(dateToCompare);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Date before the start date but with a difference of just one day, to
     * ensure that the method returns true.
     */
    @Test
    public void testIfDateToCompareIsGreaterStartDateWithDifferenceOfOneDay() {
        //Arrange
        LocalDate dateToCompare = LocalDate.of(2022, 2, 22);
        Period periodToTest = new Period(LocalDate.of(2022, 2, 21), 3);

        //Act
        boolean result = periodToTest.isDateEqualOrGreaterThanStartDate(dateToCompare);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 5: Date after the start date but with a difference of just one day, to
     * ensure that the method returns false.
     */
    @Test
    public void testIfDateToCompareIsNotGreaterStartDateWithDifferenceOfOneDay() {
        //Arrange
        LocalDate dateToCompare = LocalDate.of(2022, 2, 22);
        Period periodToTest = new Period(LocalDate.of(2022, 2, 23), 3);

        //Act
        boolean result = periodToTest.isDateEqualOrGreaterThanStartDate(dateToCompare);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 6: Date to compare is null.
     */
    @Test
    public void ensureMethodComparingStartDateReturnsFalseWhenDateToCompareIsNull() {
        //Arrange
        LocalDate dateToCompare = null;
        Period periodToTest = new Period(LocalDate.of(2022, 2, 23), 3);

        //Act
        boolean result = periodToTest.isDateEqualOrGreaterThanStartDate(dateToCompare);

        //Assert
        assertFalse(result);
    }

    /**
     * Method isDateEqualOrLowerThanEndDate(date).
     * checks if date is equal or lower than end date.
     * Scenario 1: Date is equal end date.
     */
    @Test
    public void testIfDateToCompareIsEqualEndDate() {
        //Arrange
        LocalDate dateToCompare = LocalDate.of(2022, 2, 22);
        Period periodToTest = new Period(LocalDate.of(2022, 2, 1), 3);

        //Act
        boolean result = periodToTest.isDateEqualOrLowerThanEndDate(dateToCompare);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Date is greater than end date.
     */
    @Test
    public void testIfDateToCompareIsGreaterEndDate() {
        //Arrange
        LocalDate dateToCompare = LocalDate.of(2022, 2, 23);
        Period periodToTest = new Period(LocalDate.of(2022, 2, 1), 3);

        //Act
        boolean result = periodToTest.isDateEqualOrLowerThanEndDate(dateToCompare);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: Date is lower than end date.
     */
    @Test
    public void testIfDateToCompareIsLowerEndDate() {
        //Arrange
        LocalDate dateToCompare = LocalDate.of(2022, 2, 1);
        Period periodToTest = new Period(LocalDate.of(2022, 2, 1), 3);

        //Act
        boolean result = periodToTest.isDateEqualOrLowerThanEndDate(dateToCompare);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 4: Date to compare is null.
     */
    @Test
    public void ensureMethodComparingEndDateReturnsFalseWhenDateToCompareIsNull() {
        //Arrange
        LocalDate dateToCompare = null;
        Period periodToTest = new Period(LocalDate.of(2022, 2, 23), 3);

        //Act
        boolean result = periodToTest.isDateEqualOrLowerThanEndDate(dateToCompare);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Date after the end date but with a difference of just one day, to
     * ensure that the method returns false.
     */
    @Test
    public void testIfDateToCompareIsNotLowerThanEndDateWithDifferenceOfOneDay() {
        //Arrange
        LocalDate dateToCompare = LocalDate.of(2022, 3, 17);
        Period periodToTest = new Period(LocalDate.of(2022, 2, 23), 3);

        //Act
        boolean result = periodToTest.isDateEqualOrLowerThanEndDate(dateToCompare);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 6: Date before the end date but with a difference of just one day, to
     * ensure that the method returns true.
     */
    @Test
    public void testIfDateToCompareIsLowerThanEndDateWithDifferenceOfOneDay() {
        //Arrange
        LocalDate dateToCompare = LocalDate.of(2022, 2, 15);
        Period periodToTest = new Period(LocalDate.of(2022, 2, 23), 3);

        //Act
        boolean result = periodToTest.isDateEqualOrLowerThanEndDate(dateToCompare);

        //Assert
        assertTrue(result);
    }
}