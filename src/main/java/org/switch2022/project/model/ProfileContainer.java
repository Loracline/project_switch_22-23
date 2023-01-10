package org.switch2022.project.model;

import java.util.List;

/**
 * Class ProfileContainer is built to allow access to class Profile.
 */
public class ProfileContainer {
    /**
     * ProfileContainer contains profiles
     */
    private List<Profile> profiles;

    public ProfileContainer(List<Profile> profiles) {
        this.profiles = profiles;
    }

    /**
     * Check if first is lower than second
     *
     * @param first  integer
     * @param second integer
     * @return True if first is lower than second, and False otherwise
     */
    public static boolean isLower(int first, int second) {
        return first < second;
    }

    /**
     * This method creates a Profile
     *
     * @return an object Profile
     */
    public Profile createProfile(String name) {
        return new Profile(name);
    }

    /**
     * This method validates if profile exits
     *
     * @param profile one must check
     * @return true if profile exists in profiles
     */
    public boolean doesProfileNameExist(Profile profile) {
        return this.profiles.contains(profile);
    } //alterar para privado

    /**
     * This method adds profile to profiles
     *
     * @param profile one must add
     */
    public boolean addProfile(Profile profile) {
        boolean isAddedToList = false;
        if (!doesProfileNameExist(profile)) {
            profiles.add(profile);
            isAddedToList = true;
        }
        return isAddedToList;
    }

    /**
     * This method identifies the requested profile by indication of profileName
     *
     * @return an object Profile
     */

    public Profile getProfileByName(String profileName) {
        Profile profile = new Profile(profileName);
        Profile requestedProfile = null;
        int i = 0;
        while (isLower(i, this.profiles.size())) {
            if (this.profiles.contains(profile)) {
                requestedProfile = profiles.get(i);
                break;
            }
            i++;
        }
        return requestedProfile;
    }
}
