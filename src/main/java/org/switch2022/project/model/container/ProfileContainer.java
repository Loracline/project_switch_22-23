package org.switch2022.project.model.container;

import org.switch2022.project.model.Profile;

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
     * This method validates if profile exits
     *
     * @param profile one must check
     * @return true if profile exists in profiles
     */
    private boolean doesProfileNameExist(Profile profile) {
        return this.profiles.contains(profile);
    }

    /**
     * This method creates profile  and adds it to profiles
     *
     * @param name one must add
     */
    public boolean createProfile(String name) {
        Profile newProfile =  new Profile(name);
        boolean isAddedToList = false;
        if (!doesProfileNameExist(newProfile)) {
            profiles.add(newProfile);
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
