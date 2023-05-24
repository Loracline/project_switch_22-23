package org.switch2022.project.ddd.datamodel.JPA;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "resources")
public class ProjectResourceJpa {
    @Id
    private String id;
    private String projectCode;
    private String accountEmail;
    private String role;
    private String startDate;
    private String endDate;
    private float costPerHour;
    private float percentageOfAllocation;

    public ProjectResourceJpa(String id, String projectCode, String accountEmail, String role, String startDate,
                              String endDate, float costPerHour, float percentageOfAllocation) {
        this.id = id;
        this.projectCode = projectCode;
        this.accountEmail = accountEmail;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
        this.costPerHour = costPerHour;
        this.percentageOfAllocation = percentageOfAllocation;
    }
}
