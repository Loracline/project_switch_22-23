package org.switch2022.project.factories;

import org.switch2022.project.model.BusinessSector;

/**
 Interface for a Business Sector factory.
 */

public interface IFactoryBusinessSector {

    /**
     This method creates a new Business Sector object with the specified name with no return.
     @param businessSectorName the name of the Business Sector
     */

    public BusinessSector createBusinessSector (String businessSectorName);

}
