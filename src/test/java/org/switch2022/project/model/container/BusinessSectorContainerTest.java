package org.switch2022.project.model.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.container.BusinessSectorContainer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusinessSectorContainerTest {

  /**
   * BeforeEach and AfterEach executes common code before/after running the tests below.
   */

  BusinessSector businessSectorOne, businessSectorTwo;
  List<BusinessSector> businessSectors;
  BusinessSectorContainer businessSectorContainer;



  @BeforeEach
  void setUp() {

    businessSectorOne = new BusinessSector("mining");
    businessSectorTwo = new BusinessSector("fishing");

    businessSectors = new ArrayList<>();
    businessSectors.add(businessSectorOne);
    businessSectors.add(businessSectorTwo);

    businessSectorContainer = new BusinessSectorContainer(businessSectors);
  }

  @AfterEach
  void tearDown() {
    businessSectorOne = null;
    businessSectorTwo = null;
    businessSectors.clear();
    businessSectorContainer = null;

  }


  @Test
  void ensureBusinessSectorIsntAddedSuccessfully_CaseInsensitive() {
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