package org.switch2022.project.ddd.domain.model.customer;

import org.switch2022.project.ddd.domain.value_object.TaxId;

/**
 * Interface representing a repository for managing {@link Customer} entities.
 * Provides methods for saving and retrieving customer data.
 */
public interface ICustomerRepository {

    /**
     * Saves a {@link Customer} object in the repository.
     *
     * @param customer The customer to be saved.
     * @return {@code true} if the customer was successfully saved, {@code false} otherwise.
     */
    boolean save(Customer customer);

    /**
     * Finds the customer name associated with the given tax ID in the repository.
     *
     * @param taxId The tax ID of the customer.
     * @return The name of the customer.
     */
    String findCustomerNameByTaxId(TaxId taxId);

    /**
     * Finds the customer tax ID associated with the given customer name in the repository.
     *
     * @param customerName The name of the customer.
     * @return The tax ID of the customer.
     */
    String findCustomerTaxIdByName(String customerName);
}
