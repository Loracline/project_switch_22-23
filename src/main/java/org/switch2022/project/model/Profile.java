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

    public static final String ADMINISTRATOR = "administrator";
    public static final String MANAGER = "manager";
    public static final String USER = "user";

    /**
     * Constructor
     */
    public Profile(String profileName) {
        this.profileName = profileName.toLowerCase().trim();
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
        if (toCompare == null){
            return false;
        }
        if (toCompare.getClass() != this.getClass()) {
            return false;
        }
        Profile profile1 = (Profile) toCompare;
        return Objects.equals(profileName, profile1.profileName.toLowerCase().trim());
    }

    /**
     * This method verifies if profile is "Manager" by checking its profile name.
     *
     * @return TRUE if "Manager" and FALSE otherwise.
     */

    public boolean isProfileRequired(String profileNameRequired) {
        return this.profileName.equals(profileNameRequired);
    }

    /**
     * This method verifies if profile is the one intended through its profileName.
     *
     * @param profileName of the seeked account
     * @return TRUE if profile has given profileName, and FALSE otherwise.
     */
    public boolean checkProfileByProfileName(String profileName) {
        return this.profileName.equalsIgnoreCase(profileName);
    }
}

