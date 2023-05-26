package org.switch2022.project.ddd.datamodel_jpa;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "profiles")

public class ProfileJpa {
    /**
     * Attributes
     */
    @Id
    @Getter
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

    protected ProfileJpa() {
    }

}
