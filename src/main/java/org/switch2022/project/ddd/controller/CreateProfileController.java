package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.ProfileService;
import org.switch2022.project.ddd.dto.ProfileCreationDto;

/**
 * Class CreateProfileController is built to create profiles.
 */
@Controller
public class CreateProfileController {
    @Autowired
    private ProfileService profileService;

    /**
     * This method receives a name as a String and creates a new profile with that name.
     *
     * @param profileCreationDto the ProfileCreationDto containing the information needed to create a new instance of
     *                          Profile.
     * @return TRUE if the profile is created, and throws an AlreadyExistsInRepoException
     * exception otherwise.
     */
    public boolean createProfile(ProfileCreationDto profileCreationDto) {
        return profileService.createProfile(profileCreationDto);
    }
}

