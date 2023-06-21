package org.switch2022.project.ddd.dto;

import java.util.Objects;

public class ProjectDto {
    /**
     * Attributes
     */
    public final String code;
    public final String projectName;
    public final String customerName;
    public final String status;
    public final String startDate;
    public final String endDate;

    /**
     * Constructor
     *
     * @param code         of the project.
     * @param projectName  of the project.
     * @param customerName of the project.
     * @param status       of the project.
     * @param startDate    of the project.
     * @param endDate      of the project.
     */
    public ProjectDto(String code, String projectName, String customerName,
                      String status, String startDate,
                      String endDate) {
        this.code = code.toLowerCase().trim();
        this.projectName = projectName.toLowerCase().trim();
        this.customerName = customerName.toLowerCase().trim();
        this.status = status.toLowerCase().trim();
        this.startDate = startDate.toLowerCase().trim();
        this.endDate = endDate.toLowerCase().trim();
    }

    /**
     * The equals() method is used to determine whether two objects are equal in
     * terms of their content.
     * The default implementation of the equals() method compares the memory
     * addresses of the two objects, which means that two objects are considered
     * equal only if they are the same object in memory.
     *
     * @param o projectDto to compare to.
     * @return TRUE if equal, and FALSE otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        ProjectDto that = (ProjectDto) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(projectName, that.projectName) && Objects.equals(customerName, that.customerName)
                && Objects.equals(status, that.status) && Objects.equals(startDate,
                that.startDate)
                && Objects.equals(endDate, that.endDate);
    }

    /**
     * The hashCode() method is used to generate a unique hash code for an
     * object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(code, projectName, customerName, status, startDate, endDate);
    }
}
