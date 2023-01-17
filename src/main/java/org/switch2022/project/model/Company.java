package org.switch2022.project.model;

import org.switch2022.project.container.AccountContainer;
import org.switch2022.project.container.ProfileContainer;

import java.util.Objects;

/**
 * Class Company is built to create and manipulate AccountContainer and ProfileContainer.
 */
public class Company {
    /**
     * Attributes of the class Company, according to the Class Diagram.
     */
    private AccountContainer accountContainer;
    private ProfileContainer profileContainer;

    /**
     * Company constructor
     */
    public Company(AccountContainer accountContainer, ProfileContainer profileContainer) {
        this.accountContainer = accountContainer;
        this.profileContainer = profileContainer;
    }


    /**
     * Getter method for the attribute accounts.
     *
     * @return the list of Accounts in the Company
     */
    public AccountContainer getAccountContainer() {
        return accountContainer;
    }

    /**
     * Getter method for the attribute Profile Container.
     *
     * @return the container of profiles
     */
    public ProfileContainer getProfileContainer() {
        return profileContainer;
    }
    /**
     * Method createProfile
     *
     * @return true if profile is created
     * @return false if profile isn't created successfully
     */
    public boolean createProfile (String name){
        return profileContainer.createProfile(name);
    }

    public boolean changeStatus(String email, boolean status){
        return accountContainer.changeStatus(email, status);
    }

    public boolean changeProfile(String email, String profileName) {
        boolean wasAccountProfileUpdated = false;
        Profile profile = profileContainer.getProfileByName(profileName);
        Account account = accountContainer.getAccountByEmail(email);

        if (account != null && profile != null) {
            account.setProfile(profile);
            wasAccountProfileUpdated = true;
        }

        return wasAccountProfileUpdated;
    }
}

