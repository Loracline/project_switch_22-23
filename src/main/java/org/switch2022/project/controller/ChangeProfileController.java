package org.switch2022.project.controller;

import org.switch2022.project.model.Company;

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
    public void changeProfile(String email, String profileName) {
        this.company.changeAccountProfile(email, profileName);
    }
}
