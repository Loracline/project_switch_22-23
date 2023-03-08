package org.switch2022.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProductBacklogTest {
    /*
      METHOD equals()
     */

    /**
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameProductBacklogEqualsItself() {
        // Arrange
        ProductBacklog reference = new ProductBacklog();
        ProductBacklog other = reference;
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }
    
    /**
     * Scenario 3: Verify if a ProductBacklog and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureProductBacklogDoesNotEqualOtherTypeOfObject() {
        // Arrange
        ProductBacklog reference = new ProductBacklog();
        String other = "User";
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: Verify if a ProductBacklog and a null object are not the same.
     */
    @Test
    void ensureProductBacklogDoesNotEqualNull() {
        // Arrange
        ProductBacklog reference = new ProductBacklog();
        ProductBacklog other = null;
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /*
      METHOD hashCode()
     */

    /**
     * Scenario 1: Two ProductBacklog objects are the same.
     */
    @Test
    public void ensureTwoProductBacklogsHashcodeAreTheSame() {
        // Arrange
        ProductBacklog productBacklogOne = new ProductBacklog();
        ProductBacklog productBacklogTwo = new ProductBacklog();

        // Act
        int productBacklogOneHashCode = productBacklogOne.hashCode();
        int productBacklogTwoHashCode = productBacklogTwo.hashCode();

        // Assert
        assertEquals(productBacklogOneHashCode, productBacklogTwoHashCode);
    }

}
