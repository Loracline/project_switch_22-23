package org.switch2022.project.ddd.dto.mapper;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.model.profile.Profile;

/**
 * Mapper class responsible for converting Profile objects to ProfileDto objects.
 */

@Component
public class ProfileMapper {

    /**
     * Converts a Profile object to a ProfileDto object.
     *
     * @param profile The Profile object to be converted.
     * @return A ProfileDto object representing the converted profile.
     */
    public ProfileDto profileToProfileDto(Profile profile) {
        return new ProfileDto(profile.getProfileName());
    }
}
