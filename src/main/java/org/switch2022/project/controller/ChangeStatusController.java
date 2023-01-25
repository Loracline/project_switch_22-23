package org.switch2022.project.controller;

import org.switch2022.project.model.Company;

/**
 * Class ChangeStatusController acts as an intermediary between the user
 * interface (UI) and the business logic underlying the following USs:
 * "US005 - As Administrator, I want to inactivate a user account."
 * "US006 - As Administrator, I want to activate a user account."
 */
public class ChangeStatusController {
    /**
     * Attributes
     */
    private final Company company;


    /**
     * Constructor
     */
    public ChangeStatusController(Company company) {
        this.company = company;
    }


    /**
     * This method changes the status of a specific e-mail account.
     *
     * @param email  of the account one wish to change.
     * @param status TRUE = "Active" or FALSE = "Inactive".
     * @return TRUE if changed, and FALSE otherwise.
     */
    public boolean changeStatus(String email, boolean status) {
        return (company.changeStatus(email, status));
    }
}