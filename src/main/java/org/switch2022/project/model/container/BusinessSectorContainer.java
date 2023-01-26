package org.switch2022.project.model.container;

import org.switch2022.project.model.BusinessSector;


import java.util.List;

/**
 * Class BusinessSectorContainer is built to access and manipulate the set of
 * business sectors of the projects of this company.
 */
public class BusinessSectorContainer {
    /**
     * Attributes
     */
    private final List<BusinessSector> businessSectors;

    /**
     * Constructor
     */
    public BusinessSectorContainer(List<BusinessSector> businessSectors) {
        this.businessSectors = businessSectors;
    }

    /**
     * This method validates if business sector already exists in the container.
     *
     * @param businessSector one must check.
     * @return TRUE if exists and FALSE otherwise.
     */
    private boolean doesBusinessSectorExist(BusinessSector businessSector) {
        return this.businessSectors.contains(businessSector);
    }

    /**
     * This method creates a new business sector and adds it to the container
     * if it doesn't already exist.
     *
     * @param businessSectorName one intend to add.
     * @return TRUE if added and FALSE otherwise.
     */
    public boolean createBusinessSector(String businessSectorName) {
        BusinessSector businessSector = new BusinessSector(businessSectorName);
        boolean isAddedToList = false;
        if (!businessSectorName.isEmpty() && !doesBusinessSectorExist(businessSector)) {
            businessSectors.add(businessSector);
            isAddedToList = true;
        }
        return isAddedToList;
    }
}
