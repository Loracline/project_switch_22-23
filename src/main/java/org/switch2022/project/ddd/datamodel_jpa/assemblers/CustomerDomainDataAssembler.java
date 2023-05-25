package org.switch2022.project.ddd.datamodel_jpa.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.datamodel_jpa.CustomerJpa;
import org.switch2022.project.ddd.domain.model.customer.Customer;
import org.switch2022.project.ddd.domain.model.customer.ICustomerFactory;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.domain.value_object.TaxId;

/**
 * Assembles data between the domain model and JPA data model for the {@link Customer} entity.
 * This class is responsible for converting {@link Customer} objects to {@link CustomerJpa} objects and vice versa.
 */
@Service
public class CustomerDomainDataAssembler {

    @SuppressWarnings("all")
    @Autowired
    ICustomerFactory factory;

    /**
     * Converts a {@link Customer} object to a {@link CustomerJpa} object.
     *
     * @param customer The {@link Customer} object to be converted.
     * @return The corresponding {@link CustomerJpa} object.
     */
    public CustomerJpa toData(Customer customer) {
        return new CustomerJpa(customer.getTaxId(), customer.getName());
    }

    /**
     * Converts a {@link CustomerJpa} object to a {@link Customer} object.
     *
     * @param customerJpa The {@link CustomerJpa} object to be converted.
     * @return The corresponding {@link Customer} object.
     */
    public Customer toDomain(CustomerJpa customerJpa) {
        return factory.createCustomer(
                new TaxId(customerJpa.getCustomerTaxId()),
                new Name(customerJpa.getCustomerName()));
    }
}
