package org.switch2022.project.ddd.datamodel.JPA.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.datamodel.JPA.ProjectResourceJpa;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceFactory;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.utils.Utils;

import java.time.LocalDate;


@Service
public class ResourceDomainDataAssembler {

    @Autowired
    IProjectResourceFactory factory;

    /**
     * Converts a ProjectResource domain instance to a ProjectResourceJpa data model instance.
     *
     * @param projectResource The ProjectResource instance to be converted.
     * @return The converted ProjectResourceJpa instance.
     */
    public ProjectResourceJpa toData(ProjectResource projectResource) {
        return new ProjectResourceJpa(projectResource.getProjectResourceId(), projectResource.getCode(),
                projectResource.getEmail(), projectResource.getRoleInProject(),
                projectResource.getPeriod().getStartDate(), projectResource.getPeriod().getEndDate(),
                projectResource.getCostPerHour(), projectResource.getPercentageOfAllocation());
    }

    public ProjectResource toDomain(ProjectResourceJpa projectResourceJpa) {
        ProjectResourceId projectResourceId =
                new ProjectResourceId(Utils.getIntFromAlphanumericString(projectResourceJpa.getId(), "PR"));
        Code code = Code.getCodeFromString(projectResourceJpa.getProjectCode());
        Email email = new Email(projectResourceJpa.getAccountEmail());
        Role role = Role.generateRole(projectResourceJpa.getRole());
        Period period = new Period(LocalDate.parse(projectResourceJpa.getStartDate()), LocalDate.parse(projectResourceJpa.getEndDate()));
        CostPerHour cost = new CostPerHour(projectResourceJpa.getCostPerHour());
        PercentageOfAllocation percentageOfAllocation =
                new PercentageOfAllocation(projectResourceJpa.getPercentageOfAllocation());

        return factory.createProjectResource(projectResourceId, code, email, role, period
                , cost, percentageOfAllocation);
    }
}
