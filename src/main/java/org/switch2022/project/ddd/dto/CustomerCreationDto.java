package org.switch2022.project.ddd.dto;

/**
 * Represents a Data Transfer Object (DTO) for creating a customer.
 */
public class CustomerCreationDto {

    /**
     * The name of the customer.
     */
    private final String name;

    /**
     * The tax ID of the customer.
     */
    private final String taxIdNumber;

    /**
     * Constructs a new CustomerCreationDto with the specified tax ID and name.
     *
     * @param name The tax ID of the customer.
     * @param taxIdNumber  The name of the customer.
     */
    public CustomerCreationDto(String name, String taxIdNumber) {
        this.name = name.toLowerCase();
        this.taxIdNumber = taxIdNumber.trim();
    }

    /**
     * Retrieves the tax ID of the customer.
     *
     * @return The tax ID of the customer as a String.
     */
    public String getTaxIdNumber() {
        return taxIdNumber;
    }

    /**
     * Retrieves the name of the customer.
     *
     * @return The name of the customer as a String.
     */
    public String getName() {
        return name;
    }
}
