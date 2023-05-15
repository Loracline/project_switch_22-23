package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.exceptions.InvalidInputException;
import org.switch2022.project.ddd.utils.Validate;

/**
 * A value object representing a tax identification number.
 */
public class TaxId implements ValueObject<TaxId> {

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
     * Validates whether the tax ID is a valid ID for Portugal or Spain.
     *
     * @return {@code TRUE} if the tax ID is valid for Portugal or Spain.
     * @throws InvalidInputException if the tax ID is not valid for either Portugal or Spain, or if the
     *                               country is not supported for tax ID validation.
     */
    public boolean isValid() throws InvalidInputException {
        if (isValidPortugalTaxId(this.number) || isValidSpainTaxId(this.number)) {
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
        return number.length() != 9;
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
        int firstDigit = Character.getNumericValue(number.charAt(0));
        if (firstDigit != 1 && firstDigit != 2 && firstDigit != 5 &&
                firstDigit != 6 && firstDigit != 8 && firstDigit != 9) {
            return false;
        }

        // Calculating the sum of the first 8 digits of the number, using a weighting factor of (9 - i) for the i-th
        // digit. The sum is used to calculate the check digit.
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            sum += Character.getNumericValue(number.charAt(i)) * (9 - i);
        }

        // Calculating the check digit. If it's 10 or 11, it's set to 0.
        int checkDigit = 11 - (sum % 11);
        if (checkDigit == 10 || checkDigit == 11) {
            checkDigit = 0;
        }

        // Extracting the last digit of the number and comparing it to the calculated check digit. They must be equal.
        int lastDigit = Character.getNumericValue(number.charAt(8));
        return checkDigit == lastDigit;
    }

    /**
     * Checks whether the given NIF (Número de Identificación Fiscal) is valid for an individual in Spain.
     * The format of the NIF is 8 digits, followed by a letter, which can be "TRWAGMYFPDXBNJZSQVHLCKE".
     *
     * @param nif the NIF to be validated.
     * @return TRUE if the NIF is valid for an individual in Spain, FALSE otherwise.
     * @throws NumberFormatException if the first 8 characters of the NIF cannot be parsed as an integer.
     * @see <a href="https://en.wikipedia.org/wiki/National_identification_number#Spain">https://en.wikipedia.org/wiki/National_identification_number#Spain</a>
     */
    private boolean isValidSpainTaxId(String nif) {
        // Checking if NIF has 9 characters.
        if (isLengthInvalid()) {
            return false;
        }

        // Checking if the first 8 characters are digits.
        String firstEightCharacters = nif.substring(0, 8);
        if (!firstEightCharacters.matches("[0-9]+")) {
            return false;
        }

        // Checking if the last character is a letter.
        String lastCharacter = nif.substring(8).toUpperCase();
        if (!lastCharacter.matches("[A-Z]")) {
            return false;
        }

        // Calculating the letter that corresponds to the first 8 digits.
        String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
        int number = Integer.parseInt(firstEightCharacters);
        int index = number % 23;
        char expectedLetter = letters.charAt(index);

        // Comparing the expected letter with the actual last character of the NIF.
        return expectedLetter == lastCharacter.charAt(0);
    }
}
