package org.switch2022.project.ddd.infrastructure;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class AccountRepository implements IAccountRepository {

    /**
     * Attributes
     */
    private final List<Account> accounts = new ArrayList<>();

    /**
     * This method checks if an instance of AccountRepository is equal to another object.
     *
     * @param o object to compare with.
     * @return true if the two have the same attribute value, and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountRepository that = (AccountRepository) o;
        return Objects.equals(accounts, that.accounts);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the
     * object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return Objects.hash(accounts);
    }


    /**
     * This method adds an instance of Account to AccountRepository if that instance
     * does not
     * already exist there.
     *
     * @param account to be added to the repository.
     * @return true if the account was added to the account repository, and throws an
     * AlreadyExistInRepoException otherwise.
     */
    public boolean add(Account account) {
        if (accounts.contains(account)) {
            throw new AlreadyExistsInRepoException("The account already exists in the " +
                    "repository.");
        } else {
            accounts.add(account);
            return true;
        }
    }

    /**
     * This method gets all the Accounts
     *
     * @return a list with all accounts.
     */
    @Override
    public List<Account> getAccounts() {
        return this.accounts;
    }

    /**
     * This method returns an optional of an account.
     * @param email to search for the account.
     * @return an optional of account with the requested account or optional of null if
     * it does not find the desired account.
     */
    @Override
    public Account getAccountByEmail(String email) {
        Account accountRequested = null;
        int i = 0;
        while (i < this.accounts.size() && accountRequested == null) {
            if (accounts.get(i).hasEmail(email)) {
                accountRequested = accounts.get(i);
            }
        }
        if (accountRequested == null) throw new NotFoundInRepoException("This account " +
                "doesn't exist");
        return accountRequested;
    }
}