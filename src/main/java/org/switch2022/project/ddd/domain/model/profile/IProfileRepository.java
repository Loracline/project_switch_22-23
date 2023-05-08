package org.switch2022.project.ddd.domain.model.profile;

/**
 * Profile Repository Interface.
 */
public interface IProfileRepository {

    /**
     * This method adds a new profile to the repository of profiles if it does not exist.
     *
     * @param profile to be added to the repository.
     * @return true if the profile is added and an exception otherwise.
     */

    boolean add(Profile profile);

}

