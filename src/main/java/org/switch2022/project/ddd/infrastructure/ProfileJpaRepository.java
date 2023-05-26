package org.switch2022.project.ddd.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.switch2022.project.ddd.datamodel_jpa.ProfileJpa;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.ProfileDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.profile.IProfileRepository;
import org.switch2022.project.ddd.domain.model.profile.Profile;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;
import org.switch2022.project.ddd.infrastructure.jpa.IProfileJpaRepository;

import java.util.Optional;


@Repository("profile_jpa")
public class ProfileJpaRepository implements IProfileRepository {
    /**
     * Attributes
     */
    @Autowired
    ProfileDomainDataAssembler assembler;
    @Autowired
    IProfileJpaRepository jpaRepository;

    /**
     * This method adds a new Profile instance to the repository of profiles.
     *
     * @param profile to be added to the repository.
     * @return true if the project resource is added, and throws an exception otherwise.
     */
    public boolean save(Profile profile) {
        ProfileJpa profileJpa = assembler.toData(profile);
        if (jpaRepository.existsById(profileJpa.getProfileId())) {
            throw new AlreadyExistsInRepoException("The profile already exists in the repository.");
        } else {
            jpaRepository.save(profileJpa);
            return true;
        }
    }

    /**
     * This method gets the number of profile entries in the repository.
     *
     * @return the integer equivalent to the number of profile entries in the repository.
     */
    public int count(){
        return (int) jpaRepository.count();
    }

    /**
     * This method returns the profile in the repository that has a given profile name.
     *
     * @param profileName the name of the profile to be found
     * @return the profile with the given name, and throw an NotFoundInRepoException otherwise.
     *
     */
    public Profile findByProfileName(Name profileName){
        Optional<Profile> profile = Optional.ofNullable(jpaRepository.findByProfileName(profileName));

        if(profile.isPresent()){
            return profile.get();
        } else {
            throw new NotFoundInRepoException("There is no profile with the given name in the repository.");
        }
    }


}
