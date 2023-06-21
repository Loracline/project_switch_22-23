package org.switch2022.project.ddd.domain.model.account;

import org.switch2022.project.ddd.domain.value_object.Email;

import java.util.List;

/**
 * Interface for a repository of Accounts.
 */
public interface IAccountRepository {

    /**
     * This method adds a new business sector to the repository of business sectors.
     *
     * @param account to be added to the repository.
     * @return true if the account is added, and throws an AlreadyExistInRepoException otherwise.
     */
    boolean save(Account account);

    /**
     * This method returns all the account
     *
     * @return a list with all accounts.
     */
    List<Account> findAll();

    /**
     * Retrieves a list of accounts that match the given list of emails.
     *
     * @param emails the list of emails to match accounts against.
     * @return a list of accounts matching the provided emails.
     */
    List<Account> findAccountsByEmails(List<Email> emails);

    /**
     * This method returns an account with the given.
     *
     * @param email to search for the account.
     * @return an account with the given email.
     */
    Account findAccountByEmail(String email);
}
