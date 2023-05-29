package org.switch2022.project.ddd.dto.mapper;

import org.switch2022.project.ddd.domain.model.customer.Customer;
import org.switch2022.project.ddd.dto.CustomerDto;

/**
 * Mapper class responsible for converting Customer objects to CustomerDto objects.
 */
public class CustomerMapper {

    /**
     * Converts a Customer object to a CustomerDto object.
     *
     * @param customer The Customer object to be converted.
     * @return A CustomerDto object representing the converted customer.
     */
    public CustomerDto customerToDto(Customer customer) {
        return new CustomerDto(customer.getName(), customer.getTaxId());
    }
}
