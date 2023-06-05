package org.switch2022.project.ddd.datamodel_jpa;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.utils.Validate;

import java.util.Objects;

@NoArgsConstructor
@Entity
@Table(name = "resources")
public class ProjectResourceJpa {
    @Id
    @Getter
    private String id;
    @Getter
    private String projectCode;
    @Getter
    private String accountEmail;
    @Getter
    private String role;
    @Getter
    private String startDate;
    @Getter
    private String endDate;
    @Getter
    private float costPerHour;
    @Getter
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

    /**
     * This method checks if an instance of ProjectResourceJpa is equal to another object.
     *
     * @param other object to compare with.
     * @return <code>true</code> if the two instances are equals, and <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        ProjectResourceJpa that = (ProjectResourceJpa) other;
        return Objects.equals(id, that.id);
    }


    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
