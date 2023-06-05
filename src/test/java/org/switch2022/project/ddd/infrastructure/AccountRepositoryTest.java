package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountRepositoryTest {

    /**
     * Method: save().
     * Scenario 01: make sure an instance of Account is successfully added to the repository.
     * Expected return: TRUE.
     */
    @Test
    void ensureAccountIsAddedToRepository() {
        //Arrange
        Account accountDouble = mock(Account.class);
        AccountRepository repository = new AccountRepository();

        //Act
        boolean result = repository.save(accountDouble);

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

        repository.save(accountDouble);
        String expected = "The account already exists in the repository.";

        //Act
        AlreadyExistsInRepoException exception = assertThrows(AlreadyExistsInRepoException.class, () ->
                repository.save(accountDouble));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Method findAll()
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
        repository.save(accountOne);
        repository.save(accountTwo);
        repository.save(accountThree);

        // Act
        List<Account> result = repository.findAll();

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
        List<Account> result = repository.findAll();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD findAccountsByEmails()
     */
    @DisplayName("List of accounts matching e-mails")
    @Test
    void ensureThatAListWithAccountsISReturnedIfThereAreMatchingEmails() {
        // Arrange
        AccountRepository repository = new AccountRepository();

        List<Account> expected = new ArrayList<>();
        Account accountOne = mock(Account.class);
        Account accountTwo = mock(Account.class);
        Account accountThree = mock(Account.class);
        expected.add(accountOne);
        expected.add(accountTwo);
        expected.add(accountThree);

        repository.save(accountOne);
        repository.save(accountTwo);
        repository.save(accountThree);

        List<Email> emails = new ArrayList<>();
        Email emailOne = mock(Email.class);
        Email emailTwo = mock(Email.class);
        Email emailThree = mock(Email.class);
        emails.add(emailOne);
        emails.add(emailTwo);
        emails.add(emailThree);

        String stringOf_emailOne = "emailOne@isep.ipp.pt";
        String stringOf_emailTwo = "emailTwo@isep.ipp.pt";
        String stringOf_emailThree = "emailThree@isep.ipp.pt";

        when(emailOne.getEmailAddress()).thenReturn(stringOf_emailOne);
        when(emailTwo.getEmailAddress()).thenReturn(stringOf_emailTwo);
        when(emailThree.getEmailAddress()).thenReturn(stringOf_emailThree);
        when(accountOne.hasEmail(stringOf_emailOne)).thenReturn(true);
        when(accountTwo.hasEmail(stringOf_emailTwo)).thenReturn(true);
        when(accountThree.hasEmail(stringOf_emailThree)).thenReturn(true);

        // Act
        List<Account> result = repository.findAccountsByEmails(emails);

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Empty list of e-mails")
    @Test
    void ensureThatAnEmptyListIsReturnedIfThereAreNoEmailsToMatch() {
        // Arrange
        AccountRepository repository = new AccountRepository();

        List<Account> expected = new ArrayList<>();
        Account accountOne = mock(Account.class);
        Account accountTwo = mock(Account.class);
        Account accountThree = mock(Account.class);

        repository.save(accountOne);
        repository.save(accountTwo);
        repository.save(accountThree);

        List<Email> emails = new ArrayList<>();

        // Act
        List<Account> result = repository.findAccountsByEmails(emails);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Method findAccountByEmail()
     * Scenario 1: returns an account with the given e-mail
     */
    @Test
    void ensureAccountIsReturned() {
        // Arrange
        Account accountOne = mock(Account.class);
        Account accountTwo = mock(Account.class);

        AccountRepository repository = new AccountRepository();
        repository.save(accountOne);
        repository.save(accountTwo);

        String email = "ana@isep.pt";
        when(accountOne.hasEmail(email)).thenReturn(true);

        // Act
        Account result = repository.findAccountByEmail(email);

        // Assert
        assertEquals(accountOne, result);
    }

    /**
     * Scenario 2: Account is not retrieved because the list is empty
     */
    @Test
    void ensureThatAccountIsNotFoundBecauseListIsEmpty() {
        //Arrange
        AccountRepository repository = new AccountRepository();
        String email = "ana@isep.pt";
        String message = "This account doesn't exist";

        //Act
        NotFoundInRepoException result =
                assertThrows(NotFoundInRepoException.class, () -> repository.findAccountByEmail(email));

        //Assert
        assertEquals(message, result.getMessage());
    }

    /**
     * Scenario 3: Account is not retrieved because it is not found.
     */
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
                assertThrows(NotFoundInRepoException.class, () -> repository.findAccountByEmail(email));

        //Assert
        assertEquals(message, result.getMessage());
    }
}