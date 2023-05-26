package org.switch2022.project.ddd.dto;

/**
 * Represents a Data Transfer Object (DTO) for creating a customer.
 */
public class CustomerCreationDto {

    /**
     * The tax ID of the customer.
     */
    private final String customerTaxId;

    /**
     * The name of the customer.
     */
    private final String customerName;

    /**
     * Constructs a new CustomerCreationDto with the specified tax ID and name.
     *
     * @param customerTaxId The tax ID of the customer.
     * @param customerName  The name of the customer.
     */
    public CustomerCreationDto(String customerTaxId, String customerName) {
        this.customerTaxId = customerTaxId;
        this.customerName = customerName;
    }

    /**
     * Retrieves the tax ID of the customer.
     *
     * @return The tax ID of the customer as a String.
     */
    public String getCustomerTaxId() {
        return customerTaxId;
    }

    /**
     * Retrieves the name of the customer.
     *
     * @return The name of the customer as a String.
     */
    public String getCustomerName() {
        return customerName;
    }
}
