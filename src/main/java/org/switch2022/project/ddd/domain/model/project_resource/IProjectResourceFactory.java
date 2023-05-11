package org.switch2022.project.ddd.domain.model.project_resource;

import org.switch2022.project.ddd.domain.value_object.*;

public interface IProjectResourceFactory {
    /**
     * @param code  the code of the Project.
     * @param email the email of the Account allocated to Project.
     */
    ProjectResource createProjectResource(Code code, Email email, Role role, Period period,
                                          CostPerHour cost, PercentageOfAllocation percentageOfAllocation);
}
