package org.switch2022.project.ddd.domain.model.profile;
import org.switch2022.project.ddd.domain.value_object.Name;

/**
 * Factory of Profile class.
 */

public interface IProfileFactory {

        /**
         * This method creates a Profile object with no return.
         *
         * @param idProfileNumber of the Profile to create.
         * @param profileName       is the name.
         */

     Profile createProfile(Name profileName, int idProfileNumber);
    }


