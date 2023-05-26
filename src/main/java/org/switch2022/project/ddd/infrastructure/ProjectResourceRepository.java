package org.switch2022.project.ddd.infrastructure;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceRepository;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component("resource_memory")
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

    /**
     * Retrieves a list of Email objects associated with the specified project code.
     *
     * @param code The project code used to filter the account emails.
     * @return A List of Email objects representing the account emails.
     */
    public List<Email> findAccountEmailsByProjectCode(Code code) {
        List<Email> emails = new ArrayList<>();
        for (int i = 0; i < projectResources.size(); i++) {
            if (projectResources.get(i).hasProjectCode(code)) {
                String stringOf_email = this.projectResources.get(i).getEmail();
                Email email = new Email(stringOf_email);
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

    /**
     * This method retrieves a list of string representations of project codes to which a given account is allocated
     * to.
     *
     * @param email the value object email that represents the desired account.
     * @return a list of string representations of the project codes, or an empty list if no resource with the given
     * account email was found.
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
    public List<ProjectResource> findResourcesByAccountEmail(Email email) {
        List<ProjectResource> resources = new ArrayList<>();
        for (int i = 0; i < this.projectResources.size(); i++) {
            if (this.projectResources.get(i).hasAccount(email)) {
                resources.add(this.projectResources.get(i));
            }
        }
        return resources;
    }
}

