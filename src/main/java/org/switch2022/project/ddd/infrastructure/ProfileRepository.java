package org.switch2022.project.ddd.infrastructure;

import org.springframework.stereotype.Repository;
import org.switch2022.project.ddd.domain.model.profile.IProfileRepository;
import org.switch2022.project.ddd.domain.model.profile.Profile;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Class ProfileRepository allows to manage profiles records.
 */
@Repository("profile_memory")
public class ProfileRepository implements IProfileRepository {
    /**
     * Attributes
     */

    private final List<Profile> profiles = new ArrayList<>();

    /**
     * This method checks if an instance of ProfileRepository is equal to another object.
     *
     * @param o object to compare with.
     * @return true if the two have the same attribute value, and <code>false</code>
     * otherwise.
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
     * This method is used to generate a unique hash code for an object, based on the
     * object's state.
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


    public boolean save(Profile profile) {
        if (profiles.contains(profile)) {
            throw new AlreadyExistsInRepoException("The profile already exists in the " +
                    "repository.");
        } else {
            profiles.add(profile);
            return true;
        }
    }

    /**
     * This method gets the size of the repository list.
     *
     * @return the integer equivalent to the size of the list of profiles.
     */
    @Override
    public int count() {
        return profiles.size();
    }

    /**
     * This method returns the profile in this repository with the given name.
     * If the profile is not found, it throws a NotFoundInRepoException.
     *
     * @param profileName the name of the profile to be found
     * @return the profile with the given name.
     */
    @Override
    public Profile findByProfileName(Name profileName) {
        Profile profileRequested = null;
        int i = 0;
        while (i < this.profiles.size() && profileRequested == null) {
            if (profiles.get(i).hasName(profileName)) {
                profileRequested = profiles.get(i);
            }
            i++;
        }
        if (profileRequested == null) {
            throw new NotFoundInRepoException("There is no profile with the given name " +
                    "in the repository.");
        }
        return profileRequested;

    }

    /**
     * This method returns the profile in this repository with the given name.
     * If the profile is not found, it throws a NotFoundInRepoException.
     *
     * @param profileName the name of the profile to be found
     * @return the profile with the given name.
     */
    @Override
    public Optional<Profile> findByNameOfProfile(Name profileName) {
        Optional<Profile> profileRequested = Optional.empty();
        int i = 0;
        while (i < this.profiles.size()) {
            if (profiles.get(i).hasName(profileName)) {
                profileRequested = Optional.ofNullable(profiles.get(i));
            }
            i++;
        }
        return profileRequested;
    }

}




