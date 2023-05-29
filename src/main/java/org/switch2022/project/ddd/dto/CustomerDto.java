package org.switch2022.project.ddd.dto;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) representing a customer.
 * <p>
 * This class encapsulates the customer's name and tax ID number.
 */
public class CustomerDto {

    /**
     * The name of the customer.
     */
    public final String name;

    /**
     * The tax ID number of the customer.
     */
    public final String taxIdNumber;

    /**
     * Constructs a new CustomerDto object with the specified name and tax ID number.
     *
     * @param name        The name of the customer.
     * @param taxIdNumber The tax ID number of the customer.
     */
    public CustomerDto(String name, String taxIdNumber) {
        this.name = name.toLowerCase().trim();
        this.taxIdNumber = taxIdNumber.toLowerCase().trim();
    }

    /**
     * Checks if the current CustomerDto object is equal to another object.
     * Two CustomerDto objects are considered equal if they have the same values for the 'name' and 'taxIdNumber'
     * fields.
     *
     * @param toCompare The object to compare with the current CustomerDto.
     * @return {@code TRUE} if the objects are equal, {@code FALSE} otherwise.
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
        CustomerDto other = (CustomerDto) toCompare;
        return Objects.equals(name, other.name) && Objects.equals(taxIdNumber, other.taxIdNumber);
    }

    /**
     * Generates the hash code for the CustomerDto object.
     * The hash code is computed based on the 'name' and 'taxIdNumber' fields.
     *
     * @return The hash code value for the CustomerDto object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, taxIdNumber);
    }
}
