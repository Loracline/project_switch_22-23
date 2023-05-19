package org.switch2022.project.ddd.domain.model.project_resource;

import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Email;

import java.util.List;

public interface IProjectResourceRepository {

    /**
     * This method adds a new projectResource to the repository of project resources.
     *
     * @param projectResource to be added to the repository.
     * @return true if the project resource is added, and throws an exception otherwise.
     */
    boolean save(ProjectResource projectResource);

    /**
     * This method returns an unmodifiable list (read-only) of Project Resources.
     *
     * @return an unmodifiable view of Project Resources.
     */
    List<ProjectResource> findAll();

    /**
     * This method retrieves a list of string representations of project codes to which a given account is allocated
     * to.
     *
     * @param email the value object email that represents the desired account.
     * @return a list of string representations of the project codes, or an empty list if no resource with the given account email was found.
     */
    List<Code> findProjectCodesByAccountEmail(Email email);

    /**
     * Retrieves a list of Email objects associated with the specified project code.
     *
     * @param code The project code used to filter the account emails.
     * @return A List of Email objects representing the account emails.
     */
    List<Email> findAccountEmailsByProjectCode(Code code);

    /**
     * Finds project resources associated with the specified email.
     *
     * @param email The email address used to search for project resources.
     * @return A List of ProjectResource objects that match the provided email.
     */
    List<ProjectResource> findResourcesByAccountEmail(Email email);
}
