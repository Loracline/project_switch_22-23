package org.switch2022.project;

import org.switch2022.project.Account;
import org.switch2022.project.Profile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Repository is built to create and manipulate lists of Accounts and
 * Profiles (to be updated...).
 */
public class Repository {
    /**
     * Attributes of the class Repository, according to the Class Diagram.
     */
    private List<Account> accountList;
    private List<Profile> profileList;

    /**
     * Repository constructor
     */
    public Repository(List<Account> accountList, List<Profile> profileList) {
        this.accountList = accountList;
        this.profileList = profileList;
    }

    public Repository() {
        this.accountList = new ArrayList<Account>();
        this.profileList = new ArrayList<Profile>();
    }

    /**
     * Getter method for the attribute ACCOUNTS LIST.
     *
     * @return the list of Accounts in the Repository
     */
    public List<Account> getAccountsList() {
        return accountList;
    }


    /**
     * This method checks if there is any account in the list that has the
     * e-mail passed by parameter.
     *
     * @param email that one must find out if exists in the Repository
     * @return TRUE if there is an account with given e-mail and FALSE otherwise
     */
    public boolean doesEmailExist(String email) {
        boolean emailExistance = false;
        int index = 0;
        while (index < this.accountList.size()) {
            if (accountList.get(index).getEmail().contains(email)) {
                emailExistance = true;
                break;
            }
            index++;
        }
        return emailExistance;
    }

    /**
     * Register new account method. After validating email is unique, creates new
     * account with given parameters and adds it to accountList
     *
     * @param name        of the new account
     * @param email       of the new account
     * @param phoneNumber of the new account
     * @param photo       of the new account
     * @return instance of Account with given parameters if email is unique;
     * @return null if email already exists
     */
    public Account registerAccount(String name, String email, long phoneNumber, BufferedImage photo) {
        Account result = null;

        if (!doesEmailExist(email)) {
            result = new Account(name, email, phoneNumber, photo);
            this.addAccount(result);
        }

        return result;
    }

    /**
     * Add account method to save registered accounts in the attribute ACCOUNTS LIST
     *
     * @param acc instance of Account to be added to accountList
     */
    private void addAccount(Account acc) {
        this.accountList.add(acc);
    }

}
