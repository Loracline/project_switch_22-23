package org.switch2022.project.controller;

import org.switch2022.project.model.Company;
import org.switch2022.project.model.Profile;

/**
 * Class ProjectTypologyController is built to allow access to ProjectTypologyContainer
 * in Company Class.
 */
public class CreateProjectTypologyController {
    /**
     * Attributes of the class ProjectTypologyController, according to the US007 Class Diagram.
     */
    private final Company company;

    /**
     * ProjectTypologyController constructor
     */
    public CreateProjectTypologyController(Company company) {
        this.company = company;
    }

    public boolean createProjectTypology(String email, String projectTypology) {
        return company.validateProfileRequired(email, Profile.ADMINISTRATOR) &&
                company.createProjectTypology(projectTypology);
    }
}
