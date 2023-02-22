package org.switch2022.project.controller;

import org.switch2022.project.model.container.Company;
import org.switch2022.project.model.Profile;

/**
 * Class ChangeProfileController acts as an intermediary between the user
 * interface (UI) and the business logic underlying the "US003 - As
 * Administrator, I want to change the profile of a user account."
 */
public class ChangeProfileController {
    /**
     * Attributes
     */
    private final Company company;


    /**
     * Constructor
     */
    public ChangeProfileController(Company company) {
        this.company = company;
    }


    /**
     * This method changes the profile associated to a specific e-mail account.
     *
     * @param email       of the account one wish to change.
     * @param profileName of the desired profile.
     * @return TRUE if changed, and FALSE otherwise.
     */
    public boolean changeProfile(String email, String profileName, String actorEmail) {

        return company.validateProfileRequired(actorEmail, Profile.ADMINISTRATOR) &&
                company.changeProfile(email, profileName);
    }
}
