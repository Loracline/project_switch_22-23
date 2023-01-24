package org.switch2022.project.model;

import java.util.Objects;

/**
 * Class Project is built to create and manage new projects.
 * A project is defined by code, name, customer, status , project typology and
 * business sector.
 */
public class Project {
    /**
     * Attributes
     */
    private final String projectCode;
    private final String projectName;
    private final Customer customer;
    private final ProjectTypology projectTypology;
    private final BusinessSector businessSector;
    private String projectStatus;

    /**
     * Constructor
     */
    public Project(String projectCode, String name, Customer customer, ProjectTypology projectTypology, BusinessSector businessSector) {
        this.projectCode = projectCode;
        this.projectName = name;
        this.customer = customer;
        this.projectStatus = "planned";
        this.projectTypology = projectTypology;
        this.businessSector = businessSector;
    }

    /**
     * This method checks if two instances of Project are equal by comparing
     * all their attributes.
     *
     * @param toCompare Project instance to compare with
     * @return TRUE if the two have the same attributes, and FALSE otherwise
     */
    @Override
    public boolean equals(Object toCompare) {
        if (this == toCompare) {
            return true;
        }
        if (!(toCompare instanceof Project)) {
            return false;
        }
        Project project = (Project) toCompare;
        return Objects.equals(projectCode, project.projectCode) && Objects.equals(projectName, project.projectName) && Objects.equals(customer, project.customer) && Objects.equals(projectStatus, project.projectStatus) && Objects.equals(projectTypology, project.projectTypology) && Objects.equals(businessSector, project.businessSector);
    }

    /**
     * Getter method for the attribute: projectCode.
     *
     * @return the code of the project.
     */
    public String getProjectCode() {
        return this.projectCode;
    }

    /**
     * Getter method for the attribute: projectName.
     *
     * @return the name of the project.
     */
    public String getProjectName() {
        return this.projectName;
    }

    /**
     * Getter method of the attribute: customer.
     *
     * @return the customer of the project.
     */
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * Getter method for the attribute: projectStatus.
     *
     * @return the status of the project.
     */
    public String getProjectStatus() {
        return this.projectStatus;
    }

    /**
     * Getter method for the attribute: projectTypology.
     *
     * @return the typology of the project.
     */
    public ProjectTypology getProjectTypology() {
        return this.projectTypology;
    }

    /**
     * Getter method for the attribute: businessSector.
     *
     * @return the business sector of the project.
     */
    public BusinessSector getBusinessSector() {
        return this.businessSector;
    }
}
