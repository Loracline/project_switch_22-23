package org.switch2022.project.ddd.domain.model.customer;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.domain.value_object.TaxId;
import org.switch2022.project.ddd.utils.Validate;

public class Customer implements Entity<Customer> {

    private final TaxId customerTaxId;
    private final Name customerName;

    /**
     * Constructor.
     *
     * @param customerTaxId       tax ID of the Customer.
     * @param customerName        name of the Customer.
     */
    protected Customer(TaxId customerTaxId, Name customerName) {
        Validate.notNull(customerName, "");

        this.customerTaxId = customerTaxId;
        this.customerName = customerName;
    }

    /**
     * This method checks if two instances of Customer are equal by comparing the value of the tax ID.
     *
     * @param other Customer instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameIdentityAs(Customer other) {
        return other != null && this.customerTaxId.equals(other.customerTaxId);
    }

    public boolean hasTaxId(TaxId taxId) {
        return this.customerTaxId.equals(taxId);
    }

    public String getCustomerName() {
        return customerName.toString();
    }
}
