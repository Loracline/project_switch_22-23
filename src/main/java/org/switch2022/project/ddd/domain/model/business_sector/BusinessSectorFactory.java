package org.switch2022.project.ddd.domain.model.business_sector;

import org.switch2022.project.ddd.domain.model.typology.Typology;
import org.switch2022.project.ddd.domain.value_object.Name;

/**
 * Class BusinessSectorFactory implements IBusinessSectorFactory in order to create
 * a BusinessSector object.
 */

public class BusinessSectorFactory implements IBusinessSectorFactory {
    /**
     * This method creates an instance of business sector.
     *
     * @param idNumber is an attribute of Typology.
     * @param name is an attribute of Typology.
     * @return a new object Typology.
     */
    @Override
    public BusinessSector createBusinessSector(final Number idNumber, Name name){
        return new BusinessSector(idNumber, name);
    }
}
