package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.datamodel_jpa.AccountJpa;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.AccountDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.infrastructure.jpa.IAccountJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = AccountJpaRepository.class)
class AccountJpaRepositoryTest {

    @InjectMocks
    AccountJpaRepository accountJpaRepository;

    @MockBean
    IAccountJpaRepository crudRepository;

    @MockBean
    AccountDomainDataAssembler assembler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * METHOD save()
     */

    @DisplayName("Account is saved")
    @Test
    void ensureAccountIsSaved() {
        // Arrange
        Account account = mock(Account.class);
        when(account.getAccountEmail()).thenReturn("josh@email.pt");
        when(crudRepository.existsById(account.getAccountEmail())).thenReturn(false);

        AccountJpa accountJpa = mock(AccountJpa.class);
        when(assembler.toData(account)).thenReturn(accountJpa);

        //Act
        boolean result = accountJpaRepository.save(account);

        //Assert
        assertTrue(result);
    }

    @DisplayName("Account is not saved because is null")
    @Test
    void ensureAccountIsNotSavedWhenItsNull() {
        //Act
        boolean result = accountJpaRepository.save(null);

        //Assert
        assertFalse(result);
    }

    @DisplayName("Account already exists")
    @Test
    void ensureAccountIsNotSavedWhenAlreadyExistsInRepo() {
        // Arrange
        Account account = mock(Account.class);
        when(account.getAccountEmail()).thenReturn("josh@email.pt");
        when(crudRepository.existsById(account.getAccountEmail())).thenReturn(true);

        //Act
        boolean result = accountJpaRepository.save(account);

        //Assert
        assertFalse(result);
    }

    /**
     * METHOD findAll()
     */

    @DisplayName("Find all accounts on the repository")
    @Test
    void ensureFindAllReturnsTheCorrectInfo() {
        // Arrange
        List<AccountJpa> accountJpas = new ArrayList<>();
        AccountJpa accountJpaOne = mock(AccountJpa.class);
        AccountJpa accountJpaTwo = mock(AccountJpa.class);
        accountJpas.add(accountJpaOne);
        accountJpas.add(accountJpaTwo);

        when(crudRepository.findAll()).thenReturn(accountJpas);

        Account accountOne = mock(Account.class);
        Account accountTwo = mock(Account.class);

        when(assembler.toDomain(accountJpaOne)).thenReturn(accountOne);
        when(assembler.toDomain(accountJpaTwo)).thenReturn(accountTwo);

        // Act
        List<Account> accounts = accountJpaRepository.findAll();

        // Assert
        assertEquals(accountOne, accounts.get(0));
        assertEquals(accountTwo, accounts.get(1));
    }

    /**
     * METHOD findAllById()
     */

    @DisplayName("Find accounts searching by respective Id, which is the (unique) Email")
    @Test
    void ensureFindAccountsByEmailsReturnsTheCorrectAccounts() {
        // Arrange
        Email emailOne = mock(Email.class);
        when(emailOne.getEmailAddress()).thenReturn("josh@email.pt");

        Email emailTwo = mock(Email.class);
        when(emailTwo.getEmailAddress()).thenReturn("annah@email.pt");

        List<Email> emails = new ArrayList<>();
        emails.add(emailOne);
        emails.add(emailTwo);

        List<String> emailsString = new ArrayList<>();
        emailsString.add("josh@email.pt");
        emailsString.add("annah@email.pt");

        List<AccountJpa> accountJpas = new ArrayList<>();
        AccountJpa accountJpaOne = mock(AccountJpa.class);
        AccountJpa accountJpaTwo = mock(AccountJpa.class);
        accountJpas.add(accountJpaOne);
        accountJpas.add(accountJpaTwo);

        when(crudRepository.findAllById(emailsString)).thenReturn(accountJpas);

        Account accountOne = mock(Account.class);
        when(assembler.toDomain(accountJpaOne)).thenReturn(accountOne);

        Account accountTwo = mock(Account.class);
        when(assembler.toDomain(accountJpaTwo)).thenReturn(accountTwo);

        // Act
        List<Account> accounts = accountJpaRepository.findAccountsByEmails(emails);

        // Assert
        assertEquals(accountOne, accounts.get(0));
        assertEquals(accountTwo, accounts.get(1));
    }

    /**
     * METHOD findAccountByEmail()
     */

    @DisplayName("Account corresponds to the respective Email")
    @Test
    void ensureFindAccountByEmailReturnsTheCorrectAccount() {
        // Arrange
        String email = "josh@email.pt";
        AccountJpa accountJpa = mock(AccountJpa.class);
        when(crudRepository.findById(email)).thenReturn(Optional.of(accountJpa));

        Account accountToBeReturned = mock(Account.class);
        when(assembler.toDomain(accountJpa)).thenReturn(accountToBeReturned);

        // Act
        Account account = accountJpaRepository.findAccountByEmail(email);

        // Assert
        assertEquals(accountToBeReturned, account);
    }

    @DisplayName("Account dont match with any Email")
    @Test
    void ensureFindAccountByEmailDoesNotReturnAnyAccountWhenEmailDoesNotMatch() {
        // Arrange
        String email = "josh@email.pt";
        when(crudRepository.findById(email)).thenReturn(Optional.empty());

        // Act
        Account account = accountJpaRepository.findAccountByEmail(email);

        // Assert
        assertNull(account);
    }
}
