package org.switch2022.project.ddd.datamodel_jpa.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.switch2022.project.ddd.datamodel_jpa.ProfileJpa;
import org.switch2022.project.ddd.domain.model.profile.IProfileFactory;
import org.switch2022.project.ddd.domain.model.profile.Profile;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.domain.value_object.ProfileId;
import org.switch2022.project.ddd.utils.Utils;

public class ProfileDomainDataAssembler {

    @Autowired
    private IProfileFactory profileFactory;

    /**
     * Converts a Profile domain instance to a ProfileJpa data model instance.
     *
     * @param profile The Profile instance to be converted.
     * @return The converted ProfileJpa instance.
     */
    public ProfileJpa toData(Profile profile) {
        return new ProfileJpa(profile.getProfileId(), profile.getProfileName());
    }

    /**
     * Converts a ProfileJpa data model instance to a Profile domain instance.
     *
     * @param profileJpa The ProfileJpa instance to be converted.
     * @return The converted Profile instance.
     */
    public Profile toDomain(ProfileJpa profileJpa) {
        int profileNumber = Utils.getIntFromAlphanumericString(profileJpa.getProfileId(), "PRF");
        Name profileName = new Name(profileJpa.getProfileName());
        return profileFactory.createProfile(profileName, profileNumber);

    }
}
