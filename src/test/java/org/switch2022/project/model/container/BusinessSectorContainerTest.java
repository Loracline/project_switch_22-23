package org.switch2022.project.model.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BusinessSectorContainerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */
    BusinessSectorContainer businessSectorContainer;

    @BeforeEach
    void setUp() {

        //Business Sector Container
        businessSectorContainer = new BusinessSectorContainer();
        businessSectorContainer.createBusinessSector("mining");
        businessSectorContainer.createBusinessSector("fishing");
    }

    @AfterEach
    void tearDown() {
        businessSectorContainer = null;
    }

    /**
     * Testing if one can create a business sector and add it to the container.
     */
    @Test
    void ensureBusinessSectorIsNotAddedSuccessfully_CaseInsensitive() {

        //Arrange
        boolean expected = false;
        //Act
        boolean result = businessSectorContainer.createBusinessSector("Mining");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureAddBusinessSectorToBusinessSectorsListSuccessfully() {
        //Arrange
        boolean expected = true;
        //Act
        boolean result = businessSectorContainer.createBusinessSector("farming");
        //Assert
        assertEquals(expected, result);

    }

    @Test
    void ensureAddBusinessSectorToBusinessSectorsListUnsuccessfully() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = businessSectorContainer.createBusinessSector("fishing");
        //Assert
        assertEquals(expected, result);

    }

    @Test
    void ensureAddBusinessSectorToBusinessSectorsListUnsuccessfully_emptyBusinessSectorName() {

        //Arrange
        boolean expected = false;
        //Act
        boolean result = businessSectorContainer.createBusinessSector("");
        //Assert
        assertEquals(expected, result);

    }

    @Test
    void ensureBusinessSectorIsRetrieved() {
        BusinessSector expected = new BusinessSector("sports");
        BusinessSector result = businessSectorContainer.getBusinessSector("sports");
        assertEquals(expected,result);
    }

    @Test
    void ensureBusinessSectorIsNotRetrieved() {
        BusinessSector expected = new BusinessSector("sportss");
        BusinessSector result = businessSectorContainer.getBusinessSector("sports");
        assertNotEquals(expected,result);
    }
}