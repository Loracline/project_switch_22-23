package org.switch2022.project.ddd.domain.model.project_resource;

import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.domain.value_object.ProjectResourceId;

public interface IProjectResourceFactory {
    /**
     * @param code  the code of the Project.
     * @param email the email of the Account allocated to Project.
     */
    ProjectResource createProjectResource(ProjectResourceId projectResourceId, Code code, Email email);
}
