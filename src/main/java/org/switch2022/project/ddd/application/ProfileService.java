package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.profile.IProfileFactory;
import org.switch2022.project.ddd.domain.model.profile.IProfileRepository;
import org.switch2022.project.ddd.domain.model.profile.Profile;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.dto.ProfileCreationDto;
import org.switch2022.project.ddd.dto.mapper.ProfileDto;
import org.switch2022.project.ddd.dto.mapper.ProfileMapper;

import java.util.Optional;

/**
 * Class ProfileService allows to create and manipulate the Profile aggregate.
 */
@Service
public class ProfileService {
    @Autowired
    private IProfileFactory profileFactory;
    @Qualifier("profile_jpa")
    @Autowired
    private IProfileRepository profileRepository;
    @Autowired
    private ProfileMapper mapper;

    /**
     * This method receives a name, creates a profile and adds it to the repository.
     *
     * @param profileCreationDto the ProfileCreationDto containing the information needed to create a new instance of
     *                          Profile.
     * @return TRUE if the Profile is created and added to the profile repository
     * successfully, and throws an
     * AlreadyExistsInRepoException otherwise.
     */

    public boolean createProfile(ProfileCreationDto profileCreationDto) {
        Name name = new Name(profileCreationDto.profileName);
        int idProfileNumber = calculateNextProfileNumber();
        Profile profile = profileFactory.createProfile(name,idProfileNumber);
        return profileRepository.save(profile);
    }

    /**
     * This method calculates the next profile number
     *
     * @return the number of profiles already contained in the list (equivalent to the
     * size of the list) plus one, which logically equals the next number.
     */
    private int calculateNextProfileNumber() {
        return profileRepository.count() + 1;
    }

    public Optional<ProfileDto> getProfile(String profileName){
        Name nameOfProfile = new Name(profileName);
        Optional<Profile> optionalProfile = (profileRepository.findByNameOfProfile(nameOfProfile));
        Optional<ProfileDto> optionalProfileDto = Optional.empty();
        if(optionalProfile.isPresent()) {
            optionalProfileDto = Optional.of(mapper.profileToProfileDto(optionalProfile.get()));
        }
        return optionalProfileDto;
    }
}
