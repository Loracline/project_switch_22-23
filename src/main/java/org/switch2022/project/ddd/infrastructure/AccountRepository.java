package org.switch2022.project.ddd.infrastructure;

import org.springframework.stereotype.Repository;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.InvalidInputException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;
import org.switch2022.project.ddd.utils.Validate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository("account_memory")
public class AccountRepository implements IAccountRepository {

    /**
     * Attributes
     */
    private final List<Account> accounts = new ArrayList<>();

    /**
     * This method adds an instance of Account to AccountRepository if that instance
     * does not
     * already exist there.
     *
     * @param account to be added to the repository.
     * @return true if the account was added to the account repository, and throws an
     * AlreadyExistInRepoException otherwise.
     */
    public boolean save(Account account) {
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
    public List<Account> findAll() {
        return Collections.unmodifiableList(accounts);
    }


    /**
     * Retrieves a list of accounts that match the given list of emails.
     *
     * @param emails the list of emails to match accounts against.
     * @return a list of accounts matching the provided emails.
     * @throws InvalidInputException if the object is null.
     */
    @Override
    public List<Account> findAccountsByEmails(List<Email> emails) {
        Validate.notNull(emails, "E-mail's list can't be null.");
        List<Account> accountsWithMatchingEmails = new ArrayList<>();
        for (int i = 0; i < emails.size(); i++) {
            Account account = findAccountByEmail(emails.get(i).getEmailAddress());
            accountsWithMatchingEmails.add(account);
        }
        return accountsWithMatchingEmails;
    }

    /**
     * This method returns an optional of an account.
     *
     * @param email to search for the account.
     * @return an optional of account with the requested account or optional of null if
     * it does not find the desired account.
     */
    @Override
    public Account findAccountByEmail(String email) {
        Account accountRequested = null;
        for (int i = 0; i < this.accounts.size(); i++) {
            if (accounts.get(i).hasEmail(email)) {
                accountRequested = accounts.get(i);
            }
        }
        if (accountRequested == null) {
            throw new NotFoundInRepoException("This account " +
                    "doesn't exist");
        }
        return accountRequested;
    }
}