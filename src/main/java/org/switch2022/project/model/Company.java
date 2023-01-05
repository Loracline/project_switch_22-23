package org.switch2022.project.model;

import java.awt.image.BufferedImage;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company that = (Company) o;
        return Objects.equals(profileContainer, that.profileContainer) && Objects.equals(accountContainer, that.accountContainer);
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
