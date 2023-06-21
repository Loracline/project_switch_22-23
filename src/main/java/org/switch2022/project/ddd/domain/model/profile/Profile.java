package org.switch2022.project.ddd.domain.model.profile;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.domain.value_object.ProfileId;
import org.switch2022.project.ddd.utils.Validate;

import java.util.Objects;

/**
 * Class Profile is built to create and manager new profiles.
 * Each profile will have permissions associated with available actions and
 * functionalities.
 * A profile is defined by an id and a name.
 */

public class Profile implements Entity<Profile> {
    /**
     * Attributes
     */
    private final ProfileId profileId;
    private final Name profileName;

    /**
     * Constructor.
     *
     * @param profileName     of the profile.
     * @param idProfileNumber of the profile.
     */
    protected Profile(Name profileName, Integer idProfileNumber) {
        Validate.notNull(profileName, "Profile name can't be null.");
        this.profileName = profileName;
        this.profileId = new ProfileId(idProfileNumber);
    }

    /**
     * This method checks if an instance of a Profile is equal to another object.
     *
     * @param toCompare object to compare with.
     * @return true if the two Profile instances are the same object. Otherwise, checks
     * if the
     * object parameter is null or has a different class than Profile, and return false if
     * either of these conditions are true.
     */
    @Override
    public boolean equals(Object toCompare) {
        if (this == toCompare) {
            return true;
        }
        if (toCompare == null || getClass() != toCompare.getClass()) {
            return false;
        }
        Profile profile = (Profile) toCompare;
        return Objects.equals(profileId, profile.profileId) &&
                Objects.equals(profileName, profile.profileName);
    }

    /**
     * This method is used to generate a unique hash for an object, based on the
     * object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return profileId.hashCode();
    }

    /**
     * This method checks if two instances of Profile are equal by comparing the value
     * of the
     * attribute profile id.
     *
     * @param other Profile instance to compare with.
     * @return TRUE if the two have the same attribute value, and FALSE otherwise.
     */
    @Override
    public boolean sameIdentityAs(Profile other) {
        return other != null && this.profileId.equals(other.profileId);
    }

    /**
     * Getter method for the attribute: name.
     *
     * @return a String with the profile name.
     */

    public String getProfileName() {
        return this.profileName.getName();
    }

    /**
     * Getter method for the attribute: id.
     *
     * @return a String with the code of the project.
     */
    public String getProfileId() {
        return this.profileId.getProfileId();
    }


    /**
     * Checks if the profile has a name that matches the provided profile name.
     *
     * @param profileName the name to check against the account's name
     * @return true if the account has a name that matches the provided profile name,
     * false otherwise.
     **/
    public boolean hasName(Name profileName) {
        return this.profileName.getName().equals(profileName.getName());


    }
}
