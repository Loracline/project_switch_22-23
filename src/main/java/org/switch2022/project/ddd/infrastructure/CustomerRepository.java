package org.switch2022.project.ddd.infrastructure;

import org.springframework.stereotype.Repository;
import org.switch2022.project.ddd.domain.model.customer.Customer;
import org.switch2022.project.ddd.domain.model.customer.ICustomerRepository;
import org.switch2022.project.ddd.domain.value_object.TaxId;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link ICustomerRepository} interface that stores customer data in memory.
 * This class manages the persistence and retrieval of {@link Customer} objects.
 */
@Repository("customer_memory")
public class CustomerRepository implements ICustomerRepository {

    private final List<Customer> customers = new ArrayList<>();

    /**
     * Saves a {@link Customer} object in the repository.
     * If the customer already exists in the repository, an {@link AlreadyExistsInRepoException} is thrown.
     *
     * @param customer The customer to be saved.
     * @return {@code true} if the customer was successfully saved, {@code false} otherwise.
     * @throws AlreadyExistsInRepoException if the customer's tax ID already exists in the repository.
     */
    @Override
    public boolean save(Customer customer) {
        if (!customers.contains(customer)) {
            customers.add(customer);
            return true;
        } else {
            throw new AlreadyExistsInRepoException("Customer's tax ID already exists!");
        }
    }

    /**
     * Finds the customer name associated with the given tax ID in the repository.
     *
     * @param taxId The tax ID of the customer.
     * @return The name of the customer.
     * @throws NotFoundInRepoException if a customer with the given tax ID does not exist in the repository.
     */
    @Override
    public String findCustomerNameByTaxId(TaxId taxId) {
        String requestedName = null;
        int i = 0;
        while (i < this.customers.size()) {
            if (customers.get(i).hasTaxId(taxId)) {
                requestedName = customers.get(i).getName();
                i = this.customers.size();
            }
            i++;
        }
        if (requestedName == null) {
            throw new NotFoundInRepoException("Customer with this tax ID does not exist in Repository.");
        }
        return requestedName;
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
        String requestedTaxId = null;
        int i = 0;
        while (i < this.customers.size()) {
            if (customers.get(i).getName().contains(customerName)) {
                requestedTaxId = customers.get(i).getTaxId();
                i = this.customers.size();
            }
            i++;
        }
        if (requestedTaxId == null) {
            throw new NotFoundInRepoException("Customer with this name does not exist in the Repository.");
        }
        return requestedTaxId;
    }

    /**
     * Retrieves all customers.
     *
     * @return An unmodifiable list of Customer objects representing all customers.
     */
    public List<Customer> findAll() {
        return Collections.unmodifiableList(customers);
    }

    /**
     * Retrieves an optional customer from the repository based on the provided tax ID.
     *
     * @param taxId The tax ID of the customer to be retrieved.
     * @return An optional containing the customer object corresponding to the provided tax ID, or an empty optional if
     * the customer is not found.
     */
    public Optional<Customer> findCustomerByTaxId(TaxId taxId) {
        Optional<Customer> opCustomer = Optional.empty();
        int i = 0;
        while (i < this.customers.size()) {
            if (customers.get(i).hasTaxId(taxId)) {
                opCustomer = Optional.of(customers.get(i));
                i = this.customers.size();
            }
            i++;
        }
        return opCustomer;
    }
}
