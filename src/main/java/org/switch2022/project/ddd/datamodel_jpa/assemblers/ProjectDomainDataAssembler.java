package org.switch2022.project.ddd.datamodel_jpa.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.datamodel_jpa.ProductBacklogJpa;
import org.switch2022.project.ddd.datamodel_jpa.ProjectJpa;
import org.switch2022.project.ddd.domain.model.project.IFactoryProject;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.utils.Utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Component
public class ProjectDomainDataAssembler {
    @Autowired
    private IFactoryProject factoryProject;

    /**
     * Converts a Project object to a ProjectJpa object for persistence.
     *
     * @param project The Project object to be converted.
     * @return The converted ProjectJpa object.
     */
    public ProjectJpa toData(Project project) {

        Optional<SprintDuration> sprintDurationOptional = project.getSprintDuration();
        int sprintDuration = 0;
        if (sprintDurationOptional.isPresent()) {
            sprintDuration = sprintDurationOptional.get().getSprintDuration();
        }
        ProductBacklogJpa productBacklogJpa = createProductBacklogJpa(project);

        return new ProjectJpa(project.getProjectCode(), project.getBudget(), project.getProjectName(),
                project.getDescription(), project.getProjectStatus(), project.getPlannedSprints(),
                project.getStartDate(), project.getEndDate(), sprintDuration,
                project.getBusinessSectorId(), project.getCustomerTaxIDAsString(), project.getProjectTypologyId(),
                productBacklogJpa);
    }

    /**
     * Creates a ProductBacklogJpa object based on the provided Project.
     *
     * @param project The Project object used to create the ProductBacklogJpa.
     * @return The created ProductBacklogJpa object.
     */
    private ProductBacklogJpa createProductBacklogJpa(Project project) {
        List<String> userStories = new ArrayList<>();
        List<UsId> userStoriesId = project.getProductBacklog();
        for (UsId userStoryId : userStoriesId) {
            String id = userStoryId.getUserStoryId();
            userStories.add(id);
        }
        return new ProductBacklogJpa(project.getProductBacklogId(), userStories);
    }

    /**
     * Converts a ProjectJpa object to a Project domain object.
     *
     * @param projectJpa The ProjectJpa object to be converted.
     * @return The converted Project domain object.
     */

    public Project toDomain(ProjectJpa projectJpa) {
        Name projectName = new Name(projectJpa.getProjectName());
        Description projectDescription = new Description(projectJpa.getDescription());
        String projectCode = projectJpa.getProjectCode();
        int projectNumber = Utils.getIntFromAlphanumericString
                (projectCode, "p");
        BusinessSectorId businessSectorId = new BusinessSectorId(Utils.getIntFromAlphanumericString
                (projectJpa.getBusinessSectorId(), "bs"));
        TaxId taxId = new TaxId(projectJpa.getCustomerTaxId());
        ProjectTypologyId projectTypology = new ProjectTypologyId(Utils.getIntFromAlphanumericString
                (projectJpa.getProjectTypologyId(), "pt"));
        Budget budget = new Budget(BigDecimal.valueOf(projectJpa.getBudget()));
        ProjectStatus projectstatus =
                ProjectStatus.valueOf(projectJpa.getProjectStatus().toUpperCase());
        NumberOfPlannedSprints numberOfPlannedSprints = new NumberOfPlannedSprints
                (projectJpa.getNumberOfPlannedSprints());
        Project project = factoryProject.createProject(projectNumber, projectName, projectDescription, businessSectorId,
                taxId, projectTypology);
        project.setProjectStatus(projectstatus);
        project.isBudgetAssigned(budget);

        project.isNumberOfPlannedSprintsDefined(numberOfPlannedSprints);

        if (project.hasStatus(ProjectStatus.INCEPTION)) {
            project.setSprintDuration(projectJpa.getSprintDuration());
        }
        if(!Objects.equals(projectJpa.getStartDate(), "") && !Objects.equals(projectJpa.getEndDate(), "")) {
            LocalDate startDate = LocalDate.parse(projectJpa.getStartDate());
            LocalDate endDate = LocalDate.parse(projectJpa.getEndDate());
            Period projectPeriod = new Period(startDate, endDate);
            project.isPeriodAssigned(projectPeriod);
        }

        Iterator<String> userStoriesIterator = projectJpa.getProductBacklog().getUserStories().iterator();
        int i = 0;
        while (userStoriesIterator.hasNext()) {
            String userStoryId = userStoriesIterator.next();
            String tobeRemoved = projectCode + "_" + "us";

            UsId usid = new UsId(projectCode, Integer.toString(
                    Utils.getIntFromAlphanumericString(userStoryId, tobeRemoved)));
            project.addUserStory(i, usid);
            i++;
        }
        return project;
    }
}
