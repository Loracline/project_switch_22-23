package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.InvalidInputException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountRepositoryTest {

    /**
     * Method: equals()
     * Scenario 01: Test to ensure the object equals itself
     */
    @SuppressWarnings("all")
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
    @SuppressWarnings("all")
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

    /**
     * Method getAccounts()
     * Scenario 1: returns a list with all Accounts
     */
    @Test
    void ensureThatAListWithAccountsISReturned() {
        // Arrange
        Account accountOne = mock(Account.class);
        Account accountTwo = mock(Account.class);
        Account accountThree = mock(Account.class);

        List<Account> expected = Arrays.asList(accountOne, accountTwo, accountThree);

        AccountRepository repository = new AccountRepository();
        repository.add(accountOne);
        repository.add(accountTwo);
        repository.add(accountThree);

        // Act
        List<Account> result = repository.getAccounts();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: returns an empty list
     */
    @Test
    void ensureThatAnEmptyListISReturned() {
        // Arrange
        List<Account> expected = new ArrayList<>();

        AccountRepository repository = new AccountRepository();

        // Act
        List<Account> result = repository.getAccounts();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Method getAccounts(emails) returns all the accounts with emails matching a given list of emails that is passed
     * as argument.
     * Scenario 01: returns a list with all Accounts that have matching emails.
     */
    @Test
    void ensureThatAListWithAccountsISReturnedIfThereAreMatchingEmails() {
        // Arrange
        AccountRepository repository = new AccountRepository();
        List<String> emails = mock(List.class);

        Account accountOneDouble = mock(Account.class);
        Account accountTwoDouble = mock(Account.class);
        Account accountThreeDouble = mock(Account.class);

        repository.add(accountOneDouble);
        repository.add(accountTwoDouble);
        repository.add(accountThreeDouble);

        when(emails.size()).thenReturn(1);
        when(accountOneDouble.hasEmail(any())).thenReturn(true);
        when(accountTwoDouble.hasEmail(any())).thenReturn(true);

        List<Account> expected = new ArrayList<>();
        expected.add(accountOneDouble);
        expected.add(accountTwoDouble);

        // Act
        List<Account> result = repository.getAccounts(emails);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Method getAccounts(emails) returns all the accounts with emails matching a given list of emails that is passed
     * as argument.
     * Scenario 02: returns an empty list if no accounts have matching emails.
     */
    @Test
    void ensureThatAnEmptyListIsReturnedIfThereAreNoMatchingEmails() {
        // Arrange
        AccountRepository repository = new AccountRepository();
        List<String> emails = mock(List.class);

        Account accountOneDouble = mock(Account.class);
        Account accountTwoDouble = mock(Account.class);
        Account accountThreeDouble = mock(Account.class);

        repository.add(accountOneDouble);
        repository.add(accountTwoDouble);
        repository.add(accountThreeDouble);

        when(emails.size()).thenReturn(1);

        List<Account> expected = new ArrayList<>();

        // Act
        List<Account> result = repository.getAccounts(emails);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Method getAccounts(emails) returns all the accounts with emails matching a given list of emails that is passed
     * as argument.
     * Scenario 03: returns an empty list if an empty list of emails has been passed as argument.
     */
    @Test
    void ensureThatAnEmptyListIsReturnedIfThereAreNoEmailsToMatch() {
        // Arrange
        AccountRepository repository = new AccountRepository();
        List<String> emails = mock(List.class);

        Account accountOneDouble = mock(Account.class);
        Account accountTwoDouble = mock(Account.class);
        Account accountThreeDouble = mock(Account.class);

        repository.add(accountOneDouble);
        repository.add(accountTwoDouble);
        repository.add(accountThreeDouble);

        List<Account> expected = new ArrayList<>();

        // Act
        List<Account> result = repository.getAccounts(emails);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Method getAccounts(emails) returns all the accounts with emails matching a given list of emails that is passed
     * as argument.
     * Scenario 04: throws an exception if a null object has been passed as argument.
     * It should throw an InvalidInputException.
     */
    @Test
    void ensureThatThrowsAnExceptionIfTheEmailListIsNull() {
        // Arrange
        AccountRepository repository = new AccountRepository();

        // Act and Assert
        assertThrows(InvalidInputException.class, () -> repository.getAccounts(null));
    }

    /**
     * Method getAccountByEmail()
     * Scenario 1: returns
     */

    @Test
    void ensureAccountIsReturned() {
        // Arrange
        Account accountOne = mock(Account.class);
        Account accountTwo = mock(Account.class);

        AccountRepository repository = new AccountRepository();
        repository.add(accountOne);
        repository.add(accountTwo);

        String email = "ana@isep.pt";
        when(accountOne.hasEmail(email)).thenReturn(true);

        // Act
        Account result = repository.getAccountByEmail(email);

        // Assert
        assertEquals(accountOne, result);
    }

    @Test
    void ensureThatAccountIsNotFoundBecauseListIsEmpty() {
        //Arrange
        AccountRepository repository = new AccountRepository();
        String email = "ana@isep.pt";
        String message = "This account doesn't exist";

        //Act
        NotFoundInRepoException result =
                assertThrows(NotFoundInRepoException.class, () -> repository.getAccountByEmail(email));

        //Assert
        assertEquals(message, result.getMessage());
    }

    @Test
    void ensureThatAccountIsNotFoundBecauseIsNotInTheList() {
        //Arrange
        Account accountOne = mock(Account.class);

        AccountRepository repository = new AccountRepository();
        String email = "ana@isep.pt";
        when(accountOne.hasEmail(email)).thenReturn(false);
        String message = "This account doesn't exist";

        //Act
        NotFoundInRepoException result =
                assertThrows(NotFoundInRepoException.class, () -> repository.getAccountByEmail(email));

        //Assert
        assertEquals(message, result.getMessage());
    }

    /**
     * Method IsAValidAccount().
     * Scenario 01:Make sure the account is valid and active.
     * Expected return: True.
     */
    @Test
    void ensureThatAccountIsValid() {
        //Arrange
        Email accountEmailDouble= mock(Email.class);
        Account accountDouble = mock(Account.class);
        AccountRepository repository = new AccountRepository();
        repository.add(accountDouble);
        when(accountDouble.hasEmail(accountEmailDouble.getEmail())).thenReturn(true);
        when(accountDouble.getAccountStatus()).thenReturn(true);
        //Act
        boolean result = repository.IsAValidAccount(accountEmailDouble);
        //Assert
        assertTrue(result);
    }

    /**
     * Scerario 02: the account exists, but is inactive.
     * Expected return: false.
     */
    @Test
    void ensureTheAccointExistisButIsInactivate() {
        //Arrange
        Email accountEmailDouble= mock(Email.class);
        Account accountDouble = mock(Account.class);
        AccountRepository repository = new AccountRepository();
        repository.add(accountDouble);
        when(accountDouble.hasEmail(accountEmailDouble.getEmail())).thenReturn(true);
        when(accountDouble.getAccountStatus()).thenReturn(false);
        //Act
        boolean result = repository.IsAValidAccount(accountEmailDouble);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 03: the account is invalid, because the account does not exist in the list.
     * Expected return: false.
     */
    @Test
    void ensureTheAccountIsInvalidBecauseTheAccountDoesNotExist() {
        //Arrange
        Email accountEmailDouble= mock(Email.class);
        Account accountDouble = mock(Account.class);
        AccountRepository repository = new AccountRepository();
        when(accountDouble.getAccountStatus()).thenReturn(false);
        //Act
        boolean result = repository.IsAValidAccount(accountEmailDouble);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 03: the account is invalid, because the account is null.
     * Expected return: false.
     */
    @Test
    void ensureTheAccountIsInvalidBecauseTheAccountIsNull() {
        //Arrange
        AccountRepository repository = new AccountRepository();
        //Act
        boolean result = repository.IsAValidAccount(null);
        //Assert
        assertFalse(result);
    }

}