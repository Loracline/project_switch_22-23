package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProfileContainer {
  private List<Profile> profileList;

  public ProfileContainer(List<Profile> profileList) {
    this.profileList = profileList;
  }

  public ProfileContainer(){
    this.profileList = new ArrayList<Profile>();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ProfileContainer)) return false;
    ProfileContainer that = (ProfileContainer) o;
    return Objects.equals(profileList, that.profileList);
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
