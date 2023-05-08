package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AccountRepositoryTest {

    /**
     * Method: equals()
     * Scenario 01: Test to ensure the object equals itself
     */
    @Test
    void ensureSameObjectEqualsItself() {
        // Arrange
        AccountRepository repositoryOne = new AccountRepository();
        AccountRepository repositoryReference = repositoryOne;
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
        AccountRepository repositoryOne = new AccountRepository();

        Object otherObject = new Object();
        boolean expected = false;

        //Act
        boolean result = repositoryOne.equals(otherObject);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 03: Test to ensure that two objects from the same class are different
     */
    @Test
    void ensureTwoAccountRepositoriesAreNotEqual() {
        // Arrange
        AccountRepository repositoryOne = new AccountRepository();
        Account accountDouble = mock(Account.class);
        repositoryOne.add(accountDouble);
        AccountRepository repositoryTwo = new AccountRepository();

        boolean expected = false;
        //Act
        boolean result = repositoryOne.equals(repositoryTwo);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 04: Test to ensure that two objects from the same class are equals.
     */
    @Test
    void ensureTwoAccountRepositoriesAreEqual() {
        // Arrange
        AccountRepository repositoryOne = new AccountRepository();
        Account accountDouble = mock(Account.class);
        repositoryOne.add(accountDouble);
        AccountRepository repositoryTwo = new AccountRepository();
        repositoryTwo.add(accountDouble);

        boolean expected = true;
        //Act
        boolean result = repositoryOne.equals(repositoryTwo);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 05: Test to ensure that a AccountRepository doesn't equal a null object.
     */
    @Test
    void ensureThatAccountRepositoryDoesNotEqualsNull() {
        // Arrange
        AccountRepository repositoryOne = new AccountRepository();

        boolean expected = false;
        //Act
        boolean result = repositoryOne.equals(null);
        //Assert
        assertEquals(expected, result);
    }


    /**
     * Method:hashCode()
     * Scenario 01:Two equal AccountRepository objects are the same.
     */
    @Test
    void ensureTwoAccountInstancesHashcodeAreTheSame() {
        Account accountDouble = mock(Account.class);
        AccountRepository repositoryOne = new AccountRepository();
        repositoryOne.add(accountDouble);
        AccountRepository repositoryTwo = new AccountRepository();
        repositoryTwo.add(accountDouble);

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
        Account accountOneDouble = mock(Account.class);
        Account accountTwoDouble = mock(Account.class);
        AccountRepository repositoryOne = new AccountRepository();
        repositoryOne.add(accountOneDouble);
        AccountRepository repositoryTwo = new AccountRepository();
        repositoryTwo.add(accountTwoDouble);

        //Assert
        assertNotEquals(repositoryOne.hashCode(), repositoryTwo.hashCode());
    }

    /**
     * Method: add().
     * Scenario 01: make sure an instance of Account is successfully added to the repository.
     * Expected return: TRUE.
     */

    @Test
    void ensureAccountIsAddedToRepository() {
        //Arrange
        Account accountDouble = mock(Account.class);
        AccountRepository repository = new AccountRepository();

        //Act
        boolean result = repository.add(accountDouble);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: check that an instance of Account is not added to the repository if it already exists in
     * the repository.
     * Expected return: AlreadyExistsInRepoException.
     */


    @Test
    void ensureThatAnExceptionIsThrownWhenAccountAlreadyExistsInRepo() {
        //Arrange
        Account accountDouble = mock(Account.class);
        AccountRepository repository = new AccountRepository();

        repository.add(accountDouble);
        String expected = "The account already exists in the repository.";

        //Act
        AlreadyExistsInRepoException exception = assertThrows(AlreadyExistsInRepoException.class, () ->
                repository.add(accountDouble));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

}