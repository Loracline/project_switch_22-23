package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.ProfileService;

/**
 * Class CreateProfileController is built to create profiles.
 */
public class CreateProfileController {
    @Autowired
    private ProfileService profileService;

    /**
     * This method receives a name as a String and creates a new profile with that name.
     *
     * @param name that represents the name of a profile.
     * @return TRUE if the profile is created, and throws an AlreadyExistsInRepoException
     * exception otherwise.
     */
    public boolean createProfile(String name) {
        return profileService.createProfile(name);
    }
}

