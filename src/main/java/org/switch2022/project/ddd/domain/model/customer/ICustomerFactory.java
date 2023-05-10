package org.switch2022.project.ddd.domain.model.customer;

import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.domain.value_object.TaxId;

/**
 * An interface representing a factory for creating customers.
 */
public interface ICustomerFactory {

    /**
     * Creates a new costumer with the specified tax ID, name and phone number.
     *
     * @param customerTaxId       the tax ID of the customer.
     * @param customerName        the name of the customer.
     * @return a new Customer object with the specified information.
     */
    Customer createCustomer(TaxId customerTaxId, Name customerName);
}
