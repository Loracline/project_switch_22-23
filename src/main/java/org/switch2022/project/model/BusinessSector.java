package org.switch2022.project.model;

import java.util.Objects;

/**
 * Class BusinessSector is built to create and manage new business sectors.
 * A business sector is defined by name.
 */
public class BusinessSector {
    /**
     * Attributes
     */
    private final String businessSectorName;

    /**
     * Constructor
     */
    public BusinessSector(String businessSectorName) {
        this.businessSectorName = businessSectorName.toLowerCase().trim();
    }

    /**
     * Getter method for the attribute: businessSectorName.
     *
     * @return the name of the business sector.
     */
    public String getBusinessSectorName() {
        return businessSectorName;
    }

    /**
     * This method checks if two instances of BusinessSector are equal by
     * comparing its names.
     *
     * @param toCompare BusinessSector instance to compare with.
     * @return TRUE if the two have the same business sector name, and FALSE
     * otherwise.
     */
    @Override
    public boolean equals(Object toCompare) {
        if (this == toCompare) {
            return true;
        }
        if (toCompare ==null){
            return false;
        }
        if (toCompare.getClass() != this.getClass()) {
            return false;
        }
        BusinessSector that = (BusinessSector) toCompare;
        return Objects.equals(businessSectorName, that.businessSectorName.toLowerCase().trim());
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessSectorName);
    }
}
