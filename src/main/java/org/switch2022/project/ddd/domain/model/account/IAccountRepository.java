package org.switch2022.project.ddd.domain.model.account;

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
}
