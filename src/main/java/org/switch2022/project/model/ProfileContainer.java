package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProfileContainer {
  private List<Profile> profiles;

  public ProfileContainer(List<Profile> profiles){
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
   * @return true if profile exists in profile list
   */
  public boolean doesProfileNameExists(Profile profile) {
    boolean profileExits = false;
    if (this.profiles.contains(profile)) {
      profileExits = true;
    }
    return profileExits;
  }
  /**
   * This method adds profile to profilesList
   *
   * @param toAddProfile
   */
  public boolean addProfile(Profile toAddProfile) {
    boolean isAddedToList = false;
    if (!doesProfileNameExists(toAddProfile)){
      profiles.add(toAddProfile);
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
