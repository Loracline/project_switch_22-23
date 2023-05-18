package org.switch2022.project.ddd.domain.model.account;

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
    boolean add(Account account);

    /**
     * This method returns all the account
     * @return a list with all accounts.
     */
    List<Account> findAll();


    /**
     * This method returns all the accounts with emails matching a given list of emails that is passed as argument.
     * @param emails list of emails to match with accounts.
     * @return a list with accounts with matching emails, or an empty list if no accounts have matching emails.
     */
    List<Account> getAccounts(List<String> emails);

    /**
     * This method returns an account with the given.
     * @param email to search for the account.
     * @return an account with the given email.
     */
    Account getAccountByEmail(String email);
}
