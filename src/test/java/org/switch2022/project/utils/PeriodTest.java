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
        LocalDate startDate = LocalDate.now();
        int sprintDuration = 2;
        Period period = new Period(startDate, sprintDuration);
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
}