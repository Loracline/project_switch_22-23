package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.switch2022.project.ddd.application.ChangeProfileService;
import org.switch2022.project.ddd.utils.Validate;

/**
 * Class ChangeProfileController acts as an intermediary between the user
 * interface (UI) and is built to change the Profile of an Account.
 */
public class ChangeProfileController {
    @Autowired
    private ChangeProfileService changeProfileService;

    /**
     * Changes the profile of the account with the given email.
     *
     * @param email       the email of the account to change status
     * @param profileName the new profile name of the account
     * @return true if the profile was successfully changed or false otherwise.
     */
    public boolean changeProfile(String email, String profileName) {
        Validate.notNullOrEmptyOrBlank(email, "email");
        Validate.notNullOrEmptyOrBlank(profileName, "profileName");
        return changeProfileService.changeProfile(email, profileName);
    }
}
