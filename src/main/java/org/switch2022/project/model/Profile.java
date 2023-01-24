package org.switch2022.project.model;

import java.util.Objects;

/**
 * Class Profile is built to create and manage new profiles.
 * A profile is defined by name.
 */
public class Profile {
    /**
     * Attributes
     */
    private final String profileName;

    public String getProfileName() {
        return profileName;
    }

    /**
     * Constructor
     */
    public Profile(String profileName) {
        this.profileName = profileName.toLowerCase();
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileName);
    }

    /**
     * This method checks if two instances of Profile are equal by comparing its
     * names.
     *
     * @param toCompare Profile instance to compare with.
     * @return TRUE if the two have the same profile name, and FALSE otherwise.
     */

    @Override
    public boolean equals(Object toCompare) {
        if (this == toCompare) {
            return true;
        }
        if (!(toCompare instanceof Profile)) {
            return false;
        }
        Profile profile1 = (Profile) toCompare;
        return Objects.equals(profileName, profile1.profileName.toLowerCase());
    }

    /**
     * This method verifies if profile is "Manager" by checking its profile name.
     *
     * @return TRUE if "Manager" and FALSE otherwise.
     */
    public boolean isManager() {
        Profile profile = new Profile("Manager");
        return this.profileName.equals(profile.profileName);
    }

    /**
     * This method verifies if profile is "Administrator" by checking its profile
     * name.
     *
     * @return TRUE if "Administrator" and FALSE otherwise.
     */
    public boolean isAdministrator() {
        Profile profile = new Profile("Administrator");
        return this.profileName.equals(profile.profileName);
    }

    /**
     * This method verifies if profile is "User" by checking its profile name.
     *
     * @return TRUE if "User" amd FALSE otherwise.
     */
    public boolean isUser() {
        Profile profile = new Profile("User");
        return this.profileName.equals(profile.profileName);
    }
}

