package org.switch2022.project.ddd.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.ddd.datamodel_jpa.BusinessSectorJpa;
import org.switch2022.project.ddd.domain.model.business_sector.BusinessSector;


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

    Optional<BusinessSectorJpa> findByName(String businessSectorName);

    /**
     * Retrieves the number of business sectors in the repository.
     *
     * @return The count of business sectors.
     */
    long count();

    /**
     * Saves a business sector entity in the repository.
     * @param businessSector The project entity to be saved.
     * @return true if the project is successfully saved.
     */
    boolean save(BusinessSector businessSector);

    /**
     * Retrieves all business sectors in the repository.
     *
     * @return An Iterable containing all business sectors.
     */
    Iterable<BusinessSectorJpa> findAll();

    /** Finds a business sector with the given business sector ID in the
     * repository.
     *
     * @param businessSectorId The business sector ID of the business sector.
     * @return An optional containing the business sector if found, or an
     * optional otherwise.
     */
    Optional<BusinessSectorJpa>findByIdNumber(String businessSectorId);
}


