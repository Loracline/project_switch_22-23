package org.switch2022.project.ddd.domain.model.customer;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.domain.value_object.TaxId;
import org.switch2022.project.ddd.utils.Validate;

/**
 * The Customer class represents a customer of the business.
 * It contains their tax ID and name as value objects.
 * This class implements the Entity interface, which defines the contract for comparing the identity of entities.
 */
public class Customer implements Entity<Customer> {

    private final TaxId customerTaxId;
    private final Name customerName;

    /**
     * Constructor for creating a new instance of Customer.
     *
     * @param customerTaxId the tax ID of the Customer.
     * @param customerName  the name of the Customer.
     * @throws IllegalArgumentException if either parameter is null.
     */
    protected Customer(TaxId customerTaxId, Name customerName) {
        Validate.notNull(customerTaxId, "Customer's tax ID can't be null.");
        Validate.notNull(customerName, "Customer's name can't be null.");

        this.customerTaxId = customerTaxId;
        this.customerName = customerName;
    }

    /**
     * Compares the identity of this Customer instance with another instance.
     * Two instances are considered equal if they have the same tax ID.
     *
     * @param other the Customer instance to compare with.
     * @return <code>true</code> if the two have the same tax ID, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameIdentityAs(Customer other) {
        return other != null && this.customerTaxId.equals(other.customerTaxId);
    }

    /**
     * Checks if this Customer instance has a specific tax ID.
     *
     * @param taxId the tax ID to check.
     * @return <code>true</code> if the Customer's tax ID matches the given tax ID, and <code>false</code> otherwise.
     */
    public boolean hasTaxId(TaxId taxId) {
        return this.customerTaxId.equals(taxId);
    }

    /**
     * Gets the name of this Customer instance.
     *
     * @return the name of the Customer.
     */
    public String getName() {
        return customerName.toString();
    }

    /**
     * Gets the tax ID of this Customer instance.
     *
     * @return the tax ID of the Customer.
     */
    public String getTaxId() {
        return customerTaxId.toString();
    }
}
