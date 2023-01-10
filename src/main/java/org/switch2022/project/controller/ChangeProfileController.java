package org.switch2022.project.controller;

import org.switch2022.project.model.*;

/**
 * Class ChangeProfileController is built to allow access to ProfileContainer and AccountContainer
 * in Company Class.
 */
public class ChangeProfileController {
  /**
   * Attributes of the class ChangeProfileController, according to the Class Diagram.
   */
  private Company company;

  /**
   * ChangeProfileController constructor
   */
  public ChangeProfileController(Company company) {
    this.company = company;
  }
  /**
   * This method updates the account Profile
   *
   * @param email
   * @param profileName
   *
   * @return true if account Profile is updated successfully
   * @return false if account Profile isn't updated
   */
  public boolean changeProfile(String email, String profileName) {
    boolean wasAccountProfileUpdated = false;
    ProfileContainer profileContainer = company.getProfileContainer();
    Profile profile = profileContainer.getProfileByName(profileName);

    AccountContainer accountContainer = company.getAccountContainer();
    Account account = accountContainer.getAccountByEmail(email);

    if (account != null && profile != null) {
      account.setProfile(profile);
      wasAccountProfileUpdated = true;
    }
    return wasAccountProfileUpdated;
  }
}
