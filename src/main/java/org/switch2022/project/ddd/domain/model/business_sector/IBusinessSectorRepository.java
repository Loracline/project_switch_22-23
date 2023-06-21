package org.switch2022.project.ddd.domain.model.business_sector;

import org.switch2022.project.ddd.domain.value_object.BusinessSectorId;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;
import java.util.List;
import java.util.Optional;

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

    /**
     * Retrieves all business sectors in the repository.
     *
     * @return A List containing all business sectors.
     */
    List<BusinessSector> findAll();

    /**
     * Retrieves an optional business sector from the repository based on the provided ID.
     *
     *@param businessSectorId The ID of the business sector to be retrieved.
     *@return An optional containing the business sector object corresponding to the provided business sector ID,
     * or an empty optional if the business sector is not found.
     */
    Optional<BusinessSector> findByIdNumber(BusinessSectorId businessSectorId);
}