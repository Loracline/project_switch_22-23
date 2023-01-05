package org.switch2022.project.controller;

import org.switch2022.project.model.*;

/**
 * Class ChangeProfileController is built to allow access to change account methods
 * in repository Class.
 */
public class ChangeProfileController{
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
     * @return an object Account
     */
    public void changeProfile(String email, String profileName) {
        ProfileContainer profileContainer = company.getProfileContainer();
        Profile profile = profileContainer.getProfileByName(profileName);

        AccountContainer accountContainer = company.getAccountContainer();
        Account account = accountContainer.getAccountByEmail(email);

        account.setProfile(profile);

    }
}
