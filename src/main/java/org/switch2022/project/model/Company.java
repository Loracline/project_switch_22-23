package org.switch2022.project.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class Repository is built to create and manipulate lists of Accounts and
 * Profiles (to be updated...).
 */
public class Company {
  /**
   * Attributes of the class Repository, according to the Class Diagram.
   */
  private AccountContainer accountList;
  private ProfileContainer profileList;

  /**
   * Repository constructor
   */
  public Company(AccountContainer accountList, ProfileContainer profileList) {
    this.accountList = accountList;
    this.profileList = profileList;
  }

    public Company() {
        this.accountList = new AccountContainer();
        this.profileList = new ProfileContainer();
    }


    /**
     * Getter method for the attribute ACCOUNTS LIST.
     *
     * @return the list of Accounts in the Repository
     */
    public AccountContainer getAccountsList() {
        return accountList;
    }


    /**
     * This method checks if there is any account in the list that has the
     * e-mail passed by parameter.
     *
     * @param email that one must find out if exists in the Repository
     * @return TRUE if there is an account with given e-mail and FALSE otherwise
     */


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

    /**
     * This method creates a Profile
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
    }// se o Profile nao estiver na ProfileList retorna true.

   /* public boolean validateProfilee(String profileName) {
        boolean profileListcontainsProfile = true;
        Profile validateProfile = createProfile(profileName);

        if (!this.profileList.contains(validateProfile)) {
            profileListcontainsProfile = false;
        }
        return profileListcontainsProfile;
    }*/

    /**
     * This method adds profile to profilesList
     *
     * @param toAddProfile
     */
    public boolean addProfileToProfilesList(Profile toAddProfile) {
        boolean isAddedToList = profileList.add(toAddProfile);
        return isAddedToList;
    }

    public void registerProfile(String profileName) {
        String profileNameLowerCase = profileName.toLowerCase();
        if (validateProfile(profileNameLowerCase)) {
            Profile profile = new Profile(profileName);
            addProfileToProfilesList(profile); // FUNDIR METODO addProfileToProfilesList AQUI.
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company that = (Company) o;
        return Objects.equals(profileList, that.profileList);
    }

    /**
     * This method changes/updates the accountProfile
     *
     * @return an object Account
     */
    public void changeAccountProfile(String email, String profileName) {
        Profile profile = getProfileByName(profileName);
        Account account = getAccountByEmail(email);
        account.updateAccountProfile(profile);
    }
}
