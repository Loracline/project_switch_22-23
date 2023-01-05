package org.switch2022.project.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
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
