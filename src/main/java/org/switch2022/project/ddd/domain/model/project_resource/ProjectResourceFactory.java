package org.switch2022.project.ddd.domain.model.project_resource;

import org.switch2022.project.ddd.domain.value_object.*;

public class ProjectResourceFactory implements IProjectResourceFactory {

    /**
     * This method creates a new Project Resource.
     */
    @Override
    public ProjectResource createProjectResource(ProjectResourceId projectResourceId, Code code, Email email) {
        return new ProjectResource(projectResourceId, code, email);
    }

    /**
     * This method creates a new Project Resource.
     */
    public ProjectResource createProjectResource(ProjectResourceId projectResourceId, Code code, Email email, Role role, Period period,
                                                 CostPerHour cost, PercentageOfAllocation percentageOfAllocation) {
        ProjectResource projectResource = new ProjectResource(projectResourceId,code, email);
        projectResource.setRole(role);
        projectResource.setPeriod(period);
        projectResource.setCost(cost);
        projectResource.setPercentageOfAllocation(percentageOfAllocation);
        return projectResource;
    }
}
