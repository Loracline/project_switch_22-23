package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PeriodTest {
    /**
     * METHOD constructor public Period(final LocalDate startDate, final Number duration)
     * <p>
     * Scenario 1: verifies if an instance of Period is not created because the date
     * passed as argument is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenDateIsNull() {
        //Arrange
        Number duration = 3;
        String expected = "The start date must not be null";

        //Act
        InvalidInputException exception =
                assertThrowsExactly(InvalidInputException.class, () ->
                new Period(null, duration));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 2: verifies if an instance of Period is not created because the
     * duration passed as argument is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenDurationIsNull() {
        //Arrange
        LocalDate date = LocalDate.now();
        Number duration = null;
        String expected = "The duration must not be null";

        //Act
        InvalidInputException exception =
                assertThrowsExactly(InvalidInputException.class, () ->
                new Period(date, duration));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 3: verifies if an instance of Period is not created because the
     * duration passed as argument is negative.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenDurationIsNegative() {
        //Arrange
        LocalDate date = LocalDate.now();
        Number duration = -3;
        String expected = "The duration must not be negative";

        //Act
        InvalidInputException exception =
                assertThrowsExactly(InvalidInputException.class, () ->
                new Period(date, duration));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD constructor public Period(final LocalDate startDate, final LocalDate
     * endDate)
     * <p>
     * Scenario 1: verifies if an instance of Period is not created because the start
     * date passed as argument is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStartDateIsNull() {
        //Arrange
        LocalDate endDate = LocalDate.now();
        String expected = "The start date must not be null";

        //Act
        InvalidInputException exception =
                assertThrowsExactly(InvalidInputException.class, () ->
                new Period(null, endDate));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 2: verifies if an instance of Period is not created because the end
     * date passed as argument is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenEndDateIsNull() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = null;

        String expected = "The end date must not be null";

        //Act
        InvalidInputException exception =
                assertThrowsExactly(InvalidInputException.class, () ->
                new Period(startDate, endDate));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 3: verifies if an instance of Period is not created because the end
     * date passed as argument is before
     * the start date.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenEndDateIsBeforeStartDate() {
        //Arrange
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 1, 1);

        String expected = "The date must be after 2024-01-01";

        //Act
        InvalidInputException exception =
                assertThrowsExactly(InvalidInputException.class, () ->
                new Period(startDate, endDate));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD getStartDate()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the start
     * date attribute of the
     * Period value object.
     */
    @Test
    void ensureStartDateIsRetrievedSuccessfully() {
        // Arrange
        LocalDate startDate = LocalDate.of(2023, 4, 2);
        LocalDate endDate = LocalDate.of(2023, 5, 2);
        Period period = new Period(startDate, endDate);

        String expected = "2023-04-02";

        // Act
        String result = period.getStartDate();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD getEndDate()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the end
     * date attribute of the
     * Period value object.
     */
    @Test
    void ensureEndDateIsRetrievedSuccessfully() {
        // Arrange
        LocalDate startDate = LocalDate.of(2023, 4, 2);
        LocalDate endDate = LocalDate.of(2023, 5, 2);
        Period period = new Period(startDate, endDate);

        String expected = "2023-05-02";

        // Act
        String result = period.getEndDate();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD calculateEndDate()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the end
     * date attribute of the
     * Period value object.
     */
    @Test
    void ensureEndDateIsCalculatedAndRetrievedSuccessfully() {
        // Arrange
        LocalDate startDate = LocalDate.of(2023, 4, 2);
        Number duration = 2;
        Period period = new Period(startDate, duration);
        String expected = "2023-04-16";

        //Act
        String result = period.getEndDate();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of Period are equal if the value of their
     * attributes start and end dates
     * are the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoPeriodInstancesHaveTheSameDateValues() {
        //Arrange
        LocalDate startDate = LocalDate.of(2023, 4, 2);
        LocalDate endDate = LocalDate.of(2023, 5, 2);

        Period period = new Period(startDate, endDate);
        Period otherPeriod = new Period(startDate, endDate);

        //Act
        boolean result = period.sameValueAs(otherPeriod);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of Period are not equal if the value of
     * their attribute end date is not
     * the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoPeriodInstancesHaveDifferentStartDatesValues() {
        //Arrange
        LocalDate startDate = LocalDate.of(2023, 4, 2);
        LocalDate otherStartDate = LocalDate.of(2023, 5, 2);
        LocalDate endDate = LocalDate.of(2023, 10, 2);

        Period period = new Period(startDate, endDate);
        Period otherPeriod = new Period(otherStartDate, endDate);

        //Act
        boolean result = period.sameValueAs(otherPeriod);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: Verify that two instances of Period are not equal if the value of
     * their attribute end date is not
     * the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoPeriodInstancesHaveDifferentEndDatesValues() {
        //Arrange
        LocalDate startDate = LocalDate.of(2023, 4, 2);
        LocalDate endDate = LocalDate.of(2023, 5, 2);
        LocalDate otherEndDate = LocalDate.of(2023, 11, 2);

        Period period = new Period(startDate, endDate);
        Period otherPeriod = new Period(startDate, otherEndDate);

        //Act
        boolean result = period.sameValueAs(otherPeriod);

        //Assert
        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSamePeriodEqualsItself() {
        // Arrange
        LocalDate startDate = LocalDate.of(2023, 4, 2);
        LocalDate endDate = LocalDate.of(2023, 5, 2);

        Period reference = new Period(startDate, endDate);
        Period other = reference;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two objects with the same id are equal.
     */
    @Test
    void ensureTwoInstancesWithSameIdAreEqual() {
        LocalDate startDate = LocalDate.of(2023, 4, 2);
        LocalDate endDate = LocalDate.of(2023, 5, 2);

        Period reference = new Period(startDate, endDate);
        Period other = new Period(startDate, endDate);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 3: Verify if two different objects of the same class are different from
     * each other.
     */
    @Test
    void ensureTwoDifferentPeriodInstancesAreNotTheSame() {
        //Arrange
        LocalDate startDate = LocalDate.of(2023, 4, 2);
        LocalDate endDate = LocalDate.of(2023, 5, 2);
        LocalDate otherEndDate = LocalDate.of(2023, 10, 2);

        Period reference = new Period(startDate, endDate);
        Period other = new Period(startDate, otherEndDate);

        //Act
        boolean result = reference.equals(other);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a Period instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensurePeriodDoesNotEqualOtherTypeOfObject() {
        //Arrange
        LocalDate startDate = LocalDate.of(2023, 4, 2);
        LocalDate endDate = LocalDate.of(2023, 5, 2);

        Period reference = new Period(startDate, endDate);
        String other = "other object";

        //Act
        boolean result = reference.equals(other);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if a Period and a null object are not the same.
     */
    @Test
    void ensurePeriodInstanceDoesNotEqualNull() {
        //Arrange
        LocalDate startDate = LocalDate.of(2023, 4, 2);
        LocalDate endDate = LocalDate.of(2023, 5, 2);

        Period reference = new Period(startDate, endDate);

        //Act
        boolean result = reference.equals(null);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal Period objects are the same.
     */
    @Test
    public void ensureTwoPeriodInstancesHashcodeAreTheSame() {
        // Arrange
        LocalDate startDate = LocalDate.of(2023, 4, 2);
        LocalDate endDate = LocalDate.of(2023, 5, 2);

        Period periodOne = new Period(startDate, endDate);
        Period periodTwo = new Period(startDate, endDate);

        // Act
        int periodOneHashCode = periodOne.hashCode();
        int periodTwoHashCode = periodTwo.hashCode();

        // Assert
        assertEquals(periodOneHashCode, periodTwoHashCode);
    }

    /**
     * Scenario 2: Two different Period objects are not the same.
     */
    @Test
    public void ensureTwoPeriodInstancesHashcodeAreNotTheSame() {
        // Arrange
        LocalDate startDate = LocalDate.of(2023, 4, 2);
        LocalDate endDate = LocalDate.of(2023, 5, 2);
        LocalDate otherEndDate = LocalDate.of(2023, 10, 2);

        Period periodOne = new Period(startDate, endDate);
        Period periodThree = new Period(startDate, otherEndDate);

        // Act
        int periodOneHashCode = periodOne.hashCode();
        int periodThreeHashCode = periodThree.hashCode();

        // Assert
        assertNotEquals(periodOneHashCode, periodThreeHashCode);
    }

    /**
     * Method isStartDateBeforeNow() Verifies the startDate is not before today date.
     * <p>
     * Scenario 1: when start date is in the past.
     */
    @Test
    public void ensureThatStartDateIsBeforeTodayDate() {
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
     * Scenario 9: verify endDate of Period2 is before startDate of Period1.
     * should return true.
     */
    @Test
    void ensureEndDateOfPeriodTwoIsBeforeStartDateOfPeriodOne() {
        //Arrange
        LocalDate startDateOne = LocalDate.of(2022, 1, 1);
        Period period1 = new Period(startDateOne, 3);
        LocalDate startDateTwo = LocalDate.of(2022, 1, 23);
        Period period2 = new Period(startDateTwo, 1);
        boolean expected = true;

        // Act
        boolean result = period1.isPeriodNotOverlapping(period2);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 10: verify endDate of Period2 is after startDate of Period1.
     * should return true.
     */
    @Test
    void ensureEndDateOfPeriodTwoIsAfterStartDateOfPeriodOne() {
        //Arrange
        LocalDate startDateOne = LocalDate.of(2022, 1, 1);
        Period period1 = new Period(startDateOne, 3);
        LocalDate startDateTwo = LocalDate.of(2022, 1, 21);
        Period period2 = new Period(startDateTwo, 1);
        boolean expected = false;

        // Act
        boolean result = period1.isPeriodNotOverlapping(period2);

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
        Period periodToTest =
                new Period(LocalDate.of(2022, 2, 1), 3);

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
        Period periodToTest =
                new Period(LocalDate.of(2022, 2, 1), 3);

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
        Period periodToTest =
                new Period(LocalDate.of(2022, 2, 1), 3);

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
        Period periodToTest =
                new Period(LocalDate.of(2022, 2, 21), 3);

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
        Period periodToTest =
                new Period(LocalDate.of(2022, 2, 23), 3);

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
        Period periodToTest =
                new Period(LocalDate.of(2022, 2, 23), 3);

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

    /**
     *Method: isDateEqualOrLowerThanStartDate
     * scenario 1: returns true because date is equal
     *
     */
    @Test
    public void testIfDateToCompareEqualsToStartDate() {
        //Arrange
        LocalDate dateToCompare = mock(LocalDate.class);
        LocalDate startDate = mock(LocalDate.class);
        Period periodToTest = new Period(startDate,2);
        when(dateToCompare.isBefore(startDate)).thenReturn(false);
        when(dateToCompare.isEqual(startDate)).thenReturn(true);
        //Act
        boolean result = periodToTest.isDateEqualOrLowerThanStartDate(dateToCompare);

        //Assert
        assertTrue(result);
    }

    /**
     * scenario 2: returns true because date is lower
     */

    @Test
    public void testIfDateToCompareIsLowerThanStartDate() {
        //Arrange
        LocalDate dateToCompare = mock(LocalDate.class);
        LocalDate startDate = mock(LocalDate.class);
        Period periodToTest = new Period(startDate,2);
        when(dateToCompare.isBefore(startDate)).thenReturn(true);
        when(dateToCompare.isEqual(startDate)).thenReturn(false);
        //Act
        boolean result = periodToTest.isDateEqualOrLowerThanStartDate(dateToCompare);

        //Assert
        assertTrue(result);
    }
    /**
     * scenario 3: returns false because date is greater
     */
    @Test
    public void testIfDateToCompareIsNotEqualNeitherLowerThanStartDate() {
        //Arrange
        LocalDate dateToCompare = mock(LocalDate.class);
        LocalDate startDate = mock(LocalDate.class);
        Period periodToTest = new Period(startDate,2);
        when(dateToCompare.isBefore(startDate)).thenReturn(false);
        when(dateToCompare.isEqual(startDate)).thenReturn(false);
        //Act
        boolean result = periodToTest.isDateEqualOrLowerThanStartDate(dateToCompare);

        //Assert
        assertFalse(result);
    }
    /**
     *Method: isDateEqualOrGreaterThanEndDate
     * scenario 1: returns true because date is equal
     *
     */
    @Test
    public void testIfDateToCompareEqualsToEndDate() {
        //Arrange
        LocalDate dateToCompare = mock(LocalDate.class);
        LocalDate startDate = mock(LocalDate.class);
        LocalDate endDate = mock(LocalDate.class);
        Period periodToTest = new Period(startDate,endDate);
        when(dateToCompare.isAfter(endDate)).thenReturn(false);
        when(dateToCompare.isEqual(endDate)).thenReturn(true);
        //Act
        boolean result = periodToTest.isDateEqualOrGreaterThanEndDate(dateToCompare);

        //Assert
        assertTrue(result);
    }
    /**
     * scenario 2: returns true because date is greater
     */
    @Test
    public void testIfDateToCompareIsGreaterThanEndDate() {
        //Arrange
        LocalDate dateToCompare = mock(LocalDate.class);
        LocalDate startDate = mock(LocalDate.class);
        LocalDate endDate = mock(LocalDate.class);
        Period periodToTest = new Period(startDate,endDate);
        when(dateToCompare.isAfter(endDate)).thenReturn(true);
        when(dateToCompare.isEqual(endDate)).thenReturn(false);
        //Act
        boolean result = periodToTest.isDateEqualOrGreaterThanEndDate(dateToCompare);

        //Assert
        assertTrue(result);
    }

    /**
     * scenario 3: returns false because date is lower
     */
    @Test
    public void testIfDateToCompareIsLowerThanEndDate() {
        //Arrange
        LocalDate dateToCompare = mock(LocalDate.class);
        LocalDate startDate = mock(LocalDate.class);
        LocalDate endDate = mock(LocalDate.class);
        Period periodToTest = new Period(startDate,endDate);
        when(dateToCompare.isAfter(endDate)).thenReturn(false);
        when(dateToCompare.isEqual(endDate)).thenReturn(false);
        //Act
        boolean result = periodToTest.isDateEqualOrGreaterThanEndDate(dateToCompare);

        //Assert
        assertFalse(result);
    }
}
