package org.switch2022.project.controller;

import org.switch2022.project.model.Profile;
import org.switch2022.project.model.Company;

/**
 * Class CreateProfileController is built to allow access to register profile methods
 * in repository Class.
 */

public class CreateProfileController {

    /**
     * Attributes of the class CreateProfileController, according to the Class Diagram.
     */

    private Company company;

    /**
     * CreateProfileController constructor
     */

    public CreateProfileController(Company company) {
        this.company = company;
    }

    /**
     * Validate and create a new profile method.
     *
     * @param profileName
     *
     * checks whether the profile already exists or not,
     * creates a new profile and adds it to the list of profiles.
     */

    public boolean addNewProfile(String profileName) {
        boolean isAddedToList = false;
        boolean isValidProfile = company.validateProfile(profileName);
        if (isValidProfile) {
            Profile profile = company.createProfile(profileName);
            isAddedToList = company.addProfileToProfilesList(profile);
        }
        return isAddedToList;
    }
}




