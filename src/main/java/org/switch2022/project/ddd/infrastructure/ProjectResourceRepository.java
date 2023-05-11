package org.switch2022.project.ddd.infrastructure;

import org.springframework.stereotype.Repository;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceRepository;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
public class ProjectResourceRepository implements IProjectResourceRepository {
    /**
     * Attributes
     */
    private final List<ProjectResource> projectResources = new ArrayList<>();


    /**
     * Method equals(Object o)
     * This method checks if an instance of ProjectResourceRepository is equal to another object.
     *
     * @param o object to compare with.
     * @return true if the two have the same attribute value, and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectResourceRepository that = (ProjectResourceRepository) o;
        return Objects.equals(projectResources, that.projectResources);
    }

    /**
     * Method hashCode()
     * This method is used to generate a unique hash code for an object, based on the
     * object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return Objects.hash(projectResources);
    }

    /**
     * This method adds a new ProjectResource instance to the repository of project resources.
     *
     * @param projectResource to be added to the repository.
     * @return true if the project resource is added, and throws an exception otherwise.
     */
    public boolean add(ProjectResource projectResource) {
        if (hasResource(projectResource)) {
            throw new AlreadyExistsInRepoException("The project resource already exists in the repository.");
        } else {
            projectResources.add(projectResource);
            return true;
        }
    }

    /**
     * This method returns a list of project resources with a given project code.
     *
     * @param projectCode the code of the project from which the resources are being queried.
     * @return an unmodifiable list of resources whose attribute projectCode equals the projectCode of interest.
     */
    public List<ProjectResource> getResourcesByProjectCode(Code projectCode){
        List<ProjectResource> resourcesInProject = new ArrayList<>();

        for (int i = 0; i < projectResources.size(); i++) {
            if(projectResources.get(i).hasProjectCode(projectCode)){
                resourcesInProject.add(projectResources.get(i));
            }
        }
        return Collections.unmodifiableList(resourcesInProject);
    }

    /**
     * This method checks if a given instance of ProjectResource already exists in the list of project resources.
     * @param projectResource project resource to look for in the project resource list.
     * @return true if an instance of ProjectResource with the same projectCode, accountEmail, role, and
     * allocationPeriod as the projectResource of interest already exists in the list, and false otherwise.
     */
    private boolean hasResource(ProjectResource projectResource){
        boolean hasResource = false;

        for (int i = 0; i < projectResources.size(); i++) {
            if(projectResources.get(i).hasSameAllocationInfo(projectResource)){
                hasResource = true;
                i = projectResources.size();
            }
        }
        return hasResource;
    }

}