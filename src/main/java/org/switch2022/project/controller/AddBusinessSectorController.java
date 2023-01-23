package org.switch2022.project.controller;

import org.switch2022.project.model.Company;

/**
 * Class AddBusinessSectorController is built to allow access to Company
 * in Company Class.
 */
public class AddBusinessSectorController {
    /**
     * Attributes of the class AddBusinessSectorController, according to the Class Diagram.
     */
    private final Company company;

    /**
     * CreateAddBusinessSectorController constructor
     */
    public AddBusinessSectorController(Company company) {
        this.company = company;
    }

    /**
     * Method addBusinessSector
     *
     * @return true if businessSector is created
     */
    public boolean addBusinessSector(String businessSectorName, String email) {
        boolean addBusinessSector = company.validateAdministrator(email) && company.addBusinessSector(businessSectorName);
        return addBusinessSector;
    }
}
