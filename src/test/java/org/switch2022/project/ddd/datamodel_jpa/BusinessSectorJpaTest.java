package org.switch2022.project.ddd.datamodel_jpa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}