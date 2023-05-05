package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.business_sector.BusinessSector;
import org.switch2022.project.ddd.domain.model.typology.Typology;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BusinessSectorRepositoryTest {
    /**
     * Method: equals()
     * Scenario 01: Test to ensure the object equals itself
     */
    @Test
    void ensureSameObjectEqualsItself() {
        // Arrange
        BusinessSectorRepository repositoryOne = new BusinessSectorRepository();
        BusinessSectorRepository repositoryReference = repositoryOne;
        boolean expected = true;

        //Act
        boolean result = repositoryOne.equals(repositoryReference);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 02:Test to ensure that two objects are from different classes
     */
    @Test
    void ensureObjectsAreFromDifferentClasses() {
        // Arrange
        BusinessSectorRepository repositoryOne = new BusinessSectorRepository();

        Object otherObject = new Object();
        boolean expected = false;

        //Act
        boolean result = repositoryOne.equals(otherObject);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 04: Test to ensure that two objects from the same class are different
     */
    @Test
    void ensureTwoBusinessSectorRepositoriesAreNotEqual() {
        // Arrange
        BusinessSectorRepository repositoryOne = new BusinessSectorRepository();
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        repositoryOne.add(businessSectorDouble);
        BusinessSectorRepository repositoryTwo = new BusinessSectorRepository();

        boolean expected = false;
        //Act
        boolean result = repositoryOne.equals(repositoryTwo);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 05: Test to ensure that two objects from the same class are equals.
     */
    @Test
    void ensureTwoBusinessSectorRepositoriesAreEqual() {
        // Arrange
        BusinessSectorRepository repositoryOne = new BusinessSectorRepository();
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        repositoryOne.add(businessSectorDouble);
        BusinessSectorRepository repositoryTwo = new BusinessSectorRepository();
        repositoryTwo.add(businessSectorDouble);

        boolean expected = true;
        //Act
        boolean result = repositoryOne.equals(repositoryTwo);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 06: Test to ensure that a BusinessSectorRepository doesn't equal a null object.
     */
    @Test
    void ensureThatBusinessSectorRepositoryDoesNotEqualsNull() {
        // Arrange
        BusinessSectorRepository repositoryOne = new BusinessSectorRepository();

        boolean expected = false;
        //Act
        boolean result = repositoryOne.equals(null);
        //Assert
        assertEquals(expected, result);
    }


    /**
     * Method:hashCode()
     * Scenario 01:Two equal BusinessSectorRepository objects are the same.
     */
    @Test
    void ensureTwoUsIdInstancesHashcodeAreTheSame() {
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        BusinessSectorRepository repositoryOne = new BusinessSectorRepository();
        repositoryOne.add(businessSectorDouble);
        BusinessSectorRepository repositoryTwo = new BusinessSectorRepository();
        repositoryTwo.add(businessSectorDouble);

        //Assert
        assertEquals(repositoryOne.hashCode(), repositoryTwo.hashCode());
    }


    /**
     * Method:hashCode()
     * Scenario 02:Two objects with the different code and different hash codes are two different objects
     */
    @Test
    void ensureTwoDifferentBusinessSectorRepositoriesHaveTheSameHashCode() {
        //Arrange
        BusinessSector businessSectorOneDouble = mock(BusinessSector.class);
        BusinessSector businessSectorTwoDouble = mock(BusinessSector.class);
        BusinessSectorRepository repositoryOne = new BusinessSectorRepository();
        repositoryOne.add(businessSectorOneDouble);
        BusinessSectorRepository repositoryTwo = new BusinessSectorRepository();
        repositoryTwo.add(businessSectorTwoDouble);

        //Assert
        assertNotEquals(repositoryOne.hashCode(), repositoryTwo.hashCode());
    }

    /**
     * Method: add().
     * Scenario 01: make sure an instance of BusinessSector is successfully added to the repository.
     * Expected return: TRUE.
     */

    @Test
    void ensureTypologyIsAddedToRepository() {
        //Arrange
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        BusinessSectorRepository repository = new BusinessSectorRepository();

        //Act
        boolean result = repository.add(businessSectorDouble);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: check that an instance of BusinessSector is not added to the repository if it alreay exists in
     * the repository.
     * Expected return: AlreadyExistsInRepoException.
     */


    @Test
    void ensureThatAnExceptionIsThrownWhenBusinessSectorAlreadyExistsInRepo() {
        //Arrange
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        BusinessSectorRepository repository = new BusinessSectorRepository();

        repository.add(businessSectorDouble);
        String expected = "The business sector already exists in the repository.";

        //Act
        AlreadyExistsInRepoException exception = assertThrows(AlreadyExistsInRepoException.class, () ->
                repository.add(businessSectorDouble));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Method: getSize().
     * <br>
     * Scenario 01: return the number of typologies from a list
     * Expected result: the number of BusinessSector instances in the list.
     */
    @Test
    void ensureThatTheNumberOfTypologiesInTheRepoIsReturned() {
        //Arrange
        BusinessSector businessSectorOneDouble = mock(BusinessSector.class);
        BusinessSector businessSectorTwoDouble = mock(BusinessSector.class);

        BusinessSectorRepository repository = new BusinessSectorRepository();
        repository.add(businessSectorOneDouble);
        repository.add(businessSectorTwoDouble);

        int expected = 2;
        int result = repository.getSize();

        assertEquals(expected, result);
    }

}