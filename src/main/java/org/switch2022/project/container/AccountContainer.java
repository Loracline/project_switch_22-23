package org.switch2022.project.container;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.Profile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Class AccountContainer is built to access and manipulate the set of accounts
 * of this company
 */
public class AccountContainer {
    /**
     * Attributes
     */
    private final List<Account> accounts = new ArrayList<>();

    // "GETTERS"

    /**
     * Getter method for the attribute: accounts
     *
     * @return a copy list of all accounts of the company.
     */
    public List<Account> getAccounts() {
        return new ArrayList<>(List.copyOf(accounts));
    }

    /**
     * This method verify the existence of an account by e-mail confirmation.
     *
     * @return the account with given e-mail.
     */
    public Account getAccountByEmail(String email) {
        Account requestedAccount = null;
        int i = 0;
        while (i < this.accounts.size() && requestedAccount == null) {
            if (accounts.get(i).checkAccountFromEmail(email)) {
                requestedAccount = accounts.get(i);
            }
            i++;
        }
        return requestedAccount;
    }


    // VALIDATE ACTORS

    /**
     * This method validates if account with given e-mail has the
     * profile required
     *
     * @param email of given account.
     * @return TRUE if it has the profile required and FALSE otherwise.
     */
    public boolean validateProfileRequired(String email, String profileNameRequired) {
        boolean isProfileRequired = false;
        Account account = getAccountByEmail(email);
        if (account.isProfileRequired(profileNameRequired)) {
            isProfileRequired = true;
        }
        return isProfileRequired;
    }


    // VALIDATE INSTANCE IN CONTAINER

    /**
     * This method checks if there is any account in the list that has the
     * e-mail passed by parameter.
     *
     * @param email that one must find out if exists
     * @return TRUE if there is an account with given e-mail and FALSE otherwise
     */
    public boolean doesEmailExist(String email) {
        boolean emailExists = false;
        int i = 0;
        while (i < this.accounts.size()) {
            if (accounts.get(i).getEmail().equals(email)) {
                emailExists = true;
                break;
            }
            i++;
        }
        return emailExists;
    }


    // ADD TO CONTAINER

    /**
     * This method adds new account to the container of accounts after validating
     * if an account with given e-mail doesn't already exist.
     *
     * @param name        of the new account
     * @param email       of the new account
     * @param phoneNumber of the new account
     * @param photo       of the new account
     * @return TRUE if account is added to container and FALSE otherwise.
     */
    public boolean addAccount(String name, String email, long phoneNumber,
                              BufferedImage photo) {
        boolean isAccountAdded = false;
        Account account = new Account(name, email, phoneNumber, photo);
        if (!doesEmailExist(email)) {
            this.accounts.add(account);
            isAccountAdded = true;
        }
        return isAccountAdded;
    }


    // LIST IN CONTAINER

    /**
     * This method lists all accounts that have the profile "User".
     *
     * @return a list of accounts with profile "User".
     */
    public List<Account> listAllUsers() {
        List<Account> users = new ArrayList<>();
        int i = 0;
        while (i < accounts.size()) {
            if (accounts.get(i).isProfileRequired(Profile.USER)) {
                users.add(accounts.get(i));
            }
            i++;
        }
        return users;
    }

    /**
     * This method checks if a given account is present in the accounts list.
     *
     * @param account to check for presence in the list.
     * @return TRUE if the account is present in the list, and FALSE otherwise.
     */
    public boolean doesAccountExist(Account account) {
        return accounts.contains(account);
    }
}
