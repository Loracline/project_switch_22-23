package org.switch2022.project.ddd.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.ddd.datamodel_jpa.BusinessSectorJpa;
import org.switch2022.project.ddd.datamodel_jpa.CustomerJpa;
import org.switch2022.project.ddd.domain.model.business_sector.BusinessSector;
import org.switch2022.project.ddd.domain.value_object.Name;

import java.util.Optional;

/**
 * JPA repository interface for managing BusinessSectorJpa entities.
 * Provides basic CRUD operations and custom queries for business sector data.
 */
public interface IBusinessSectorJpaRepository extends CrudRepository<BusinessSectorJpa, String> {
    /**
     * This method returns the business sector in the repository that has a given business sector name.
     *
     * @param businessSectorName the name of the business sector to be found.
     * @return the business sector with the given name, and throw an NotFoundInRepoException otherwise.
     */

    Optional<BusinessSectorJpa> findByBusinessSectorName(String businessSectorName);
}

