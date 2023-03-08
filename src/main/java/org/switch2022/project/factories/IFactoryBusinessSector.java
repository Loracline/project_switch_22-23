package org.switch2022.project.factories;

import org.switch2022.project.model.BusinessSector;

/**
 * Implementation of the FactoryBusinessSector interface that creates instances of the
 * Business Customer class
 */

public class IFactoryBusinessSector {
    /**
     * This method creates a new Customer object with the specified name and NIF.
     *
     * @param businessSectorName the name of the Business Sector
     * @return a new Business Sector object with the specified name
     */

    public BusinessSector createBusinessSector (String businessSectorName) {
        return new BusinessSector(businessSectorName);
    }
}
