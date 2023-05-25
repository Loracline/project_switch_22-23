package org.switch2022.project.ddd.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.ddd.datamodel_jpa.CustomerJpa;

import java.util.Optional;

/**
 * JPA repository interface for managing {@link CustomerJpa} entities.
 * Provides basic CRUD operations and custom queries for customer data.
 */
public interface ICustomerJpaRepository extends CrudRepository<CustomerJpa, String> {

    /**
     * Checks if a customer with the given tax ID exists in the repository.
     *
     * @param taxId The tax ID of the customer.
     * @return {@code true} if a customer with the given tax ID exists, {@code false} otherwise.
     */
    boolean existsByCustomerTaxId(String taxId);

    /**
     * Finds a customer with the given tax ID in the repository.
     *
     * @param taxId The tax ID of the customer.
     * @return An optional containing the customer if found, or an empty optional otherwise.
     */
    Optional<CustomerJpa> findByCustomerTaxId(String taxId);

    /**
     * Finds a customer with the given name in the repository.
     *
     * @param name The name of the customer.
     * @return An optional containing the customer if found, or an empty optional otherwise.
     */
    Optional<CustomerJpa> findByCustomerName(String name);
}
