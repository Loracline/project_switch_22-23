package org.switch2022.project;

import java.util.Objects;

public class Profile {
    private String profileName;

    Profile(String profileName) {
        this.profileName = profileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile)) return false;
        Profile profile = (Profile) o;
        return Objects.equals(profileName, profile.profileName);
    }
}
