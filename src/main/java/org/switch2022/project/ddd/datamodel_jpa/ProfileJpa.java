package org.switch2022.project.ddd.datamodel_jpa;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Table(name = "profiles")
public class ProfileJpa {
    /**
     * Attributes
     */
    @Id
    private String profileId;
    private String profileName;

    /**
     * Constructs a new instance of ProfileJpa with the provided parameters.
     *
     * @param profileId            The identifier of the profile.
     * @param profileName          The name of the profile.
     */

    public ProfileJpa(String profileId, String profileName) {
        this.profileId = profileId;
        this.profileName = profileName;
    }

    /**
     * This method checks if two instances of ProfileJpa are equal by
     * comparing its ids.
     *
     * @param o object to compare with.
     * @return TRUE if the two have the same id, and FALSE otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof ProfileJpa)) {
            return false;
        }
        ProfileJpa that = (ProfileJpa) o;
        return profileId.equals(that.profileId);
    }

    /**
     * The hashCode() method is used to generate a unique hash code for an
     * object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(profileId, profileName);
    }

    /**
     * Retrieves the profile ID.
     * @return The profile ID.
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * Retrieves the profile name.
     * @return The profile name.
     */
    public String getProfileName() {
        return profileName;
    }
}
