package org.switch2022.project.controller;

import org.switch2022.project.model.Company;
/**
 * Class ProjectTypologyController is built to allow access to ProjectTypologyContainer
 * in Company Class.
 */
public class ProjectTypologyController {
    /**
     * Attributes of the class ProjectTypologyController, according to the US007 Class Diagram.
     */
    private Company company;
    /**
     * ProjectTypologyController constructor
     */
    public ProjectTypologyController(Company company){
        this.company=company;
    }
    public boolean createProjectTypology(String email,String projectTypology){
       boolean createProjectTypology = company.validateAdministrator(email) && company.createProjectTypology(projectTypology);
       return createProjectTypology;
    }
}
