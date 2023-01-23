package org.switch2022.project.model.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.BusinessSector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusinessSectorContainerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */
    BusinessSector businessSectorOne, businessSectorTwo;
    List<BusinessSector> businessSectors;
    BusinessSectorContainer businessSectorContainer;

    @BeforeEach
    void setUp() {
        /*
          Business sectors created.
         */
        businessSectorOne = new BusinessSector("mining");
        businessSectorTwo = new BusinessSector("fishing");

        /*
          Container of business sectors created.
         */
        businessSectors = new ArrayList<>();
        businessSectorContainer = new BusinessSectorContainer(businessSectors);

        /*
          Business sectors added to the Container.
         */
        businessSectors.add(businessSectorOne);
        businessSectors.add(businessSectorTwo);
    }

    @AfterEach
    void tearDown() {
        businessSectorOne = null;
        businessSectorTwo = null;
        businessSectors.clear();
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
}