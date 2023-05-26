package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.exceptions.InvalidInputException;
import org.switch2022.project.ddd.utils.Validate;

/**
 * A value object representing a tax identification number.
 */
public class TaxId implements ValueObject<TaxId> {

    public static final int MIN_INDEX = 0;
    public static final int MAX_INDEX = 8;
    public static final int MAX_LENGTH = 9;
    public static final int CHECK_DIGIT_ZERO = 0;
    public static final int CHECK_DIGIT_TEN = 10;
    public static final int CHECK_DIGIT_ELEVEN = 11;
    public static final int FIRST_DIGIT_ONE = 1;
    public static final int FIRST_DIGIT_TWO = 2;
    public static final int FIRST_DIGIT_FIVE = 5;
    public static final int FIRST_DIGIT_SIX = 6;
    public static final int FIRST_DIGIT_EIGHT = 8;
    public static final int FIRST_DIGIT_NINE = 9;

    private final String number;

    /**
     * Creates a new TaxId instance with the given number.
     *
     * @param number the tax identification number to set, not null, empty, or blank.
     * @throws IllegalArgumentException if the given number is null, empty, or blank.
     */
    public TaxId(String number) {
        Validate.notNullOrEmptyOrBlank(number, "number");

        this.number = number
                .replaceAll("[\\s-]+", "")
                .toUpperCase();
    }

    /**
     * Checks if this TaxId object has the same value as the other TaxId object. Two TaxId objects are considered equal
     * if their "number" fields have the same value after normalization.
     *
     * @param other the other TaxId object to compare to.
     * @return TRUE if this TaxId object has the same value as the other TaxId object, FALSE otherwise.
     */
    @Override
    public boolean sameValueAs(TaxId other) {
        return other != null && this.number.equals(other.number);
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
     * Returns a hash code for this TaxId based on its number. The hash code is computed as the hash code of
     * the normalized number string.
     * Two TaxId objects that are considered equal according to {@link #sameValueAs(TaxId)} will have the same hash code.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return number.hashCode();
    }

    /**
     * Returns the tax identification number represented by this object.
     *
     * @return the tax identification number represented by this object.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Checks if the tax ID is valid by performing validation specific to Portugal.
     *
     * @return TRUE if the tax ID is valid, FALSE otherwise.
     * @throws InvalidInputException if the tax ID is invalid or the country is unsupported for tax ID validation.
     */
    public boolean isValid() throws InvalidInputException {
        if (isValidPortugalTaxId(this.number)) {
            return true;
        } else {
            throw new InvalidInputException("Invalid or unsupported country for tax ID validation.");
        }
    }

    /**
     * Checks if the length of the tax ID number is invalid.
     *
     * @return TRUE if the length of the tax ID number is not 9, FALSE otherwise.
     */
    private boolean isLengthInvalid() {
        return number.length() != MAX_LENGTH;
    }

    /**
     * Checks whether the given tax ID number is valid for Portugal.
     *
     * @param number the tax ID number to check.
     * @return {@code TRUE} if the tax ID number is valid for Portugal; {@code FALSE} otherwise.
     */
    private boolean isValidPortugalTaxId(String number) {
        // Checking if the length of the number is 9.
        if (isLengthInvalid()) {
            return false;
        }

        // Extracting the first digit of the number and checking if it's a valid first digit for PT
        int firstDigit = Character.getNumericValue(number.charAt(MIN_INDEX));
        if (firstDigit != FIRST_DIGIT_ONE && firstDigit != FIRST_DIGIT_TWO && firstDigit != FIRST_DIGIT_FIVE &&
                firstDigit != FIRST_DIGIT_SIX && firstDigit != FIRST_DIGIT_EIGHT && firstDigit != FIRST_DIGIT_NINE) {
            return false;
        }

        // Calculating the sum of the first 8 digits of the number, using a weighting factor of (9 - i) for the i-th
        // digit. The sum is used to calculate the check digit.
        int sum = 0;
        for (int i = MIN_INDEX; i < MAX_INDEX; i++) {
            sum += Character.getNumericValue(number.charAt(i)) * (MAX_LENGTH - i);
        }

        // Calculating the check digit. If it's 10 or 11, it's set to 0.
        int checkDigit = CHECK_DIGIT_ELEVEN - (sum % CHECK_DIGIT_ELEVEN);
        if (checkDigit == CHECK_DIGIT_TEN || checkDigit == CHECK_DIGIT_ELEVEN) {
            checkDigit = CHECK_DIGIT_ZERO;
        }

        // Extracting the last digit of the number and comparing it to the calculated check digit. They must be equal.
        int lastDigit = Character.getNumericValue(number.charAt(MAX_INDEX));
        return checkDigit == lastDigit;
    }
}
