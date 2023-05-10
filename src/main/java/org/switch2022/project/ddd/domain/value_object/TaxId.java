package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

/**
 * Represents a tax identification number for a specific country.
 * This class supports tax identification numbers for Portugal and Spain, and provides validation for each.
 */
public class TaxId implements ValueObject<TaxId> {

    private final String number;

    /**
     * Constructor.
     *
     * @param number of the Customer.
     */
    public TaxId(String number) {
        Validate.notNullOrEmptyOrBlank(number, "number");
        this.number = number.trim();
    }

    /**
     * This method checks if the value of the attributes of this object are equal to the value of the attributes of the
     * given object. Value objects compare by the values of their attributes, since they don't have an identity.
     *
     * @param other The other value object to compare to.
     * @return <code>TRUE</code> if the attributes of this object and the given object have the same value, and
     * <code>FALSE</code> otherwise or if the given object is null.
     */
    @Override
    public boolean sameValueAs(TaxId other) {
        return other != null && this.number.equals(other.number);
    }
}
