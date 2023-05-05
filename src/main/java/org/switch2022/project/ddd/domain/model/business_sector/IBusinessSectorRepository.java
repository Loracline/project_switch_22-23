package org.switch2022.project.ddd.domain.model.business_sector;

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
    boolean add(BusinessSector businessSector);

    /**
     * This method gets the size of the repository list.
     *
     * @return the integer equivalent to the size of the list of business sectors.
     */
    int getSize();
}