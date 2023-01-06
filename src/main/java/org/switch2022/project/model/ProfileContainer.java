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
   * This method creates a Profile
   *
   * @return an object Profile
   */
  public Profile createProfile(String name) {
    Profile newProfile = new Profile(name);
    return newProfile;
  }

  /**
   * This method validates if profile exits
   *
   * @param profile
   * @return true if profile exists in profiles
   */
  public boolean doesProfileNameExist(Profile profile) {
    boolean profileExits = false;
    if (this.profiles.contains(profile)) {
      profileExits = true;
    }
    return profileExits;
  }

  /**
   * This method adds profile to profiles
   *
   * @param profile
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
    for (int i = 0; i < this.profiles.size(); i++) {
      if (this.profiles.contains(profile)) {
        requestedProfile = profiles.get(i);
        break;
      }
    }
    return requestedProfile;
  }
}
