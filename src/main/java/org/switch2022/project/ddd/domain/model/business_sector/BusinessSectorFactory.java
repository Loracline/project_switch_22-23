package org.switch2022.project.ddd.domain.model.business_sector;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.value_object.Name;

/**
 * Class BusinessSectorFactory implements IBusinessSectorFactory in order to create
 * a BusinessSector object.
 */

@Component
public class BusinessSectorFactory implements IBusinessSectorFactory {
    /**
     * This method creates an instance of business sector.
     *
     * @param businessSectorNumber is the number of the business sector.
     * @param businessSectorName is the name of the business sector.
     * @return a new business sector.
     */
    @Override
    public BusinessSector createBusinessSector(final Number businessSectorNumber, Name businessSectorName){
        return new BusinessSector(businessSectorNumber, businessSectorName);
    }
}
