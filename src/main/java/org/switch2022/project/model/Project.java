package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;
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
    private int sprintDuration;
    private final ProductBacklog productBacklog = new ProductBacklog();
    private final List<Sprint> sprints = new ArrayList<>();

    /**
     * Constructor
     */
    public Project(String projectCode, String name, Customer customer,
                   ProjectTypology projectTypology,
                   BusinessSector businessSector) {
        this.projectCode = projectCode;
        this.projectName = name;
        this.customer = customer;
        this.projectStatus = "planned";
        this.projectTypology = projectTypology;
        this.businessSector = businessSector;
        this.sprintDuration = 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode, projectName, customer, projectTypology,
                businessSector, projectStatus, sprintDuration);
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
        if (toCompare == null) {
            return false;
        }
        if (toCompare.getClass() != this.getClass()) {
            return false;
        }
        Project project = (Project) toCompare;
        return Objects.equals(projectCode, project.projectCode) &&
                Objects.equals(projectName, project.projectName) &&
                Objects.equals(customer, project.customer) &&
                Objects.equals(projectStatus, project.projectStatus) &&
                Objects.equals(projectTypology, project.projectTypology) &&
                Objects.equals(businessSector, project.businessSector);
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

    public void setProjectStatus(String status) {
        this.projectStatus = status.toLowerCase();
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

    public boolean hasProjectCode(String projectCode) {
        return projectCode.equals(this.projectCode);
    }

    public boolean isProjectOpen() {
        boolean result = false;
        final String STATUS = "closed";
        if (!STATUS.equalsIgnoreCase(this.projectStatus)) {
            result = true;
        }
        return result;
    }

    /**
     * getters and setters for sprintDuration.
     *
     * @return the sprint duration of the project.
     */
    public int getSprintDuration() {
        return this.sprintDuration;
    }

    public boolean setSprintDuration(int sprintDuration) {
        boolean isSprintDurationValid = false;

        if (sprintDuration > 0 && sprintDuration < 5) {
            this.sprintDuration = sprintDuration;
            isSprintDurationValid = true;
        }
        return isSprintDurationValid;
    }
}
