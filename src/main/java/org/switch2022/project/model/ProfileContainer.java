package org.switch2022.project.model;

import java.util.List;
import java.util.Objects;

public class ProfileContainer {
  private List<Profile> profileList;

  public ProfileContainer(List<Profile> profileList){
    this.profileList = profileList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ProfileContainer)) return false;
    ProfileContainer that = (ProfileContainer) o;
    return Objects.equals(profileList, that.profileList);
  }
}
