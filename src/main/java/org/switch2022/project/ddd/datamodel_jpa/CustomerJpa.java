package org.switch2022.project.ddd.datamodel_jpa;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a customer entity in the Java Persistence API (JPA) data model.
 * This class is used to map customer data to the corresponding database table.
 */
@NoArgsConstructor
@Entity
@Table(name = "customers")
@Data
public class CustomerJpa {

    /**
     * The tax ID of the customer.
     */
    @Id
    private String customerTaxId;

    /**
     * The name of the customer.
     */
    private String customerName;

    /**
     * Constructs a new instance of the {@code CustomerJpa} class with the specified tax ID and name.
     *
     * @param customerTaxId The tax ID of the customer.
     * @param customerName  The name of the customer.
     */
    public CustomerJpa(String customerTaxId, String customerName) {
        this.customerTaxId = customerTaxId;
        this.customerName = customerName;
    }

    /**
     * Returns the tax identification number of the customer.
     *
     * @return The tax identification number of the customer.
     */
    public String getCustomerTaxId() {
        return customerTaxId;
    }

    /**
     * Returns the name of the customer.
     *
     * @return The name of the customer.
     */
    public String getCustomerName() {
        return customerName;
    }
}
