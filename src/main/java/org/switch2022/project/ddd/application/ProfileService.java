package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.switch2022.project.ddd.domain.model.profile.IProfileFactory;
import org.switch2022.project.ddd.domain.model.profile.IProfileRepository;
import org.switch2022.project.ddd.domain.model.profile.Profile;
import org.switch2022.project.ddd.domain.value_object.Name;

/**
 * Class ProfileService allows to create and manipulate the Profile aggregate.
 */
public class ProfileService {
    @Autowired
    private IProfileFactory profileFactory;
    @Autowired
    private IProfileRepository profileRepository;

    /**
     * This method receives a name, creates a profile and adds it to the repository.
     *
     * @param name
     * @return TRUE if the Profile is created and added to the profile repository
     * successfully, and throws an
     * AlreadyExistsInRepoException otherwise.
     */

    public boolean createProfile(String name) {
        Name profileName = new Name(name);
        int idProfileNumber = calculateNextProfileNumber();
        Profile profile = profileFactory.createProfile(profileName,idProfileNumber);
        return profileRepository.add(profile);
    }

    /**
     * This method calculates the next profile number
     *
     * @return the number of profiles already contained in the list (equivalent to the
     * size of the list) plus one, which logically equals the next number.
     */
    private int calculateNextProfileNumber() {
        return profileRepository.getSize() + 1;
    }
}