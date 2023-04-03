package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

public class BusinessSectorId implements ValueObject<BusinessSectorId> {

    private final String businessSectorId;

    /**
     * Constructor.
     *
     * @param idNumber the id number of the business sector.
     */
    public BusinessSectorId(final Number idNumber) {
        Validate.notNull(idNumber, "business sector number");
        Validate.notNegative(idNumber, "business sector number");

        this.businessSectorId = String.format("BS%03d", idNumber).toLowerCase();

    }

    /**
     * Getter method for the attribute: businessSectorId.
     *
     * @return String representation of the business sector ID.
     */
    public String getBusinessSectorId() {
        return businessSectorId;
    }

    /**
     * This method checks if two instances of BusinessSectorId are equal by comparing the value of the attribute
     * business sector ID.
     *
     * @param other BusinessSectorId instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(BusinessSectorId other) {
        return other != null && this.businessSectorId.equals(other.businessSectorId);
    }

    /**
     * This method checks if an instance of BusinessSectorId is equal to another object.
     *
     * @param o object to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BusinessSectorId other = (BusinessSectorId) o;

        return sameValueAs(other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return businessSectorId.hashCode();
    }
}
