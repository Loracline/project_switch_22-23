package org.switch2022.project.ddd.domain.model.customer;

import org.switch2022.project.ddd.domain.value_object.TaxId;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

/**
 * An interface representing a repository for managing customers.
 */
public interface ICustomerRepository {

    /**
     * Retrieves the customer name with the specified tax ID from the repository, if it exists.
     *
     * @param taxId the tax ID of the customer to retrieve.
     * @return an Optional containing the customer with the specified tax ID, or an empty Optional if no such customer
     * exists.
     */
    String getCustomerNameByTaxId(TaxId taxId);

    /**
     * Adds the specified customer to the repository.
     *
     * @param customer the customer to add to the repository.
     * @return TRUE if the customer was added successfully.
     * @throws org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException if the repository already contains
     *                                                                            a customer with the same tax ID as
     *                                                                            the specified customer.
     */
    boolean addCustomerToRepository(Customer customer);

    /**
     * Retrieves the ID of a customer with the given name from the repository.
     *
     * @param customerName the name of the customer whose ID is being requested.
     * @return the ID of the customer with the given name.
     * @throws NotFoundInRepoException if a customer with the given name is not found in the repository.
     */

    String getCustomerTaxIdByName(String customerName);
}
