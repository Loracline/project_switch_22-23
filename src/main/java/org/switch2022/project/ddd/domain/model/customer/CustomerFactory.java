package org.switch2022.project.ddd.domain.model.customer;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.domain.value_object.TaxId;

/**
 * An implementation of the {@link ICustomerFactory} interface that creates new customers.
 */
@Component
public class CustomerFactory implements ICustomerFactory {

    /**
     * Creates a new customer with the specified tax ID, name and phone number.
     *
     * @param customerTaxId       the tax ID of the customer.
     * @param customerName        the name of the customer.
     * @return a new Customer object with the specified information.
     */
    @Override
    public Customer createCustomer(TaxId customerTaxId, Name customerName) {
        return new Customer(customerTaxId, customerName);
    }
}
