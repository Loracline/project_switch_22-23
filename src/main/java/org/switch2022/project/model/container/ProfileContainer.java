package org.switch2022.project.model.container;

import org.switch2022.project.model.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Class ProfileContainer is built to access and manipulate the set of profiles
 * of this company.
 */
public class ProfileContainer {
    /**
     * Attributes
     */
    private final List<Profile> profiles = new ArrayList<>();

    /**
     * This method checks if profile already exists in the container.
     *
     * @param profile one must check.
     * @return TRUE if profile exists and FALSE otherwise.
     */
    private boolean doesProfileExist(Profile profile) {
        return this.profiles.contains(profile);
    }

    /**
     * This method creates a new profile and add it to the container if it
     * doesn't already exist.
     *
     * @param name of the profile to create.
     * @return TRUE if profile added and FALSE otherwise.
     */
    public boolean createProfile(String name) {
        Profile newProfile = new Profile(name);
        boolean isAddedToList = false;
        if (!doesProfileExist(newProfile)) {
            profiles.add(newProfile);
            isAddedToList = true;
        }
        return isAddedToList;
    }

    /**
     * This method searches for the profile with given name.
     *
     * @param profileName of the intended profile.
     * @return profile with given name.
     */
    public Profile getProfileByName(String profileName) {
        Profile requestedProfile = null;
        int i = 0;
        while (i < this.profiles.size() && requestedProfile == null) {
            if (this.profiles.get(i).checkProfileByProfileName(profileName)) {
                requestedProfile = profiles.get(i);
            }
            i++;
        }
        return requestedProfile;
    }
}
