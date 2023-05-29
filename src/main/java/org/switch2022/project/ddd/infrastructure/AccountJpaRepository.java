package org.switch2022.project.ddd.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.switch2022.project.ddd.datamodel_jpa.AccountJpa;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.AccountDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.infrastructure.jpa.IAccountJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link IAccountRepository} interface that uses JPA to persist and retrieve
 * account data.
 * This class interacts with the {@link IAccountJpaRepository} for performing CRUD operations on
 * {@link AccountJpa} entities.
 */
@Repository("account_jpa")
public class AccountJpaRepository implements IAccountRepository {

    @Autowired
    IAccountJpaRepository crudRepository;

    @Autowired
    AccountDomainDataAssembler assembler;

    /**
     * Saves an Account object if it is not null and does not already exist in the repository.
     *
     * @param account the Account object to be saved/added to the repository.
     * @return true if the Account was successfully saved, false otherwise.
     */
    @Override
    public boolean save(Account account) {
        boolean wasSaved = false;

        if (account != null && !crudRepository.existsById(account.getAccountEmail())) {
            AccountJpa accountJpa = assembler.toData(account);
            crudRepository.save(accountJpa);
            wasSaved = true;
        }

        return wasSaved;
    }

    /**
     * Retrieves all Account objects from the repository.
     * <p>
     * This method retrieves all Account objects stored in the repository by calling the
     * crudRepository.findAll() method. It then converts the retrieved AccountJpa objects to Account
     * objects using the convertAccountJpasToAccounts method and returns a List of Account objects.
     *
     * @return a List of all Account objects in the repository.
     */
    @Override
    public List<Account> findAll() {
        Iterable<AccountJpa> accountJpas = crudRepository.findAll();
        return convertAccountJpasToAccounts(accountJpas);
    }

    /**
     * Retrieves a list of Account objects from the repository based on the provided list of emails.
     * <p>
     * This method takes a list of Email objects and retrieves Account objects from the repository
     * based on the corresponding email strings.
     *
     * @param emails the list of Email objects used to retrieve the corresponding Account objects.
     * @return a List of Account objects matching the provided emails.
     */
    @Override
    public List<Account> findAccountsByEmails(List<Email> emails) {
        List<String> emailStrings = new ArrayList<>();

        for (Email email : emails) {
            emailStrings.add(email.getEmail());
        }

        Iterable<AccountJpa> accountJpas = crudRepository.findAllById(emailStrings);

        return convertAccountJpasToAccounts(accountJpas);
    }

    /**
     * Retrieves an Account object from the repository based on the provided email.
     * <p>
     * This method takes an email string and retrieves an Account object from the repository
     * that matches the provided email.
     *
     * @param email the email used to retrieve the corresponding Account object
     * @return the Account object matching the provided email, or null if not found
     */
    @Override
    public Account findAccountByEmail(String email) {
        Optional<AccountJpa> accountJpaOptional = crudRepository.findById(email);
        Account account = null;

        if (accountJpaOptional.isPresent()) {
            AccountJpa accountJpa = accountJpaOptional.get();
            account = assembler.toDomain(accountJpa);
        }

        return account;
    }

    /**
     * Converts an Iterable of AccountJpa objects to a List of Account objects.
     * <p>
     * This method takes an Iterable of AccountJpa objects and converts each AccountJpa object
     * to an Account object using the assembler.toDomain method. The converted Account objects
     * are then added to a List and returned.
     *
     * @param accountJpas the Iterable of AccountJpa objects to be converted
     * @return a List of Account objects converted from the AccountJpa objects
     */
    private List<Account> convertAccountJpasToAccounts(Iterable<AccountJpa> accountJpas) {
        List<Account> accounts = new ArrayList<>();

        for (AccountJpa accountJpa : accountJpas) {
            Account account = assembler.toDomain(accountJpa);
            accounts.add(account);
        }

        return accounts;
    }
}
