package org.switch2022.project.utils.dto;

import java.util.Objects;

public class ListProjectsDTO {
    /**
     * Attributes of the class GetProjectsDTO.
     */

    public final String code;
    public final String name;
    public final String customer;
    public final String status;
    public final String projectTypology;
    public final String businessSector;

    /**
     * Constructor of the class GetProjectsDTO.
     */
    public ListProjectsDTO(String code, String name, String customer, String status,
                           String projectTypology, String businessSector) {
        this.code = code;
        this.name = name;
        this.customer = customer;
        this.status = status;
        this.projectTypology = projectTypology;
        this.businessSector = businessSector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ListProjectsDTO that = (ListProjectsDTO) o;
        return Objects.equals(code, that.code) && Objects.equals(name, that.name) &&
                Objects.equals(customer, that.customer) && Objects.equals(status, that.status) &&
                Objects.equals(projectTypology, that.projectTypology) &&
                Objects.equals(businessSector, that.businessSector);
    }
    @Override
    public int hashCode() {
        return Objects.hash(code, name, customer, status, projectTypology, businessSector);
    }
}
