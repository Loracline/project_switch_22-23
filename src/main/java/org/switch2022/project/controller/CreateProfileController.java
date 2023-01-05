package org.switch2022.project.controller;

import org.switch2022.project.model.Profile;
import org.switch2022.project.model.Company;
import org.switch2022.project.model.ProfileContainer;

/**
 * Class CreateProfileController is built to allow access to register profile methods
 * in repository Class.
 */

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
    public ProfileContainer getProfileContainer() {
        ProfileContainer pc =company.getProfileContainer();
        return pc;
    }
    /**
     *
     *
     * @param profileName checks whether the profile already exists or not,
     *                    creates a new profile and adds it to the list of profiles.
     * @return true if profile is created
     * @return false if profile isn't created successfully
     */
    public boolean addProfile(String profileName) {
        ProfileContainer pc = getProfileContainer();
        boolean isAddedToList = false;
        Profile profile = pc.createProfile(profileName);
        if (pc.addProfile(profile)){
            isAddedToList=true;
        }
        return isAddedToList;
    }
}




