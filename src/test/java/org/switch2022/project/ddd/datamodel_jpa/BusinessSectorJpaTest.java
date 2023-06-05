package org.switch2022.project.ddd.datamodel_jpa;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.project.Project;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BusinessSectorJpaTest {

    @Test
    void ensureBusinessSectorJpaIsCreated(){
        //Act
        BusinessSectorJpa businessSectorJpa = new BusinessSectorJpa();

        //Assert
        assertNotNull(businessSectorJpa);
    }

    @Test
    void getIdNumber() {
        //Arrange
        BusinessSectorJpa businessSectorJpa = new BusinessSectorJpa("bs001", "IT");
        String expected = "bs001";

        //Act
        String result = businessSectorJpa.getIdNumber();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getName() {
        //Arrange
        BusinessSectorJpa businessSectorJpa = new BusinessSectorJpa("bs001", "IT");
        String expected = "IT";

        //Act
        String result = businessSectorJpa.getName();

        //Assert
        assertEquals(expected,result);
    }
    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify that the same object equals itself.
     */
    @Test
    void ensureSameBusinessSectorEqualsItself() {
        // Arrange
        BusinessSectorJpa businessSectorJpa = new BusinessSectorJpa("bs001", "IT");
        BusinessSectorJpa other = businessSectorJpa;
        boolean expected = true;

        // Act
        boolean result = businessSectorJpa.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two objects with the same attributes are equal.
     */
    @Test
    void ensureTwoInstancesWithSameIdAreEqual() {
        // Arrange
        BusinessSectorJpa businessSectorJpa = new BusinessSectorJpa("bs001", "IT");
        BusinessSectorJpa other =  new BusinessSectorJpa("bs001", "IT");
        boolean expected = true;

        // Act
        boolean result = businessSectorJpa.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Verify that two objects with different ids are not equal.
     */
    @Test
    void ensureTwoInstancesWithDifferentIdsAreNotEqual() {
        // Arrange
        BusinessSectorJpa reference = new BusinessSectorJpa("bs001", "IT");
        BusinessSectorJpa other = new BusinessSectorJpa("bs002", "IT");
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
        BusinessSectorJpa businessSectorJpa = new BusinessSectorJpa("bs001", "IT");
        BusinessSectorJpa other = null;
        boolean expected = false;

        // Act
        boolean result = businessSectorJpa.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 5: Verify that the object businessSector does not equal other type of object.
     */
    @Test
    void ensureBusinessSectorDoesNotEqualOtherTypeOfObject() {
        // Arrange
        BusinessSectorJpa businessSectorJpa = new BusinessSectorJpa("bs001", "IT");
        Project other = mock(Project.class);
        boolean expected = false;

        // Act
        boolean result = businessSectorJpa.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Verify that two equal BusinessSector objects have the same hashcode.
     */
    @Test
    void ensureTwoEqualBusinessSectorInstancesHaveTheSameHashcode() {
        // Arrange
        BusinessSectorJpa reference = new BusinessSectorJpa("bs001", "IT");
        BusinessSectorJpa other = new BusinessSectorJpa("bs001", "IT");

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
    void ensureTwoDifferentBusinessSectorInstancesHaveDifferentHashcode() {
        // Arrange

        BusinessSectorJpa reference = new BusinessSectorJpa("bs001", "IT");
        BusinessSectorJpa other = new BusinessSectorJpa("bs002", "IT");
        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertNotEquals(expected, result);
    }
}