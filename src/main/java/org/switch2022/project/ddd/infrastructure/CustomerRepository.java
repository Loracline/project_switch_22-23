package org.switch2022.project.ddd.infrastructure;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.model.customer.Customer;
import org.switch2022.project.ddd.domain.model.customer.ICustomerRepository;
import org.switch2022.project.ddd.domain.value_object.TaxId;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class representing a repository for managing customers.
 */
@Component
public class CustomerRepository implements ICustomerRepository {

    private final List<Customer> customers = new ArrayList<>();

    /**
     * Returns TRUE if this CustomerRepository is equal to the specified object.
     *
     * @param toCompare the object to compare to this CustomerRepository.
     * @return TRUE if the objects are equal, and FALSE otherwise.
     */
    @Override
    public boolean equals(Object toCompare) {
        if (this == toCompare) {
            return true;
        }
        if (toCompare == null) {
            return false;
        }
        if (this.getClass() != toCompare.getClass()) {
            return false;
        }
        CustomerRepository other = (CustomerRepository) toCompare;
        return Objects.equals(customers, other.customers);
    }

    /**
     * Returns the hash code for this CustomerRepository.
     *
     * @return the hash code for this CustomerRepository.
     */
    @Override
    public int hashCode() {
        return customers.hashCode();
    }

    /**
     * Adds the specified customer to the repository.
     *
     * @param customer the customer to add to the repository.
     * @return TRUE if the customer was added successfully.
     * @throws AlreadyExistsInRepoException if the repository already contains
     *                                      a customer with the same tax ID as
     *                                      the specified customer.
     */
    @Override
    public boolean addCustomerToRepository(Customer customer) {
        if (!customers.contains(customer)) {
            customers.add(customer);
            return true;
        } else {
            throw new AlreadyExistsInRepoException("Customer's tax ID already exists!");
        }
    }

    /**
     * Retrieves the name of a customer with the given tax ID from the repository.
     *
     * @param taxId the tax ID of the customer whose name is being requested.
     * @return the name of the customer with the given tax ID.
     * @throws NotFoundInRepoException if a customer with the given tax ID is not found in the repository.
     */
    @Override
    public String getCustomerNameByTaxId(TaxId taxId) {
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
     * Retrieves the ID of a customer with the given name from the repository.
     *
     * @param customerName the name of the customer whose ID is being requested.
     * @return the ID of the customer with the given name.
     * @throws NotFoundInRepoException if a customer with the given name is not found in the repository.
     */
    @Override
    public String getCustomerTaxIdByName(String customerName) {
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
}
