package org.switch2022.project;

/**
 * Class CreateProfileController is built to allow access to register profile methods
 * in repository Class.
 */

public class CreateProfileController {

    /**
     * Attributes of the class CreateProfileController, according to the Class Diagram.
     */

    private Repository repository;

    /**
     * CreateProfileController constructor
     */

    public CreateProfileController(Repository repository) {
        this.repository = repository;
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
        boolean isValidProfile = repository.validateProfile(profileName);
        if (isValidProfile) {
            Profile profile = repository.createProfile(profileName);
            isAddedToList = repository.addProfileToProfilesList(profile);
        }
        return isAddedToList;
    }
}




