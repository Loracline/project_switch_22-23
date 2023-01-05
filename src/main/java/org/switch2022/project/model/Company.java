package org.switch2022.project.model;

import java.util.Objects;

/**
 * Class Company is built to create and manipulate Containers.
 */
public class Company {
  /**
   * Attributes of the class Company, according to the Class Diagram.
   */
  private AccountContainer accountContainer;
  private ProfileContainer profileContainer;

  /**
   * Company constructor
   */
  public Company(AccountContainer accountContainer, ProfileContainer profileContainer) {
    this.accountContainer = accountContainer;
    this.profileContainer = profileContainer;
  }

  public Company() {
    this.accountContainer = new AccountContainer();
    this.profileContainer = new ProfileContainer();
  }


  /**
   * Getter method for the attribute accounts.
   *
   * @return the list of Accounts in the Company
   */
  public AccountContainer getAccountContainer() {
    return accountContainer;
  }

    /**
     * Getter method for the attribute Profile Container.
     *
     * @return the container of profiles
     */
    public ProfileContainer getProfileContainer() {
        return profileContainer;
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
     * This method identifies the requested profile by indication of profileName
     *
     * @return an object Profile
     */
    public Profile getProfile(String profileName) {
        String profileNameLowerCase = profileName.toLowerCase();
        Profile requestedProfile = null;
        for (int i = 0; i < this.profileList.size(); i++) {
            if (profileList.get(i).getProfileName().equals(profileNameLowerCase)) {
                requestedProfile = profileList.get(i);
                break;
            }
        }
        return requestedProfile;
    }

    /**
     * This method changes/updates the accountProfile
     *
     * @return an object Account
     */
    public void changeAccountProfile(String email, String profileName) {
        Profile profile = getProfile(profileName);
        Account account = getAccount(email);
        account.updateAccountProfile(profile);
    }
}
