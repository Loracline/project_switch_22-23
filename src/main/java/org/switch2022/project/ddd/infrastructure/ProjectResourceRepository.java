package org.switch2022.project.ddd.infrastructure;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceRepository;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.domain.value_object.Period;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProjectResourceRepository implements IProjectResourceRepository {

    /**
     * Attributes
     */
    private final List<ProjectResource> projectResources = new ArrayList<>();

    /**
     * This method returns an unmodifiable list (read-only) of Project Resources.
     *
     * @return an unmodifiable view of Project Resources.
     */
    public List<ProjectResource> findAll() {
        return Collections.unmodifiableList(projectResources);
    }

    /**
     * This method adds a new ProjectResource instance to the repository of project resources.
     *
     * @param projectResource to be added to the repository.
     * @return true if the project resource is added, and throws an exception otherwise.
     */
    public boolean save(ProjectResource projectResource) {
        if (exists(projectResource)) {
            throw new AlreadyExistsInRepoException("The project resource already exists in the repository.");
        } else {
            projectResources.add(projectResource);
            return true;
        }
    }

    //TODO change name and review implementation

    /**
     * Retrieves a list of email accounts allocated to a specific project.
     *
     * @param projectCode The project code used to filter the allocated accounts.
     * @return A list of email accounts allocated to the specified project.
     */
    public List<String> getAccountsAllocatedToProject(Code projectCode) {
        List<String> emails = new ArrayList<>();
        for (int i = 0; i < projectResources.size(); i++) {
            if (projectResources.get(i).hasProjectCode(projectCode)) {
                String email = this.projectResources.get(i).getEmail();
                emails.add(email);
            }
        }
        return Collections.unmodifiableList(emails);
    }

    /**
     * This method checks if a given instance of ProjectResource already exists in the list of project resources.
     *
     * @param projectResource project resource to look for in the project resource list.
     * @return true if an instance of ProjectResource with the same projectCode, accountEmail, role, and
     * allocationPeriod as the projectResource of interest already exists in the list, and false otherwise.
     */
    private boolean exists(ProjectResource projectResource) {
        boolean hasResource = false;

        for (int i = 0; i < projectResources.size(); i++) {
            if (projectResources.get(i).hasSameAllocationInfo(projectResource)) {
                hasResource = true;
                i = projectResources.size();
            }
        }
        return hasResource;
    }

    //TODO review location of method (and name)

    /**
     * This method checks if there are any project resource in the repository that have the same project ID,
     * same account email, and overlapping period.
     *
     * @param projectCode being checked.
     * @param email       being checked.
     * @param period      being checked.
     * @return return true if the projectResource is overlapping, false otherwise.
     */
    public boolean isResourceOverlapping(Code projectCode, Email email, Period period) {
        boolean resourceIsOverlapping = false;
        for (int i = 0; i < this.projectResources.size(); i++) {
            if (this.projectResources.get(i).hasProjectCode(projectCode) &&
                    this.projectResources.get(i).hasAccount(email) &&
                    this.projectResources.get(i).isPeriodOverlapping(period)) {
                resourceIsOverlapping = true;
            }
        }
        return resourceIsOverlapping;
    }

    /**
     * This method retrieves a list of string representations of project codes to which a given account is allocated
     * to.
     *
     * @param email the value object email that represents the desired account.
     * @return a list of string representations of the project codes, or an empty list if no resource with the given account email was found.
     */
    public List<Code> findProjectCodesByAccountEmail(Email email) {
        List<Code> projectCodes = new ArrayList<>();

        for (int i = 0; i < projectResources.size(); i++) {
            if (projectResources.get(i).hasAccount(email)) {
                int codeNumber = Utils.getIntFromAlphanumericString(projectResources.get(i).getCode(), "P");
                Code code = new Code(codeNumber);
                projectCodes.add(code);
            }
        }
        return Collections.unmodifiableList(projectCodes);
    }

    /**
     * Finds project resources associated with the specified email.
     *
     * @param email The email address used to search for project resources.
     * @return A List of ProjectResource objects that match the provided email.
     */
    public List<ProjectResource> findResourcesByEmail(Email email) {
        List<ProjectResource> resources = new ArrayList<>();
        for (int i = 0; i < this.projectResources.size(); i++) {
            if (this.projectResources.get(i).hasAccount(email)) {
                resources.add(this.projectResources.get(i));
            }
        }
        return resources;
    }
}

