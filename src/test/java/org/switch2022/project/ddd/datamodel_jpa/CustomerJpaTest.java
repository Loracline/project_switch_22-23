package org.switch2022.project.ddd.datamodel_jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.project.Project;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CustomerJpaTest {

    @DisplayName("Tax ID number retrieved in String format")
    @Test
    void ensureCustomerTaxIdIsRetrievedSuccessfully() {
        // Arrange
        String expected = "514024054";
        CustomerJpa customerJpa = new CustomerJpa(expected, "Partilha Cortesia, Lda.");

        // Act
        String result = customerJpa.getCustomerTaxId();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Name retrieved in String format")
    @Test
    void ensureCustomerNameIsRetrievedSuccessfully() {
        // Arrange
        String expected = "Partilha Cortesia, Lda.";
        CustomerJpa customerJpa = new CustomerJpa("514024054", expected);

        // Act
        String result = customerJpa.getCustomerName();

        // Assert
        assertEquals(expected, result);
    }
    @DisplayName("Empty constructor with no arguments")
    @Test
    public void testNoArgsConstructor() {
        CustomerJpa customer = new CustomerJpa();

        assertNull(customer.getCustomerTaxId());
        assertNull(customer.getCustomerName());
    }

    @DisplayName("Equals and HashCode methods work correctly")
    @Test
    void ensureEqualsAndHashCodeMethodsWorkCorrectly() {
        // Arrange
        CustomerJpa customerJpa1 = new CustomerJpa("514024054", "Partilha Cortesia, Lda.");
        CustomerJpa customerJpa2 = new CustomerJpa("514024054", "Partilha Cortesia, Lda.");
        CustomerJpa customerJpa3 = new CustomerJpa("123456789", "Another Customer");

        // Assert
        assertEquals(customerJpa1, customerJpa2);
        assertEquals(customerJpa1.hashCode(), customerJpa2.hashCode());

        // Objects with different tax IDs should have different hash codes
        assertNotEquals(customerJpa1.hashCode(), customerJpa3.hashCode());

        // Objects with different names should have different hash codes
        CustomerJpa customerJpa4 = new CustomerJpa("514024054", "Different Name");
        assertNotEquals(customerJpa1.hashCode(), customerJpa4.hashCode());

        // Objects with different tax IDs and names should have different hash codes
        CustomerJpa customerJpa5 = new CustomerJpa("123456789", "Different Name");
        assertNotEquals(customerJpa3.hashCode(), customerJpa5.hashCode());

        // Objects with null fields should have consistent hash codes
        CustomerJpa customerJpa6 = new CustomerJpa(null, null);
        CustomerJpa customerJpa7 = new CustomerJpa(null, null);
        assertEquals(customerJpa6.hashCode(), customerJpa7.hashCode());
    }
    @Test
    @DisplayName("Equality between two objects with the same values")
    void testEqualsWithSameValues() {
        // Arrange
        CustomerJpa customerJpa1 = new CustomerJpa("514024054", "Partilha Cortesia, Lda.");
        CustomerJpa customerJpa2 = new CustomerJpa("514024054", "Partilha Cortesia, Lda.");

        // Act & Assert
        assertEquals(customerJpa1, customerJpa2);
        assertEquals(customerJpa2, customerJpa1);
    }

    @Test
    @DisplayName("Inequality between two objects with different names")
    void testEqualsWithDifferentNames() {
        // Arrange
        CustomerJpa customerJpa1 = new CustomerJpa("514024054", "Partilha Cortesia, Lda.");
        CustomerJpa customerJpa3 = new CustomerJpa("514024054", "Another Customer");

        // Act & Assert
        assertNotEquals(customerJpa1, customerJpa3);
        assertNotEquals(customerJpa3, customerJpa1);
    }

    @Test
    @DisplayName("Inequality when comparing with null")
    void testEqualsWithNullComparison() {
        // Arrange
        CustomerJpa customerJpa1 = new CustomerJpa("514024054", "Partilha Cortesia, Lda.");

        // Act & Assert
        assertNotEquals(customerJpa1, null);
    }

    @Test
    @DisplayName("Consistency of equals() method")
    void testEqualsConsistency() {
        // Arrange
        CustomerJpa customerJpa1 = new CustomerJpa("514024054", "Partilha Cortesia, Lda.");

        // Act & Assert
        assertEquals(customerJpa1, customerJpa1);
    }
    @DisplayName("toString method generates expected string format")
    @Test
    void ensureToStringMethodGeneratesExpectedFormat() {
        // Arrange
        CustomerJpa customerJpa = new CustomerJpa("514024054", "Partilha Cortesia, Lda.");

        // Act
        String result = customerJpa.toString();

        // Assert
        assertTrue(result.contains("514024054"));
        assertTrue(result.contains("Partilha Cortesia, Lda."));
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify that the same object equals itself.
     */
    @Test
    void ensureSameCustomerEqualsItself() {
        // Arrange
        CustomerJpa customerJpa = new CustomerJpa("217746691", "batman");
        CustomerJpa other = customerJpa;
        boolean expected = true;

        // Act
        boolean result = customerJpa.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two objects with the same attributes are equal.
     */
    @Test
    void ensureTwoInstancesWithSameIdAreEqual() {
        // Arrange
        CustomerJpa customerJpa = new CustomerJpa("217746691", "batman");
        CustomerJpa other = new CustomerJpa("217746691", "batman");
        boolean expected = true;

        // Act
        boolean result = customerJpa.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Verify that two objects with different ids are not equal.
     */
    @Test
    void ensureTwoInstancesWithDifferentIdsAreNotEqual() {
        // Arrange
        CustomerJpa reference = new CustomerJpa("217746691", "batman");
        CustomerJpa other = new CustomerJpa("217746291", "batman");
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: Verify that the object does not equal null.
     */
    @Test
    void ensureObjectDoesNotEqualNull() {
        // Arrange
        CustomerJpa customerJpa = new CustomerJpa("217746691", "batman");
        BusinessSectorJpa other = null;
        boolean expected = false;

        // Act
        boolean result = customerJpa.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 5: Verify that the object businessSector does not equal other type of object.
     */
    @Test
    void ensureCustomersDoesNotEqualOtherTypeOfObject() {
        // Arrange
        CustomerJpa customerJpa = new CustomerJpa("217746691", "batman");
        Project other = mock(Project.class);
        boolean expected = false;

        // Act
        boolean result = customerJpa.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 6: Verify that two objects with different attributes are not equal.
     */
    @Test
    void ensureTwoInstancesWithDifferentIdsAreNotEqual_Name() {
        // Arrange
        CustomerJpa reference = new CustomerJpa("217746691", "batman");
        CustomerJpa other = new CustomerJpa("217746691", "robin");
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Verify that two equal BusinessSector objects have the same hashcode.
     */
    @Test
    void ensureTwoEqualCustomersInstancesHaveTheSameHashcode() {
        // Arrange
        CustomerJpa reference = new CustomerJpa("217746691", "batman");
        CustomerJpa other = new CustomerJpa("217746691", "batman");

        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two different BusinessSector objects have different hashcode.
     */
    @Test
    void ensureTwoDifferentCustomersInstancesHaveDifferentHashcode() {
        // Arrange
        CustomerJpa reference = new CustomerJpa("217746691", "batman");
        CustomerJpa other = new CustomerJpa("217746691", "robin");

        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertNotEquals(expected, result);
    }
}