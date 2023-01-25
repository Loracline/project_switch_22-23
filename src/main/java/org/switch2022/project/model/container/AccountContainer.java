package org.switch2022.project.model.container;

import org.switch2022.project.model.Account;
import org.switch2022.project.utils.Util;

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
    private final List<Account> accounts;



    /**
     * Constructor
     */
    public AccountContainer(List<Account> accounts) {
        this.accounts = accounts;
    }



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
        while (i < this.accounts.size()) {
            if (accounts.get(i).getEmail().equalsIgnoreCase(email)) {
                requestedAccount = accounts.get(i);
                break;
            }
            i++;
        }
        return requestedAccount;
    }



    // VALIDATE ACTORS

    /**
     * This method validates if account with given e-mail has the "Manager"
     * profile.
     *
     * @param email of given account.
     * @return TRUE if "Manager" and FALSE otherwise.
     */
    public boolean validateManager(String email) {
        boolean isManager = false;
        int i = 0;
        while (i < this.accounts.size()) {
            if (accounts.get(i).checkAccountFromEmail(email)) {
                Account account = accounts.get(i);
                if (account.isManager()) {
                    isManager = true;
                }
            }
            i++;
        }
        return isManager;
    }

    /**
     * This method validates if account with given e-mail has the "Administrator"
     * profile.
     *
     * @param email of given account.
     * @return TRUE if "Administrator" and FALSE otherwise.
     */
    public boolean validateAdministrator(String email) {
        boolean isAdministrator = false;
        int i = 0;
        while (i < this.accounts.size()) {
            if (accounts.get(i).checkAccountFromEmail(email)) {
                Account account = accounts.get(i);
                if (account.isAdministrator()) {
                    isAdministrator = true;
                }
            }
            i++;
        }
        return isAdministrator;
    }

    /**
     * This method validates if account with given e-mail has the "User" profile.
     *
     * @param email of given account.
     * @return TRUE if "User" and FALSE otherwise.
     */
    public boolean validateUser(String email) {
        boolean isUser = false;
        int i = 0;
        while (i < this.accounts.size()) {
            if (accounts.get(i).checkAccountFromEmail(email)) {
                Account account = accounts.get(i);
                if (account.isUser()) {
                    isUser = true;
                }
            }
            i++;
        }
        return isUser;
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



    // CHANGE INSTANCE IN CONTAINER

    /**
     * This method changes the status of account stored in the container.
     *
     * @param email  of given account.
     * @param status one intend to change to.
     * @return TRUE if changed, and FALSE otherwise.
     */
    public boolean changeStatus(String email, boolean status) {
        boolean isChanged = false;
        if (doesEmailExist(email)) {
            Account account = getAccountByEmail(email);
            Account copyAccount = new Account(account);
            copyAccount.setStatus(status);
            if (account.getAccountStatus() != copyAccount.getAccountStatus()) {
                isChanged = true;
            }
        }
        return isChanged;
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
        while (Util.isLower(i, accounts.size())) {
            if (accounts.get(i).isUser()) {
                users.add(accounts.get(i));
            }
            i++;
        }
        return users;
    }
}
