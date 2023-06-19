package org.switch2022.project.ddd.domain.model.business_sector;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.utils.Validate;

/**
 * Class Business Sector is built to create and manage new business sectors.
 * A business sector is identified by a unique code. It also has a name.
 */

public class BusinessSector implements Entity<BusinessSector> {

    private final BusinessSectorId id;
    private final Name name;


    /**
     * Constructor: the constructor with relevant attributes for a business sector to be created in a valid state.
     */
    protected BusinessSector(Number idNumber, Name name) {
        Validate.notNull(name, "The business sector name must not be null.");
        this.id = new BusinessSectorId(idNumber);
        this.name = name;
    }

    /**
     * This method checks if an instance of Business Sector is equal to another object.
     *
     * @param toCompare object to compare with.
     * @return true if the two have the same ID, and false otherwise.
     */
    @Override
    public boolean equals(Object toCompare) {
        if (this == toCompare) {
            return true;
        }
        if (toCompare == null) {
            return false;
        }
        if (this.getClass() != toCompare.getClass()) {
            return false;
        }
        BusinessSector businessSector = (BusinessSector) toCompare;

        return sameIdentityAs(businessSector);
    }

    /**
     * This method is used to generate a unique hash for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * This method checks if two instances of Business Sector are equal by comparing the value of the attribute id.
     *
     * @param other Business Sector instance to compare with.
     * @return true if the two have the same attribute value, and false otherwise.
     */
    @Override
    public boolean sameIdentityAs(BusinessSector other) {
        return other != null && this.id.equals(other.id);
    }

    /**
     * Getter method for the attribute: id.
     *
     * @return a String with the code of the project.
     */
    public String getBusinessSectorId() {
        return this.id.getBusinessSectorId();
    }

    /**
     * Getter method for the attribute: name.
     *
     * @return a String with the name of the business sector.
     */
    public String getBusinessSectorName() {
        return this.name.getName();
    }

    /**
     * Checks if this BusinessSector instance has a specific BusinessSectorId.
     *
     * @param businessSectorId the BusinessSectorId to check.
     * @return <code>true</code> if the BusinessSector's BusinessSectorId matches the given BusinessSectorId, and <code>false</code> otherwise.
     */
    public boolean hasBusinessSectorId(BusinessSectorId businessSectorId) {
        return this.id.getBusinessSectorId().equals(businessSectorId.getBusinessSectorId());
    }
}



