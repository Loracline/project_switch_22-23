package org.switch2022.project.ddd.domain.model.business_sector;

import org.switch2022.project.ddd.domain.model.typology.Typology;
import org.switch2022.project.ddd.domain.value_object.Name;

/**
 * Factory of BusinessSector class.
 */

public interface IBusinessSectorFactory {
    /**
     * This method creates an instance of BusinessSector.
     *
     * @param idNumber of the business sector to create.
     * @param name       is the name of the business sector.
     */

    BusinessSector createBusinessSector(final Number idNumber, Name name);

}
