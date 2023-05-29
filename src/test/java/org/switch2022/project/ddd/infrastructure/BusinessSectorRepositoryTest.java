package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.business_sector.BusinessSector;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BusinessSectorRepositoryTest {
    /**
     * Method: equals()
     * Scenario 01: Test to ensure the object equals itself
     */
    @Test
    void ensureSameObjectEqualsItself() {
        // Arrange
        BusinessSectorRepository repositoryOne = new BusinessSectorRepository();
        BusinessSectorRepository repositoryTwo = repositoryOne;
        boolean expected = true;

        //Act
        boolean result = repositoryOne.equals(repositoryTwo);

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
        repositoryOne.save(businessSectorDouble);
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
        repositoryOne.save(businessSectorDouble);
        BusinessSectorRepository repositoryTwo = new BusinessSectorRepository();
        repositoryTwo.save(businessSectorDouble);

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
        repositoryOne.save(businessSectorDouble);
        BusinessSectorRepository repositoryTwo = new BusinessSectorRepository();
        repositoryTwo.save(businessSectorDouble);

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
        repositoryOne.save(businessSectorOneDouble);
        BusinessSectorRepository repositoryTwo = new BusinessSectorRepository();
        repositoryTwo.save(businessSectorTwoDouble);

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
        boolean result = repository.save(businessSectorDouble);

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

        repository.save(businessSectorDouble);
        String expected = "The business sector already exists in the repository.";

        //Act
        AlreadyExistsInRepoException exception = assertThrows(AlreadyExistsInRepoException.class, () ->
                repository.save(businessSectorDouble));

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
        repository.save(businessSectorOneDouble);
        repository.save(businessSectorTwoDouble);

        int expected = 2;
        int result = repository.count();

        assertEquals(expected, result);
    }

    /**
     * METHOD getBusinessSectorIdByName()
     * <p>
     * Scenario 1: business sector ID is retrieved successfully.
     */
    @Test
    void ensureBusinessSectorIdIsRetrievedSuccessfully() {
        // Arrange
        String businessSectorName = "Sports";
        String businessSectorId = "001";
        BusinessSector businessSectorOne = mock(BusinessSector.class);

        BusinessSectorRepository repository = new BusinessSectorRepository();
        repository.save(businessSectorOne);

        when(businessSectorOne.getBusinessSectorName()).thenReturn(businessSectorName);
        when(businessSectorOne.getBusinessSectorId()).thenReturn(businessSectorId);

        // Act
        String result = repository.getBusinessSectorIdByName(businessSectorName);

        // Assert
        assertEquals(businessSectorId, result);
    }

    /**
     * Scenario 2: business sector ID is not retrieved successfully.
     */
    @Test
    void ensureBusinessSectorIdIsNotRetrievedSuccessfully() {
        // Arrange
        // Arrange
        String businessSectorNameOne = "Sports";
        String businessSectorNameTwo = "Fashion";
        String businessSectorId = "001";
        BusinessSector businessSectorOne = mock(BusinessSector.class);


        BusinessSectorRepository repository = new BusinessSectorRepository();
        repository.save(businessSectorOne);

        when(businessSectorOne.getBusinessSectorName()).thenReturn(businessSectorNameOne);
        when(businessSectorOne.getBusinessSectorId()).thenReturn(businessSectorId);

        // Act
        NotFoundInRepoException exception = assertThrows(NotFoundInRepoException.class,
                () -> repository.getBusinessSectorIdByName(businessSectorNameTwo));

        String expected = "Business sector with this name does not exist in the Repository.";

        // Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD findAll()
     */
    @DisplayName("Empty repository")
    @Test
    void ensureReturnsEmptyListWhenEmptyRepository() {
        // Arrange
        BusinessSectorRepository repository = new BusinessSectorRepository();
        List<BusinessSector> expected = new ArrayList<>();

        // Act
        List<BusinessSector> result = repository.findAll();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: ensure that an empty list is returned when the repository is empty.
     */
    @Test
    void ensureAllBusinessSectorsInRepositoryAreReturnedInList() {
        // Arrange
        BusinessSectorRepository repository = new BusinessSectorRepository();
        List<BusinessSector> expected = new ArrayList<>();

        BusinessSector businessSectorOne = mock(BusinessSector.class);
        BusinessSector businessSectorTwo = mock(BusinessSector.class);
        BusinessSector businessSectorThree = mock(BusinessSector.class);

        expected.add(businessSectorOne);
        expected.add(businessSectorTwo);
        expected.add(businessSectorThree);

        repository.save(businessSectorOne);
        repository.save(businessSectorTwo);
        repository.save(businessSectorThree);

        // Act
        List<BusinessSector> result = repository.findAll();

        // Assert
        assertEquals(expected, result);
    }
}