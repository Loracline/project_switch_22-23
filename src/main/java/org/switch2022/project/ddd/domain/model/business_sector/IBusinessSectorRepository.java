package org.switch2022.project.ddd.domain.model.business_sector;

import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

/**
 * Interface for a repository of business sectors.
 */
public interface IBusinessSectorRepository {
    /**
     * This method adds a new business sector to the repository of business sectors.
     *
     * @param businessSector to be added to the repository.
     * @return true if the business sector is added, and throws an AlreadyExistInRepoException otherwise.
     */
    boolean save(BusinessSector businessSector);

    /**
     * This method gets the size of the repository list.
     *
     * @return the integer equivalent to the size of the list of business sectors.
     */
    int count();

    /**
     * Retrieves the ID of a customer with the given name from the repository.
     *
     * @param businessSectorName the name of the business sector whose ID is being requested.
     * @return the ID of the business sector with the given name.
     * @throws NotFoundInRepoException if a business sector with the given name is not found in the repository.
     */
    String getBusinessSectorIdByName(String businessSectorName);
}