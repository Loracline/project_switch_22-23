package org.switch2022.project.controller;

import org.switch2022.project.container.Company;
import org.switch2022.project.model.Profile;

/**
 * Class CreateProfileController is built to allow access to Company
 */
public class CreateProfileController {
    /**
     * Attributes of the class CreateProfileController, according to the Class Diagram.
     */
    private final Company company;
    /**
     * CreateProfileController constructor
     */
    public CreateProfileController(Company company) {
        this.company = company;
    }
    /**
     * Method createProfile
     *
     * @return true if profile is created
     */
    public boolean createProfile(String profileName, String actorEmail) {
        return (company.validateProfileRequired(actorEmail, Profile.ADMINISTRATOR) &&
                company.createProfile(profileName));
    }
}




