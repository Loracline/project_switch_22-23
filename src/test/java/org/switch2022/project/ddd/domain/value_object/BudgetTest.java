package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BudgetTest {
    /**
     * METHOD constructor
     * <p>
     * Scenario 1: verifies if an instance of Budget is not created because the number passed as
     * argument is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenNumberIsNull() {
        //Arrange
        String expected = "The budget must not be null";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new Budget(null));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD constructor
     * <p>
     * Scenario 1: verifies if an instance of NumberOfPlannedSprints is not created because the number passed as
     * argument is negative.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenNumberIsNegative() {
        //Arrange
        BigDecimal value = new BigDecimal(-1256987.22);

        String expected = "The budget must not be negative";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new Budget(value));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD getBudget()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the budget attribute of the
     * Budget value object.
     */
    @Test
    void ensureBudgetIsRetrievedSuccessfully() {
        // Arrange
        BigDecimal value = new BigDecimal(1256987.22);
        Budget budget = new Budget(value);
        BigDecimal expected = value;

        // Act
        BigDecimal result = budget.getBudget();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of Budget are equal if the value of their attribute budget is
     * the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoBudgetInstancesHaveTheSameBudgetValue() {
        //Arrange
        BigDecimal value = new BigDecimal(1256987.22);
        Budget budget = new Budget(value);
        Budget otherBudget = new Budget(value);
        //Act
        boolean result = budget.sameValueAs(otherBudget);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of Budget are not equal if the value of their attribute budget is not the
     * same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoBudgetInstancesHaveDifferentBudgetValues() {
        //Arrange
        BigDecimal value = new BigDecimal(1256987.22);
        BigDecimal otherValue = new BigDecimal(8916857.92);
        Budget budget = new Budget(value);
        Budget otherBudget = new Budget(otherValue);
        //Act
        boolean result = budget.sameValueAs(otherBudget);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameBudgetEqualsItself() {
        // Arrange
        BigDecimal value = new BigDecimal(1256987.22);
        Budget reference = new Budget(value);
        Budget other = reference;

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
        // Arrange
        BigDecimal value = new BigDecimal(1256987.22);
        Budget reference = new Budget(value);
        Budget other = new Budget(value);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 3: Verify that two objects with different ids are not equal.
     */
    @Test
    void ensureTwoDifferentBudgetInstancesAreNotTheSame() {
        // Arrange
        BigDecimal value = new BigDecimal(1256987.22);
        BigDecimal otherValue = new BigDecimal(8916857.92);
        Budget reference = new Budget(value);
        Budget other = new Budget(otherValue);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a Budget instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureBudgetDoesNotEqualOtherTypeOfObject() {
        // Arrange
        BigDecimal value = new BigDecimal(1256987.22);
        Budget reference = new Budget(value);
        String other = "other object";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if a Budget and a null object are not the same.
     */
    @Test
    void ensureBudgetInstanceDoesNotEqualNull() {
        // Arrange
        BigDecimal value = new BigDecimal(1256987.22);
        Budget reference = new Budget(value);
        Budget other = null;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal Budget objects are the same.
     */
    @Test
    public void ensureTwoBudgetInstancesHashcodeAreTheSame() {
        // Arrange
        BigDecimal value = new BigDecimal(1256987.22);
        Budget budgetOne = new Budget(value);
        Budget budgetTwo = new Budget(value);

        // Act
        int budgetOneHashCode = budgetOne.hashCode();
        int budgetTwoHashCode = budgetTwo.hashCode();

        // Assert
        assertEquals(budgetOneHashCode, budgetTwoHashCode);
    }

    /**
     * Scenario 2: Two different Budget objects are not the same.
     */
    @Test
    public void ensureTwoBudgetInstancesHashcodeAreNotTheSame() {
        // Arrange
        BigDecimal value = new BigDecimal(1256987.22);
        BigDecimal otherValue = new BigDecimal(8916857.92);
        Budget budgetOne = new Budget(value);
        Budget budgetThree = new Budget(otherValue);

        // Act
        int budgetOneHashCode = budgetOne.hashCode();
        int budgetThreeHashCode = budgetThree.hashCode();

        // Assert
        assertNotEquals(budgetOneHashCode, budgetThreeHashCode);
    }

}