package org.switch2022.project.ddd.infrastructure;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.profile.IProfileRepository;
import org.switch2022.project.ddd.domain.model.profile.Profile;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class ProfileRepository allows to manage profiles records.
 */
@Component
public class ProfileRepository implements IProfileRepository {
    /**
     * Attributes
     */

    private final List<Profile> profiles = new ArrayList<>();

    /**
     * This method checks if an instance of ProfileRepository is equal to another object.
     *
     * @param o object to compare with.
     * @return </code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;

        }
        ProfileRepository that = (ProfileRepository) o;
        return Objects.equals(profiles, that.profiles);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return Objects.hash(profiles);
    }

    /**
     * This method adds a new profile to the repository of profiles if it does not exist.
     *
     * @param profile to be added to the repository.
     * @return true if the profile is added and a exception otherwise.
     */


    public boolean add(Profile profile) {
        if (profiles.contains(profile)) {
            throw new AlreadyExistsInRepoException("The profile already exists in the repository.");
        } else {
            profiles.add(profile);
            return true;
        }
    }

    /**
     * This method gets the size of the repository list
     *
     * @return the integer equivalent to the size of the list of business sectors.
     */

    @Override
    public int getSize() {
        return profiles.size();
    }
}




