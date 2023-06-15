package org.switch2022.project.ddd.domain.model.customer;

import org.switch2022.project.ddd.domain.value_object.TaxId;

import java.util.List;
import java.util.Optional;

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

    /**
     * Retrieves all customers from the database.
     *
     * @return A list of Customer objects representing all customers.
     */
    List<Customer> findAll();

    /**
     * Retrieves an optional customer from the repository based on the provided tax ID.
     *
     * @param taxId The tax ID of the customer to be retrieved.
     * @return An optional containing the customer object corresponding to the provided tax ID, or an empty optional if
     * the customer is not found.
     */
    Optional<Customer> findCustomerByTaxId(TaxId taxId);
}
