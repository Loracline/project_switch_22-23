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
   * This method changes/updates the accountProfile
   *
   * @param email
   * @param profileName
   */
  public void changeProfile(String email, String profileName) {
    ProfileContainer profileContainer = company.getProfileContainer();
    Profile profile = profileContainer.getProfileByName(profileName);

    AccountContainer accountContainer = company.getAccountContainer();
    Account account = accountContainer.getAccountByEmail(email);

    if (account != null && profile != null) {
      account.setProfile(profile);
    }
  }
}
