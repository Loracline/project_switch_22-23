package org.switch2022.project.ddd.domain.model.business_sector;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.Name;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BusinessSectorTest {

    // UNIT TESTS

    /**
     * Constructor
     * <br>
     * Scenario 1: This test evaluates if an instance of business sector is not created if the idNumber is null.
     * It should throw an Illegal Argument Exception.
     */
    @Test
    void ensureAnInstanceOfBusinessSectorIsNotCreatedBecauseTheIdNumberIsNull() {
        // Arrange
        Name nameDouble = mock(Name.class);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> new BusinessSector(null, nameDouble));
    }

    /**
     * Constructor
     * <br>
     * Scenario 2: This test evaluates if an instance of business sector is not created if the idNumber is negative.
     * It should throw an Illegal Argument Exception.
     */
    @Test
    void ensureAnInstanceOfBusinessSectorIsNotCreatedBecauseTheIdNumberIsNegative() {
        // Arrange
        Name nameDouble = mock(Name.class);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> new BusinessSector(-1, nameDouble));
    }

    /**
     * Constructor
     * <br>
     * Scenario 3: This test evaluates if an instance of business sector is not created if the name is null.
     * It should throw an Illegal Argument Exception.
     */
    @Test
    void ensureAnInstanceOfBusinessSectorIsNotCreatedBecauseTheNameIsNull() {
        // Arrange, Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> new BusinessSector(1, null));
    }

    /**
     * Method: equals()
     * Scenario 01: This test evaluates if one instance of business sector equals itself.
     * It should assert true.
     */
    @Test
    void ensureSameBusinessSectorEqualsItself() {
        // Arrange
        Name nameDouble = mock(Name.class);

        BusinessSector sectorOne = new BusinessSector(1, nameDouble);
        BusinessSector sectorReference = sectorOne;

        //Act
        boolean result = sectorOne.equals(sectorReference);

        //Assert
        assertTrue(result);
    }

    /**
     * Method: equals()
     * Scenario 02: This test evaluates if two one instance of business sector is different of an instance of a
     * different class.
     * It should assert false.
     */
    @Test
    void ensureBusinessSectorInstanceIsDifferentFromInstancesOfOtherClasses() {
        // Arrange
        Name nameDouble = mock(Name.class);
        BusinessSector sectorOne = new BusinessSector(1, nameDouble);
        Object otherObject = new Object();

        //Act
        boolean result = sectorOne.equals(otherObject);

        //Assert
        assertFalse(result);
    }

    /**
     * Method: equals()
     * Scenario 03: This test evaluates if one instance of business sector is not null.
     * It should assert false.
     */
    @Test
    void ensureBusinessSectorDoesNotEqualNull() {
        //Arrange
        Name nameDouble = mock(Name.class);
        BusinessSector sectorOne = new BusinessSector(1, nameDouble);
        BusinessSector nullSector = null;

        //Act
        boolean result = sectorOne.equals(nullSector);

        //Assert
        assertFalse(result);
    }

    /**
     * Method: equals()
     * Scenario 04: This test evaluates if two instances of business sector with different ids are different.
     * It should assert False.
     */
    @Test
    void ensureTwoDifferentInstancesOfBusinessSectorAreNotEqual() {
        // Arrange
        Name nameDouble = mock(Name.class);
        BusinessSector sectorOne = new BusinessSector(1, nameDouble);
        BusinessSector sectorTwo = new BusinessSector(2, nameDouble);

        //Act
        boolean result = sectorOne.equals(sectorTwo);

        //Assert
        assertFalse(result);
    }

    /**
     * Method:hashCode()
     * Scenario 01: This test evaluates if two instances of business sector with different hash codes are different.
     * It should assert not equals.
     */

    @Test
    void ensureTwoInstancesOfBusinessSectorWithDifferentHashCodesAreNotEqual() {
        // Arrange
        Name nameDouble = mock(Name.class);
        BusinessSector sectorOne = new BusinessSector(1, nameDouble);
        BusinessSector sectorTwo = new BusinessSector(2, nameDouble);

        //Act and Assert
        assertNotEquals(sectorOne.hashCode(), sectorTwo.hashCode());
    }

    /**
     * Method: sameIdentityAs()
     * Scenario 01: This test evaluates if two instances of business sector are the same if their id attribute is the
     * same.
     * It should assert true.
     */
    @Test
    void ensureTheTwoInstancesOfBusinessSectorAreTheSameIfTheirIdAttributeIsTheSame() {
        // Arrange
        Name nameDouble = mock(Name.class);
        BusinessSector sectorOne = new BusinessSector(1, nameDouble);
        BusinessSector sectorTwo = new BusinessSector(1, nameDouble);

        //Act
        boolean result = sectorOne.sameIdentityAs(sectorTwo);

        //Assert
        assertTrue(result);
    }

    /**
     * Method: sameIdentityAs()
     * Scenario 02: This test evaluates if two instances of business sector are different if their id
     * attribute is not the same.
     * It should assert false.
     */
    @Test
    void ensureTheTwoInstancesOfBusinessSectorAreDifferentIfTheirIdAttributeIsNotTheSame() {
        //Arrange
        Name nameDouble = mock(Name.class);
        BusinessSector sectorOne = new BusinessSector(1, nameDouble);
        BusinessSector sectorTwo = new BusinessSector(2, nameDouble);


        //Act
        boolean result = sectorOne.sameIdentityAs(sectorTwo);

        //Assert
        assertFalse(result);
    }

    /**
     * Method: sameIdentityAs()
     * Scenario 03: This test evaluates if two instances of business sector are different if one of them is null.
     * It should assert false.
     */
    @Test
    void ensureTheTwoObjectsAreNotTheSameBecauseOneOfThemIsNull() {
        //Arrange
        Name nameDouble = mock(Name.class);
        BusinessSector sectorOne = new BusinessSector(1, nameDouble);
        BusinessSector sectorTwo = null;

        //Act
        boolean result = sectorOne.sameIdentityAs(sectorTwo);

        //Assert
        assertFalse(result);
    }

    /**
     * Method: getBusinessSectorId().
     * Scenario 01: This test evaluates if the string representation of the business sector id attribute of a given
     * instance of business sector is retrieved.
     * It should return a string starting with "bs", followed by up to three digits (e.g. bs001).
     */
    @Test
    public void ensureStringRepresentationOfBusinessSectorIdIsRetrieved() {
        //Arrange
        Name nameDouble = mock(Name.class);
        BusinessSector sectorOne = new BusinessSector(1, nameDouble);
        String expected = "bs001";


        //Act
        String result = sectorOne.getBusinessSectorId();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: getBusinessSectorName().
     * Scenario 01: This test evaluates if the string representation of the business sector name attribute of a given
     * instance of business sector is retrieved.
     * It should return a string in lowercase.
     */
    @Test
    public void ensureStringRepresentationOfBusinessSectorNameIsRetrieved() {
        //Arrange
        Name nameDouble = mock(Name.class);
        when(nameDouble.getName()).thenReturn("sector one");
        BusinessSector sectorOne = new BusinessSector(1, nameDouble);
        String expected = "sector one";

        //Act
        String result = sectorOne.getBusinessSectorName();

        //Assert
        assertEquals(expected, result);
    }

}