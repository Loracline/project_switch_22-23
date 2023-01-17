package org.switch2022.project.controller;

import org.switch2022.project.model.Company;

/**
 * Class CreateProfileController is built to allow access to ProfileContainer
 * in Company Class.
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
     * Method createProfile
     *
     * @return true if profile is created
     * @return false if profile isn't created successfully
     */
    public boolean createProfile(String profileName) {
        return (company.createProfile(profileName));
    }
}




