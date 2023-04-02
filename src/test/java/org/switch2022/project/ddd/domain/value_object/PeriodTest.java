package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PeriodTest {
    /**
     * METHOD constructor public Period(final LocalDate startDate, final Number duration)
     * <p>
     * Scenario 1: verifies if an instance of Period is not created because the date passed as argument is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenDateIsNull() {
        //Arrange
        Number duration = 3;
        String expected = "The start date must not be null";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                new Period(null,duration));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 2: verifies if an instance of Period is not created because the duration passed as argument is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenDurationIsNull() {
        //Arrange
        LocalDate date = LocalDate.now();
        Number duration = null;
        String expected = "The duration must not be null";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                new Period(date,duration));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 3: verifies if an instance of Period is not created because the duration passed as argument is negative.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenDurationIsNegative() {
        //Arrange
        LocalDate date = LocalDate.now();
        Number duration = -3;
        String expected = "The duration must not be negative";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                new Period(date,duration));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD constructor public Period(final LocalDate startDate, final LocalDate endDate)
     * <p>
     * Scenario 1: verifies if an instance of Period is not created because the start date passed as argument is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStartDateIsNull() {
        //Arrange
        LocalDate endDate = LocalDate.now();
        String expected = "The start date must not be null";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                new Period(null,endDate));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 2: verifies if an instance of Period is not created because the end date passed as argument is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenEndDateIsNull() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = null;

        String expected = "The end date must not be null";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                new Period(startDate,endDate));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 3: verifies if an instance of Period is not created because the end date passed as argument is before
     * the start date.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenEndDateIsBeforeStartDate() {
        //Arrange
        LocalDate startDate = LocalDate.of(2024,01,01);
        LocalDate endDate = LocalDate.of(2023,01,01);

        String expected = "The date must be after 2024-01-01";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                new Period(startDate,endDate));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD getStartDate()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the start date attribute of the
     * Period value object.
     */
    @Test
    void ensureStartDateIsRetrievedSuccessfully() {
        // Arrange
        LocalDate startDate  = LocalDate.of(2023,04,02);
        LocalDate endDate  = LocalDate.of(2023,05,02);
        Period period = new Period(startDate,endDate);

        String expected = "2023-04-02";

        // Act
        String result = period.getStartDate();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD getEndDate()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the end date attribute of the
     * Period value object.
     */
    @Test
    void ensureEndDateIsRetrievedSuccessfully() {
        // Arrange
        LocalDate startDate  = LocalDate.of(2023,04,02);
        LocalDate endDate  = LocalDate.of(2023,05,02);
        Period period = new Period(startDate,endDate);

        String expected = "2023-05-02";

        // Act
        String result = period.getEndDate();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD calculateEndDate()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the end date attribute of the
     * Period value object.
     */
    @Test
    void ensureEndDateIsCalculatedAndRetrievedSuccessfully() {
        // Arrange
        LocalDate startDate = LocalDate.of(2023, 04, 02);
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
     * Scenario 1: Verify that two instances of Period are equal if the value of their attributes start and end dates
     * are the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoPeriodInstancesHaveTheSameDateValues() {
        //Arrange
        LocalDate startDate  = LocalDate.of(2023,04,02);
        LocalDate endDate  = LocalDate.of(2023,05,02);

        Period period = new Period(startDate,endDate);
        Period otherPeriod = new Period(startDate,endDate);

        //Act
        boolean result = period.sameValueAs(otherPeriod);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of Period are not equal if the value of their attribute end date is not
     * the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoPeriodInstancesHaveDifferentStartDatesValues() {
        //Arrange
        LocalDate startDate  = LocalDate.of(2023,04,02);
        LocalDate otherStartDate  = LocalDate.of(2023,05,02);
        LocalDate endDate  = LocalDate.of(2023,10,02);

        Period period = new Period(startDate,endDate);
        Period otherPeriod = new Period(otherStartDate,endDate);

        //Act
        boolean result = period.sameValueAs(otherPeriod);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: Verify that two instances of Period are not equal if the value of their attribute end date is not
     * the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoPeriodInstancesHaveDifferentEndDatesValues() {
        //Arrange
        LocalDate startDate  = LocalDate.of(2023,04,02);
        LocalDate endDate  = LocalDate.of(2023,05,02);
        LocalDate otherEndDate  = LocalDate.of(2023,11,02);

        Period period = new Period(startDate,endDate);
        Period otherPeriod = new Period(startDate,otherEndDate);

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
        LocalDate startDate  = LocalDate.of(2023,04,02);
        LocalDate endDate  = LocalDate.of(2023,05,02);

        Period reference = new Period(startDate,endDate);
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
        LocalDate startDate  = LocalDate.of(2023,04,02);
        LocalDate endDate  = LocalDate.of(2023,05,02);

        Period reference = new Period(startDate,endDate);
        Period other = new Period(startDate,endDate);

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
        LocalDate startDate  = LocalDate.of(2023,04,02);
        LocalDate endDate  = LocalDate.of(2023,05,02);
        LocalDate otherEndDate  = LocalDate.of(2023,10,02);

        Period reference = new Period(startDate,endDate);
        Period other = new Period(startDate,otherEndDate);

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
        LocalDate startDate  = LocalDate.of(2023,04,02);
        LocalDate endDate  = LocalDate.of(2023,05,02);

        Period reference = new Period(startDate,endDate);
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
        LocalDate startDate  = LocalDate.of(2023,04,02);
        LocalDate endDate  = LocalDate.of(2023,05,02);

        Period reference = new Period(startDate,endDate);

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
        LocalDate startDate  = LocalDate.of(2023,04,02);
        LocalDate endDate  = LocalDate.of(2023,05,02);

        Period periodOne = new Period(startDate,endDate);
        Period periodTwo = new Period(startDate,endDate);

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
        LocalDate startDate  = LocalDate.of(2023,04,02);
        LocalDate endDate  = LocalDate.of(2023,05,02);
        LocalDate otherEndDate  = LocalDate.of(2023,10,02);

        Period periodOne = new Period(startDate,endDate);
        Period periodThree = new Period(startDate,otherEndDate);

        // Act
        int periodOneHashCode = periodOne.hashCode();
        int periodThreeHashCode = periodThree.hashCode();

        // Assert
        assertNotEquals(periodOneHashCode, periodThreeHashCode);
    }
}