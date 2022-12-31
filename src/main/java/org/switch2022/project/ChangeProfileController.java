package org.switch2022.project;

/**
 * Class ChangeProfileController is built to allow access to change account methods
 * in repository Class.
 */
public class ChangeProfileController{
    private Repository repository;

    /**
     * ChangeProfileController constructor
     */
    public ChangeProfileController(Repository repository) {
        this.repository = repository;
    }
    public Account changeProfile(String email, String profileName) {
        return this.repository.changeAccountProfile(email, profileName);
    }
}
