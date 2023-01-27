package org.switch2022.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BusinessSectorTest {

    /**
     * BeforeEach and AfterEach execute common code before/after running the
     * tests below.
     */
    BusinessSector businessSectorOne;
    BusinessSector businessSectorTwo;

    @BeforeEach
    void setUp() {
        businessSectorOne = new BusinessSector("fishing");
        businessSectorTwo = new BusinessSector("mining");
    }

    @AfterEach
    void tearDown() {
        businessSectorOne = null;
    }

    /**
     * Testing the equals() method.
     */
    @Test
    void ensureSameObjectEqualsItself() {
        //Arrange
        BusinessSector other = businessSectorOne;
        boolean expected = true;

        //Act
        boolean result = businessSectorOne.equals(other);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureTwoBusinessSectorsAreNotTheSame() {
        //Arrange
        boolean expected = false;

        //Expected
        boolean result = businessSectorTwo.equals(businessSectorOne);

        //Act
        assertEquals(expected, result);
    }

    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureObjectDoesNotEqualsOtherTypeOfObject() {
        //Arrange
        String other = "mining";
        boolean expected = false;

        //Act
        boolean result = businessSectorTwo.equals(other);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Testing the hashCode() method.
     */
    @Test
    public void testHashCodeBusinessSector() {
        //Arrange
        BusinessSector businessSectorOne = new BusinessSector("fishing");
        BusinessSector businessSectorTwo = new BusinessSector("fishing");
        BusinessSector businessSectorThree = new BusinessSector("farming");

        //Act and Assert
        assertEquals(businessSectorOne.hashCode(), businessSectorTwo.hashCode());

        assertNotEquals(businessSectorOne.hashCode(), businessSectorThree.hashCode());
    }

    /**
     * Test to verify if a Business Sector Name is successfully retrieved. Should return CustomerName.
     */

    @Test
    void ensureThatBusinessSectorNameIsSuccessfullyRetrieved() {
        //Arrange
        String expected = "fishing";

        //Act
        String result = businessSectorOne.getBusinessSectorName();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify if a Business Sector Name is successfully retrieved. Should return false.
     */

    @Test
    void ensureThatBusinessSectorNameIsNotRetrieved() {
        //Arrange
        String expected = "mining";

        //Act
        String result = businessSectorOne.getBusinessSectorName();

        //Assert
        assertNotEquals(expected, result);
    }
}