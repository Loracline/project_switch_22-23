package org.switch2022.project.ddd.domain.model.project_resource;

import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.util.List;

public interface IProjectResourceRepository {
    /**
     * This method adds a new projectResource to the repository of project resources.
     *
     * @param projectResource to be added to the repository.
     * @return true if the project resource is added, and throws an exception otherwise.
     */
    boolean add(ProjectResource projectResource);


    /**
     * This method returns a list of project resources with a given project code.
     *
     * @param projectCode the code of the project from which the resources are being queried.
     * @return a list of accounts whose attribute projectCode equals the projectCode of interest.
     */
    List<ProjectResource> getResourcesByProjectCode(Code projectCode);

}
