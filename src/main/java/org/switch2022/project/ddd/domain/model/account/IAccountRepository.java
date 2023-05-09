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
    List<Account> getAccounts();

    /**
     * This method returns an account with the given.
     * @param email to search for the account.
     * @return an account with the given email.
     */
    Account getAccountByEmail(String email);
}
