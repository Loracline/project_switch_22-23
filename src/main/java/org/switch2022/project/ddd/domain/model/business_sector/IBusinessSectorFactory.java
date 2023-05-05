package org.switch2022.project.ddd.domain.model.business_sector;

import org.switch2022.project.ddd.domain.value_object.Name;

/**
 * Factory of BusinessSector class.
 */

public interface IBusinessSectorFactory {
    /**
     * This method creates an instance of BusinessSector.
     *
     * @param businessSectorNumber is the number of the business sector.
     * @param businessSectorName is the name of the business sector.
     * @return a new business sector.
     */

    BusinessSector createBusinessSector(final Number businessSectorNumber, Name businessSectorName);

}
