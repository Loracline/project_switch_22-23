package org.switch2022.project.factories;

import org.switch2022.project.model.Profile;


/**
 * Implementation of the FactoryProfile interface that creates instances of the
 * Profile class.
 */

public class FactoryProfile implements IFactoryProfile {
    /**
     * This method creates a new Profile object.
     *
     * @param profileName the name of the Profile
     * @return a new Profile object with the specified name.
     */

    public Profile createProfile(String profileName) {
        return new Profile(profileName);
    }
}
