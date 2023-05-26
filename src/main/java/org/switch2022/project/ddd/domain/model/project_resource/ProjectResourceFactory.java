package org.switch2022.project.ddd.domain.model.project_resource;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.value_object.*;

@Component
public class ProjectResourceFactory implements IProjectResourceFactory {

    /**
     * This method creates a new Project Resource.
     */
    @Override
    public ProjectResource createProjectResource(ProjectResourceId resourceId, Code code, Email email,
                                                 Role role, Period period, CostPerHour cost,
                                                 PercentageOfAllocation percentageOfAllocation) {
        return new ProjectResource(resourceId, code, email, role, period, cost,
                percentageOfAllocation);
    }
}
