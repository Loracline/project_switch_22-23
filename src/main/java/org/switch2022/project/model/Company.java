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
}
