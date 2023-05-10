package org.switch2022.project.ddd.domain.model.project_resource;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.value_object.*;

@Component
public class ProjectResourceFactory implements IProjectResourceFactory {

    /**
     * This method creates a new Project Resource.
     */
    @Override
    public ProjectResource createProjectResource(Code code, Email email,
                                                 Role role, Period period, CostPerHour cost,
                                                 PercentageOfAllocation percentageOfAllocation) {
        ProjectResource projectResource = new ProjectResource(code, email, role, period, cost,
                percentageOfAllocation);
        return projectResource;
    }
}
