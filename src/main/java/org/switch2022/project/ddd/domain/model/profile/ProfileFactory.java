package org.switch2022.project.ddd.domain.model.profile;
import org.switch2022.project.ddd.domain.value_object.Name;

/**
 * Class ProfileFactory implements IProfileFactory in order to create
 * a Profile object.
 */

public class ProfileFactory implements IProfileFactory {
    /**
     * This method creates a new Profile object.
     *
     * @param profileName the name of the profile to create.
     * @param   idProfileNumber is an attribute of Profile.
     * @return the newly created Profile object.
     */


    public Profile createProfile (Name profileName, int idProfileNumber) {
        return new Profile(profileName, idProfileNumber);
    }
}
