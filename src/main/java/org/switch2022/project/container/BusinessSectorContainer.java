package org.switch2022.project.container;

import org.switch2022.project.model.BusinessSector;

import java.util.List;
/**
 * Class BusinessSectorContainer is built to allow access to class BusinessSector.
 */
public class BusinessSectorContainer {
    /**
     * BussinessSectorContainer contains business Sectors
     */
private List<BusinessSector> businessSectors;

    public BusinessSectorContainer(List<BusinessSector> businessSectors) {
        this.businessSectors = businessSectors;
    }
    /**
     * This method validates if businessSector exits
     *
     * @param businessSector one must check
     * @return true if businessSector exists in businessSectors
     */
    private boolean doesBusinessSectorExist (BusinessSector businessSector){
        return this.businessSectors.contains(businessSector);
    }
}
