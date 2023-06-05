package org.switch2022.project.ddd.datamodel_jpa;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Table(name = "projects")
public class ProjectJpa {
    /**
     * Attributes
     */
    @Id
    private String projectCode;

    private double budget;
    private String projectName;
    private String description;
    private String projectStatus;
    private int numberOfPlannedSprints;
    private String startDate;
    private String endDate;
    private int sprintDuration;
    private String businessSectorId;
    private String customerTaxId;
    private String projectTypologyId;

    /**
     * The product backlog associated with the project.
     *
     * <p>The product backlog represents a prioritized list of user stories, features, and tasks
     * that need to be implemented and delivered as part of the project. It captures the requirements
     * and desired functionalities of the project in an organized manner.
     *
     * <p>This field establishes a One-to-One relationship between the project and its product backlog.
     * The cascade type is set to CascadeType.ALL, which means that any operations performed on the project
     * will be cascaded to the associated product backlog, allowing for automatic persistence and deletion
     * of the backlog when the project is saved or removed.
     *
     * <p>The "productBacklog_id" column is used as the foreign key in the database to establish the relationship
     * between the project and its product backlog. It references the "id" column in the product backlog table.
     * The JoinColumn annotation specifies the name of the foreign key column and the referenced column.
     */

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productBacklog_id", referencedColumnName = "productBacklogId")
    private ProductBacklogJpa productBacklog;

    /**
     * Constructs a new instance of the ProjectJpa class with the provided parameters.
     *
     * @param projectCode            The code or identifier of the project.
     * @param budget                 The budget allocated for the project.
     * @param projectName            The name of the project.
     * @param description            The description or details of the project.
     * @param projectStatus          The current status of the project.
     * @param numberOfPlannedSprints The number of planned sprints for the project.
     * @param startDate              The start date of the project.
     * @param endDate                The end date of the project.
     * @param sprintDuration         The duration of each sprint in the project.
     * @param businessSectorId       The identifier of the business sector associated with the project.
     * @param customerTaxId          The tax ID of the customer associated with the project.
     * @param projectTypologyId      The identifier of the project typology.
     * @param productBacklog         The product backlog associated with the project.
     */

    public ProjectJpa(String projectCode, double budget, String projectName, String description, String projectStatus,
                      int numberOfPlannedSprints, String startDate, String endDate, int sprintDuration,
                      String businessSectorId, String customerTaxId, String projectTypologyId,
                      ProductBacklogJpa productBacklog) {
        this.projectCode = projectCode;
        this.budget = budget;
        this.projectName = projectName;
        this.description = description;
        this.projectStatus = projectStatus;
        this.numberOfPlannedSprints = numberOfPlannedSprints;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sprintDuration = sprintDuration;
        this.businessSectorId = businessSectorId;
        this.customerTaxId = customerTaxId;
        this.projectTypologyId = projectTypologyId;
        this.productBacklog = productBacklog;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public double getBudget() {
        return budget;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public int getNumberOfPlannedSprints() {
        return numberOfPlannedSprints;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getSprintDuration() {
        return sprintDuration;
    }

    public String getBusinessSectorId() {
        return businessSectorId;
    }

    public String getCustomerTaxId() {
        return customerTaxId;
    }

    public String getProjectTypologyId() {
        return projectTypologyId;
    }

    public ProductBacklogJpa getProductBacklog() {
        return productBacklog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        ProjectJpa that = (ProjectJpa) o;
        return Double.compare(that.budget, budget) == 0 && numberOfPlannedSprints == that.numberOfPlannedSprints &&
                sprintDuration == that.sprintDuration && projectCode.equals(that.projectCode) &&
                projectName.equals(that.projectName) && description.equals(that.description) &&
                projectStatus.equals(that.projectStatus) && startDate.equals(that.startDate) &&
                endDate.equals(that.endDate) && businessSectorId.equals(that.businessSectorId) &&
                customerTaxId.equals(that.customerTaxId) && projectTypologyId.equals(that.projectTypologyId) &&
                productBacklog.equals(that.productBacklog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode, budget, projectName, description, projectStatus, numberOfPlannedSprints,
                startDate, endDate, sprintDuration, businessSectorId, customerTaxId, projectTypologyId, productBacklog);
    }
}
