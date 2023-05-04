package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.typology.Typology;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TypologyRepositoryTest {
    /**
     * Method: equals()
     * Scenario 01: Test to ensure the object equals itself
     */
    @Test
    void ensureSameObjectEqualsItself() {
        // Arrange
        TypologyRepository typologyRepositoryOne = new TypologyRepository();
        TypologyRepository typologyRepositoryReference = typologyRepositoryOne;
        boolean expected = true;

        //Act
        boolean result = typologyRepositoryOne.equals(typologyRepositoryReference);

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
        TypologyRepository typologyRepositoryOne = new TypologyRepository();

        Object typologyRepository = new Object();
        boolean expected = false;

        //Act
        boolean result = typologyRepositoryOne.equals(typologyRepository);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 04: Test to ensure that two objects from the same class are different
     */
    @Test
    void ensureTwoProjectsAreNotEqual() {
        // Arrange
        TypologyRepository typologyRepositoryOne = new TypologyRepository();
        Typology typologyDouble = mock(Typology.class);
        typologyRepositoryOne.add(typologyDouble);
        TypologyRepository typologyRepositoryTwo = new TypologyRepository();

        boolean expected = false;
        //Act
        boolean result = typologyRepositoryOne.equals(typologyRepositoryTwo);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 05: Test to ensure that two objects from the same class are equals.
     */
    @Test
    void ensureTwoProjectsAreEqual() {
        // Arrange
        TypologyRepository typologyRepositoryOne = new TypologyRepository();
        Typology typologyDouble = mock(Typology.class);
        typologyRepositoryOne.add(typologyDouble);
        TypologyRepository typologyRepositoryTwo = new TypologyRepository();
        typologyRepositoryTwo.add(typologyDouble);

        boolean expected = true;
        //Act
        boolean result = typologyRepositoryOne.equals(typologyRepositoryTwo);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 06: Test to ensure that Repository doesn't equals a null object
     */
    @Test
    void ensureThatProjectDoesNotEqualsNull() {
        // Arrange
        TypologyRepository typologyRepositoryOne = new TypologyRepository();
        TypologyRepository typologyRepositoryTwo = null;

        boolean expected = false;
        //Act
        boolean result = typologyRepositoryOne.equals(typologyRepositoryTwo);
        //Assert
        assertEquals(expected, result);
    }


    /**
     * Method:hashCode()
     * Scenario 01:Two equal ProjectsRepository objects are the same.
     */
    @Test
    void ensureTwoUsIdInstancesHashcodeAreTheSame() {
        Typology typologyDouble = mock(Typology.class);
        TypologyRepository typologyRepositoryOne = new TypologyRepository();
        typologyRepositoryOne.add(typologyDouble);
        TypologyRepository typologyRepositoryTwo = new TypologyRepository();
        typologyRepositoryTwo.add(typologyDouble);

        //Assert
        assertEquals(typologyRepositoryOne.hashCode(), typologyRepositoryTwo.hashCode());
    }


    /**
     * Method:hashCode()
     * Scenario 02:Two objects with the different code and different hash codes are two different objects
     */
    @Test
    void ensureNoTwoProjectsHaveTheSameHashCode() {
        //Arrange
        Typology typologyOneDouble = mock(Typology.class);
        Typology typologyTwoDouble = mock(Typology.class);
        TypologyRepository typologyRepositoryOne = new TypologyRepository();
        typologyRepositoryOne.add(typologyOneDouble);
        TypologyRepository typologyRepositoryTwo = new TypologyRepository();
        typologyRepositoryTwo.add(typologyTwoDouble);

        //Assert
        assertNotEquals(typologyRepositoryOne.hashCode(), typologyRepositoryTwo.hashCode());
    }

    /**
     * Method: add().
     * Scenario 01: make sure the typology was successfully added to the repository.
     * Expected return: TRUE.
     */

    @Test
    void ensureTypologyIsAddedToRepository() {
        //Arrange
        Typology typologyDouble = mock(Typology.class);
        TypologyRepository typologyRepository = new TypologyRepository();

        //Act
        boolean result = typologyRepository.add(typologyDouble);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: check that the typology has not been added to the repository.
     * Expected return: FALSE.
     */


    @Test
    void ensureThatAnExceptionIsThrownWhenTypologyAlreadyExistsInRepo() {
        //Arrange
        Typology typologyDouble = mock(Typology.class);
        TypologyRepository typologyRepository = new TypologyRepository();

        typologyRepository.add(typologyDouble);
        String expected = "The typology already exists in the repository.";

        //Act
        AlreadyExistsInRepoException exception = assertThrows(AlreadyExistsInRepoException.class, () ->
                typologyRepository.add(typologyDouble));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Method: getSize().
     * <br>
     * Scenario 01: return the number of typologies from a list
     * Expected result: true.
     */
    @Test
    void ensureThatTheNumberOfTypologiesInTheRepoIsReturned() {
        //Arrange
        Typology typologyOneDouble = mock(Typology.class);
        Typology typologyTwoDouble = mock(Typology.class);

        TypologyRepository typologyRepository = new TypologyRepository();
        typologyRepository.add(typologyOneDouble);
        typologyRepository.add(typologyTwoDouble);

        int expected = 2;
        int result = typologyRepository.getSize();

        assertEquals(expected, result);

    }

}