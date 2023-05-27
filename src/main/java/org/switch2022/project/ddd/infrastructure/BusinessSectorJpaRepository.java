package org.switch2022.project.ddd.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.switch2022.project.ddd.datamodel_jpa.BusinessSectorJpa;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.BusinessSectorDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.business_sector.BusinessSector;
import org.switch2022.project.ddd.domain.model.business_sector.IBusinessSectorRepository;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;
import org.switch2022.project.ddd.infrastructure.jpa.IBusinessSectorJpaRepository;

import java.util.Optional;


/**
 * Implementation of the IBusinessSectorRepository interface that uses JPA to persist and retrieve business sector data.
 * This class interacts with the IBusinessSectorJpaRepository for performing CRUD operations on
 * BusinessSectorJpa entities.
 */

@Repository("businessSector_jpa")
public class BusinessSectorJpaRepository implements IBusinessSectorRepository {

    /**
     * Attributes
     */
    @SuppressWarnings("all")
    @Autowired
    IBusinessSectorJpaRepository crudRepository;
    @SuppressWarnings("all")
    @Autowired
    BusinessSectorDomainDataAssembler assembler;


    /**
     * Adds a new BusinessSector instance to the repository of business sectors.
     *
     * @param businessSector The BusinessSector instance to be added to the repository.
     * @return true if the business sector is successfully added to the repository.
     * @throws AlreadyExistsInRepoException if a business sector with the same ID already
     *                                      exists in the repository.
     */

    @Override
    public boolean save(BusinessSector businessSector) {
        BusinessSectorJpa businessSectorJpa = assembler.toData(businessSector);
        if (crudRepository.existsById(businessSectorJpa.getIdNumber())) {
            throw new AlreadyExistsInRepoException("The business sector already exists in the repository.");
        } else {
            crudRepository.save(businessSectorJpa);
            return true;
        }
    }

    /**
     * Retrieves the size of the repository list.
     * @return The number of elements in the repository.
     */
    @Override
    public int count() {
        return (int) crudRepository.count();
    }

    /**
     * Retrieves the ID of a business sector by its name from the repository.
     * @param businessSectorName The name of the business sector.
     * @return The ID of the business sector with the given name.
     * @throws NotFoundInRepoException If a business sector with the given name is not found in the repository.
     */
    @Override
    public String getBusinessSectorIdByName(String businessSectorName) {
        Optional<BusinessSectorJpa> businessSector = crudRepository.findByName(businessSectorName);

        if (businessSector.isPresent()) {
            return businessSector.get().getName();
        } else {
            throw new NotFoundInRepoException("There is no business sector with the given name in the repository.");
        }
    }

}





