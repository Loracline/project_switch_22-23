package org.switch2022.project;

import org.switch2022.project.Account;
import org.switch2022.project.Profile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public Repository(List<Profile> profilesList) {
        this.profileList = profilesList;
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
            if (accountList.get(index).getEmail().equals(email)) {
                emailExistance = true;
                break;
            }
            index++;
        }
        return emailExistance;

        //Possivel substituicao para ciclo while. (A aguardar validaçao do resto dos elementos)
        /*for (int i = 0; i < this.accountList.size(); i++) {
            if (accountList.get(i).getEmail().equals(email)) {
                emailExistance = true;
                break;
        }*/
    }

    /**
     * Register new account method. After validating email is unique, creates new
     * account with given parameters and adds it to accountList
     *
     * @param name        of the new account
     * @param email       of the new account
     * @param phoneNumber of the new account
     * @param photo       of the new account
     * @param status      of the new account
     * @return instance of Account with given parameters if email is unique;
     * @return null if email already exists
     */
    public boolean registerAccount(String name, String email, long phoneNumber,
                             BufferedImage photo, boolean status) {
        boolean result = false;

        if (!doesEmailExist(email)) {
            Account account = new Account(name, email, phoneNumber, photo, status);
            this.addAccount(account);
            result = true;
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



    public Account getAccount(String email) {
        Account requestedAccount = null;
        for (Account account : this.accountList) {
            if (account.getEmail().equals(email)) {
                requestedAccount = account;
                break;
            }
        }
        return requestedAccount;
    }
    //Possivel substituicao do teste Account getAccount (A aguardar validaçao do resto dos elementos)
      /*  Account requestedAccount = null;
        for (int i = 0; i < this.accountList.size() ; i++) {
            if (accountList.get(i).getEmail().equals(email)) {
                requestedAccount = accountList.get(i);
                break;
            }
        }
        return requestedAccount;
    */
    /**
     * This metod creates a Profile
     *
     * @return an object Profile
     */
    public Profile createProfile(String name) {
        Profile newProfile = new Profile(name);
        return newProfile;
    }

    /**
     * This method validates if profile exits
     *
     * @param profileName
     */
    public boolean validateProfile(String profileName) {
        Profile validateProfile = createProfile(profileName);

        if (!this.profileList.contains(validateProfile)) {
            return true;
        }
        return false;
    }

    /**
     * This method adds profile to profilesList
     *
     * @param toAddProfile
     */
    public boolean addProfileToProfilesList(Profile toAddProfile) {
        boolean isAddedToList =  profileList.add(toAddProfile);
        return isAddedToList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Repository)) return false;
        Repository that = (Repository) o;
        return Objects.equals(profileList, that.profileList);
    }
}
