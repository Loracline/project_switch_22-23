package org.switch2022.project.ddd.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.ddd.datamodel_jpa.ProfileJpa;


public interface IProfileJpaRepository extends CrudRepository<ProfileJpa, String> {

    /**
     * This method returns the profile in the repository that has a given profile name.
     *
     * @param profileName the name of the profile to be found
     * @return the profile with the given name, and throw an NotFoundInRepoException otherwise.
     *
     */
    ProfileJpa findByProfileName(String profileName);

}

