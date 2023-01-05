package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProfileContainer {
  private List<Profile> profiles;

  public ProfileContainer(List<Profile> profileList){
    this.profiles = profileList;
  }

  public ProfileContainer(){
    this.profiles = new ArrayList<Profile>();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ProfileContainer)) return false;
    ProfileContainer that = (ProfileContainer) o;
    return Objects.equals(profiles, that.profiles);
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
   * @param profileName
   * @return true if profile exists in profile list
   */
  public boolean doesProfileNameExists(Profile profileName) {
    boolean profileExits = false;
    if (this.profiles.contains(profileName)) {
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
    String profileNameLowerCase = profileName.toLowerCase();
    Profile requestedProfile = null;
    for (int i = 0; i < this.profileList.size(); i++) {
      if (profileList.get(i).getProfileName().equals(profileNameLowerCase)) {
        requestedProfile = profileList.get(i);
        break;
      }
    }
    return requestedProfile;
  }
}
