package org.switch2022.project.utils.dto;

import java.util.Objects;

public class GetProjectDTO {
    /**
     * Attributes of the class GetProjectsDTO.
     */

    public String code;
    public String name;
    public String customer;
    public String status;
    public String projectTypology;
    public String businessSector;

    /**
     * Constructor of the class GetProjectsDTO.
     */
    public GetProjectDTO(String code, String name, String customer, String status,
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
        GetProjectDTO that = (GetProjectDTO) o;
        return Objects.equals(code, that.code) && Objects.equals(name, that.name) &&
                Objects.equals(customer, that.customer) && Objects.equals(status, that.status) &&
                Objects.equals(projectTypology, that.projectTypology) &&
                Objects.equals(businessSector, that.businessSector);
    }
}
