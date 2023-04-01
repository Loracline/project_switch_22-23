package org.switch2022.project.ddd.dto;

import java.util.Objects;

/**
 * Represents a project data transfer object.
 * This class contains information about the project such as its code, name,
 * customer, status, typology, and business sector.
 */
public class ProjectDto {

    /**
     * Attributes
     */
    public final String code;
    public final String name;
    public final String customer;
    public final String status;
    public final String projectTypology;
    public final String businessSector;

    /**
     * Constructor
     */
    public ProjectDto(String code, String name, String customer, String status,
                      String projectTypology, String businessSector) {
        this.code = code;
        this.name = name;
        this.customer = customer;
        this.status = status;
        this.projectTypology = projectTypology;
        this.businessSector = businessSector;
    }

    /**
     * The equals() method is used to determine whether two objects are equal in
     * terms of their content. This method compares the content of the two objects,
     * which means that two objects are considered equal if they have the same
     * state or values, even if they are not the same object in memory.
     *
     * @param o the reference object with which to compare.
     * @return TRUE if equal, and FALSE otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        org.switch2022.project.ddd.dto.ProjectDto that = (org.switch2022.project.ddd.dto.ProjectDto) o;
        return Objects.equals(code, that.code) && Objects.equals(name, that.name) &&
                Objects.equals(customer, that.customer) && Objects.equals(status, that.status) &&
                Objects.equals(projectTypology, that.projectTypology) &&
                Objects.equals(businessSector, that.businessSector);
    }

    /**
     * The hashCode() method is used to generate a unique hash code for an
     * object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(code, name, customer, status, projectTypology, businessSector);
    }

    public String getProjectCode() {
        return this.code;
    }
}

