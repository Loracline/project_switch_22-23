package org.switch2022.project.factories;

import org.switch2022.project.model.Profile;

/**
 * Interface for a Profile factory.
 */
public interface IFactoryProfile {

    /**
     * This method creates a new Profile object with the specified attributes with no
     * return.
     *
     * @param profileName the name of the Profile
     */
    Profile createProfile(String profileName);
}
