package org.switch2022.project;

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
    public Profile(String name) {
        this.profileName = name.toLowerCase();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile)) return false;
        Profile profile1 = (Profile) o;
        return Objects.equals(profileName, profile1.profileName.toLowerCase());
    }

    /**
     * Getter method for the attribute profileName.
     *
     * @return the profileName of the Profile
     */
    public String getProfileName() {
        return profileName;
    }
}

