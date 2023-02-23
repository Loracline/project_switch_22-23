package org.switch2022.project.model;

import java.util.Objects;

/**
 * The class Profile was built to create and manager new profiles.
 * A profile is defined by a name.
 */
public class Profile {
    public static final String ADMINISTRATOR = "administrator";
    public static final String MANAGER = "manager";
    public static final String USER = "user";

    /**
     * Attributes
     */
    private final String profileName;

    /**
     * Constructor
     */
    public Profile(String profileName) {
        this.profileName = profileName.toLowerCase().trim();
    }

    /**
     * This method checks if two instances of class Profile are equal by
     * comparing its names.
     *
     * @param toCompare Profile instance to compare with.
     * @return TRUE if the two have the same profile name, and FALSE otherwise.
     */
    @Override
    public boolean equals(Object toCompare) {
        if (this == toCompare) {
            return true;
        }
        if (toCompare == null) {
            return false;
        }
        if (toCompare.getClass() != this.getClass()) {
            return false;
        }
        Profile reference = (Profile) toCompare;
        return Objects.equals(profileName, reference.profileName.toLowerCase().trim());
    }

    /**
     * If two objects are equal according to the equals(Object) method, then
     * calling the hashCode method on each of the two objects must produce the
     * same integer result.
     *
     * @return the hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(profileName);
    }

    /**
     * This method verifies if the profile is the one required by checking its
     * profile name.
     *
     * @return TRUE if it is the profile required or FALSE otherwise.
     */
    public boolean isProfileRequired(String profileNameRequired) {
        return this.profileName.equalsIgnoreCase(profileNameRequired);
    }
}

