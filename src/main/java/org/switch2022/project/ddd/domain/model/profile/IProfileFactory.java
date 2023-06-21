package org.switch2022.project.ddd.domain.model.profile;

import org.switch2022.project.ddd.domain.value_object.Name;

/**
 * Factory of Profile class.
 */

public interface IProfileFactory {

    /**
     * This method creates a Profile object with no return.
     *
     * @param profileName     is the name.
     * @param idProfileNumber of the Profile to create.
     * @return the created profile.
     */
    Profile createProfile(Name profileName, int idProfileNumber);
}


