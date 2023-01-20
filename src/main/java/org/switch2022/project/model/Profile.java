package org.switch2022.project.model;

import java.util.Objects;

/**
 * Class Profile is built to create and manage new profiles.
 * A profile is defined by a name.
 */

public class Profile {
    private String profileName;


    /**
     * Constructs a default User profile
     */
    public Profile(String profileName) {
        this.profileName = profileName.toLowerCase();
    }

    public boolean isManager() {
        Profile profile = new Profile("Manager");
        boolean isManager = false;
        if(this.profileName.equals(profile.profileName)) {
            isManager = true;
        }
        return isManager;
    }
    public boolean isAdministrator() {
        Profile profile = new Profile("Administrator");
        boolean isAdministrator = false;
        if(this.profileName.equals(profile.profileName)) {
            isAdministrator = true;
        }
        return isAdministrator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile)) return false;
        Profile profile1 = (Profile) o;
        return Objects.equals(profileName, profile1.profileName.toLowerCase());
    }

    public boolean isUser() {
        Profile profile = new Profile("User");
        boolean isUser = false;
        if (this.profileName.equals(profile.profileName)) {
            isUser = true;
        }
        return isUser;
    }




}

