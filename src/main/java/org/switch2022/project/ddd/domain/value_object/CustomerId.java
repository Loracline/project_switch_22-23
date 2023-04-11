package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

public class CustomerId implements ValueObject<CustomerId> {

    private final String id;

    /**
     * Constructor.
     *
     * @param idNumber the id number of the customer.
     */
    public CustomerId(final Number idNumber) {
        Validate.notNull(idNumber, "customer number");
        Validate.notNegative(idNumber, "customer number");

        this.id = String.format("C%03d", idNumber).toLowerCase();

    }

    /**
     * Getter method for the attribute: customerId.
     *
     * @return String representation of the customer ID.
     */
    public String getCustomerId() {
        return id;
    }

    /**
     * This method checks if two instances of CustomerId are equal by comparing the value of the attribute
     * customer ID.
     *
     * @param other CustomerId instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(CustomerId other) {
        return other != null && this.id.equals(other.id);
    }

    /**
     * This method checks if an instance of CustomerId is equal to another object.
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

        CustomerId other = (CustomerId) o;

        return sameValueAs(other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
