package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

/**
 * A value object representing a tax identification number.
 */
public class TaxId implements ValueObject<TaxId> {

    private final String number;

    /**
     * Constructs a new TaxId object with the specified tax identification number.
     *
     * @param number the tax identification number to be represented by this object.
     * @throws IllegalArgumentException if the specified number is null, empty, or blank.
     */
    public TaxId(String number) {
        Validate.notNullOrEmptyOrBlank(number, "number");

        this.number = number;
    }

    /**
     * Checks if this TaxId object has the same value as the other TaxId object. Two TaxId objects are considered equal
     * if their "number" fields have the same value after normalization. The normalization process removes any non-digit
     * characters from the "number" fields and removes leading zeroes. For example, the "number" fields "514 024 054"
     * and "0514-024-054" are considered equal after normalization.
     *
     * @param other the other TaxId object to compare to.
     * @return TRUE if this TaxId object has the same value as the other TaxId object, FALSE otherwise.
     */
    @Override
    public boolean sameValueAs(TaxId other) {
        if (other == null) {
            return false;
        }

        String thisNumberNormalized = this.number.replaceAll("[^0-9]", "");
        String otherNumberNormalized = other.number.replaceAll("[^0-9]", "");

        thisNumberNormalized = thisNumberNormalized.replaceFirst("^0+(?!$)", "");
        otherNumberNormalized = otherNumberNormalized.replaceFirst("^0+(?!$)", "");

        return thisNumberNormalized.equals(otherNumberNormalized);
    }

    /**
     * Returns true if this TaxId object is equal to the specified object.
     *
     * @param toCompare the object to compare this TaxId object to.
     * @return TRUE if the specified object is a non-null TaxId object and represents the same tax identification number
     * as this object, FALSE otherwise.
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
        TaxId other = (TaxId) toCompare;
        return sameValueAs(other);
    }

    /**
     * Returns a hash code for this TaxId based on its normalized number. The hash code is computed as the hash code of
     * the normalized number string.
     * Two TaxId objects that are considered equal according to {@link #sameValueAs(TaxId)} will have the same hash code.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        String normalizedNumber = this.number
                .replaceAll("[^0-9]", "")
                .replaceFirst("^0+(?!$)", "");

        return normalizedNumber.hashCode();
    }

    /**
     * Returns the tax identification number represented by this object.
     *
     * @return the tax identification number represented by this object.
     */
    public String getNumber() {
        return number;
    }
}
