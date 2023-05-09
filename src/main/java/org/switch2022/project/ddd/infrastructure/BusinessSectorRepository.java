package org.switch2022.project.ddd.infrastructure;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.model.business_sector.BusinessSector;
import org.switch2022.project.ddd.domain.model.business_sector.IBusinessSectorRepository;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class BusinessSectorRepository implements IBusinessSectorRepository {
    /**
     * Attributes
     */
    private final List<BusinessSector> businessSectors = new ArrayList<>();

    /**
     * This method checks if an instance of BusinessSectorRepository is equal to another object.
     *
     * @param o object to compare with.
     * @return true if the two have the same attribute value, and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        BusinessSectorRepository that = (BusinessSectorRepository) o;
        return Objects.equals(businessSectors, that.businessSectors);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the
     * object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(businessSectors);
    }

    /**
     * This method adds an instance of BusinessSector to BusinessSectorRepository if that instance does not
     * already exist there.
     *
     * @param businessSector to be added to the repository.
     * @return true if the business sector was added to the business sector repository, and throws an
     * AlreadyExistInRepoException otherwise.
     */
    public boolean add(BusinessSector businessSector) {
        if (businessSectors.contains(businessSector)) {
            throw new AlreadyExistsInRepoException("The business sector already exists in the repository.");
        } else {
            businessSectors.add(businessSector);
            return true;
        }
    }

    /**
     * This method gets the size of the repository list
     *
     * @return the integer equivalent to the size of the list of business sectors.
     */
    public int getSize() {
        return businessSectors.size();
    }

    /**
     * Retrieves the ID of a customer with the given name from the repository.
     *
     * @param businessSectorName the name of the business sector whose ID is being requested.
     * @return the ID of the business sector with the given name.
     * @throws NotFoundInRepoException if a business sector with the given name is not found in the repository.
     */
    @Override
    public String getBusinessSectorIdByName(String businessSectorName) {
        String requestedBusinessSectorId = null;
        int i = 0;
        while (i < this.businessSectors.size()) {
            if (businessSectors.get(i).getBusinessSectorName().contains(businessSectorName)) {
                String businessSectorIdWithLetters = businessSectors.get(i).getBusinessSectorId();
                requestedBusinessSectorId = businessSectorIdWithLetters.replaceAll("[^0-9]", "");
                i = this.businessSectors.size();
            }
            i++;
        }

        if (requestedBusinessSectorId == null) {
            throw new NotFoundInRepoException("Business sector with this name does not exist in the Repository.");
        }

        return requestedBusinessSectorId;
    }
}
