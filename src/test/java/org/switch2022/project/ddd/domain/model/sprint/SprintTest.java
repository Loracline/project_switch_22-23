package org.switch2022.project.ddd.domain.model.sprint;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.Period;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


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

        // Act
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Sprint(2, null));

        //Assert
        assertEquals(expected, result.getMessage());

    }
    /**
     * Scenario 2: Sprint is not created when the Period is negative. Should
     * throw an exception.
     */

    @Test
    public void ensureSprintIsNotCreateBecausePeriodIsNegative() {
        //Arrange
        Period period = mock(Period.class);
        String expected = "The Sprint Number must not be negative";

        // Act
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Sprint(-2, period));

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
        Sprint sprintOne = new Sprint(3, period);
        Sprint sprintTwo = new Sprint(2, period);
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
        Period period = mock(Period.class);
        Sprint sprintOne = new Sprint(3, period);
        Sprint sprintTwo = new Sprint(3, period);
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
        Sprint sprintOne = new Sprint(3, period);
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
        Sprint sprintOne = new Sprint(3, period);
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
        Sprint sprintOne = new Sprint(3, period);
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
        Sprint sprintOne = new Sprint(3, period);
        Sprint sprintTwo = new Sprint(3, period);

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
        Sprint sprintOne = new Sprint(3, period);
        Sprint sprintTwo = new Sprint(2, period);

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
        Sprint reference = new Sprint(8, period);
        Sprint other = new Sprint(8, period);
        boolean expected = true;

        //Act
        boolean result = reference.sameIdentityAs(other);

        //Assert
        assertEquals(expected, result);
    }
}