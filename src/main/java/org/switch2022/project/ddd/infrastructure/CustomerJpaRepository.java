package org.switch2022.project.ddd.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.switch2022.project.ddd.datamodel_jpa.CustomerJpa;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.CustomerDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.customer.Customer;
import org.switch2022.project.ddd.domain.model.customer.ICustomerRepository;
import org.switch2022.project.ddd.domain.value_object.TaxId;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;
import org.switch2022.project.ddd.infrastructure.jpa.ICustomerJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link ICustomerRepository} interface that uses JPA to persist and retrieve customer data.
 * This class interacts with the {@link ICustomerJpaRepository} for performing CRUD operations on {@link CustomerJpa} entities.
 */
@Repository("customer_jpa")
public class CustomerJpaRepository implements ICustomerRepository {

    @SuppressWarnings("all")
    @Autowired
    ICustomerJpaRepository crudRepository;
    @SuppressWarnings("all")
    @Autowired
    CustomerDomainDataAssembler assembler;

    /**
     * Saves a {@link Customer} object in the repository.
     * If the customer's tax ID already exists in the repository, an {@link AlreadyExistsInRepoException} is thrown.
     *
     * @param customer The customer to be saved.
     * @return {@code true} if the customer was successfully saved, {@code false} otherwise.
     * @throws AlreadyExistsInRepoException if the customer's tax ID already exists in the repository.
     */
    @Override
    public boolean save(Customer customer) {
        CustomerJpa customerJpa = assembler.toData(customer);
        if (crudRepository.existsByCustomerTaxId(customer.getTaxId())) {
            throw new AlreadyExistsInRepoException("Customer's tax ID already exists!");
        } else {
            crudRepository.save(customerJpa);
            return true;
        }
    }

    /**
     * Finds the customer name associated with the given tax ID in the repository.
     *
     * @param customerTaxId The tax ID of the customer.
     * @return The name of the customer.
     * @throws NotFoundInRepoException if a customer with the given tax ID does not exist in the repository.
     */
    @Override
    public String findCustomerNameByTaxId(TaxId customerTaxId) {
        Optional<CustomerJpa> optional = crudRepository.findByCustomerTaxId(customerTaxId.getNumber());
        String customerName;
        if (optional.isPresent()) {
            customerName = optional.get().getCustomerName();
        } else {
            throw new NotFoundInRepoException("Customer with this tax ID does not exist in the repository.");
        }
        return customerName;
    }

    /**
     * Finds the customer tax ID associated with the given customer name in the repository.
     *
     * @param customerName The name of the customer.
     * @return The tax ID of the customer.
     * @throws NotFoundInRepoException if a customer with the given name does not exist in the repository.
     */
    @Override
    public String findCustomerTaxIdByName(String customerName) {
        Optional<CustomerJpa> optional = crudRepository.findByCustomerName(customerName);
        String customerTaxId;
        if (optional.isPresent()) {
            customerTaxId = optional.get().getCustomerTaxId();
        } else {
            throw new NotFoundInRepoException("Customer with this name does not exist in the repository.");
        }
        return customerTaxId;
    }

    /**
     * Retrieves all customers from the database.
     *
     * @return A list of Customer objects representing all customers.
     */
    public List<Customer> findAll() {
        Iterable<CustomerJpa> customersJpa = crudRepository.findAll();
        List<Customer> customers = new ArrayList<>();
        customersJpa.forEach(customerJpa -> customers.add(assembler.toDomain(customerJpa)));
        return customers;
    }

    /**
     * Retrieves an optional customer from the repository based on the provided tax ID.
     *
     * @param taxId The tax ID of the customer to be retrieved.
     * @return An optional containing the customer object corresponding to the provided tax ID, or an empty optional if
     * the customer is not found.
     */
    public Optional<Customer> findCustomerByTaxId(TaxId taxId) {
        Optional<CustomerJpa> opCustomerJpa = crudRepository.findByCustomerTaxId(taxId.getNumber());
        return opCustomerJpa.map(customerJpa -> assembler.toDomain(customerJpa));
    }
}
